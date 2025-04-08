import React from "react";
import BookItem from "./BookItem";
import "./BookList.scss";
function BookList({ books, bookSelect }) {
  return (
    <table className="book-table">
      <thead>
        <tr className="book-header">
          <th>이미지</th>
          <th>제목</th>
          <th>가격</th>
          <th>저자</th>
          <th>비고</th>
        </tr>
      </thead>
      <tbody>
        {books.map((book) => (
          <BookItem key={book.ISBN} book={book} bookSelect={bookSelect} />
        ))}
      </tbody>
    </table>
  );
}
export default BookList;
