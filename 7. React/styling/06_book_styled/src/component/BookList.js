import React from "react";
import styled from "styled-components";
import BookListItem from "./BookItem";


function BookList({ books, bookSelect }) {
  return (
    <Table>
      <Thead>
        <tr>
          <th>이미지</th>
          <th>제목</th>
          <th>가격</th>
          <th>저자</th>
          <th>비고</th>
        </tr>
      </Thead>
      <Tbody>
        {books.map((book) => (
          <BookListItem key={book.ISBN} book={book} bookSelect={bookSelect} />
        ))}
      </Tbody>
    </Table>
  );
}

export default BookList;
