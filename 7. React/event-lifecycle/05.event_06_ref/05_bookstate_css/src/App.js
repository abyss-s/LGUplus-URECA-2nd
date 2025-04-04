import { useState } from 'react';
import './App.css';
import Books from './Books';
import BookList from './component/BookList';
import BookDetail from './component/BookDetail';

function App() {
  const [books] = useState(Books);
  const [book, setBook] = useState(books[0]);
  return (
    <div className='container'>
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
