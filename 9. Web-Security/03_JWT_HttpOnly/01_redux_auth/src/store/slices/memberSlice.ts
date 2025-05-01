import { createSlice, PayloadAction } from "@reduxjs/toolkit";
import { Auth, MemberState } from "@/types/member";

const initialState: MemberState = {
  memberState: null,
};

const authSlice = createSlice({
  name: "member",
  initialState,
  reducers: {
    login: (state, action: PayloadAction<Auth>) => {
      console.log("redux login....... payload:", action.payload);
      state.memberState = action.payload;
    },
    setAccessToken: (state, action: PayloadAction<string>) => {
      if (state.memberState) {
        state.memberState.accessToken = action.payload;
        console.log("member slice  newToken:", action.payload);
      }
    },
    logout: (state) => {
      state.memberState = null;
    },
  },
});

export const { login, logout, setAccessToken } = authSlice.actions;
export default authSlice.reducer;
