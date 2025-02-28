# SQL 내장 함수 정리

## 1. 수학 함수

- `floor(number)` : 주어진 숫자를 내림.
- `ceil(number)` : 주어진 숫자를 올림.
- `round(number, decimals)` : 주어진 숫자를 반올림.

### 사용 예시

```sql
select floor(3.1), ceil(3.1), round(3.1), round(3.5) from dual;
```

## 2. 문자열 함수

- `concat(string1, string2, ...)` : 여러 문자열을 연결.
- `left(string, length)` : 문자열의 왼쪽에서 지정한 개수만큼 추출.
- `right(string, length)` : 문자열의 오른쪽에서 지정한 개수만큼 추출.
- `substring(string, start, length)` 또는 `substr(string, start, length)` : 지정한 위치부터 지정한 개수만큼 추출.
- `repeat(string, count)` : 지정한 문자열을 주어진 횟수만큼 반복.
- `length(string)` : 문자열의 길이를 반환.
- `reverse(string)` : 문자열을 거꾸로 표시.
- `upper(string)` : 문자열을 대문자로 변환.
- `lower(string)` : 문자열을 소문자로 변환.
- `rtrim(string)` : 오른쪽의 공백을 삭제.
- `ltrim(string)` : 왼쪽의 공백을 삭제.
- `trim(string)` : 좌우의 공백을 삭제.

### 사용 예시

```sql
select concat(brand, ' 상품은 ', maker, ' 제조사에서 생산한 제품입니다.') from goods;

select brand, right(brand, 2), left(brand, 2), substring(brand, 1, 3), substr(brand, 1, 3) from goods;

select repeat('hello ', 5) from dual;

select email, length(email), reverse(email), upper(email), lower(email) from members;

select rtrim('    hello     '), ltrim('    hello     '), trim('    hello     ') from dual;
```

## 3. 논리 관련 함수

- `case when ~ then` : switch-case문과 유사한 조건문
- `if(condition, true_value, false_value)` : 조건식이 참이면 참일 때 값을 출력하고, 그렇지 않으면 거짓일 때 값을 출력.

### 사용 예시

```sql
select gno, brand, price,
    case
        when price < 10000 then price * 1.15
        when price >= 10000 then price * 1.1
    end as INC_price
from goods
order by INC_price asc;

select gno, brand , price,
    if(price >= 30000, price * 1.15, price * 1.1) as IncPrice
from goods;
```

## 4. 날짜 관련 함수

- `curdate()`: 현재 날짜를 반환.
- `curtime()`: 현재 시간을 반환.
- `now()`: 현재 날짜와 시간을 반환.
- `sysdate()`: 현재 날짜와 시간을 반환 (DB 서버 시간).
- `current_timestamp()`: 현재 타임스탬프를 반환.
- `DATE_ADD(date, INTERVAL value unit)`: 지정된 날짜에 시간이 더해짐.
- `DATE_SUB(date, INTERVAL value unit)`: 지정된 날짜에서 시간이 빼짐.
- `DAYOFWEEK(date)`: 날짜의 주별 일자를 반환 (일요일(1) ~ 토요일(7)).
- `WEEKDAY(date)`: 월요일(0) ~ 일요일(6)로 요일을 숫자로 반환.
- `DAYOFYEAR(date)`: 1월 1일부터 해당 날짜까지의 날수를 반환.
- `YEAR(date)`, `MONTH(date)`, `DAYOFMONTH(date)`, `HOUR(date)`, `MINUTE(date)`, `SECOND(date)` : 각각 년, 월, 일, 시, 분, 초를 반환.
- `MONTHNAME(date)` : 해당 날짜의 월 이름을 반환.
- `DAYNAME(date)` : 해당 날짜의 요일 이름을 반환.
- `QUARTER(date)` : 해당 날짜의 분기를 반환.
- `date_format(date, format)` : 날짜 형식 지정.

### 사용 예시

```sql
select curdate(), curtime(), now(), sysdate(), current_timestamp() from dual;

select date_sub(now(), interval 1 day) from dual; -- 어제
select date_add(now(), interval 1 day) from dual; -- 내일

select dayofweek(now()) from dual;

select year(sysdate()), month(sysdate()), dayofmonth(sysdate()), hour(sysdate()), minute(sysdate()), second(sysdate()) from dual;

select date_format(sysdate(), '%y년 %m월 %d일 - %p %h시 %i분 %s초') as today from dual;
```
