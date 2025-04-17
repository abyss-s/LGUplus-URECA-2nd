import styles from "@/app/page.module.scss";
import BookFetch from "@/components/books/BookFetch";
import BooksFetchAll from "@/components/books/BooksFetchAll";
import ClientComponent from "@/components/books/ClientComponent";
import { Suspense } from "react";
/* 
SSR로 axios 통신을 하기 
1. Component 선언부에 async 를 선언해야 한다. 
2. use client 선언하면 안된다. 
3. useXXXX  [ex) useState, useRef, ] 사용하면 안된다. 
4. 선언한 함수를 컴포넌트의 속성으로 전달하면 안된다 ==> hydration이므로 안됨.
*/
export default async function Home() {
  return (
    <div className={styles.container}>
      <h1 className={styles.title}>Home</h1>
      <h1 className={styles.title}>
        <Suspense fallback={<h1>Loading Book List</h1>}>
          <BooksFetchAll />
        </Suspense>
      </h1>
      <h1 className={styles.title}>
        <Suspense fallback={<h1>Loading Book Detail</h1>}>
          <BookFetch />
        </Suspense>
      </h1>

      <h1 className={styles.title}>
        {/* client component를 import해서 사용하는 것은 가능하다!!*/}
        {/* <ClientComponent /> */}
      </h1>
    </div>
  );
}
