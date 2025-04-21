"use client";

import styles from "./book_mark.module.scss";
import BookMarkItem from "@/components/book_mark/BookMarkItem";
import { useBookmarkContext } from "@/store/book-mark";
import React from "react";

export default function Bookmark() {
  const { books } = useBookmarkContext();
  return (
    <div className={styles.bookList}>
      <table className={styles.table}>
        <thead>
          <tr>
            <th>이미지</th>
            <th>책 일련 번호</th>
            <th>제목</th>
            <th>저자</th>
            <th>가격</th>
            <th>비고</th>
          </tr>
        </thead>
        <tbody>
          {books.length > 0 ? (
            books.map((book) => <BookMarkItem key={book.isbn} book={book} />)
          ) : (
            <tr>
              <td colSpan={6} style={{ textAlign: "center", padding: "1rem" }}>
                북마크 내용이 비었습니다.
              </td>
            </tr>
          )}
        </tbody>
      </table>

      <div className={styles.pagination}>
        <button>이전</button>
        <button>다음</button>
      </div>
    </div>
  );
}
