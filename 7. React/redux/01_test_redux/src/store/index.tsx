// index.ts
import { createStore, combineReducers } from "redux";
import colorReducer from "./color";

const rootReducer = combineReducers({
  color: colorReducer,
});

export const store = createStore(rootReducer);

// 타입 추론용
export type RootState = ReturnType<typeof rootReducer>;
export type AppDispatch = typeof store.dispatch;
