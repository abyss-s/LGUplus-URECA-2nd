"use client";

import { useAppSelector } from "@/store/hooks";

const ColorBox = () => {
  const color = useAppSelector((state) => state.color.color);
  const subcolor = useAppSelector((state) => state.color.subcolor);

  return (
    <>
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
    </>
  );
};

export default ColorBox;
