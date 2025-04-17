import Link from "next/link";
import styles from "./Navbar.module.scss";
import urecaLogo from "@/assets/images/ureca_logo.png";
export default function Navbar() {
  console.log(urecaLogo);
  return (
    <nav className={styles.navbar}>
      <div className={styles.logo}>
        <img src={urecaLogo.src} alt="Logo" width={100} />
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
          <Link href="/news">News</Link>
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
