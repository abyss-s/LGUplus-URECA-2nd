/*
  DDL문 (Data Definition Language)
  - Database에서 사용하는 다양한 객체를 생성, 삭제, 변경하는 문
  - 수행하면 DB에 바로 반영됨
  - 객체 종류 : table, index, view,
  - 생성    : create 객체  객체명;
    ex) create sequence board_no;
  - 삭제    : drop  객체  객체명;
    ex) drop sequence board_no;
*/

/* 테이블 
   - 데이타를 관리할 최소 단위 
   - 생성 
	 create table 테이블명(
        컬럼명   데이타타입   [[CONSTRAINT 제약조건이름] 컬럼레벨 제약조건]
        , ...
        ,[[CONSTRAINT 제약조건이름] 테이블레벨 제약조건] 
     );


데이타 무결성 :  데이타의 정확성, 일관성, 유효성이  유지되는 것을 말한다. 
 1) 개체 무결성 (Entity integrity)  
	모든 테이블이 기본 키 (primary key)로 선택된 필드 (column)를 가져야 한다. 
	기본 키로 선택된 필드는 고유한 값을 가져야 하며, 빈 값은 허용하지 않는다.
	
2) 참조 무결성 (Referential integrity) (FK)
  관계형 데이터베이스 모델에서 참조 무결성은 참조 관계에 있는 두 테이블의 데이터가 항상 일관된 값을 갖도록 유지되는 것을 말한다
  
**/

/* 주문 테이블 
 ono 		: 주문번호			primairy key
 odate 		: 주문일
 id			: 주문한 고객 아이디	foreign key
 gno		: 상품 번호 		foreign key
 quantity	: 주문한 수량 
 address	: 배송지
*/

-- 날짜 기본값 설정  5.6.5 미만은 now()
-- 날짜 기본값 설정  5.6.5 이상은 current_timestamp
create table orders(
     ono 		int primary key auto_increment
    ,odate		datetime  default current_timestamp
    ,id  		varchar(30) 
    ,gno		int	not null
    ,quantity	int
    ,address	varchar(200)
    ,foreign key fk_orders_id(id)	references member(id)
    ,foreign key fk_orders_gno(gno)	references goods(gno)
);

/*

table level
[constraint]  제약조건 제약조건명


외래키 
[constraint]  foreign key  제약조건명(컬럼명)  reference 참조할테이블명(참조할컬럼명)  [option]

option 
  - on delete : 참조 되는 테이블(부모 테이블)의 값이 삭제 될 경우 동작
  - on update : 참조 되는 테이블(부모 테이블)의 값이 변경 될 경우 동작
  
  동작  
  casecade   : 부모 테이블의 참조하는 데이타가 삭제되거나 수정되면 같이 삭제되거나 수정됨
  set null 	 : 부모 테이블의 참조하는 데이타가 삭제되거나 수정되면 null값으로 변경됨.
  no action  : 부모 테이블의 참조하는 데이타가 삭제되거나 수정되면 데이타는 변경되지 않는다. 
  set default: 부모 테이블의 참조하는 데이타가 삭제되거나 수정되면 데이타는 default 값으로 변경됨.
  
check 
 - 데이타의 도메인 무결성을 위해 check 제약 조건을 지정할 수 있다. 
 - mysql은 8.0.16버전 부터 check 제약 조건을 사용할 수 있다. 
 형식] 
 [contraint] check (조건)
*/


-- 8.0.16버전 부터 check 제약조건 됨. 


-- 테이블에 등록된 key 정보 확인
show indexes in s_emp;

-- 테이블 스키마 확인 
show create table s_emp;

/* 테이블 변경 
   alter table 테이블명( add | modify , change, drop) 컬럼이름
*/

-- s_emp 테이블에 deptno 추가 


desc s_emp;

-- 컬럼 타입 변경 


-- 컬럼 이름 변경   alter table 테이블이름 change  이전컬럼이름  변경할_컬럼이름 변경할_타입;


-- 컬럼 삭제  alter table 테이블이름 drop 칼럼이름 


-- 테이블 복사 -  데이타와 구조만 카피되고  not null 외의 제약 조건은 카피되지 않는다. 

-- 제약 조건 추가 
-- alter table 테이블명 add constraint 이름 제약조건


-- 제약 조건 삭제
-- alter table 테이블명 drop  제약조건  이름


-- insert, update 시 날짜 자동 수정   
-- 주의점) 반드시    `컬럼명` `컬럼명` 으로 컬럼명을 두번 써야 ON에서  error 안남


/*
테이블 삭제
DROP [TEMPORARY] TABLE [IF EXISTS]
     tbl_name [, tbl_name] ...
     [RESTRICT | CASCADE]
주의점 : 부모테이블인 경우 삭제가 안됨.   => 자식 테이블 삭제후 부모 테이블을 삭제해야 한다.

RESTRICT | CASCADE  옵션은 아무 일도 하지 않지만 다른 디비와의 이식성을 위해 제공됨. 
ex)
  member(부모), goods(부모), orders(자식)
  orders 삭제 => member or goods 삭제
*/


/*
truncate
- 테이블의 모든 데이타를 삭제
- 복구 할 수 없다. 
 */
truncate 테이블명; 

-- 테이블의 구조와 데이터 복제


select * from emp2;
-- 테이블의 데이타만 삭제
 
