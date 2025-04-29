import { Auth, MemberState } from "@/types/member";
import { createSlice, PayloadAction } from "@reduxjs/toolkit";
import { access } from "fs";

//////////TODO M3.  상태 유지할 state의 초기값 선언하기
const initialState: MemberState = {
  memberState: null,
};
const authSlice = createSlice({
  name: "member",
  initialState,
  //////////TODO M4. login, logout, setAccessToken 함수가 있는 reducer 선언하기
  reducers: {
    login: (state, action: PayloadAction<Auth>) => {
      state.memberState = action.payload;
    },
    logout: (state) => {
      state.memberState = null;
    },
    setAccessToken: (state, action: PayloadAction<string>) => {
      if (state.memberState) state.memberState.accessToken = action.payload;
    },
  },
});

export const { login, logout, setAccessToken } = authSlice.actions;
export default authSlice.reducer;
