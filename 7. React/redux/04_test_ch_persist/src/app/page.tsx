"use client";
import ColorBox from "@/components/ColorBox";
import SelectColors from "@/components/SelectColors";
import { Provider } from "react-redux";
import { store, persistor } from "@/store/index";
import { PersistGate } from "redux-persist/integration/react";

export default function Home() {
  if (!persistor) {
    // 클라이언트가 아닌 경우
    return <p>초기화 중...</p>;
  }

  return (
    <Provider store={store}>
      <PersistGate loading={<p>로딩 중...</p>} persistor={persistor}>
        <div>
          <SelectColors />
          <ColorBox />
        </div>
      </PersistGate>
    </Provider>
  );
}
