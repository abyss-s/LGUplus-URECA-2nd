import axios from "axios";
import { store } from "@/store/index"; // store import 추가
import { refreshAccessToken } from "@/service/member";
import { updateAccessToken } from "@/utils/tokenManager";

const BASE_URL = "http://localhost:8080/eureka/";

const api = axios.create({
  baseURL: BASE_URL,
  headers: {
    "Content-Type": "application/json;charset=utf-8",
  },
  //////////TODO 2. CORS에 의해 전송되지 않는 쿠키를 전송하기 위한 옵션
  withCredentials: true,
});

let localAccessToken = "";

//////////TODO 3. cookie로 유지하므로 삭제하기
let localRefreshToken = "";

api.interceptors.request.use(async (config) => {
  if (!localAccessToken) {
    const state = store.getState(); // 현재 Redux store 상태 가져오기
    localAccessToken = state.member.memberState?.accessToken; // 토큰 꺼내기
    //////////TODO 4. cookie로 유지하므로 삭제하기
    localRefreshToken = state.member.memberState?.refreshToken;
  }
  if (localAccessToken) {
    config.headers.Authorization = `Bearer ${localAccessToken}`;
  }
  //////////TODO 5. cookie로 유지하므로 삭제하기
  if (localRefreshToken) {
    config.headers["Refresh-Token"] = localRefreshToken;
  }

  return config;
});

api.interceptors.response.use(
  (response) => response,
  async (error) => {
    const originalRequest = error.config;

    if (error.response?.status === 401 && !originalRequest._retry) {
      originalRequest._retry = true;
      try {
        const state = store.getState(); // 현재 Redux store 상태 가져오기
        const id = state.member.memberState.id;
        console.log("axios  토큰 재발받기  id:", id);
        const accessToken = await refreshAccessToken(id);
        updateAccessToken(accessToken); // store 업데이트
        console.log("새로 발급 받은 token:", accessToken);
        originalRequest.headers.Authorization = `Bearer ${accessToken}`;
        return api(originalRequest); // 요청 다시 보내기
      } catch (refreshError) {
        console.error("Token refresh 실패", refreshError);
        return Promise.reject(refreshError);
      }
    }

    return Promise.reject(error);
  }
);

export default api;
