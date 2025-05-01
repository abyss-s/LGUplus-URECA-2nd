"use client";

import { logoutMember } from "@/service/member";
import { useAppSelector } from "@/store/hooks";
import { useAuth } from "@/store/hooks/memberHook";
import { useMutation } from "@tanstack/react-query";
import Link from "next/link";
import { useRouter } from "next/navigation";
import { useCallback } from "react";

export default function Profile() {
  //////////member state와  action 함수들을 Custom hook을 통해 전달 받기
  const { logout } = useAuth();
  const router = useRouter();
  const memberState = useAppSelector((state) => state.member.memberState);

  //////////로그인 버튼을 위한 이벤트 처리
  const handleLogin = useCallback(() => {
    router.push("/member/login");
  }, []);

  const logoutMutation = useMutation({
    mutationFn: logoutMember,
    onSuccess: (data) => {
      // 성공하면 Redux에 저장
      console.log("로그 아웃 성공!!!, data:", data);
      logout();
      router.push("/");
    },
    onError: (error) => {
      alert("로아웃 실패");
    },
  });
  //////////로그아웃 버튼을 위한 이벤트 처리
  const handleLogout = useCallback(async () => {
    console.log("logout..... memberState:", memberState);
    if (memberState) {
      const id = memberState.id;
      logoutMutation.mutate(id);
    }
  }, [memberState]);

  //////////state에 회원 정보가 없는 경우 로그인 버튼을 리턴
  if (!memberState?.isLoggedIn)
    return (
      <div>
        <button onClick={handleLogin}>로그인</button>{" "}
        <Link href="/member/regist">
          <button>회원가입</button>
        </Link>
      </div>
    );

  //////////state에 회원 정보가 있는 경우 회원정보 및 로그아웃 버튼 리턴하기
  return (
    <div>
      <span>{memberState?.id}님</span>
      <Link href="/member">
        <button>회원정보</button>
      </Link>
      <button onClick={handleLogout}>로그아웃</button>
    </div>
  );
}
