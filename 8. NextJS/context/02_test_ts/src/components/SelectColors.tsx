import { useCallback } from "react";
import { useColorContext } from "../store/color";

const colors = ["red", "orange", "yellow", "green", "blue", "indigo", "violet"];

const SelectColors = () => {
  const { changeColor, changeSubcolor } = useColorContext();

  const handleSetColor = useCallback(
    (color: string) => {
      changeColor(color);
    },
    [changeColor]
  );

  const handleSetSubcolor = useCallback(
    (subcolor: string) => {
      changeSubcolor(subcolor);
    },
    [changeSubcolor]
  );

  return (
    <div>
      <h2>색상을 선택하세요.</h2>
      <div style={{ display: "flex" }}>
        {colors.map((color) => (
          <div
            key={color}
            style={{
              background: color,
              width: "24px",
              height: "24px",
              cursor: "pointer",
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
