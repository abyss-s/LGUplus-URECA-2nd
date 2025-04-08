import { useReducer } from 'react';

export default function useInputs(initialForm) {
  const cmd = {
    CHANGE: 'CHANGE',
    RESET: 'RESET',
  };

  const reducer = (state, action) => {
    switch (action.type) {
      case cmd.CHANGE:
        return { ...state, [action.name]: action.value };
      case cmd.RESET:
        return initialForm;
      default:
        return state;
    }
  };

  const [state, dispatch] = useReducer(reducer, initialForm);

  const onChange = (e) => {
    const { name, value } = e.target;
    dispatch({ type: cmd.CHANGE, name, value });
  };

  const reset = () => {
    dispatch({ type: cmd.RESET });
  };

  return [state, onChange, reset];
}
