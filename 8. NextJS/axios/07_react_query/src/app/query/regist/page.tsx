"use client";
import styles from "./UseMutationEx.module.scss";
const UseMutaionEx = () => {
  return (
    <form onSubmit={handleSubmit} className={styles.form}>
      <input type="text" placeholder="Name" className={styles.input} required />
      <br />
      <input type="email" placeholder="Email" className={styles.input} required />
      <br />
    </form>
  );
};
export default UseMutaionEx;
