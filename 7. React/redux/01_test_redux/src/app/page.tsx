"use client";
import ColorBox from "@/components/ColorBox";
import { Provider } from "react-redux";
import { store } from "@/store/index";
import SelectColors from "@/components/SelectColors";

export default function Home() {
  return (
    <Provider store={store}>
      <div>
        <SelectColors />
        <ColorBox />
      </div>
    </Provider>
  );
}
