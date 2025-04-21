"use client";

import React, { createContext, useContext, useState, ReactNode } from "react";
import { Member } from "@/types/member";

interface MemberContextType {
  member: Member | null;
  login: (memberData: Member) => void;
  logout: () => void;
}

// 초기값 생성
const MemberContext = createContext<MemberContextType | undefined>(undefined);

// Provider 컴포넌트
export const MemberProvider = ({ children }: { children: ReactNode }) => {
  const [member, setMember] = useState<Member | null>(null);

  const login = (memberData: Member) => setMember(memberData);
  const logout = () => setMember(null);

  return (
    <MemberContext.Provider value={{ member, login, logout }}>{children}</MemberContext.Provider>
  );
};

// 커스텀 훅: useMemberContext
export const useMemberContext = () => {
  const context = useContext(MemberContext);
  if (!context) {
    throw new Error("useMemberContext must be used within a MemberProvider");
  }
  return context;
};
