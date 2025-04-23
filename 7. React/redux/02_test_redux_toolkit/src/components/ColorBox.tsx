"use client";

import { useAppSelector } from "@/store/hooks";

const ColorBox = () => {
  /////////TODO 8. useAppSelector를 통해 color 전달 받기
  const color = useAppSelector((state) => state.color.color);
  /////////TODO 9. useAppSelector를 통해 subcolor 전달 받기
  const subcolor = useAppSelector((state) => state.color.subcolor);

  return (
    <div>
      <div
        style={{
          width: "64px",
          height: "64px",
          background: color,
        }}
      />
      <div
        style={{
          width: "32px",
          height: "32px",
          background: subcolor,
        }}
      />
    </div>
  );
};

export default ColorBox;
