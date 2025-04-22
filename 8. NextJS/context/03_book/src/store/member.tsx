"use client";
import { Member } from "@/types/member";
import { createContext, ReactNode, useContext, useState, useCallback, useMemo } from "react";

interface MemberContextType {
  member: Member | null;
  login: (member: Member) => void;
  logout: () => void;
}

const MemberContext = createContext<MemberContextType | undefined>(undefined);

export const MemberProvider = ({ children }: { children: ReactNode }) => {
  const [member, setMember] = useState<Member | null>(null);

  const login = useCallback((member: Member) => {
    setMember(member);
  }, []);

  const logout = useCallback(() => {
    setMember(null);
  }, []);

  const returnValue = useMemo(
    () => ({
      member,
      login,
      logout,
    }),
    [member]
  );

  return <MemberContext.Provider value={returnValue}>{children}</MemberContext.Provider>;
};

export const useMemberContext = () => {
  const context = useContext(MemberContext);
  if (!context) {
    throw new Error("useMemberContext must be used within a MemberProvider");
  }
  return context;
};
