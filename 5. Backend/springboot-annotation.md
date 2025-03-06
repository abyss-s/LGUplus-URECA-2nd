# Spring Annotation 정리

## Bean 등록 관련 어노테이션

- **@Component, @Repository, @Service, @Controller**
  - 클래스 선언부 위에 작성
  - 해당 클래스의 객체를 bean으로 등록
- **@Bean**
  - Configuration java에서 메서드의 리턴된 객체를 bean으로 등록
  - 메서드 선언부 위에 작성
- **@Configuration**
  - Bean 환경 설정을 Java로 구성할 때 사용
- **@Scope**
  - Bean의 life cycle 설정

## 생명주기 관련 어노테이션 (Javax Annotation API 필요)

- **@PostConstruct**
  - 객체 생성 후에 호출될 메서드를 지정
- **@PreDestroy**
  - 객체가 소멸되기 전에 호출될 메서드를 지정
- **@Resource**
  - Javax Annotation API 필요

## 의존성 주입(DI) 관련 어노테이션

- **@Autowired**
  - 속성, 생성자, setter 메서드, 일반 메서드 위에 선언 가능
  - Spring에서 지원
- **@Resource(name="빈 이름")**
  - 속성, setter 메서드 위에 선언
- **@Inject**
  - 속성, 생성자, setter 메서드, 일반 메서드 위에 선언
  - 자바에서 지원
- **@Qualifier("빈 이름")**
  - @Autowired로 주입할 때 동일한 타입의 객체가 여러 개일 경우 name으로 구별

## AOP 관련 어노테이션

- **@Aspect**
  - 클래스를 Aspect로 설정
- **@Pointcut**
  - 자주 사용하는 pointcut을 지정
- **어드바이저 관련**
  - @Before
  - @After
  - @AfterReturning
  - @AfterThrowing
  - @Around
- **@Transactional**
  - Spring에서 AOP로 트랜잭션 처리

## Web 관련 어노테이션

- **@RequestMapping**
  - URL 매핑
- **HTTP 메서드별 매핑**
  - @GetMapping
  - @PostMapping
  - @PutMapping
  - @DeleteMapping
- **@RequestParam**
  - String, Primitive, Map에 대한 요청 데이터 지정
- **@ModelAttribute**
  - DTO에 대한 요청 데이터 지정
- **예외 처리 관련**
  - @ExceptionHandler - 에러 처리하는 함수 선언
  - @ControllerAdvice - 에러 처리하는 클래스 선언
- **비동기 통신 관련**
  - @PathVariable - 비동기 통신으로 전달된 GET, DELETE 방식의 요청 데이터를 URL에서 추출할 때 지정
  - @RequestBody - 비동기 통신으로 전달된 PUT, POST 방식의 요청 데이터 추출
- **응답 관련**
  - @ResponseBody - 응답을 특정 URL로 이동하지 않고 직접 출력
  - @RestController - RESTful API를 위한 Controller 선언

## Spring Boot 관련 어노테이션

- **@SpringBootApplication**
  - Spring Boot를 실행
- **@Component(basePackages='')**
  - Base package 설정
- **@MapperScan(basePackages = { "com.ssafy.\\_\\_.model.dao" })**
  - MyBatis Mapper에 대한 basePackages 설정
