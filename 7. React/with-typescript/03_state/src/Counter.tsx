import React, { useState } from "react";

const Counter = () => {
  /*
   * 함수형 Component에서 state 사용
   * 형식]  const[ state명, setter] = useState(기본값);
   * setter: setState명
   */
  const [count, setCount] = useState<number>(0);
  const onIncrease = () => {
    setCount(count + 1);
  };
  const onDecrease = () => {
    setCount(count - 1);
  };
  return (
    <div>
      <h1> Fun Counte : {count}</h1>
      <button onClick={onIncrease}>+</button>
      <button onClick={onDecrease}>-</button>
    </div>
  );
};

export default Counter;
