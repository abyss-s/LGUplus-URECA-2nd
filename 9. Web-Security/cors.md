# CORS

## REST (Representational State Transfer)

- 자원(Resource)을 화면에 직접 표시하던 방식에서 벗어나, **자원에 대한 정보를 XML 또는 JSON 형태로 표현**함
- URL은 하나의 고유한 리소스를 대표하도록 설계되어야 함
- 예: `/users/1` → id가 1인 사용자 자원

## CORS란?

### 출처(Origin)란?

- **서버의 위치**를 의미하며, URL의 다음 3가지 요소로 구성됨:
  - `scheme` (프로토콜: http, https 등)
  - `host` (도메인)
  - `port` (포트 번호)

> 예: `https://example.com:3000` → 이 전체가 하나의 origin

### CORS의 필요성

- RESTful API를 활용할 때, **웹 프론트엔드와 백엔드가 서로 다른 서버**에서 제공되는 경우가 많음
- 이러한 **cross-origin 요청을 보안적으로 안전하게 허용**하기 위해 CORS 정책이 도입됨

## CORS 정의

- CORS (Cross-Origin Resource Sharing)는
  > 최초에 리소스를 제공한 origin과 다른 origin에서 리소스를 요청할 때,  
  > 특정 HTTP 헤더를 사용하여 **브라우저가 제한적으로 허용**하는 정책

### 관련 요청 헤더

- `Origin`: 요청을 보낸 출처
- `Access-Control-Request-Method`: 본 요청에서 사용될 HTTP 메서드
- `Access-Control-Request-Headers`: 본 요청에 포함될 헤더

## 동일 출처 정책 (Same-Origin Policy)

- 브라우저는 보안상의 이유로 **동일 출처(origin)가 아닌 경우 요청을 차단**
- 단, 서버가 명시적으로 허용하면 예외적으로 허용됨 (CORS 정책)

## CORS 요청의 종류

### 메서드 별 정리

| 요청 종류 | 메서드          | 조건                        | 사전 요청 필요 여부 |
| --------- | --------------- | --------------------------- | ------------------- |
| Simple    | GET, POST, HEAD | 안전한 Content-Type 및 헤더 | ❌                  |
| Preflight | PUT, DELETE 등  | 커스텀 헤더 포함 등         | ✅                  |

### 1. 프리플라이트(Preflight) 요청

- **실제 요청 전에 사전 확인 요청을 보내는 방식**
- `OPTIONS` 메서드를 사용해, 서버에 해당 요청을 허용하는지 먼저 확인

#### 구조

1. `OPTIONS` 메서드로 예비 요청 전송
2. 요청 헤더에 `Origin`, `Access-Control-Request-Method`, `Access-Control-Request-Headers` 포함
3. 서버가 허용하면, 본 요청을 브라우저가 이어서 전송

> 예시:

```http
OPTIONS /api/data HTTP/1.1
Origin: https://frontend.com
Access-Control-Request-Method: PUT
Access-Control-Request-Headers: Content-Type
```

### 2. Simple 요청

- 조건을 만족하면 프리플라이트 없이 바로 요청 가능

#### 조건

- 허용된 메서드: `GET`, `POST`, `HEAD`
- 안전한 헤더만 포함
- 허용된 Content-Type만 사용:
  - `application/x-www-form-urlencoded`
  - `multipart/form-data`
  - `text/plain`

## 서버가 CORS를 허용한 경우 응답 방식

1. **브라우저**가 `Origin` 헤더 포함 요청 전송
2. **서버**는 출처 확인 후 허용된 경우 응답
3. **브라우저**는 응답의 `Access-Control-Allow-Origin` 헤더를 확인
4. 해당 출처가 허용되었다면, 응답을 처리함

### 예시 응답 헤더

```http
Access-Control-Allow-Origin: https://frontend.com
Access-Control-Allow-Methods: GET, POST, PUT, DELETE
Access-Control-Allow-Headers: Content-Type
```

---

### CORS와 Credentials (쿠키 등)

- 기본적으로 **브라우저는 cross-origin 요청에 쿠키를 포함하지 않음**
- 서버가 `Access-Control-Allow-Credentials: true`를 설정하면 쿠키 사용 가능

> 프론트엔드 요청 시 axios 등에서는 다음처럼 설정해야 함:

```js
axios.get('https://api.example.com/data', {
  withCredentials: true,
});
```

> 서버 응답 헤더:

```http
Access-Control-Allow-Credentials: true
Access-Control-Allow-Origin: https://frontend.com
```

## Axios와 PUT, DELETE 요청에서 발생하는 문제

- `PUT`, `DELETE` 요청은 **Simple 요청이 아님**
- 따라서 브라우저는 먼저 **프리플라이트 요청**을 보냄
- 서버가 프리플라이트 요청을 허용하지 않으면, 본 요청이 실패
