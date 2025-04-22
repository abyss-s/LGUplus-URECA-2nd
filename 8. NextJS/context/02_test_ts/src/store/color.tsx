import { createContext, useState, useContext, ReactNode } from "react";

/////todo1. 제공할 상태와 상태를 변경할 함수에 대한 타입 설정하기
interface ColorContextType {
  color: string;
  subcolor: string;
  changeColor: (color: string) => void;
  changeSubcolor: (subcolor: string) => void;
}
/////todo2. createContext()함수로 제공할 context를 생성하기
const ColorContext = createContext<ColorContextType | undefined>(undefined);

/////todo3. 타입에 해당하는 구현부를 Provider로 작성해서 리턴하기
/////type에서 선언한 상태와 함수를 value에 필수로 작성하기
export const ColorProvider = ({ children }: { children: ReactNode }) => {
  const [color, setColor] = useState<string>("black");
  const [subcolor, setSubcolor] = useState<string>("red");
  const changeColor = (c: string) => {
    setColor(c);
  };
  const changeSubcolor = (c: string) => {
    setSubcolor(c);
  };

  return (
    <ColorContext.Provider value={{ color, subcolor, changeColor, changeSubcolor }}>
      {children}
    </ColorContext.Provider>
  );
};

// 커스텀 훅: useColorContext
export const useColorContext = () => {
  const context = useContext(ColorContext);
  if (!context) {
    throw new Error("useColorContext must be used within a MemberProvider");
  }
  return context;
};
