"use client";

import Link from "next/link";
import { useRouter } from "next/navigation";

export default function Profile() {
  //////////todo11. member state와  action 함수들을 Custom hook을 통해 전달 받기
  const router = useRouter();

  const dummyUser = {
    id: "ureca",
    password: "1111",
    name: "유레카",
    email: "ureca@example.com",
  };

  //////////TODO 14 로그인 버튼을 위한 이벤트 처리

  //////////TODO 15 로그아웃 버튼을 위한 이벤트 처리

  //////////TODO 12 state에 회원 정보가 없는 경우 로그인 버튼을 리턴

  //////////TODO 13.state에 회원 정보가 있는 경우 회원정보 및 로그아웃 버튼 리턴하기
  return (
    <div>
      <span>{}님</span>
      <Link href="/member">
        <button>회원정보</button>
      </Link>
      <button>로그아웃</button>
    </div>
  );
}
