# XML 매퍼를 사용한 DAO

## XML 매퍼의 장점

- **SQL 쿼리의 분리**: SQL 쿼리를 별도의 XML 파일에 작성하여 코드에서 분리할 수 있음
- **유지보수 용이성**: SQL 수정 시 자바 코드 변경 없이 XML 파일만 수정하면 됨
- **가독성 증가**: SQL과 Java 코드가 혼합되지 않아 가독성이 향상됨

## MyBatis 설정 (Gradle-Groovy 기반)

### 1. @Mapper 사용

- `@Mapper` 어노테이션을 사용하면 자동으로 인터페이스 기반 DAO를 생성한다.
- Spring Container에서 해당 인터페이스를 MyBatis와 연동된 DAO로 인식하고, 구현체를 자동으로 생성한다.

```java
// DAO 인터페이스
@Mapper
public interface BookDao {
    ..
}
```

### 2. 기존 DAO를 삭제하고 MyBatis 매핑을 적용

기존에 `@Repository` 혹은 `JdbcTemplate`을 사용한 DAO 클래스를 제거한다.

```java
// @Repository  // 기존 방식 (삭제)
// public class BookDaoImpl implements BookDao {
// ...
// }
```

👉 이 클래스를 삭제하고 `@Mapper` 기반 인터페이스로 변경

### 3. XML 매퍼 생성 (`mapper/BookMapper.xml`)

인터페이스와 1:1 매칭되도록 XML 파일 작성

```xml
<mapper namespace="com.example.project.model.dao.BookDao">

    <select id="searchAll" parameterType="PageBean" resultType="Book">
        SELECT * FROM book
        ORDER BY isbn
    </select>

    <select id="search" parameterType="string" resultType="Book">
        SELECT * FROM book WHERE isbn = #{isbn}
    </select>

    <insert id="insert" parameterType="Book">
        INSERT INTO book (isbn, title, author, price, describ, img)
        VALUES (#{isbn}, #{title}, #{author}, #{price}, #{describ}, #{img})
    </insert>

    <update id="update" parameterType="Book">
        UPDATE book
        SET title = #{title}, author = #{author}, price = #{price},
            describ = #{describ}, img = #{img}
        WHERE isbn = #{isbn}
    </update>

    <delete id="remove" parameterType="string">
        DELETE FROM book WHERE isbn = #{isbn}
    </delete>

</mapper>
```

### 3. MyBatis 설정 적용 (`application.properties` or `application.yml`)

```properties
mybatis.type-aliases-package=com.example.project.model.dto
mybatis.mapper-locations=classpath:mapper/*.xml
```

```yaml
mybatis:
  type-aliases-package: com.example.project.model.dto
  mapper-locations: classpath:mapper/*.xml
```

### 5. 서비스에서 호출 방식 변경

기존에 `BookDaoImpl` 클래스를 직접 사용했다면, `@Mapper` 기반 인터페이스로 변경 하면 자동 주입됨.

```java
@Service
public class BookService {
    private final BookDao bookDao;

    @Autowired
    public BookService(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    public List<Book> getAllBooks(PageBean bean) {
        return bookDao.searchAll(bean);
    }
}
```

### 6. application.properties 설정

MyBatis가 XML 매퍼 파일을 찾을 수 있도록 설정을 추가해야 한다.

```properties
# MyBatis 설정
mybatis.type-aliases-package=com.example.project.model.dto
mybatis.mapper-locations=classpath:mapper/*.xml
```

### 7. build.gradle 설정 (Gradle 기반)

```groovy
dependencies {
    implementation 'org.mybatis.spring.boot:mybatis-spring-boot-starter:3.0.1'
    implementation 'org.springdoc:springdoc-openapi-starter-webmvc-ui:2.0.2'
}
```

openapi-starter-webmvc의 경우, SpringBoot 버전에 따라 차이가 있을 수 있다.
나는 3.X.X 버전을 사용하고 있어서 2.0.2 버전을 사용했다.

### 4. XML 매퍼 설정

XML 매퍼 파일은 SQL 쿼리를 정의하고 DAO 인터페이스와 연결하는 역할을 한다.

### 1. 네임스페이스 설정

```xml
<mapper namespace="com.example.project.model.dao.BookDao">
```

### 2. SQL 매핑

#### 단일 검색 (select)

```xml
<select id="search" parameterType="string" resultType="Book">
    SELECT isbn, title, author, price, describ, img
    FROM book
    WHERE isbn = #{isbn}
</select>
```

#### 전체 검색 (selectAll)

```xml
<select id="searchAll" parameterType="PageBean" resultType="Book">
    SELECT * FROM book
    <where>
        <include refid="searchCon"/>
    </where>
    ORDER BY isbn
    LIMIT #{start}, #{interval}
</select>
```

#### 데이터 개수 조회 (totalCount)

```xml
<select id="totalCount" parameterType="PageBean" resultType="int">
    SELECT COUNT(*) FROM book
    <where>
        <include refid="searchCon"/>
    </where>
</select>
```

#### 검색 조건 재사용 (sql 태그 활용)

```xml
<sql id="searchCon">
    <if test="key != 'all' and word != null and word != ''">
        <choose>
            <when test="key == 'isbn'">
                isbn LIKE CONCAT('%', #{word}, '%')
            </when>
            <when test="key == 'title'">
                title LIKE CONCAT('%', #{word}, '%')
            </when>
            <when test="key == 'author'">
                author LIKE CONCAT('%', #{word}, '%')
            </when>
        </choose>
    </if>
</sql>
```

#### 데이터 삽입 (insert)

```xml
<insert id="insert" parameterType="Book">
    INSERT INTO book (isbn, title, author, price, describ, img)
    VALUES (#{isbn}, #{title}, #{author}, #{price}, #{describ}, #{img})
</insert>
```

#### 데이터 수정 (update)

```xml
<update id="update" parameterType="Book">
    UPDATE book
    SET title = #{title}, author = #{author}, price = #{price},
        describ = #{describ}, img = #{img}
    WHERE isbn = #{isbn}
</update>
```

## 매핑 구문 작성 시 주의사항

### 1. 네임스페이스 및 ID 일치 필수

- `namespace="com.example.project.model.dao.BookDao"`
- `<select id="search">` → DAO 인터페이스의 메서드명과 일치해야 함
- 일치하지 않을 경우 실행 시 에러 발생

### 2. 컬럼명과 속성명 일치

- 컬럼명과 VO/DTO 속성명이 다를 경우 `alias`를 사용하여 매핑해야 함

---

매핑 구문
