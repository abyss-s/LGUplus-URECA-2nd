"use client";
import { Member } from "@/types/member";
import { useAppDispatch, useAppSelector } from "@/store/hooks";
import { login, logout, setAccessToken } from "@/store/slices/memberSlice";
import { useCallback } from "react";
import { useMutation } from "@tanstack/react-query";
import { loginMember } from "@/service/member";
import { useRouter } from "next/navigation";
export const useAuth = () => {
  const dispatch = useAppDispatch();

  const memberState = useAppSelector((state) => state.member.memberState);
  const router = useRouter();
  const loginMutation = useMutation({
    mutationFn: loginMember,
    onSuccess: (data) => {
      // 성공하면 Redux에 저장
      dispatch(login(data));
      router.push("/");
    },
    onError: (error) => {
      console.error("로그인 실패:", error);
      alert("로그인 실패");
    },
  });
  //////////TODO M8. dispatch함수로 login action  선언하기
  const signIn = useCallback(
    (member: Member) => {
      loginMutation.mutate(member);
    },
    [loginMutation]
  );

  //////////TODO M5. setAccessToken  함수를 수행시킬 함수 정의 하기
  const setToken = useCallback((token: string) => dispatch(setAccessToken(token)), [dispatch]);
  const signOut = useCallback(() => dispatch(logout()), [dispatch]);

  //////////TODO M6. setAccessToken  함수를 수행시킬 함수 내보내기
  return { memberState, loginMutation, login: signIn, logout: signOut, setToken };
};
