# SQL JOIN 문

## 1. JOIN 정의

- **JOIN**: 두 개 이상의 테이블을 결합하여 데이터를 조회하는 방법입니다.

### 종류

1. **Inner Join**: 조인 조건에 맞는 데이터만 조회.
2. **Outer Join**: 조인 조건에 맞지 않는 데이터도 포함하여 조회.
   - **Left Outer Join**: 왼쪽 테이블의 모든 데이터와 조인 조건을 만족하는 오른쪽 테이블의 데이터 조회.
   - **Right Outer Join**: 오른쪽 테이블의 모든 데이터와 조인 조건을 만족하는 왼쪽 테이블의 데이터 조회.
   - **Full Outer Join**: 두 테이블의 모든 데이터를 포함하여 조회. (일반적으로 사용되지 않음)
3. **Self Join**: 동일한 테이블 내에서 조인.
4. **Natural Join**: 동일한 열 이름을 가진 컬럼을 기준으로 자동으로 조인.
5. **Cross Join (Cartesian Product)**: 조인 조건을 생략하면 두 테이블의 모든 조합을 조회.

## 2. 카티시안 곱 (Cross Join)

- **정의**: 조인 조건을 생략했을 때 두 테이블의 모든 행을 연결한 결과를 조회.

### 사용 예시

```sql
SELECT gno, brand, price, goods.cno AS cno, category.cno AS 분류번호, name
FROM goods, category;
```

## 3. Inner Join

- **형식**:
  ```sql
  SELECT *
  FROM 테이블명1
  JOIN 테이블명2 ON 조건;
  ```

### 사용 예시

```sql
-- Inner Join 예시
SELECT gno, brand, price, name
FROM goods INNER JOIN category ON goods.cno = category.cno
ORDER BY gno;
```

## 4. Outer Join

### 4.1 Left Outer Join

- **정의**: 왼쪽 테이블의 모든 행과, 조인 조건을 만족하는 오른쪽 테이블의 행을 조회. 오른쪽 테이블에 조인 조건을 만족하는 행이 없으면, NULL이 반환됩니다.

#### 형식

```sql
SELECT 컬럼명1, 컬럼명2, ...
FROM 왼쪽_테이블 LEFT JOIN 오른쪽_테이블
ON 조인_조건;
```

#### 사용 예시

```sql
SELECT e.empno, e.ename, d.dname
FROM emp e
LEFT JOIN dept d ON e.deptno = d.deptno;
```

### 4.2 Right Outer Join

- **정의**: 오른쪽 테이블의 모든 행과 조인 조건을 만족하는 왼쪽 테이블의 행을 조회. 왼쪽 테이블에 조인 조건을 만족하는 행이 없으면, NULL이 반환됩니다.

#### 형식

```sql
SELECT 컬럼명1, 컬럼명2, ...
FROM 왼쪽_테이블 RIGHT JOIN 오른쪽_테이블
ON 조인_조건;
```

#### 사용 예시

```sql
SELECT d.dname, e.ename
FROM dept d
RIGHT JOIN emp e ON d.deptno = e.deptno;
```

### 4.3 Full Outer Join

- **정의**: 두 테이블에 대한 모든 행을 조회하고, 조인 조건을 만족하지 않는 행은 NULL로 표시됩니다.

#### 형식

```sql
SELECT 컬럼명1, 컬럼명2, ...
FROM 왼쪽_테이블 FULL OUTER JOIN 오른쪽_테이블
ON 조인_조건;
```

#### 사용 예시

```sql
SELECT e.empno, e.ename, d.dname
FROM emp e
FULL OUTER JOIN dept d ON e.deptno = d.deptno;
```

## 5. Self Join

- **정의**: 동일한 테이블을 조인하여 상사와 직원 관계를 나타낼 때 사용.

### 사용 예시

```sql
SELECT e.empno, e.ename, e.job, e.sal, e.mgr, m.ename
FROM emp e
JOIN emp m ON e.mgr = m.empno;
```

## 6. Natural Join

- **정의**: 동일한 컬럼명을 가진 컬럼을 기준으로 자동으로 조인합니다.

### 사용 예시

```sql
SELECT empno, ename, deptno, dname
FROM emp NATURAL JOIN dept;
```
