import Link from "next/link";
import styles from "./Navbar.module.scss";
export default function Navbar() {
  return (
    <nav className={styles.navbar}>
      <div className={styles.logo}>
        <img src="/assets/images/ureca_logo.png" alt="Logo" width={100} />
      </div>
      <ul className={styles.navLinks}>
        <li>
          <Link href="/">Home</Link>
        </li>
        <li>
          <Link href="/boards">게시판</Link>
        </li>
        <li>
          <Link href="/qna">QnA</Link>
        </li>
        <li>
          <Link href="/books">도서정보</Link>
        </li>
      </ul>
      <div className={styles.search}>
        <button>로그인</button>
      </div>
    </nav>
  );
}
