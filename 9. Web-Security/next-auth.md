# Next Auth

**Next Auth**는 Next.js 프로젝트에서 사용자 인증 및 세션 관리를 위한 오픈 소스 라이브러리다.
Google, GitHub, Kakao 등 다양한 인증 공급자를 지원하며, Next.js의 서버와 클라이언트 양쪽에서 인증과 세션 관리를 처리한다. 덕분에 글로벌 상태 관리(Context, Redux, Zustand 등)에 토큰을 별도로 저장할 필요가 없다.

## 설치하기

```bash
npm install next-auth
```

## 데이터 타입 확장 및 상태 관리

### 1. 비동기 통신과 인증에 필요한 데이터 타입 설정하기

`next-auth.d.ts` 파일을 생성하고 다음과 같이 작성한다.

#### 1.1 Session 객체와 User 객체 확장하기

```typescript
declare module 'next-auth' {
  interface Session extends DefaultSession {
    // 세션에서 관리할 추가 상태를 선언한다.
  }
  interface User extends DefaultUser {
    // 비동기 통신 시 사용할 추가 데이터를 선언한다.
  }
}
```

#### 1.2 JWT 확장하기

```typescript
declare module 'next-auth/jwt' {
  interface JWT {
    // JWT에 추가로 저장할 데이터를 선언한다.
  }
}
```

## NextAuth 설정하기

### 1. 환경 설정하기

프로젝트 루트 디렉토리에 `.env.local` 파일을 생성하고 다음과 같이 작성한다.

```plaintext
NEXTAUTH_SECRET=your_secret_key
```

`NEXTAUTH_SECRET`는 쿠키에 저장되는 세션 정보를 암호화하는 키다. 추가로 사용할 인증 공급자(Google, GitHub, Kakao 등)의 클라이언트 ID와 클라이언트 시크릿도 환경 변수로 설정해야 한다.

SECRET 키를 생성하는 방법은 다음과 같다.

```bash
openssl rand -base64 32
```

### 2. NextAuth 옵션 설정하기

`app/api/auth/[...nextauth]/route.ts` 파일에 다음과 같이 작성한다.

```typescript
import NextAuth from 'next-auth';
import GoogleProvider from 'next-auth/providers/google';

export const authOptions = {
  providers: [
    GoogleProvider({
      clientId: process.env.GOOGLE_CLIENT_ID!,
      clientSecret: process.env.GOOGLE_CLIENT_SECRET!,
    }),
    // 추가 공급자를 이곳에 작성한다.
  ],
  session: {
    strategy: 'jwt', // JWT 기반 세션 관리 방식
  },
  pages: {
    signIn: '/auth/signin', // 커스텀 로그인 페이지 경로
  },
  callbacks: {
    async signIn({ user, account, profile }) {
      return true; // 로그인 성공 시 true 반환
    },
    async redirect({ url, baseUrl }) {
      return baseUrl; // 로그인 후 리다이렉트할 URL 설정
    },
    async jwt({ token, user }) {
      // JWT 토큰 생성 및 업데이트
      return token;
    },
    async session({ session, token }) {
      // 세션 객체를 수정할 때 호출된다.
      return session;
    },
  },
  secret: process.env.NEXTAUTH_SECRET, // 필수 설정
};

const handler = NextAuth(authOptions);
export { handler as GET, handler as POST };
```

> **주의**: NextAuth 버전 4 이상에서는 `strategy: 'jwt'`로 작성해야 하며, 콜백 함수의 파라미터 형식도 구조 분해 할당을 사용하는 것이 최신 문법이다.

## 기본 제공 경로

NextAuth는 기본적으로 다음과 같은 API 경로를 제공해 인증과 관련된 다양한 작업을 수행할 수 있다.

- **로그인**: `/api/auth/signin`
- **로그아웃**: `/api/auth/signout`
- **콜백**: `/api/auth/callback/[provider]`
- **세션 정보 조회**: `/api/auth/session`
- **사용자 정보 조회**: `/api/auth/user`

## 추가사항

- **다양한 인증 공급자 지원**: Google, GitHub 외에도 Twitter, Facebook 등 여러 공급자를 사용할 수 있다. 각 공급자별 환경 변수를 별도로 설정해야 한다.
- **커스텀 페이지 설정**: 로그인(signIn), 로그아웃(signOut) 페이지를 직접 만들어 연결할 수 있다.
- **데이터베이스 연동**: NextAuth는 데이터베이스를 연동해 사용자 정보를 저장하고 권한 관리(Role-based access control, RBAC) 등을 구현할 수도 있다.
- **보안 강화**: `NEXTAUTH_SECRET` 설정은 필수이며, 특히 배포 환경에서는 반드시 강력한 시크릿 키를 사용해야 한다.

