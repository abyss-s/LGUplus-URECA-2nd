import "./BookMarkItem.scss";
import { Book } from "@/types/book";
import Link from "next/link";
import { useBookmarkContext } from "@/store/book-mark";
import { useCallback } from "react";

interface Props {
  book: Book;
}
const CartItem = ({ book }: Props) => {
  const { removeBook } = useBookmarkContext();
  const handleRemove = useCallback(() => {
    removeBook(book.isbn);
  }, [book]);
  return (
    <tr className="book-row" key={book.isbn}>
      <td>
        <img src={`/assets/images/${book.img}`} alt={book.title} className="book-thumbnail" />
      </td>
      <td>{book.isbn}</td>
      <td>
        <Link href={`/books/${book.isbn}`}> {book.title}</Link>
      </td>
      <td>{book.author}</td>
      <td>{book.price}</td>
      <td>
        <button className="select-button" onClick={handleRemove}>
          삭제
        </button>
      </td>
    </tr>
  );
};

export default CartItem;
