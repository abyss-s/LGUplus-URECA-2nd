"use client";

import useColorHooks from "@/store/colorHooks";

const ColorBox = () => {
  const { color, subColor } = useColorHooks();

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
          background: subColor,
        }}
      />
    </>
  );
};

export default ColorBox;
