import axios from "axios";
import { getSession } from "next-auth/react";
//const BASE_URL = "http://10.4.2.100:8080/eureka/";
const BASE_URL = "http://localhost:8080/eureka/";

// local vue api axios instance
const api = axios.create({
  baseURL: BASE_URL,
  headers: {
    "Content-Type": "application/json;charset=utf-8",
  },
  withCredentials: true,
});

api.interceptors.request.use(async (config) => {
  //////////// TODO 5.  next-auth를 통해 accessToken과 refreshToken 가져오기

  console.log("axios.....accessToken:", accessToken);
  console.log("axios.....refreshToken:", refreshToken);

  if (accessToken) {
    config.headers.Authorization = `Bearer ${accessToken}`;
  }
  if (refreshToken) {
    config.headers["Refresh-Token"] = refreshToken; // 서버와 약속된 이름으로!
  }
  return config;
});
export default api;
