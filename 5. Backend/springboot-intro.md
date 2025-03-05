# 스프링부트 시작하기

## 스프링의 특징

### 1. 제어의 역전 (IoC, Inversion of Control)

- 객체의 생성과 생명주기 관리를 스프링 컨테이너가 담당합니다.
- 개발자는 객체를 직접 생성하지 않고, 외부에서 제공된 객체를 사용합니다.
- 객체 간의 결합도를 낮추어 유지보수성을 높일 수 있습니다.

### 2. 의존성 주입 (DI, Dependency Injection)

- 객체가 의존하는 다른 객체를 스프링 컨테이너에서 자동으로 주입하는 방식입니다.
- IoC 개념을 구현하는 방법 중 하나로, 객체 간의 의존성을 외부에서 설정하여 결합도를 줄입니다.
- 스프링은 생성자 주입을 권장합니다.

### 3. 어노테이션 기반 개발

어노테이션은 `@`를 사용하여 선언하며, 컴파일 시점 혹은 런타임 시점에 필요한 정보를 제공합니다.

- `@Bean`: 메서드를 통해 객체를 스프링 빈으로 등록합니다.
- `@Configuration`: 자바 기반의 빈 설정을 정의하는 클래스에 사용됩니다.
- `@Component`: 자동으로 빈 등록이 필요한 클래스에 사용됩니다.
- `@Service`: 서비스 계층에서 사용되며, 비즈니스 로직을 처리합니다.
- `@Repository`: 데이터 액세스 계층을 나타내며, 데이터베이스와의 연동을 담당합니다.
- `@Controller`: 컨트롤러 계층에서 사용되며, 클라이언트 요청을 처리합니다.
- `@RestController`: JSON 또는 XML 응답을 반환하는 컨트롤러에서 사용됩니다.
- `@Scope`: 빈의 생명주기를 설정합니다.

## 스프링부트 관련 의존성 및 도구

### 1. JDBC (Java Database Connectivity)

- Java 애플리케이션에서 데이터베이스와 상호작용할 수 있도록 제공되는 API입니다.
- 주요 기능
  1. 데이터베이스 연결 (Connection)
  2. SQL 실행 (Statement)
  3. 결과 처리 (ResultSet)

### 2. DB Connection Pool (DBCP)

- 여러 개의 DB Connection을 하나의 Pool에 모아 관리하여 성능을 향상시키는 기술입니다.
- 대표적인 DBCP 구현체: HikariCP (스프링 부트 기본 제공)

### 3. MyBatis

- SQL 매핑을 쉽게 처리할 수 있도록 도와주는 데이터베이스 프레임워크입니다.
- SQL을 XML 또는 애노테이션 방식으로 관리할 수 있습니다.

### 4. 로깅 (Logging)

- 스프링 부트는 기본적으로 `spring-boot-starter-logging`을 제공하며, 내부적으로 SLF4J와 Logback을 사용합니다.
- 주요 로그 레벨: TRACE < DEBUG < INFO < WARN < ERROR

## 경로 및 URL 이해하기

- **서버 루트**: 서버의 최상위 디렉토리로, 도메인과 포트 번호를 포함합니다.
- **컨텍스트 루트**: 웹 애플리케이션의 기본 URL 경로입니다.

```plaintext
http://www.example.com:8080/myapp
=> 서버 루트: http://www.example.com:8080
=> 컨텍스트 루트: /myapp
```

## MVC (Model-View-Controller) 패턴

애플리케이션을 세 가지 주요 컴포넌트로 분리하는 설계 패턴입니다.

1. **Model**: 데이터와 비즈니스 로직을 담당합니다.
2. **View**: 사용자 인터페이스를 표현합니다.
3. **Controller**: 사용자 입력을 처리하고 Model과 View 간의 상호작용을 관리합니다.

MVC 패턴을 사용하면 유지보수성과 확장성이 향상됩니다.

## 스프링부트 설치 및 실행

### 1. 메이븐(Maven) 설정

#### IntelliJ (Mac Homebrew)에서 Maven 설정하는 방법

1. Homebrew를 사용하여 Maven 설치
   ```sh
   brew install maven
   ```
2. 환경 변수(PATH) 등록
   ```sh
   echo 'export PATH="/opt/homebrew/bin:$PATH"' >> ~/.zshrc
   source ~/.zshrc
   ```
3. IntelliJ 터미널에서 메이븐 컴파일 및 패키징
   ```sh
   mvn clean package
   ```
4. 프로젝트 빌드 실행 → IntelliJ에서 **파일 → 캐시 무효화 후 다시 시작**

### 2. 그래들(Gradle) 설정

1. Homebrew를 사용하여 Gradle 설치
   ```sh
   brew install gradle
   ```
2. 그래들 빌드 실행
   ```sh
   ./gradlew build
   ```

### 3. JDBC 드라이버 추가 (IntelliJ, MacOS M3 기준)

1. 프로젝트 루트 디렉터리에 `/lib` 폴더 생성
2. `/lib` 폴더에 필요한 JDBC 드라이버 JAR 파일 추가
3. `File -> Project Structure -> Dependencies`에서 추가한 JAR 파일을 프로젝트에 포함
4. 프로젝트를 재실행하여 설정 완료

## `application.properties` 설정

스프링 부트 애플리케이션의 설정을 관리하는 구성 파일

> 참고 예시들

```properties
spring.application.name=SpringBootTest
server.servlet.context-path=/[경로명]
server.port=80

# MySQL hikariCP(https://github.com/brettwooldridge/HikariCP) 연결 설정
spring.datasource.hikari.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.hikari.jdbc-url=jdbc:mysql://localhost:3306/[DB명]?serverTimezone=UTC&useUniCode=yes&characterEncoding=UTF-8
spring.datasource.hikari.username=[유저명]
spring.datasource.hikari.password=[비밀번호]
spring.datasource.hikari.pool-name=hikari-pool

## MyBatis Setting
mybatis.type-aliases-package=com.uplus.eureka.*.model.dto
mybatis.mapper-locations=mapper/*.xml

# JPA 설정
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
spring.jpa.hibernate.ddl-auto=update

#log level Setting
logging.level.root=info
logging.level.com.uplus.eureka=debug
logging.level.com.zaxxer.hikari.pool=info

spring.mvc.pathmatch.matching-strategy = ANT_PATH_MATCHER
```

이제 스프링 부트 프로젝트를 설정하고 실행할 수 있습니다!
