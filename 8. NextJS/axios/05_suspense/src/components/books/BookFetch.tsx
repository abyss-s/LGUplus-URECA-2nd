import { Book } from "@/types/book";
import { localAxios } from "@/utils/http-commons";
import React from "react";
import styles from "@/app/page.module.scss";

const BookFetch = async () => {
  const axios = localAxios();

  const getBook = async () => {
    console.log("fetch getBook.........");
    await new Promise((resolve) => setTimeout(resolve, 3000));
    const response = await axios.get("/book/2025-04-15");
    //ssr이므로 console.log는  브라우져에 출력되지 않고 terminal에 출력된다.
    console.log(response.data);
    return response.data;
  };

  const book: Book = await getBook();

  return <h1 className={styles.title}>{JSON.stringify(book)}</h1>;
};

export default BookFetch;
