"use client";

import { useMemberContext } from "@/store/member";
import Link from "next/link";
import { useRouter } from "next/navigation";
import { useCallback } from "react";

export default function Profile() {
  ///////todo1. store/member에서  member, logout, login 추출하기
  const { member, logout, login } = useMemberContext();
  const dummyUser = {
    id: "ureca",
    name: "유레카",
    password: "1111",
    email: "ureca@example.com",
  };

  ////////todo3. 로그아웃할때 이동하기 위한 router 객체 추출
  const router = useRouter();
  ////////todo4. 로그아웃을 위한 이벤트 함수 작성
  const handleLogout = useCallback(() => {
    logout();
    router.push("/books");
  }, []);
  ////////todo2. member가 없는 경우 로그인 버튼 표시하기
  if (!member) return <button onClick={() => login(dummyUser)}> 로그인</button>;
  return (
    <div>
      <span>{member.name}님</span>
      <Link href="/member">
        <button>회원정보</button>
      </Link>
      <button onClick={handleLogout}>로그아웃</button>
    </div>
  );
}
