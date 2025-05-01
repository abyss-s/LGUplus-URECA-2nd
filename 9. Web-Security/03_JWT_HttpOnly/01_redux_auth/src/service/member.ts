import { Auth, Member, ResMember } from "@/types/member";
import axios from "@/utils/http-commons";

export const searchMember = async (id: string): Promise<ResMember> => {
  const { data } = await axios.get(`/member/${id}`);
  return data;
};

export const loginMember = async (member: Member): Promise<Auth> => {
  const response = await axios.post("/member/login", { id: member.id, password: member.password });
  const accessToken = response.headers["authorization"];
  //////////TODO 6. cookie로 유지하므로 삭제하기
  const refreshToken = response.headers["refresh-token"];

  //////////TODO 7. cookie로 유지하므로 삭제하기
  return { accessToken, refreshToken, id: member.id, isLoggedIn: true };
};

export const refreshAccessToken = async (id: string) => {
  console.log("재발행 받기 위한 id:", id);
  const response = await axios.post("/member/refresh", { id });
  const accessToken = response.headers["authorization"];
  return accessToken; // { accessToken: string }
};
export const logoutMember = async (id: string) => {
  console.log("logout 요청.........................................");
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
