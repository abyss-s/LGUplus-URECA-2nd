"use client";
import { createUser } from "@/service/query";
import { useMutation } from "@tanstack/react-query";
import React, { useState } from "react";
import styles from "./UseMutationEx.module.scss";
const UseMutaionEx = () => {
  const [name, setName] = useState("");
  const [email, setEmail] = useState("");

  const mutation = useMutation({
    mutationFn: createUser,
    onSuccess: (data) => {
      alert(`User created! ID: ${data.id}`);
    },
    onError: (error: Error) => {
      alert(`Error: ${error.message}`);
    },
  });

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    mutation.mutate({ name, email });
  };

  return (
    <form onSubmit={handleSubmit} className={styles.form}>
      <input
        type="text"
        placeholder="Name"
        value={name}
        onChange={(e) => setName(e.target.value)}
        className={styles.input}
        required
      />
      <br />
      <input
        type="email"
        placeholder="Email"
        value={email}
        onChange={(e) => setEmail(e.target.value)}
        className={styles.input}
        required
      />
      <br />
      <button type="submit" disabled={mutation.isPending} className={styles.submitButton}>
        {mutation.isPending ? "Submitting..." : "Submit"}
      </button>
    </form>
  );
};
export default UseMutaionEx;
