// hooks/useColorActions.ts
import { useCallback } from "react";
import { useDispatch } from "react-redux";
import { changeColor, changeSubColor } from "../store/colorSlice"; // 경로는 프로젝트 구조에 맞게 조정
import { useAppSelector } from "./hooks";

const useColorHooks = () => {
  const color = useAppSelector((state) => state.color.color);
  const subColor = useAppSelector((state) => state.color.subcolor);
  const dispatch = useDispatch();

  const setColor = useCallback(
    (color: string) => {
      dispatch(changeColor(color));
    },
    [dispatch]
  );

  const setSubColor = useCallback(
    (subColor: string) => {
      dispatch(changeSubColor(subColor));
    },
    [dispatch]
  );

  return {
    color,
    subColor,
    changeColor: setColor,
    changeSubColor: setSubColor,
  };
};

export default useColorHooks;
