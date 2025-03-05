# DML 문 (Data Manipulation Language)

DML 문은 데이터를 `INSERT`, `UPDATE`, `DELETE`하는 문장이며, 수행된 내용이 임시 저장소에 저장되어 실제 DB에는 반영되지 않음. DML 문을 수행한 후, `TCL (Transaction Control Language)`을 수행해야 함

## TCL (Transaction Control Language)

- `COMMIT` : 데이터베이스에 반영함
- `SAVEPOINT [이름]` : 임시 저장 위치를 지정함
- `ROLLBACK` : 수행한 모든 DML 문을 취소함
- `ROLLBACK TO [SAVEPOINT 이름]` : 지정한 savepoint까지 취소함
- `UPDATE`, `DELETE` 문을 수행하면 `COMMIT` 또는 `ROLLBACK`하기 전까지 해당 레코드는 `LOCK` 상태가 됨

## 트랜잭션 특징

- **원자성(Atomicity)** : 모든 작업이 성공하거나, 하나라도 실패하면 롤백함 (예: 은행 송금 시스템)

## 레코드 락 (Record Lock)

- 특정 row의 `INDEX`를 기준으로 Lock을 거는 것
- 트랜잭션에서 해당 row의 `INSERT`, `UPDATE`, `DELETE` 작업이 락이 걸린 동안 불가능함

## DML 문 예제

```sql
-- 데이터 삽입
INSERT INTO emp (empno, ename, sal) VALUES (3, 'kdg', 10000);

INSERT INTO goods (brand, price, maker, cno)
VALUES ('LG 모니터', 200000, 'LG전자', 20);

-- SAVEPOINT 설정
SAVEPOINT s1;

-- 데이터 수정
UPDATE goods SET price = 140000 WHERE gno = 9;

-- 특정 SAVEPOINT 이후 작업 취소
ROLLBACK TO s1;

-- 데이터 확인
SELECT * FROM goods;
SELECT * FROM emp;

-- 변경 사항 저장
COMMIT;
```

## DELETE 문

```sql
-- 전체 데이터 삭제
DELETE FROM emp;
SELECT * FROM emp;
ROLLBACK;

-- 조건에 맞는 데이터만 삭제
DELETE FROM 테이블명 WHERE 조건;
```

## UPDATE 문

```sql
-- 전체 데이터 수정
UPDATE emp SET sal = 10000;
SELECT * FROM emp;
ROLLBACK;

-- 조건에 맞는 데이터 수정
UPDATE goods SET price = 5000 WHERE gno = 2;
COMMIT;
```

## 테이블 복사

```sql
-- 구조만 복사
CREATE TABLE empcopy AS SELECT * FROM emp WHERE 1=0;

-- 데이터 + 구조 복사
CREATE TABLE empcopy AS SELECT * FROM emp;

SELECT * FROM empcopy;

-- TRUNCATE (ROLLBACK 불가능)
TRUNCATE empcopy;
ROLLBACK; -- 영향 없음
```

## 서브쿼리를 이용한 UPDATE

- `INSERT`, `UPDATE` 문에는 **스칼라 서브쿼리(단일 행, 단일 열 반환)**만 사용 가능함

```sql
-- MySQL 형식 예제
UPDATE 테이블명 SET 컬럼명 = (SELECT * FROM (스칼라 서브쿼리) AS alias);
```
