"use client";
import React, { useEffect, useRef, useState } from "react";
import styles from "./detail.module.scss";
import { localAxios } from "@/util/http-commons";
export default function BookDetail({ params: { isbn } }: { params: { isbn: string } }) {
  const [isEditMode, setIsEditMode] = useState(false);
  //입력 form에 ref 달기 위해 선언
  const titleRef = useRef<HTMLInputElement>(null);
  const authorRef = useRef<HTMLInputElement>(null);
  const priceRef = useRef<HTMLInputElement>(null);
  const describRef = useRef<HTMLTextAreaElement>(null);

  //////////////////////todo1. loading, error에 대한 상태 선언하기

  //////////////////////todo2. axios로 비동기 통신하는 함수 선언하기

  //////////////////////todo3. useEffect로 비동기 통신 함수 호출하기

  const handleRemove = () => {
    console.log("도서 목록으로 이동");
    //router.push("/books"); //
    // 목록 이동 로직
  };

  //////////////////////todo4. loading, error에 대한 화면
  return (
    <div className={styles.container}>
      <h2 className={styles.title}>도서 상세 정보</h2>
      <table className={styles.table}>
        <tbody>
          <tr>
            <td>책 일련 번호</td>
            <td>
              <input
                className={styles.readonly}
                value={isbn}
                type="text"
                name="isbn"
                readOnly={true}
              />
            </td>
          </tr>
          <tr>
            <td>제목</td>
            <td>
              <input
                className={`${!isEditMode ? styles.readonly : styles.input}`}
                type="text"
                name="title"
                ref={titleRef}
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
                name="author"
                readOnly={!isEditMode}
              />
            </td>
          </tr>
          <tr>
            <td>가격</td>
            <td>
              <input
                className={`${!isEditMode ? styles.readonly : styles.input}`}
                ref={priceRef}
                type="number"
                name="price"
                readOnly={!isEditMode}
              />
            </td>
          </tr>
        </tbody>
      </table>

      <div className={styles.infoLabel}>책 정보</div>
      <textarea
        name="describ"
        ref={describRef}
        className={`${styles.textarea} ${!isEditMode ? styles.treadonly : ""}`}
        readOnly={!isEditMode}
      ></textarea>

      <div className={styles.buttonGroup}>
        <button onClick={() => setIsEditMode((prev) => !prev)}>수정</button>
        <button onClick={handleRemove}>삭제</button>
      </div>
    </div>
  );
}
