/*
useReducer
- 다양한 컴포넌트의 상황에 따라 state의 상태 관리를 구조적으로 할 수 있는 Hook
- 상태 변화 로직을 하나의 함수(리듀서)에서 관리할 수 있어서 복잡한 상태 로직을 처리할 때 유용

const [state, dispatch] = useReducer(reducer, state객체)
reducer   : dispatch에 의해 호출되어 state를 관리할 함수
state객체  : 관리할 state

reducer(state, action)

*/
