import { createSlice, PayloadAction } from "@reduxjs/toolkit";

//////////todo1. state를 위한 타입 선언하기 
interface ColorState {
  color: string;
  subcolor: string;
}

//////////todo2. slice에서 사용할 초기값 선언하기 
const initialState: ColorState = {
  color: "black",
  subcolor: "red",
};

//////////todo3. slice선언하기 {슬라이스이름, 초기값, reducers}
const colorSlice = createSlice({
  name: "color",
  initialState,
  reducers: {
    changeColor(state, action: PayloadAction<string>) {
      state.color = action.payload;
    },
    changeSubColor(state, action: PayloadAction<string>) {
      state.subcolor = action.payload;
    },
  },
});

//////////todo4. slicer가 제공하는 action함수들 export 하기
export const { changeColor, changeSubColor } = colorSlice.actions;

//////////todo5. reducer export 하기 
export default colorSlice.reducer;



