import styles from "@/app/page.module.scss";
export default async function Home() {
  return (
    <div className={styles.container}>
      <h1 className={styles.title}>Home</h1>
    </div>
  );
}
