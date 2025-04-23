"use client";
import ColorBox from "@/components/ColorBox";
import SelectColors from "@/components/SelectColors";
import { Provider } from "react-redux";
import { store } from "@/store/index";
export default function Home() {
  {
    /*TODO 7. Provider로  redux가 적용될 부분 설정하기   */
  }
  return (
    <Provider store={store}>
      <div>
        <SelectColors />
        <ColorBox />
      </div>
    </Provider>
  );
}
