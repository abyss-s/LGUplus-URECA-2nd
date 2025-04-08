import React from "react";
import "./BookDetail.scss";

function BookDetail({ book }) {
  return (
    <div className="book-card">
      <img src={require(`../assets/images/${book.img}`)} alt={book.title} className="book-image" />
      <div className="book-info">
        <h2 className="book-title">{book.title}</h2>
        <p className="book-author">{book.author}</p>
        <p className="book-price">{book.price}</p>
      </div>
    </div>
  );
}

export default BookDetail;
