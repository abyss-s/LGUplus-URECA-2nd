## RESTful API와 MVC 패턴

RESTful API는 웹 서비스를 설계하고 구현하는 아키텍처 스타일로, MVC 패턴과 함께 사용될 수 있습니다.

### MVC 패턴

MVC 패턴은 애플리케이션을 세 가지 주요 컴포넌트로 분리합니다:

1. **Model**: 데이터와 비즈니스 로직을 처리
   여기에는 Service와 Repository가 포함
2. **View**: 사용자에게 데이터를 표현합니다. RESTful API에서는 주로 JSON 형식의 응답이 View 역할을 합니다[1].
3. **Controller**: 사용자 요청을 받아 처리하고, 적절한 응답을 반환합니다

<img width="1244" alt="image" src="https://github.com/user-attachments/assets/7aae56d3-506e-4312-9524-3dca1476dc97" />

### DAO, DTO, Service

- **DAO (Data Access Object)**: 데이터베이스와 직접 상호작용하는 객체입니다.
- **DTO (Data Transfer Object)**: 데이터베이스에서 얻은 데이터를 Service나 Controller로 전달할 때 사용하는 객체입니다[3].
- **Service**: 비즈니스 로직을 처리하는 계층으로, DAO를 통해 데이터를 가져오고 처리합니다.

## HTTP 요청 메소드

RESTful API에서 주로 사용되는 HTTP 메소드는 다음과 같습니다:

1. **GET**: 리소스를 조회합니다. 일반적으로 body 없이 URL을 통해 리소스를 표현합니다
2. **POST**: 새로운 리소스를 생성합니다
3. **PUT**: 리소스를 완전히 대체(갱신)합니다. 모든 필드 값을 전송해야 합니다
4. **PATCH**: 리소스의 일부를 수정합니다. 변경할 필드만 전송합니다
5. **DELETE**: 특정 리소스를 삭제합니다. 일반적으로 body 없이 URL로 삭제할 리소스를 지정합니다

### RESTful API 설계 규칙

1. 슬래시(/)로 계층 관계를 표현합니다. 예: `/product/food`
2. URI 마지막에는 슬래시를 사용하지 않습니다.
3. 하이픈(-)을 사용하여 URI 가독성을 높입니다. 밑줄(\_)은 사용하지 않습니다.
4. URI 경로는 소문자로 작성합니다.
5. 파일 확장자는 URI에 포함시키지 않고, Accept 헤더를 사용합니다

## URL 규칙 & HTTP 메소드

### 1. **쿼리 스트링 (Query String)**

- **GET** 요청에서 필터링, 정렬, 검색 등에 사용  
  예시: `/products?category=electronics&sort=price`

### 2. **폼 데이터 (Form Data)**

- **POST** 요청에서 폼을 통해 데이터 전송  
  예시: `/login?username=john&password=secret`

### 3. **GET 메소드와 PK (Primary Key)**

- **GET** 요청에서 특정 리소스를 조회할 때 사용 (PK로 구분)  
  예시: `/products/12345` (특정 상품 조회), `/products` (전체 목록 조회)

### 4. **POST, PUT, DELETE 메소드**

- **POST**: 리소스 생성 (`/products`)
- **PUT**: 리소스 전체 수정 (`/products/12345`)
- **DELETE**: 리소스 삭제 (`/products/12345`)

### 5. **기타 메소드**

- **PATCH**: 부분 수정 (`/users/123`)
- **OPTIONS**: 지원하는 메소드 조회 (`/products`)

---

| 메소드 | 설명             | 예시                           |
| ------ | ---------------- | ------------------------------ |
| GET    | 리소스 조회      | `/products`, `/products/12345` |
| POST   | 리소스 생성      | `/products`, `/login`          |
| PUT    | 리소스 수정      | `/products/12345`              |
| DELETE | 리소스 삭제      | `/products/12345`              |
| PATCH  | 리소스 부분 수정 | `/users/123`                   |

### 강사님 보충자료 
![image](https://github.com/user-attachments/assets/4443d70f-70f9-43d5-a2a6-5907d4f83c73)


