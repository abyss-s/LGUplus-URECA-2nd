import { Member } from "@/types/member";
import { useAppDispatch, useAppSelector } from "@/store/hooks";
import { login, logout } from "@/store/slices/memberSlice";
import { useCallback } from "react";

export const useAuth = () => {
  //////////TODO M6. useAppDispatch 함수로 부터 dispatch 전달받기
  const dispatch = useAppDispatch();

  //////////TODO M7. useAppSelector 함수로 부터 member State 전달받기
  const memberState = useAppSelector((state) => state.member.member);

  //////////TODO M8. dispatch함수로 login action  선언하기
  const signIn = useCallback((member: Member) => dispatch(login(member)), [dispatch]);

  //////////TODO M9. dispatch함수로 logout action 선언하기
  const signOut = useCallback(() => dispatch(logout()), [dispatch]);

  //////////TODO M10. 상태와 action함수 return 하기
  return { memberState, login: signIn, logout: signOut, isLoggedIn: !!memberState };
};
