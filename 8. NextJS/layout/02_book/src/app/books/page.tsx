"use client";
import styles from "@/app/books/book.module.scss";

export default function Books() {
  const books = [
    {
      isbn: "9917-1",
      title: "처음하는 리액트",
      author: "ureca",
      price: 30000,
      description: "",
      img: "domain.jpg",
    },
  ];
  const options = [
    { value: "all", text: "---선택하세요---" },
    { value: "title", text: "제목" },
    { value: "author", text: "작성자" },
  ];

  return (
    <div className={styles.bookList}>
      <div className={styles.header}>
        <div className={styles.searchArea}></div>
      </div>

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
        <tbody></tbody>
      </table>

      <div className={styles.pagination}>
        <button>이전</button>
        <button>다음</button>
      </div>
    </div>
  );
}
