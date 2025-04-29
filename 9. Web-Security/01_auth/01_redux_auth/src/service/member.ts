import { Auth, Member, ResMember } from "@/types/member";
import axios from "@/utils/http-commons";
import { isAnyOf } from "@reduxjs/toolkit";

export const searchMember = async (id: string): Promise<ResMember> => {
  const { data } = await axios.get(`/member/${id}`);
  return data;
};

//////////TODO M7. token처리를 위한 인증 api 함수 생성하기
export const loginMember = async (member: Member): Promise<Auth> => {
  const response = await axios.post("/member/login", { id: member.id, password: member.password });
  const accessToken = response.headers["authorization"];
  const refreshToken = response.headers["refresh-token"];

  return { accessToken, refreshToken, id: member.id, isLoggedIn: true };
};

//////////TODO M8. access token이 만료된 경우 재발급 받기위한  api 함수 생성하기
export const refreshAccessToken = async (id: string) => {
  const response = await axios.post("/member/refresh", { id });
  const accessToken = response.headers["authorization"];
  return accessToken;
};

export const logoutMember = async (id: string) => {
  console.log("service>member>logoutMember......id:", id);
  const { data } = await axios.get(`/member/logout/${id}`);
  return data;
};

export const updateMember = async (member: Member): Promise<Member> => {
  const { data } = await axios.put("/member", member);
  return data;
};
export const registMember = async (member: Member): Promise<Member> => {
  const { data } = await axios.post("/member", member);
  return data;
};
export const removeMember = async (id: string): Promise<Member> => {
  const { data } = await axios.delete(`/member/${id}`);
  return data;
};
