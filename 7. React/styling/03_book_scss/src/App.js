import BookDetail from "./component/BookDetail";
import BookList from "./component/BookList";
import "./App.css";
import Books from "./Books";
import { useState } from "react";

function App() {
  const [books] = useState(Books);
  const [book, setBook] = useState(Books[0]);

  return (
    <div className="container">
      <BookDetail book={book} />
      <BookList
        books={books}
        bookSelect={(selectBook) => {
          setBook(selectBook);
        }}
      />
    </div>
  );
}

export default App;
