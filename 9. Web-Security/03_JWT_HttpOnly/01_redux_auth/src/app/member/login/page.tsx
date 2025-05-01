"use client";

import { useCallback, useRef } from "react";
import styles from "./login.module.scss";

import { useAuth } from "@/store/hooks/memberHook";

const MemberLogin = () => {
  const idRef = useRef<HTMLInputElement>(null);
  const passwordRef = useRef<HTMLInputElement>(null);
  const { login, loginMutation } = useAuth();

  const handleLogin = useCallback(async () => {
    const id = idRef.current?.value.trim() || "";
    const password = passwordRef.current?.value.trim() || "";

    if (!id) {
      alert("아이디를 입력하세요");
      idRef.current?.focus();
      return;
    }
    if (!password) {
      alert("비밀번호를 입력하세요");
      passwordRef.current?.focus();
      return;
    }

    login({ id, password });

    // 성공 후 리다이렉트
    // (loginMutation.isSuccess 대신 onSuccess에서 처리해도 됨)
  }, [login]);

  return (
    <div className={styles.container}>
      <table className={styles.table}>
        <caption>로 그 인</caption>
        <tbody>
          <tr>
            <td>아 이 디</td>
            <td>
              <input type="text" ref={idRef} placeholder="아이디를 입력하세요" />
            </td>
          </tr>
          <tr>
            <td>비밀번호</td>
            <td>
              <input type="password" ref={passwordRef} />
            </td>
          </tr>
        </tbody>
      </table>
      <div className={styles.buttonGroup}>
        <button
          className={styles.registerButton}
          onClick={handleLogin}
          disabled={loginMutation.isPending}
        >
          {loginMutation.isPending ? "로그인 중..." : "로그인"}
        </button>
      </div>
    </div>
  );
};

export default MemberLogin;