---

# 1. Provider별 설정 예시

NextAuth에서는 다양한 인증 공급자(Provider)를 사용할 수 있다. 각각 Provider를 설정할 때 필요한 `clientId`, `clientSecret` 같은 환경 변수를 준비하고, `providers` 배열에 추가하면 된다.

## 1.1 Google

```typescript
import GoogleProvider from 'next-auth/providers/google';

GoogleProvider({
  clientId: process.env.GOOGLE_CLIENT_ID!,
  clientSecret: process.env.GOOGLE_CLIENT_SECRET!,
});
```

필요한 환경 변수:

```env
GOOGLE_CLIENT_ID=xxxx
GOOGLE_CLIENT_SECRET=xxxx
```

## 1.2 GitHub

```typescript
import GitHubProvider from 'next-auth/providers/github';

GitHubProvider({
  clientId: process.env.GITHUB_CLIENT_ID!,
  clientSecret: process.env.GITHUB_CLIENT_SECRET!,
});
```

필요한 환경 변수:

```env
GITHUB_CLIENT_ID=xxxx
GITHUB_CLIENT_SECRET=xxxx
```

## 1.3 Kakao

NextAuth 기본 제공 Provider에 Kakao는 없지만, `credentials`나 직접 OAuth2 설정으로 연결할 수 있다. 외부 라이브러리(`next-auth-kakao-provider`)를 쓰거나 직접 설정한다.

간단한 예시:

```typescript
import KakaoProvider from 'next-auth/providers/kakao';

KakaoProvider({
  clientId: process.env.KAKAO_CLIENT_ID!,
  clientSecret: process.env.KAKAO_CLIENT_SECRET!,
});
```

필요한 환경 변수:

```env
KAKAO_CLIENT_ID=xxxx
KAKAO_CLIENT_SECRET=xxxx
```

> 참고: Kakao는 일부 설정이 달라 별도 callback 처리가 필요할 수 있다.

## 1.4 Credentials (아이디/비밀번호 직접 로그인)

```typescript
import CredentialsProvider from 'next-auth/providers/credentials';

CredentialsProvider({
  name: 'Credentials',
  credentials: {
    email: { label: 'Email', type: 'email' },
    password: { label: 'Password', type: 'password' },
  },
  async authorize(credentials) {
    const user = await fetchUser(credentials.email, credentials.password);
    if (user) {
      return user;
    }
    return null;
  },
});
```

> 직접 로그인 처리를 하려면 `authorize` 함수에서 사용자 인증 로직을 직접 작성해야 한다.

# 2. Session과 JWT에 데이터 추가하는 방법

NextAuth 기본 설정만 쓰면 session에는 기본적인 정보(user name, email 등)만 들어간다.  
하지만 **사용자 ID, role, custom data** 같은 것을 세션이나 JWT에 넣고 싶을 때가 있다.  
그럴 때는 `jwt` 콜백과 `session` 콜백을 활용한다.

## 2.1 JWT에 데이터 추가하기

```typescript
callbacks: {
  async jwt({ token, user }) {
    if (user) {
      token.id = user.id; // User가 로그인할 때 토큰에 id 추가
      token.role = user.role; // 예를 들어 role도 추가
    }
    return token;
  },
}
```

- `user` 객체는 로그인 성공 시에만 존재한다.
- `token`은 JWT 토큰 안에 저장되는 데이터다.

## 2.2 Session에 데이터 추가하기

```typescript
callbacks: {
  async session({ session, token }) {
    if (token) {
      session.user.id = token.id; // JWT에서 꺼내 세션에 넣기
      session.user.role = token.role;
    }
    return session;
  },
}
```

- 이렇게 하면 클라이언트 쪽에서 `useSession()`을 호출했을 때 `id`, `role` 같은 정보도 바로 사용할 수 있다.

## 2.3 타입스크립트 타입 확장

**next-auth.d.ts** 파일을 만들어서 타입도 확장해야 한다.

```typescript
import { DefaultSession } from 'next-auth';

declare module 'next-auth' {
  interface Session {
    user: {
      id: string;
      role: string;
    } & DefaultSession['user'];
  }
}

declare module 'next-auth/jwt' {
  interface JWT {
    id: string;
    role: string;
  }
}
```

이렇게 하면 타입스크립트에서 `session.user.id`, `session.user.role`을 사용할 때 타입 에러가 나지 않는다.
