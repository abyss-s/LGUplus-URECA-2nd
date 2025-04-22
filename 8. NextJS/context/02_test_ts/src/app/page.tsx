"use client";
import ColorBox from "@/components/ColorBox";
import { ColorProvider } from "@/store/color";
import SelectColors from "@/components/SelectColors";

export default function Home() {
  return (
    <ColorProvider>
      <div>
        <SelectColors />
        <ColorBox />
      </div>
    </ColorProvider>
  );
}
