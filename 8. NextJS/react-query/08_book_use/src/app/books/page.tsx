"use client";
import styles from "@/app/books/book.module.scss";
import SelectBox from "@/components/common/SelectBox";
import BookItem from "@/components/books/BookItem";
import { useQuery } from "@tanstack/react-query";
import { Book } from "@/types/book";
import { useCallback, useRef, useState } from "react";
import { searchAllBooks } from "@/service/books";
export default function Books() {
  //todo1. books, loading, error 상태 제거하기

  const [selectedKey, setSelectedKey] = useState("all");
  const wordRef = useRef<HTMLInputElement>(null);

  const options = [
    { value: "all", text: "---선택하세요---" },
    { value: "title", text: "제목" },
    { value: "author", text: "작성자" },
  ];

  //todo2. useQuery를 다시 수행 시키기 위한 상태 값 설정하기
  const [queryKeyState, setQueryKeyState] = useState({ key: "all", word: "" });

  //todo3. loadBooks 함수 useEffect함수 모두 제거하기
  //todo4. useQuery 작성하기
  const {
    data: books = [],
    isLoading,
    error,
  } = useQuery<Book[]>({
    queryKey: ["books", queryKeyState],
    queryFn: () => searchAllBooks({ key: queryKeyState.key, word: queryKeyState.word, pageNo: 1 }),
  });

  const handleSelect = useCallback(
    (key: string) => {
      console.log("key......", key);
      setSelectedKey(key);
    },
    [selectedKey]
  );
  //todo5. handleSearch변경 word 값이 없으면 전체 조회하도록 변경
  const handleSearch = useCallback(() => {
    setQueryKeyState({ key: selectedKey, word: wordRef.current?.value || "" });
  }, [selectedKey]);

  return (
    <div className={styles.bookList}>
      <div className={styles.header}>
        <div className={styles.searchArea}>
          <SelectBox selectOptions={options} onKeySelect={handleSelect} />
          <input
            type="text"
            placeholder="검색어를 입력하세요"
            className={styles.searchInput}
            ref={wordRef}
            defaultValue=""
          />
          <button className={styles.searchButton} onClick={handleSearch}>
            검색
          </button>
        </div>
      </div>

      {/*todo5. isloading일때 error일때 화면 변경하기*/}
      {isLoading && <p className="loading">Loading...</p>}
      {error && <p className="error">Error: {(error as Error).message}</p>}
      <table className={styles.table}>
        <thead>
          <tr>
            <th>이미지</th>
            <th>책 일련 번호</th>
            <th>제목</th>
            <th>저자</th>
            <th>가격</th>
          </tr>
        </thead>
        <tbody>
          {books.map((book: Book) => (
            <BookItem key={book.isbn} book={book} />
          ))}
        </tbody>
      </table>
      <div className={styles.pagination}>
        <button>이전</button>
        <button>다음</button>
      </div>
    </div>
  );
}
