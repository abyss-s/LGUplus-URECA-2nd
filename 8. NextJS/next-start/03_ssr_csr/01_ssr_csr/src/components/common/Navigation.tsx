"use client";
import React from "react";
import styles from "./Navigation.module.scss";
import Link from "next/link";
import { usePathname } from "next/navigation";

const Navigation = () => {
  //usePathname()   : client  component이므로 use client를 붙여야 실행 오류가 발생하지 않는다.
  //                  ==> CSR
  const path = usePathname();
  return (
    <nav className={styles.nav}>
      <ul className={styles.menu}>
        <li>
          <Link className={styles.menu_item} href="/">
            Home {path === "/" ? "❤" : ""}
          </Link>
        </li>
        <li>
          <Link className={styles.menu_item} href="/about-us">
            About us {path === "/about-us" ? "❤" : ""}
          </Link>
        </li>
        <li>
          <Link className={styles.menu_item} href="/book">
            Book{path === "/book" ? "❤" : ""}
          </Link>
        </li>
        <li>
          <Link className={styles.menu_item} href="/qna">
            QnA
          </Link>
        </li>
        <li>
          <Link className={styles.menu_item} href="/test">
            Test
          </Link>
        </li>
        <li>
          <Link className={styles.menu_item} href="/book/book-insert">
            Book Insert
          </Link>
        </li>
      </ul>
    </nav>
  );
};

export default Navigation;
