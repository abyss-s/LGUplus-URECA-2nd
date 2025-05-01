"use client";

import styles from "./book_mark.module.scss";
import BookMarkItem from "@/components/book_mark/BookMarkItem";
import React, { useCallback } from "react";
import { useBookmark } from "@/store/hooks/bookmarkHook"; //

export default function Bookmark() {
  //////////커스텀 훅을 통해 bookmark 상태와 clearBookMark함수 전달 받기
  const { bookmark, clearBookMark } = useBookmark();

  //////////모두 삭제 버튼을 위한 이벤트 함수 작성하기
  const handlerClear = useCallback(() => {
    clearBookMark();
  }, []);

  return (
    <div className={styles.bookList}>
      <div>
        {/* //////////이벤트 등록하기  */}
        <button className={styles.registerButton} onClick={handlerClear}>
          모두 삭제
        </button>
      </div>
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
          {/* //////////bookmark 상태를 이용해서 bookmark  목록 만들기   */}
          {bookmark.length > 0 ? (
            bookmark.map((book) => <BookMarkItem key={book.isbn} book={book} />)
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
