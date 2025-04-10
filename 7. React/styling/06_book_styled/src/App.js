import React, { useState } from "react";
import styled from "styled-components";
import BookDetail from "./component/BookDetail";
import BookList from "./component/BookList";
import Books from "./Books";

const Container = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 20px;

  @media (min-width: 768px) {
    flex-direction: column;
    justify-content: center;
    align-items: center;
  }
`;

const BookDetailWrapper = styled.div`
  display: none;

  @media (min-width: 768px) {
    display: block;
  }
`;

function App() {
  const [books] = useState(Books);
  const [book, setBook] = useState(Books[0]);

  return (
    <Container>
      {/* 모바일에서는 안 보이고, 태블릿 이상에서만 보이게 설정 */}
      <BookDetailWrapper>
        <BookDetail book={book} />
      </BookDetailWrapper>
      <BookList books={books} bookSelect={(selectBook) => setBook(selectBook)} />
    </Container>
  );
}

export default App;
