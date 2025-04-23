"use client";

import { useColorHooks } from "@/store/colorHooks";

const ColorBox = () => {
  const { color, subcolor } = useColorHooks();

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
