"use client";
import React, { useCallback, useEffect, useRef, useState } from "react";
import styles from "./detail.module.scss";
import { useRouter } from "next/navigation";
import { useQuery, useMutation, useQueryClient } from "@tanstack/react-query";
import { removeBook, searchBook, updateBook } from "@/service/books";
import { Book } from "@/types/book";

export default function BookDetail({ params: { isbn } }: { params: { isbn: string } }) {
  const router = useRouter();
  const [isEditMode, setIsEditMode] = useState(false);

  // Ref 선언
  const titleRef = useRef<HTMLInputElement>(null);
  const authorRef = useRef<HTMLInputElement>(null);
  const priceRef = useRef<HTMLInputElement>(null);
  const describRef = useRef<HTMLTextAreaElement>(null);

  //////////////////////todo1. useQuery로 데이터 가져오기
  const {
    data: book,
    isLoading,
    error,
  } = useQuery({
    queryKey: ["book", isbn],
    queryFn: () => searchBook(isbn),
  });

  //////////////////////todo2. useEffect 변경. book이 로딩된 뒤에 DOM이 렌더링된 상태에서 ref에 값 할당
  useEffect(() => {
    if (!book) return;
    if (titleRef.current) titleRef.current.value = book.title;
    if (authorRef.current) authorRef.current.value = book.author;
    if (priceRef.current) priceRef.current.value = book.price.toString();
    if (describRef.current) describRef.current.value = book.describ;
  }, [book]);

  //////////////////////todo4. 수정, 삭제후 캐쉬하고 있는 정보를 갱신하기 위해
  const queryClient = useQueryClient();

  //////////////////////todo5  useMutation - 책 수정
  //mutationFn: updateBook
  //mutationFn: (uBook: Book) => updateBook(uBook)
  // 인자의 타입을 명확하게 지정할 수 있다.
  // 인자의 개수가 여러개인 경우 직접 처리할 수 있다.
  const updateMutation = useMutation({
    mutationFn: (uBook: Book) => updateBook(uBook),
    onSuccess: () => {
      alert("수정 성공");
      //isbn에 해당하는 book 정보 갱신하기
      queryClient.invalidateQueries({ queryKey: ["book", isbn] });
    },
    onError: (error: any) => {
      alert("수정 실패: " + error.message);
    },
  });

  //////////////////////  수정 버튼 핸들러
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
      /////////////////////todo.6
      updateMutation.mutate(updatedBook);
    }
    setIsEditMode((prev) => !prev);
  }, [isEditMode, isbn]);

  //////////////////////todo7.  useMutation - 책 삭제
  const deleteMutation = useMutation({
    mutationFn: removeBook,
    onSuccess: () => {
      alert("삭제 성공");
      // 목록 쿼리 갱신
      queryClient.invalidateQueries({ queryKey: ["books"] });
      router.push("/books");
    },
    onError: (error: any) => {
      alert("삭제 실패: " + error.message);
    },
  });
  const handleRemove = useCallback(() => {
    //////////////////todo8.
    deleteMutation.mutate(isbn);
  }, [isbn]);

  //////////////////////todo3.  로딩/에러 상태
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
        <button onClick={handleRemove}>삭제</button>
      </div>
    </div>
  );
}
