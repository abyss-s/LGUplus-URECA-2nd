"use client";

import { logoutMember } from "@/service/member";
import { useAuth } from "@/store/hooks/memberHook";
import { handleApi } from "@/utils/handleApi";
import Link from "next/link";
import { useRouter } from "next/navigation";
import { useCallback } from "react";

export default function Profile() {
  const { memberState, logout } = useAuth();
  const router = useRouter();

  //////////TODO M7. 로그인 버튼을 위한 이벤트 처리
  const handleLogin = useCallback(() => {
    router.push("/member/login");
  }, []);

  const handleLogout = useCallback(async () => {
    if (memberState) {
      const id = memberState.id;
      console.log("logout..... id:", id);
      const { data, error } = await handleApi(() => logoutMember(id));
      if (data) {
        logout();
        router.push("/books");
      } else {
        alert(error);
      }
    }
  }, [memberState, logout]);

  if (!memberState?.isLoggedIn)
    return (
      <div>
        <button onClick={handleLogin}>로그인</button>{" "}
        <Link href="/member/regist">
          <button>회원가입</button>
        </Link>
      </div>
    );

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
