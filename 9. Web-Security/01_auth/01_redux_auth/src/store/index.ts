// import { configureStore } from "@reduxjs/toolkit";
// import memberReducer from "./slices/memberSlice";
// import bookmarkReducer from "./slices/bookmarkSlice";

// export const store = configureStore({
//   reducer: {
//     member: memberReducer,
//     bookmark: bookmarkReducer,
//   },
// });

// //RootState와 AppDispatch 타입 추출
// export type RootState = ReturnType<typeof store.getState>;
// export type AppDispatch = typeof store.dispatch;

// store.ts
import { configureStore, combineReducers } from "@reduxjs/toolkit";
import memberReducer from "./slices/memberSlice";
import bookmarkReducer from "./slices/bookmarkSlice";
import {
  persistStore,
  persistReducer,
  FLUSH,
  REHYDRATE,
  PAUSE,
  PERSIST,
  PURGE,
  REGISTER,
} from "redux-persist";
import storageSession from "redux-persist/lib/storage/session"; // sessionStorage 사용
import storage from "redux-persist/lib/storage"; // sessionStorage 사용

const bookmarkPersistConfig = {
  key: "bookmark",
  storage,
};

const memberPersistConfig = {
  key: "member",
  storage: storageSession,
};

const rootReducer = combineReducers({
  member: persistReducer(memberPersistConfig, memberReducer),
  bookmark: persistReducer(bookmarkPersistConfig, bookmarkReducer),
});

export const store = configureStore({
  reducer: rootReducer,
  middleware: (getDefaultMiddleware) =>
    getDefaultMiddleware({
      serializableCheck: {
        ignoredActions: [FLUSH, REHYDRATE, PAUSE, PERSIST, PURGE, REGISTER],
      },
    }),
});

export const persistor = persistStore(store);

// RootState와 AppDispatch 타입 추출
export type RootState = ReturnType<typeof store.getState>;
export type AppDispatch = typeof store.dispatch;
