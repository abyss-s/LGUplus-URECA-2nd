import styles from "@/app/page.module.scss";
import ClientComponent from "@/components/books/ClientComponent";
import { Book } from "@/types/book";
import { localAxios } from "@/utils/http-commons";
/* 
SSR로 axios 통신을 하기 
1. Component 선언부에 async 를 선언해야 한다. 
2. use client 선언하면 안된다. 
3. useXXXX  [ex) useState, useRef, ] 사용하면 안된다. 
4. 선언한 함수를 컴포넌트의 속성으로 전달하면 안된다 ==> hydration이므로 안됨.
*/
export default async function Home() {
  const axios = localAxios();
  const options = [
    { value: "all", text: "---선택하세요---" },
    { value: "title", text: "제목" },
    { value: "author", text: "작성자" },
  ];
  const handleSelect = (key: string) => {
    console.log("key......", key);
  };
  // Home을 async로 선언했기 때문에 SeverComponent이므로 useXXX  사용하면 에러 발생
  // const [data] = useState<string>("hello");
  const getBooks = async () => {
    console.log("fetch getBooks.........", Date.now());
    await new Promise((resolve) => setTimeout(resolve, 5000));
    const response = await axios.get("/book");
    //ssr이므로 console.log는  브라우져에 출력되지 않고 terminal에 출력된다.
    console.log(response.data);
    return response.data.books;
  };
  const getBook = async () => {
    console.log("fetch getBook.........", Date.now());
    await new Promise((resolve) => setTimeout(resolve, 5000));
    const response = await axios.get("/book/2025-04-15");
    //ssr이므로 console.log는  브라우져에 출력되지 않고 terminal에 출력된다.
    console.log(response.data);
    return response.data;
  };
  // const books: Book[] = await getBooks();
  // const book: Book = await getBook();
  const [books, book] = await Promise.all([getBooks(), getBook()]);

  return (
    <div className={styles.container}>
      <h1 className={styles.title}>Home</h1>
      <h1 className={styles.title}>{JSON.stringify(books)}</h1>
      <h1 className={styles.title}>{JSON.stringify(book)}</h1>
      {/* server component에서는  자식 컴포넌트에 속성으로 함수를 전달할 수 없다.  */}
      {/* <h1 className={styles.title}>
        <SelectBox selectOptions={options} onKeySelect={handleSelect} />
      </h1> */}
      <h1 className={styles.title}>
        {/* client component를 import해서 사용하는 것은 가능하다!!*/}
        <ClientComponent />
      </h1>
    </div>
  );
}
