import NextAuth, { DefaultSession, DefaultUser } from "next-auth";

//////////// TODO 1. Next Auth 에서 사용할 Session과 User 타입 선언하기
declare module "next-auth" {
  interface Session extends DefaultSession {
    accessToken?: string;
    refreshToken?: string;
    id?: string;
  }

  interface User extends DefaultUser {
    password?: string;
    accessToken?: string;
    refreshToken?: string;
    id?: string;
    expiresAt?: number;
  }
}

//////////// TODO 2. 응답 받은 JWT를 위한 type 선언하기
declare module "next-auth/jwt" {
  interface JWT {
    accessToken?: string;
    refreshToken?: string;
    id?: string;
    expiresAt?: number;
  }
}
