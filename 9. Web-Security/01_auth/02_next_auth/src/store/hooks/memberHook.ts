// hooks/useAuth.ts
import { useAppDispatch, useAppSelector } from "@/store/hooks";
import { login, logout } from "@/store/slices/memberSlice";
import { Member } from "@/types/member";

export const useAuth = () => {
  //////////todo6. useAppDispatch 함수로 부터 dispatch 전달받기
  const dispatch = useAppDispatch();

  //////////todo7. useAppSelector 함수로 부터 member State 전달받기
  const memberState = useAppSelector((state) => state.member.member);

  //////////todo8. dispatch함수로 login action  선언하기
  const signIn = (user: Member) => dispatch(login(user));

  //////////todo9. dispatch함수로 logout action 선언하기
  const signOut = () => dispatch(logout());

  //////////todo10. 상태와 action함수 return 하기
  return {
    memberState,
    login: signIn,
    logout: signOut,
    isLoggedIn: !!memberState,
  };
};
