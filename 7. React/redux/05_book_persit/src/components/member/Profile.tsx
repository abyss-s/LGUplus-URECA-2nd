"use client";

import { useAuth } from "@/store/hooks/memberHook";
import Link from "next/link";
import { useRouter } from "next/navigation";
import { useCallback } from "react";

export default function Profile() {
  //////////TODO M11. member state와  action 함수들을 Custom hook을 통해 전달 받기
  const { memberState, login, logout, isLoggedIn } = useAuth();

  const router = useRouter();

  const dummyUser = {
    id: "ureca",
    password: "1111",
    name: "유레카",
    email: "ureca@example.com",
  };

  //////////TODO M14 로그인 버튼을 위한 이벤트 처리
  const handleLogin = useCallback(() => {
    login(dummyUser);
  }, [login]);

  //////////TODO M15 로그아웃 버튼을 위한 이벤트 처리
  const handleLogOut = useCallback(() => {
    logout();
    router.push("/books");
  }, [logout]);

  //////////TODO M12 state에 회원 정보가 없는 경우 로그인 버튼과 회원 가입 버튼을 리턴
  if (!isLoggedIn)
    return (
      <div>
        <button onClick={handleLogin}>로그인</button>
        <Link href="/member/regist">
          <button>회원가입</button>
        </Link>
      </div>
    );

  //////////TODO M13.state에 회원 정보가 있는 경우 회원정보 및 로그아웃 버튼 리턴하기
  return (
    <div>
      <span>{memberState?.name}님</span>
      <Link href="/member">
        <button>회원정보</button>
      </Link>
      <button onClick={handleLogOut}>로그아웃</button>
    </div>
  );
}
