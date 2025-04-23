// store/index.ts
import { configureStore, combineReducers } from "@reduxjs/toolkit";
import {
  persistReducer,
  persistStore,
  FLUSH,
  REHYDRATE,
  PAUSE,
  PERSIST,
  PURGE,
  REGISTER,
} from "redux-persist";
import storage from "redux-persist/lib/storage";

import colorReducer from "./colorSlice";

const persistConfig = {
  key: "root",
  storage,
  whitelist: ["color"],
};

const rootReducer = combineReducers({
  color: colorReducer,
});

const persistedReducer = persistReducer(persistConfig, rootReducer);

export const store = configureStore({
  reducer: persistedReducer,
  middleware: (getDefaultMiddleware) =>
    getDefaultMiddleware({
      serializableCheck: {
        ignoredActions: [FLUSH, REHYDRATE, PAUSE, PERSIST, PURGE, REGISTER],
      },
    }),
});

// persistor는 클라이언트에서만
let persistorInstance: ReturnType<typeof persistStore> | null = null;

if (typeof window !== "undefined") {
  persistorInstance = persistStore(store);
}

export const persistor = persistorInstance;

export type RootState = ReturnType<typeof store.getState>;
export type AppDispatch = typeof store.dispatch;
