import { Book } from "@/types/book";
import { localAxios } from "@/utils/http-commons";
import React from "react";
import styles from "@/app/page.module.scss";
const BooksFetchAll = async () => {
  const axios = localAxios();
  const getBooks = async () => {
    console.log("fetch getBooks.........");
    await new Promise((resolve) => setTimeout(resolve, 5000));
    const response = await axios.get("/book");
    //ssr이므로 console.log는  브라우져에 출력되지 않고 terminal에 출력된다.
    console.log(response.data);
    return response.data.books;
  };
  const books: Book[] = await getBooks();
  return <h1 className={styles.title}>{JSON.stringify(books)}</h1>;
};

export default BooksFetchAll;
