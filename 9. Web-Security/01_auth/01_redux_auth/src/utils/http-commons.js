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
  withCredentials: true,
});

let localAccessToken = "";
let localRefreshToken = "";

api.interceptors.request.use(async (config) => {
  if (!localAccessToken) {
    //axios는 화면이 아니기 때문에 useAuth() 같은 store을 직접 호출해서 사용할 수없다.
    //
    const state = store.getState(); // 현재 Redux store 상태 가져오기
    localAccessToken = state.member.memberState?.accessToken; // 토큰 꺼내기
    localRefreshToken = state.member.memberState?.refreshToken;
  }
  if (localAccessToken) {
    config.headers.Authorization = `Bearer ${localAccessToken}`;
  }
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
        
        localAccessToken = accessToken;
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
