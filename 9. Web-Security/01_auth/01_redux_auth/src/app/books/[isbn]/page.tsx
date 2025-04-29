"use client";
import React, { useCallback, useEffect, useRef, useState } from "react";
import styles from "./detail.module.scss";
import { useRouter } from "next/navigation";
import { useQuery, useMutation, useQueryClient } from "@tanstack/react-query";
import { removeBook, searchBook, updateBook } from "@/service/books";
import { Book } from "@/types/book";
import { useBookmark } from "@/store/hooks/bookmarkHook";

export default function BookDetail({ params: { isbn } }: { params: { isbn: string } }) {
  const queryClient = useQueryClient();
  const router = useRouter();
  const [isEditMode, setIsEditMode] = useState(false);

  const titleRef = useRef<HTMLInputElement>(null);
  const authorRef = useRef<HTMLInputElement>(null);
  const priceRef = useRef<HTMLInputElement>(null);
  const describRef = useRef<HTMLTextAreaElement>(null);

  const {
    data: book,
    isLoading,
    error,
  } = useQuery({
    queryKey: ["book", isbn],
    queryFn: () => searchBook(isbn),
  });

  useEffect(() => {
    if (!book) return;
    if (titleRef.current) titleRef.current.value = book.title;
    if (authorRef.current) authorRef.current.value = book.author;
    if (priceRef.current) priceRef.current.value = book.price.toString();
    if (describRef.current) describRef.current.value = book.describ;
  }, [book]);

  const updateMutation = useMutation({
    mutationFn: (updatedBook: Book) => updateBook(updatedBook),
    onSuccess: () => {
      alert("수정 성공");
      queryClient.invalidateQueries({ queryKey: ["book", isbn] });
    },
    onError: (error: any) => {
      alert("수정 실패: " + error.message);
    },
  });

  const deleteMutation = useMutation({
    mutationFn: () => removeBook(isbn),
    onSuccess: () => {
      alert("삭제 성공");
      queryClient.invalidateQueries({ queryKey: ["book"] });
      router.push("/books");
    },
    onError: (error: any) => {
      alert("삭제 실패: " + error.message);
    },
  });

  const handleUpdate = useCallback(() => {
    if (isEditMode) {
      const title = titleRef.current?.value.trim() || "";
      const author = authorRef.current?.value.trim() || "";
      const price = Number(priceRef.current?.value || "0");
      const describ = describRef.current?.value || "";

      if (!title) {
        alert("제목을 입력하세요");
        titleRef.current?.focus();
        return;
      }
      if (!author) {
        alert("저자를 입력하세요");
        authorRef.current?.focus();
        return;
      }

      const updatedBook: Book = {
        isbn,
        title,
        author,
        price,
        describ,
        img: "",
      };

      updateMutation.mutate(updatedBook);
    }
    setIsEditMode((prev) => !prev);
  }, [isEditMode, isbn, updateMutation]);

  const handleRemove = useCallback(() => {
    deleteMutation.mutate();
  }, [deleteMutation]);

  //////////TODO B15. 커스텀 훅을 통해 registBookMark 함수를 전달 받기
  const { registBookMark } = useBookmark();

  //////////TODO B16. 북 마크 버튼을 위한 이벤트 함수 작성하기
  const handleAddBookMark = useCallback(() => {
    if (book) {
      registBookMark(book);
      alert("선택한 책을 즐겨찾기에 담아놨습니다.");
    }
  }, [book, registBookMark]);
  if (isLoading) return <h1>Loading...</h1>;
  if (error) return <h1>Error: {String(error)}</h1>;

  return (
    <div className={styles.container}>
      <h2 className={styles.title}>도서 상세 정보</h2>
      <table className={styles.table}>
        <tbody>
          <tr>
            <td>책 일련 번호</td>
            <td>
              <input className={styles.readonly} value={isbn} type="text" readOnly />
            </td>
          </tr>
          <tr>
            <td>제목</td>
            <td>
              <input
                className={`${!isEditMode ? styles.readonly : styles.input}`}
                type="text"
                ref={titleRef}
                readOnly={!isEditMode}
              />
            </td>
          </tr>
          <tr>
            <td>저자</td>
            <td>
              <input
                className={`${!isEditMode ? styles.readonly : styles.input}`}
                type="text"
                ref={authorRef}
                readOnly={!isEditMode}
              />
            </td>
          </tr>
          <tr>
            <td>가격</td>
            <td>
              <input
                className={`${!isEditMode ? styles.readonly : styles.input}`}
                type="number"
                ref={priceRef}
                readOnly={!isEditMode}
              />
            </td>
          </tr>
        </tbody>
      </table>

      <div className={styles.infoLabel}>책 정보</div>
      <textarea
        ref={describRef}
        className={`${styles.textarea} ${!isEditMode ? styles.treadonly : ""}`}
        readOnly={!isEditMode}
      ></textarea>

      <div className={styles.buttonGroup}>
        <button onClick={handleUpdate}>{isEditMode ? "저장" : "수정"}</button>
        {/* //////////todo17. 이벤트 등록하기  */}
        <button onClick={handleAddBookMark}>북마크</button>
        <button onClick={handleRemove}>삭제</button>
      </div>
    </div>
  );
}
