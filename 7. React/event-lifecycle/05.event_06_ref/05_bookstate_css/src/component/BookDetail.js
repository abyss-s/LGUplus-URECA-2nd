import React from 'react';
import '../styles/BookDetail.css';

const BookDetail = ({ book }) => {
  return (
    <div className='book-card'>
      <img className='book-image' src={require(`../assets/images/${book.img}`)} alt={book.title} />
      <div className='book-info'>
        <h2 className='book-title'>{book.title}</h2>
        <h4 className='book-author'>{book.author}</h4>
        <h4 className='book-price'>{book.price}</h4>
      </div>
    </div>
  );
};

export default BookDetail;
