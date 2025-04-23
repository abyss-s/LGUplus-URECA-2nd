//////////TODO 6. configureStore로 store 선언하기

import { configureStore } from "@reduxjs/toolkit";
import colorReducer from "./colorSlice";
/*
configureStore({reducer:{ }})
*/
export const store = configureStore({
  reducer: {
    color: colorReducer,
  },
});

export type RootState = ReturnType<typeof store.getState>;
export type AppDispatch = typeof store.dispatch;
