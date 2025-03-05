## SubQuery

### 1. 서브쿼리란?

- Query문 내에 작성하는 Query
- 기본적으로 외부 Query가 수행되기 전에 서브쿼리가 먼저 수행되고 그 결과를 외부 Query에서 사용한다.
- 단, 상호 연관 쿼리는 외부 Query의 한 행에 대해 서브쿼리가 수행되므로, 서브쿼리는 외부 Query의 행 수만큼 수행된다.

### 2. 서브쿼리의 종류

- **WHERE 절에서 사용**: 단일행, 다중행, 다중열, 상호 연관 서브쿼리
- **FROM 절에서 사용**: 인라인 뷰(Inline View)
- **SELECT 절에서 사용**: 스칼라 서브쿼리 (단일행, 단일열)

### 3. 서브쿼리가 사용되는 위치

- `SELECT` 절, `FROM` 절, `WHERE` 절, `HAVING` 절, `ORDER BY` 절
  - **(단, `GROUP BY` 절 제외)**
- `CREATE TABLE` 문, `INSERT` 문, `UPDATE` 문, `DELETE` 문

### 4. 서브쿼리 작성 규칙

- 서브쿼리는 반드시 `()` 괄호 내에 작성해야 한다.

## 서브쿼리 활용 예제

### 1. SELECT 절에서의 서브쿼리 (스칼라 서브쿼리)

#### - OUTER JOIN 한 것과 같은 효과

```sql
SELECT empno, ename, sal, deptno,
       (SELECT dname FROM dept d WHERE e.deptno = d.deptno) AS dname,
       (SELECT loc FROM dept d WHERE e.deptno = d.deptno) AS loc
FROM emp e;
```

### 2. WHERE 절에서의 서브쿼리

#### - 서브쿼리의 결과를 WHERE 절에서 비교 데이터로 사용

#### - 단일행 서브쿼리 예제

```sql
-- 사원번호가 7369인 사원의 업무와 같은 업무를 하는 사원 조회
SELECT * FROM emp
WHERE job = (SELECT job FROM emp WHERE empno = 7369);
```

```sql
-- 평균 급여보다 많이 받는 사원의 정보 조회
SELECT * FROM emp
WHERE sal > (SELECT AVG(sal) FROM emp);
```

```sql
-- 'MANAGER'인 사원들 중 평균 급여보다 많이 받는 사원 조회
SELECT empno, job, sal
FROM emp
WHERE job = 'MANAGER' AND sal > (SELECT AVG(sal) FROM emp);
```

#### - 다중행 서브쿼리 예제

```sql
-- 부서 번호 10번에 근무하는 사원들과 같은 급여를 받는 사원 조회
SELECT * FROM emp
WHERE sal IN (SELECT sal FROM emp WHERE deptno = 10);
```

```sql
-- 'SCOTT', 'CLARK'가 관리하는 사원의 모든 정보 조회
SELECT * FROM emp
WHERE mgr IN (SELECT empno FROM emp WHERE ename IN ('SCOTT', 'CLARK'));
```

```sql
-- 각 부서의 평균 급여보다 많이 받는 사원의 정보 조회
SELECT * FROM emp
WHERE sal > ALL (SELECT AVG(sal) FROM emp GROUP BY deptno);
```

#### - 다중열 서브쿼리 예제

```sql
-- ADAMS 사원의 업무와 부서가 같은 사원의 정보 조회
SELECT * FROM emp
WHERE (job, deptno) = (SELECT job, deptno FROM emp WHERE ename = 'ADAMS');
```

### 3. FROM 절에서의 서브쿼리 (Inline View)

```sql
-- 소속된 부서의 평균 급여보다 많이 받는 사원의 정보 조회
SELECT * FROM emp e
WHERE sal > (SELECT AVG(sal) FROM emp a WHERE e.deptno = a.deptno);
```

```sql
-- 부서별 최저 급여를 받는 사원 정보 조회
SELECT * FROM emp
JOIN (SELECT deptno, MIN(sal) AS minSal FROM emp GROUP BY deptno) j
USING (deptno)
WHERE sal = minSal;
```

### 4. 특정 조건을 만족하는 데이터 조회

```sql
-- 판매된 적이 있는 상품 조회
SELECT * FROM goods
WHERE gno IN (SELECT DISTINCT gno FROM orders);
```

```sql
-- 판매된 적이 없는 상품 조회
SELECT * FROM goods
WHERE gno NOT IN (SELECT DISTINCT gno FROM orders);
```

### 5. N-Top 조회

```sql
-- 급여가 높은 상위 5명의 사원 조회 (MySQL 기준)
SELECT * FROM emp
ORDER BY sal DESC
LIMIT 0, 5;
```

```sql
-- 급여가 높은 사원 중 6~10위 조회 (Oracle 기준)
SELECT *
FROM (
    SELECT ROWNUM ro, a.*
    FROM (
        SELECT empno, ename, sal FROM emp ORDER BY sal DESC
    ) a
)
WHERE ro BETWEEN 6 AND 10;
```
