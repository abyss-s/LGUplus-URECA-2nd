"use client";

import { useCallback } from "react";
import { useAppDispatch } from "@/store/hooks";
import { changeColor, changeSubColor } from "@/store/color";

const colors = ["red", "orange", "yellow", "green", "blue", "indigo", "violet"];

const SelectColors = () => {
  const dispatch = useAppDispatch();

  const handleSetColor = useCallback(
    (color: string) => {
      dispatch(changeColor(color));
    },
    [dispatch],
  );

  const handleSetSubcolor = useCallback(
    (subcolor: string) => {
      dispatch(changeSubColor(subcolor));
    },
    [dispatch],
  );

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
