import { createSlice, PayloadAction } from "@reduxjs/toolkit";
import { Book } from "@/types/book";

//////////TODO B1.Bookmark state를 위한 타입 선언하기
interface BookmarkState {
  books: Book[];
}

//////////TODO B2. slice에서 사용할 초기값 선언하기
const initialState: BookmarkState = {
  books: [],
};

//////////TODO B3. slice선언하기   reducers[registBookMark, removeBookMark, clearBookMark]
const bookmarkSlice = createSlice({
  name: "bookmark",
  initialState,
  reducers: {
    registBookMark: (state, action: PayloadAction<Book>) => {
      const exists = state.books.find((book) => book.isbn === action.payload.isbn);
      if (!exists) {
        state.books.push(action.payload);
      }
    },
    removeBookMark: (state, action: PayloadAction<string>) => {
      state.books = state.books.filter((book) => book.isbn !== action.payload);
    },
    clearBookMark: (state) => {
      state.books = [];
    },
  },
});

//////////TODO B4. slicer가 제공하는 action함수들 export 하기
export const { registBookMark, removeBookMark, clearBookMark } = bookmarkSlice.actions;

//////////TODO B5. reducer export 하기
export default bookmarkSlice.reducer;
