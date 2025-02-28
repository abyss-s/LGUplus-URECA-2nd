# SQL SELECT 문 정리

## 1. 기본 SELECT 문

### 형식

```sql
SELECT [DISTINCT] * | 컬럼명 [AS alias] | 간단한 연산 | 함수 호출
FROM 테이블명 [AS alias], ...
[WHERE 조건]
[GROUP BY 컬럼명, ... [HAVING 조건]]
[ORDER BY 컬럼명 [ASC | DESC], ...]
```

### 사용 예시

```sql
-- 모든 행, 모든 열 조회
SELECT * FROM members;

-- 특정 열 조회
SELECT id, name, email FROM members;

-- 상품에 대한 정보 조회
SELECT gno, brand, price, price * 1.1 AS IncPrice FROM goods;
```

## 2. WHERE 절

- **조건 연산자**: `=` (같음), `!=`, `<>` (다름), `>`, `>=`, `<`, `<=`
- **AND / OR**: 복수 조건 결합
- **IS NULL / IS NOT NULL**: NULL 값 비교

### 사용 예시

```sql
-- 조건을 통한 행 추출
SELECT gno, brand, price FROM goods WHERE price >= 5000;

-- 특정 ID 제외
SELECT * FROM members WHERE id != 'kdg';

-- NULL 값 조회
SELECT * FROM goods WHERE cno IS NULL;
SELECT * FROM goods WHERE cno IS NOT NULL;
```

## 3. DISTINCT와 중복 제거

- **DISTINCT**: 중복된 값을 제거하여 조회

### 사용 예시

```sql
-- 중복 없는 분류 번호 조회
SELECT DISTINCT cno FROM goods;
```

## 4. BETWEEN과 IN 연산자

- **BETWEEN**: 범위 조건 조회
- **IN**: 열거형 데이터 조회

### 사용 예시

```sql
-- 가격 범위 조회
SELECT * FROM goods WHERE price BETWEEN 5000 AND 15000;

-- 특정 분류 번호 제외
SELECT * FROM goods WHERE cno NOT IN (10, 20);
```

## 5. LIKE 연산자

- **LIKE**: 특정 문자열 패턴 포함 조회
- **와일드카드**:
  - `_`: 한 문자를 대체
  - `%`: 문자열 길이 상관없음

### 사용 예시

```sql
-- 특정 제조사가 포함된 상품 조회
SELECT * FROM goods WHERE maker LIKE '%LG%';
SELECT * FROM goods WHERE brand LIKE '%마우스%';
```

## 6. CASE 문

- **CASE**: 조건에 따른 값 반환

### 사용 예시

```sql
SELECT gno, brand, price, cno,
    CASE cno
        WHEN '10' THEN '음식'
        WHEN '20' THEN '전자제품'
        WHEN '30' THEN '책'
        WHEN '40' THEN '가구'
        ELSE '미분류'
    END AS 분류명
FROM goods;
```

## 7. ORDER BY 절

- **ORDER BY**: 정렬
- **ASC**: 오름 차순 (생략 가능)
- **DESC**: 내림 차순

### 사용 예시

```sql
-- 가격 오름 차순 정렬
SELECT * FROM goods ORDER BY price;

-- 가격 내림 차순 정렬
SELECT * FROM goods ORDER BY price DESC;

-- 복수 기준 정렬
SELECT * FROM goods ORDER BY cno DESC, brand;
```

## 8. Alias 사용

- **Alias**: SQL 문에서 쉽게 참조하기 위해 컬럼에 별칭을 정의

### 사용 예시

```sql
SELECT empno AS employNo, ename AS employName, sal AS salary
FROM emp
WHERE empno > 7500
ORDER BY employNo; -- ORDER BY 절에서는 alias 사용 가능
```

## SQL 쿼리 실행 순서

1. **FROM**: 데이터 소스를 정의합니다. 테이블과 조인 조건이 결정됩니다.

2. **WHERE**: 데이터를 필터링합니다. `FROM` 절에서 정의된 데이터 중 조건을 만족하는 행만 선택됩니다.

3. **GROUP BY**: 데이터를 그룹화합니다. 같은 값을 가진 행들을 묶어줍니다.

4. **HAVING**: 그룹화된 데이터에 대해 조건을 적용합니다. `GROUP BY`로 묶인 데이터에서 조건을 만족하는 그룹만 선택됩니다.

5. **SELECT**: 최종적으로 반환할 열을 지정합니다. 필요한 열, 별칭, 함수 등을 정의합니다.

6. **ORDER BY**: 정렬 기준에 따라 결과를 오름차순 또는 내림차순으로 정렬합니다.

### 예시

```sql
SELECT brand, COUNT(*) AS product_count
FROM goods
WHERE price > 5000
GROUP BY brand
HAVING COUNT(*) > 1
ORDER BY product_count DESC;
```

### 수행 순서:

1. **FROM goods**: `goods` 테이블에서 데이터를 선택.
2. **WHERE price > 5000**: 가격이 5000보다 큰 행만 필터링.
3. **GROUP BY brand**: 브랜드별로 데이터를 그룹화.
4. **HAVING COUNT(\*) > 1**: 그룹화된 결과 중 상품 수가 1보다 큰 그룹만 선택.
5. **SELECT brand, COUNT(\*) AS product_count**: 필요한 열과 계산된 열을 선택.
6. **ORDER BY product_count DESC**: `product_count`를 기준으로 내림차순 정렬.
