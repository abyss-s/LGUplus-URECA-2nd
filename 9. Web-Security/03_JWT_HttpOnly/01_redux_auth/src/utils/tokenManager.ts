// utils/tokenManager.ts
import { store } from "@/store";
import { setAccessToken } from "@/store/slices/memberSlice";

export const updateAccessToken = (newToken: string) => {
  console.log("updateAccessToken:", newToken);
  store.dispatch(setAccessToken(newToken));
};
