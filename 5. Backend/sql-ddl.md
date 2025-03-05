# DDL (Data Definition Language) 문

데이터베이스 객체(테이블, 인덱스, 뷰 등)를 생성, 삭제, 변경하는 SQL문. 수행 즉시 DB에 반영됨.

### 테이블 생성 예제

```sql
CREATE TABLE orders (
    ono INT PRIMARY KEY AUTO_INCREMENT,
    odate DATETIME DEFAULT CURRENT_TIMESTAMP,
    id VARCHAR(30),
    gno INT NOT NULL,
    quantity INT,
    address VARCHAR(200),
    FOREIGN KEY (id) REFERENCES member(id),
    FOREIGN KEY (gno) REFERENCES goods(gno)
);
```

### 외래키 옵션

- **CASCADE** : 부모 데이터 삭제/수정 시 자식 데이터도 삭제/수정
- **SET NULL** : 부모 데이터 삭제/수정 시 자식 데이터 NULL 값으로 변경
- **NO ACTION** : 부모 데이터 변경 불가
- **SET DEFAULT** : 부모 데이터 삭제/수정 시 기본값으로 변경

### CHECK 제약조건 (MySQL 8.0.16 이상 지원)

```sql
CREATE TABLE s_emp (
    empno INT PRIMARY KEY,
    ename VARCHAR(30) NOT NULL,
    salary DECIMAL(11,2),
    commission_pct DECIMAL(4,2),
    CHECK (commission_pct IN (10, 12.5, 15, 17.5, 20)),
    CHECK (salary >= 1000)
);
```

### 테이블 변경

```sql
-- 컬럼 추가
ALTER TABLE s_emp ADD deptno INT;

-- 컬럼 타입 변경
ALTER TABLE s_emp MODIFY deptno VARCHAR(30);

-- 컬럼 이름 변경
ALTER TABLE s_emp CHANGE deptno address VARCHAR(200);

-- 컬럼 삭제
ALTER TABLE s_emp DROP address;
```

### 제약조건 추가 및 삭제

```sql
-- PRIMARY KEY 추가
ALTER TABLE emp3 ADD CONSTRAINT pk_emp3_empno PRIMARY KEY(empno);

-- FOREIGN KEY 추가
ALTER TABLE emp3 ADD CONSTRAINT fk_emp3_deptno FOREIGN KEY(deptno) REFERENCES dept(deptno);

-- 제약조건 삭제
ALTER TABLE emp3 DROP FOREIGN KEY fk_emp3_deptno;
ALTER TABLE emp3 DROP PRIMARY KEY;
```

### 테이블 삭제 및 데이터 초기화

```sql
-- 테이블 삭제
DROP TABLE members;
DROP TABLE emp3;

-- 모든 데이터 삭제 (ROLLBACK 불가)
TRUNCATE TABLE empcopy;
```
