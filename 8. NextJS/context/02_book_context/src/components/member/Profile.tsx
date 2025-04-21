"use client";

import { useMemberContext } from "@/store/member";

export default function Profile() {
  const { member, logout, login } = useMemberContext();
  const dummyUser = {
    id: "ureca",
    name: "유레카",
    email: "ureca@example.com",
  };
  if (!member) return <button onClick={() => login(dummyUser)}>로그인</button>;

  return (
    <div>
      <span>{member.name}님 환영합니다!</span>
      <button onClick={logout}>로그아웃</button>
    </div>
  );
}
