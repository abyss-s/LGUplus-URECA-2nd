"use client";
import { useCallback, useEffect, useRef, useState } from "react";
import styles from "./member.module.scss";
import { useMutation, useQuery } from "@tanstack/react-query";
import { searchMember, updateMember } from "@/service/member";
import { Member } from "@/types/member";
import { useAuth } from "@/store/hooks/memberHook";
export default function MemberDetail() {
  const [isEditMode, setIsEditMode] = useState(false);
  const idRef = useRef<HTMLInputElement>(null);
  const passwordRef = useRef<HTMLInputElement>(null);
  const nameRef = useRef<HTMLInputElement>(null);
  const emailRef = useRef<HTMLInputElement>(null);
  const addressRef = useRef<HTMLInputElement>(null);
  const phoneRef = useRef<HTMLInputElement>(null);

  const [id, setId] = useState("");

  //////////TODO M14. member state를 Custom hook을 통해 전달 받기
  const { memberState } = useAuth();

  //////////TODO M15. id 상태 변경하기
  useEffect(() => {
    if (memberState) {
      setId(memberState.id);
    }
  }, [memberState]);

  const {
    data: find,
    isLoading,
    error,
  } = useQuery({
    queryKey: ["member", id],
    queryFn: () => searchMember(id),
  });

  useEffect(() => {
    if (!find) return;
    idRef.current!.value = find.id;
    passwordRef.current!.value = find.password;
    nameRef.current!.value = find.name;
    emailRef.current!.value = find.email || "";
    addressRef.current!.value = find.address || "";
    phoneRef.current!.value = find.phone || "";
  }, [find]);

  const { mutate } = useMutation({
    mutationFn: updateMember,
    onSuccess: () => {
      alert("회원 정보가 수정되었습니다.");
    },
    onError: (error: any) => {
      console.error("수정 실패:", error);
      alert("수정 실패: " + error.message);
    },
  });

  const handleUpdate = useCallback(() => {
    if (isEditMode) {
      const id = idRef.current?.value.trim() || "";
      const password = passwordRef.current?.value.trim() || "";
      const name = nameRef.current?.value.trim() || "";

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
      if (!name) {
        alert("이름을 입력하세요");
        nameRef.current?.focus();
        return;
      }

      const updated: Member = {
        id,
        password,
        name,
        email: emailRef.current?.value || "",
        address: addressRef.current?.value || "",
        phone: phoneRef.current?.value || "",
      };

      mutate(updated);
    }
    setIsEditMode((prev) => !prev);
  }, [isEditMode, mutate]);

  if (isLoading) return <h1>Loading.....</h1>;
  if (error) return <h1>{(error as Error).message}</h1>;

  return (
    <div className={styles.container}>
      <table className={styles.table}>
        <caption>회원 정보</caption>
        <tbody>
          <tr>
            <td>아이디</td>
            <td>
              <input type="text" ref={idRef} readOnly />
            </td>
          </tr>
          <tr>
            <td>비밀번호</td>
            <td>
              <input
                className={!isEditMode ? styles.readonly : styles.input}
                readOnly={!isEditMode}
                type="password"
                ref={passwordRef}
              />
            </td>
          </tr>
          <tr>
            <td>이름</td>
            <td>
              <input
                className={!isEditMode ? styles.readonly : styles.input}
                readOnly={!isEditMode}
                type="text"
                ref={nameRef}
              />
            </td>
          </tr>
          <tr>
            <td>이메일</td>
            <td>
              <input
                className={!isEditMode ? styles.readonly : styles.input}
                readOnly={!isEditMode}
                type="email"
                ref={emailRef}
              />
            </td>
          </tr>
          <tr>
            <td>주소</td>
            <td>
              <input
                className={!isEditMode ? styles.readonly : styles.input}
                readOnly={!isEditMode}
                type="text"
                ref={addressRef}
              />
            </td>
          </tr>
          <tr>
            <td>전화번호</td>
            <td>
              <input
                className={!isEditMode ? styles.readonly : styles.input}
                readOnly={!isEditMode}
                type="text"
                ref={phoneRef}
              />
            </td>
          </tr>
        </tbody>
      </table>

      <div className={styles.buttonGroup}>
        <button className={styles.registerButton} onClick={handleUpdate}>
          {isEditMode ? "저장" : "수정"}
        </button>
      </div>
    </div>
  );
}
