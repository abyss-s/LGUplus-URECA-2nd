"use client";

import { useCallback } from "react";
import useColorHooks from "@/store/colorHooks";

const colors = ["red", "orange", "yellow", "green", "blue", "indigo", "violet"];

const SelectColors = () => {
  const { changeColor, changeSubColor } = useColorHooks();

  const handleSetColor = useCallback((color: string) => {
    changeColor(color);
  }, []);

  const handleSetSubcolor = useCallback((subcolor: string) => {
    changeSubColor(subcolor);
  }, []);

  return (
    <div>
      <h2>색상을 선택하세요.</h2>
      <div style={{ display: "flex", gap: "8px" }}>
        {colors.map((color) => (
          <div
            key={color}
            style={{
              background: color,
              width: "24px",
              height: "24px",
              cursor: "pointer",
              borderRadius: "4px",
            }}
            onClick={() => handleSetColor(color)}
            onContextMenu={(e) => {
              e.preventDefault();
              handleSetSubcolor(color);
            }}
          />
        ))}
      </div>
      <hr />
    </div>
  );
};

export default SelectColors;
