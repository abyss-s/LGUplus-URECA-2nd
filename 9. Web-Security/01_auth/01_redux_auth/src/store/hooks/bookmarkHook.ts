import { useCallback } from "react";
import { useAppDispatch, useAppSelector } from "../hooks";
import { Book } from "@/types/book";
import { registBookMark, clearBookMark, removeBookMark } from "../slices/bookmarkSlice";

export const useBookmark = () => {
  //////////TODO B6. useAppDispatch 함수로 부터 dispatch 전달받기
  const dispatch = useAppDispatch();

  //////////TODO B7. useAppSelector 함수로 부터 books State 전달받기
  const bookmark = useAppSelector((state) => state.bookmark.books);

  //////////TODO B8. dispatch함수로 registBookMark  선언하기
  const add = useCallback(
    (book: Book) => {
      dispatch(registBookMark(book));
    },
    [dispatch]
  );

  //////////TODO B9. dispatch함수로 removeBookMark  선언하기
  const remove = useCallback(
    (isbn: string) => {
      dispatch(removeBookMark(isbn));
    },
    [dispatch]
  );
  //////////TODO B10. dispatch함수로 clearBookMark  선언하기
  const clear = useCallback(() => {
    dispatch(clearBookMark());
  }, [dispatch]);

  //////////TODO B11. 상태와 action 함수들 리턴하기
  return {
    bookmark,
    registBookMark: add,
    removeBookMark: remove,
    clearBookMark: clear,
  };
};
