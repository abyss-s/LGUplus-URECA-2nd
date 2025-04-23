//////////TODO 1. state를 위한 타입 선언하기

import { createSlice, PayloadAction } from "@reduxjs/toolkit";

interface ColorState {
  color: string;
  subcolor: string;
}

//////////TODO 2. slice에서 사용할 초기값 선언하기
const initialState: ColorState = {
  color: "black",
  subcolor: "red",
};

//////////TODO 3. slice선언하기 {슬라이스이름, 초기값, reducers}
const colorSlice = createSlice({
  name: "color",
  initialState,
  reducers: {
    changeColor(state, action: PayloadAction<string>) {
      //redux에서 state를  immer라는 불변성 객체로 유지 하고 있기 때문에
      //상태를 직접 수정해도 된다.
      state.color = action.payload;
    },
    changeSubColor(state, action: PayloadAction<string>) {
      state.subcolor = action.payload;
    },
  },
});

//////////TODO 4. slicer가 제공하는 action함수들 export 하기
////////// redux-toolkit이 reducers 함수를 기반으로 만들어주는 action함수
////////// reducer 이름과 동일하다.
export const { changeColor, changeSubColor } = colorSlice.actions;

//////////TODO 5. reducer export 하기
export default colorSlice.reducer;
