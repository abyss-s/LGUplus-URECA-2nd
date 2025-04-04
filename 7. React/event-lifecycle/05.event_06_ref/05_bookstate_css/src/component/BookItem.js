import React from 'react';
import '../styles/BookItem.css';

const BookItem = ({ book, bookSelect }) => {
  return (
    <tr className='book-row'>
      <td>
        <img
          className='book-thumnail'
          src={require(`../assets/images/${book.img}`)}
          alt={book.title}
        />
      </td>
      <td>{book.title}</td>
      <td>{book.price}</td>
      <td>{book.author}</td>
      <td>
        <button
          className='select-button'
          onClick={() => {
            bookSelect(book);
          }}
        >
          클릭
        </button>
      </td>
    </tr>
  );
};

export default BookItem;
