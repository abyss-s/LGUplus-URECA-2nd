"use client";

import React, { createContext, useContext, useState, ReactNode } from "react";
import { Book } from "@/types/book";

interface BookmarkContextType {
  books: Book[];
  addBook: (book: Book) => void;
  removeBook: (isbn: string) => void;
  clearBooks: () => void;
}

// 초기값 생성
const BookmarkContext = createContext<BookmarkContextType | undefined>(undefined);

// Provider 컴포넌트
export const BookmarkProvider = ({ children }: { children: ReactNode }) => {
  const [books, setBooks] = useState<Book[]>([]);

  const addBook = (book: Book) => {
    setBooks((prev) => (prev.find((item) => item.isbn === book.isbn) ? prev : [...prev, book]));
  };

  const removeBook = (isbn: string) => {
    setBooks((prev) => prev.filter((book) => book.isbn !== isbn));
  };

  const clearBooks = () => {
    setBooks([]);
  };

  return (
    <BookmarkContext.Provider value={{ books, addBook, removeBook, clearBooks }}>
      {children}
    </BookmarkContext.Provider>
  );
};

// 커스텀 훅: useBookmarkContext
export const useBookmarkContext = () => {
  const context = useContext(BookmarkContext);
  if (!context) {
    throw new Error("useBookmarkContext must be used within a BookmarkProvider");
  }
  return context;
};
