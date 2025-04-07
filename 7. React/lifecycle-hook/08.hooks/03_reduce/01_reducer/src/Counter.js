import React, { useReducer, useState } from "react";
/*
useReducer
- 다양한 컴포넌트의 상황에 따라 state의 상태 관리를 구조적으로 할 수 있는 Hook
- 상태 변화 로직을 하나의 함수(리듀서)에서 관리할 수 있어서 복잡한 상태 로직을 처리할 때 유용

const [state, dispatch] = useReducer(reducer, state객체)
reducer   : dispatch에 의해 호출되어 state를 관리할 함수
state객체  : 관리할 state

reducer(state, action)

*/

function reducer(state, action) {
  console.log(action);
  // action.type 에 따라 다른 작업 수행
  switch (action.type) {
    case "INCREMENT":
      return { value: state.value + parseInt(action.value) };
    case "DECREMENT":
      return { value: state.value - parseInt(action.value) };
    case "INIT":
      return { value: 0 };
    default:
      // 아무것도 해당되지 않을 때 기존 상태 반환
      return state;
  }
}

const Counter = () => {
  const [state, dispatch] = useReducer(reducer, { value: 0 });
  const [step, setStep] = useState(1);
  return (
    <div>
      <p>
        현재 카운터 값은 <b>{state.value}</b> 입니다.
      </p>
      step : <input type="text" value={step} onChange={(e) => setStep(e.target.value)} />
      <button onClick={() => dispatch({ type: "INCREMENT", value: step })}>+{step}</button>
      <button onClick={() => dispatch({ type: "DECREMENT", value: step })}>-{step}</button>
      <button onClick={() => dispatch({ type: "INIT" })}>init</button>
    </div>
  );
};

export default Counter;
