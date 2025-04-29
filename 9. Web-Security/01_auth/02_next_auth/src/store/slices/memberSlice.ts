import { createSlice, PayloadAction } from "@reduxjs/toolkit";
import { Member } from "@/types/member";

//////////todo1.member state를 위한 타입 선언하기
interface MemberState {
  member: Member | null;
}

//////////todo2. slice에서 사용할 초기값 선언하기
const initialState: MemberState = {
  member: null,
};

//////////todo3. slice선언하기 {슬라이스이름, 초기값, reducers}
const memberSlice = createSlice({
  name: "member",
  initialState,
  reducers: {
    login: (state, action: PayloadAction<Member>) => {
      state.member = action.payload;
    },
    logout: (state) => {
      state.member = null;
    },
  },
});

//////////todo4. slicer가 제공하는 action함수들 export 하기
export const { login, logout } = memberSlice.actions;

//////////todo5. reducer export 하기
export default memberSlice.reducer;
