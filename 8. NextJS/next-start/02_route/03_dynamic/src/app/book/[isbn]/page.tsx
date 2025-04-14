import React from "react";

const BookDetail = ({ params: { isbn } }: { params: { isbn: string } }) => {
  console.log("isbn:", isbn);
  return (
    <div>
      <h1>Book Detail : {isbn}</h1>
    </div>
  );
};

export default BookDetail;
