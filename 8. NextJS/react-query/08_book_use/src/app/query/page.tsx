"use client";
import { fetchUser } from "@/service/query";
import { QueryExam } from "@/types/query";
import { useQuery } from "@tanstack/react-query";
import Link from "next/link";
import React from "react";

const UseQueryEx = () => {
  const { data, isLoading, error } = useQuery<QueryExam>({
    queryKey: ["user"],
    queryFn: fetchUser,
  });

  if (isLoading) return <div>Loading...</div>;
  if (error instanceof Error) return <div>Error: {error.message}</div>;

  return (
    <div>
      <Link href="/query/regist">등록</Link>
      <h2>User Info</h2>
      <p>Name: {data?.name}</p>
      <p>Email: {data?.email}</p>
    </div>
  );
};
export default UseQueryEx;
