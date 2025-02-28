/**
테이블명 대소문자에 관한 설정 확인.
*
show variables where variable_name like '%low%';





/**
  select문 (Query문)
  - 데이타를 조회하기 위한 질의문
  - 형식] 
  4. select [distinct]   *|컬럼명 [as alias] or 간단한 연산 or 함수 호출
  1. from   테이블명 [alias],..						mySql		오라클, DB2, ms-sql  <= alias 
  2. [where  조건]								 	 x				x
  3. [group by 컬럼명, ..	[having 조건]]         	     o				x		
  5. [order by 컬럼명 [asc|desc]], ...]			 	 o				o
  // 실행 순서를 꼭 기억해두자~⭐️
*/

-- member 테이블의 모든행, 모든 열 정보를 조회
-- => where 절을 통한 조건을 지정하지 않으면 모든 행을 조회한다.
select * from members;

-- 특정 열을 조회: * 말고, 특정 컬럼 명을 쓰면 됨
-- member 테이블의 모든 회원의 아이디, 이름 , 이메일을 조회 
select id, name, email 
from members;

-- 전체 상품에 대해서 상품번호, 상품명, 가격, 10% 인상한 가격을 조회
-- select gno, brand, price, price * 1.1 from goods;
select gno, brand, price, price * 1.1 as IncPrice 
from goods;

-- where절을 이용해서 조건에 맞는 행만 추출     
-- 조건 연산자   같음 =, 다름 !=  <> , >, >=, <, <=
--            and   or
-- 가격이 5000원 이상이 되는 상품들의 상품번호, 상품명, 가격
select gno, brand, price 
from goods 
where price >= 5000;

-- id가 kdg가 아닌 회원들의 모든 정보 조회 
select *
from members
where id != "kdg";

-- null은 =가 아니라 is, is not으로 비교하자!!
-- is null     : null인 정보를 조회 
-- is not null : null이 아닌 정보를 조회 

-- 분류 번호가 없는 상품 정보 조회 
select * from goods
where cno is null;

-- 분류 번호가 있는 상품들의 모든 정보 조회 
select * from goods
where cno is not null;

-- distinct  중복 값 제거 
-- 상품이 있는 분류 번호 조회 
select distinct cno from goods;

-- 판매된적이 있는 상품 번호를 조회
select distinct gno from orders;

-- 가격이 5000원 이상 15000이하인 제품들의 모든 조회 
select *
from goods
where price >= 5000 and price <= 15000;

/*
* between: 범위 검사를 쉽게 해줌
* \\\작은값 이상 큰값 이하\\\의 데이타를 조회할 때 사용 
* 형식] 컬럼명 between 작은값 and 큰값
*/

-- 가격이 5000원 이상 15000이하인 제품들의 모든 조회 
select * from goods 
where price between 5000 and 15000;

-- 큰값 and 작은값  => 조회가 안됨 
-- select * from goods 
-- where price between 15000 and 5000;

-- 분류번호(cno)가 10번이거나 20번인 상품들의 상품번호, 이름, 가격, 분류 번호를 조회 
select gno, brand, price, cno
from goods
where cno = 10 or cno = 20;

/* in : 열거형 데이타로 조회할 때 
		주어진 데이타 중 하나라도 일치하면 조회 됨. 
   형식]   컬럼명  in (값, 값,...)

   not in 
        주어진 데이타와 모두 일치하지 않으면 조회 됨. 
*/

-- 분류번호가 10, 20번이 아닌 상품들을 조회 
select *
from goods
where cno not in (10, 20);

-- 제조사가 LG나 롯데가 아닌 상품들을 조회 
select * 
from goods
where maker not in ("LG", "롯데");

/*
  like  
  - 제공한 조건이 포함되어 있는 데이타를 조회 
  - 대체 문자 (와일드 카드)를 사용해야 한다.
  - 속도가 느리기 때문에 특별한 경우가 아니면 사용하지 말자.
    _ : 한문자를 대체 
    % : 문자 길이 상관 없음
    ex)  '김__'  : 성이 김씨이고 이름이 2자인 사람 조회 
		 '김%'   : 성이 김씨인 모든 사람 조회 
*/

-- 제조사에 LG이라는 글자가 들어간 모든 제품을 조회 
select * from goods
where maker like "%LG%";

-- 상품명에 마우스 글자가 들어간 제품들을 조회 
select * from goods 
where brand like "%마우스%";

/*
 case ~ when ~ then    : when에 지정된 조건이 참이면 then이 수행됨. 
 형식]
   case  
       when  조건  then 수행할 값, 연산식
       when ...
       else  주어진 조건이 모두 아닌 경우 수행될 값, 연산식
   end    
*/

/* 
상품번호, 상품명, 가격, 분류번호, 분류명을 조회 
단, 분류명은 
   10인 경우  음식
   20인 경우 전자제품
   30인 경우 책
   40인 경우 가구  
   분류번호가 없는 경우 미분류로 표시
*/
select gno, brand, price, cno,
	case cno
		when '10' then '음식'
        when '20' then '전자제품'
        when '30' then '책'
        when "40" then '가구'
        else "미분류"
	end as '분류명'
from goods;

/* 
   상품번호, 상품명, 인상전 가격, 인상후 가격을 조회 하시오.	
   가격 인상은 다음과 같다. 
   가격이 10000원 미만이면 15%을 가격을 인상하고 10000원 이상은 10% 가격을 인상하시오
*/
select gno, brand, price as "인상전 가격", 
    case 
        when price < 10000 then price * 1.15
        else price * 1.10
    end as "인상후 가격"
from goods;

/*
  order by : 정렬
    - 기본적으로 오름 차순으로 정렬 한다. 
    - asc : 오름 차순 (생략 가능)    desc : 내림 차순 
	- 형식 ] order by 컬럼명 [asc|desc]
*/

-- 가격을 기준으로 오름 차순 정렬
select *
from goods
order by price;		-- default가 오름차순 정렬이므로 asc 생략 가능.

-- 내림 차순
select *
from goods
order by price desc;		-- 내림차순 정렬은 생략 불가능.

-- 분류번호를 기준으로 내림 차순 정렬, 같은 분류내에서는 제품명을 기준으로 오름 차순 
select *
from goods
order by cno desc, brand;

-- select empno as employNo, ename as employName, sal as salary
-- from emp
-- where employNo > 7500;  -- employNo 는 alias로 정의했기 때문에 where 절로 사용하면 에러 발생한다!!


select empno as employNo, ename as employName, sal as salary
from emp
where empno > 7500
order by employNo;	-- order by 절에서는 컬럼 alias를 사용할 수 있다.