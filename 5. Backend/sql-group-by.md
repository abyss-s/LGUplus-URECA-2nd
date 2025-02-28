# SQL GROUP BY 문

## 1. 집계 함수 (Aggregation Functions)

- **정의**: 집계 함수는 데이터 그룹에 대해 집계된 결과를 반환합니다.
- **주요 집계 함수**:
  - `COUNT(*)`: 전체 행 수를 계산.
  - `COUNT(column)`: NULL이 아닌 행 수를 계산.
  - `SUM(column)`: 합계를 계산.
  - `AVG(column)`: 평균을 계산.
  - `MIN(column)`: 최소값을 찾음.
  - `MAX(column)`: 최대값을 찾음.

### 사용 예시

```sql
-- 전체 직원 수 조회
SELECT COUNT(*) FROM emp;

-- comm이 있는 사원 수 조회
SELECT COUNT(comm) FROM emp;

-- 급여 관련 통계 조회
SELECT MIN(sal), MAX(sal), AVG(sal) FROM emp;
```

## 2. GROUP BY 절

- **정의**: 데이터를 특정 열을 기준으로 그룹화하여 집계 작업을 수행합니다.
- **쿼리 구조**:
  ```sql
  SELECT 컬럼, 집계함수
  FROM 테이블
  GROUP BY 컬럼
  [ORDER BY 컬럼];
  ```

### 사용 예시

```sql
-- 부서별 인원수와 평균 급여 조회
SELECT deptno, COUNT(*) AS 인원수, AVG(sal) AS 평균급여
FROM emp
GROUP BY deptno
ORDER BY deptno;

-- 업무별 인원수와 평균 급여
SELECT job, COUNT(*) AS 인원, ROUND(AVG(sal), 2) AS 평균급여
FROM emp
GROUP BY job
ORDER BY job;
```

## 3. HAVING 절

- **정의**: GROUP BY로 그룹화된 결과에 조건을 적용합니다.
- **쿼리 구조**:
  ```sql
  SELECT 집계함수
  FROM 테이블
  GROUP BY 열
  HAVING 조건;
  ```

### 사용 예시

```sql
-- 부서별 평균 급여가 2000 이상인 부서 조회
SELECT deptno, AVG(sal) AS avgSal
FROM emp
GROUP BY deptno
HAVING avgSal >= 2000;
```

## 4. ROLLUP

- **정의**: 그룹별 통계와 전체 통계를 추가로 조회합니다. `GROUP BY` 결과에 전체 합계를 추가합니다.
- **쿼리 구조**:
  ```sql
  SELECT 컬럼, 집계함수
  FROM 테이블
  GROUP BY 컬럼 WITH ROLLUP;
  ```

### 사용 예시

```sql
-- 업무별 근무 인원, 평균 급여 통계 조회
SELECT IFNULL(job, '전체') AS job, COUNT(*) AS 근무인원,
       ROUND(AVG(sal), 2) AS avgSal, MIN(sal), MAX(sal)
FROM emp
GROUP BY job WITH ROLLUP;
```

## 5. GROUPING 함수

- **정의**: ROLLUP에 의해 생성된 그룹을 식별합니다. 결과가 집계된 행인지 여부를 확인합니다.
- **쿼리 예시**:

```sql
SELECT IF(GROUPING(job) = 1, 'total', IFNULL(job, '프리랜서')) AS job,
       COUNT(*) AS 근무인원, ROUND(AVG(sal), 2) AS avgSal, MIN(sal), MAX(sal)
FROM emp
GROUP BY job WITH ROLLUP;
```

## 6. 주의사항

- GROUP BY를 사용할 때, SELECT 문의 나머지 열은 GROUP BY에 포함되거나 집계 함수로 감싸져야 합니다.
- MySQL에서는 `ONLY_FULL_GROUP_BY` 설정이 기본으로 활성화되지 않아서, GROUP BY와 관련하여 다른 열을 같이 사용하는 경우 의미 없는 결과가 나올 수 있습니다.

### 설정 예시

```sql
SET @@session.sql_mode = 'STRICT_TRANS_TABLES, NO_ENGINE_SUBSTITUTION, ONLY_FULL_GROUP_BY';
```
