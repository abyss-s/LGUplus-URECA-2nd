drop table if exists  boardfile cascade;
drop table if exists  board 	cascade;
drop table if exists  orders 	cascade;
drop table if exists  goods 	cascade;
drop table if exists  category 	cascade;
drop table if exists  members 	cascade;

-- 회원 정보

create table members(
	 id 		varchar(30) primary key    
	,password	varchar(30) not null
    ,name		varchar(30) not null
    ,email		varchar(50) 
    ,address	varchar(200)
    ,phone		varchar(15)
);


insert into members(id, password, name, email, address, phone)
values
('jaen','1111','자앤','jaen@andoridjava.com','서울시 서초구','02'),
('eureka','1111','LG','eureka@uplus.com','서울시 강남구','02'),
('mulcam','1111','멀캠','mulcam@multicampus.com','서울시 강남구','02'),
('kdg','1111','김동근','kdg@andoridjava.com','서울시 성북구','010');

-- 상품 종류를 구분하기 위한 테이블
create table category(
  cno  char(2) primary key,		-- 번호
  name varchar(50)				-- 상품 종류
);

-- category data ----------------------------- 
insert into category (cno, name) values
('10','음식'),
('20','전자제품'),
('30','책'),
('40','서적');

-- 상품 정보
create table goods(
gno   int auto_increment primary key,		-- 상품 번호
brand varchar(100) not null,				-- 상품 명
price int          default 0,				-- 금액
maker varchar(50),							-- 제조사
image varchar(50),							-- 상품 이미지 정보
info  varchar(300),							-- 상품 설명
cno		char(2),							-- 상품 종류 번호
foreign key fk_goods_cno(cno) references category(cno)
);

-- goods data ----------------------------- 
insert into goods (brand, price, maker, image, info, cno) values
('목캔디', 1200,'롯데', 'throatCandy.png','목아플때 먹는','10'),
('마우스', 9700,'LG', 'lgMouse.png','컴퓨터 입력장치','20'),
('태양의 마테차', 1700,'코카콜라', 'sun.png','다이어트할 때 좋아요','10'),
('그렘', 1500000,'LG','','가볍고 좋아요','20'),
('이것이 자바다', 30000,'한빛미디어','java.png','',null),
('오후에 마시는 마테차', 9000,'동글실업','afternoonTea.png','', 10),
('usb 마우스', 12000,'LG','usbmouse.png','', 20),
('사무용 데스크', 85000,'PATRA','par_desk.png','', 40);



-- 주문 정보
create table orders(
	 ono 		int primary key auto_increment				-- 주문 번호
    ,odate		datetime  default current_timestamp			-- 주문 일
    ,id  		varchar(30) 								-- 주문자
    ,gno		int	not null								-- 주문한 상품 번호
    ,quantity	int											-- 주문한 수량
    ,address	varchar(200)								-- 배송지
    ,foreign key fk_orders_id(id)	references members(id)	
    ,foreign key fk_orders_gno(gno)	references goods(gno)
);


insert into orders (odate, id, gno, quantity, address) values
( DATE_SUB(SYSDATE(), INTERVAL 13 MONTH), 'eureka', 2 , 1 ,'나주'),
( DATE_SUB(SYSDATE(), INTERVAL 13 MONTH), 'jaen', 4 , 2 ,'서울'),
( DATE_SUB(SYSDATE(), INTERVAL 13 MONTH), 'mulcam', 1 , 10 ,'서울'),
( DATE_SUB(SYSDATE(), INTERVAL 12 MONTH), 'eureka', 4 , 2 ,'서울'),
( DATE_SUB(SYSDATE(), INTERVAL 12 MONTH), 'eureka', 1 , 20 ,'서울'),
( DATE_SUB(SYSDATE(), INTERVAL 11 MONTH), 'jaen', 2 , 20 ,'서울'),
( DATE_SUB(SYSDATE(), INTERVAL 11 MONTH), 'eureka', 3 , 100 ,'나주'),
( DATE_SUB(SYSDATE(), INTERVAL 10 MONTH), 'jaen', 4 , 10 ,'나주'),
( DATE_SUB(SYSDATE(), INTERVAL 10 MONTH), 'jaen', 4 , 10 ,'서울'),
( DATE_SUB(SYSDATE(), INTERVAL 3 MONTH ), 'eureka', 1 , 40 ,'서울'),
( DATE_SUB(SYSDATE(), INTERVAL 2 MONTH ), 'jaen', 3 , 2 ,'서울'),
( DATE_SUB(SYSDATE(), INTERVAL 2 MONTH), 'jaen', 2 , 2 ,'서울'),
( DATE_SUB(SYSDATE(), INTERVAL 1 MONTH), 'eureka', 1 , 1 ,'서울'),
( DATE_SUB(SYSDATE(), INTERVAL 1 MONTH), 'eureka', 2 , 2 ,'서울'),
( DATE_SUB(SYSDATE(), INTERVAL 3 day ), 'eureka', 1 , 40 ,'서울'),
( DATE_SUB(SYSDATE(), INTERVAL 3 day ), 'mulcam', 1 , 5 ,'서울'),
( DATE_SUB(SYSDATE(), INTERVAL 3 day ), 'eureka', 1 , 2 ,'서울'),
( DATE_SUB(SYSDATE(), INTERVAL 2 day ), 'jaen', 4 , 2 ,'서울'),
( DATE_SUB(SYSDATE(), INTERVAL 2 day), 'jaen', 2 , 2 ,'서울'),
( DATE_SUB(SYSDATE(), INTERVAL 1 day), 'eureka', 1 , 1 ,'서울'),
( DATE_SUB(SYSDATE(), INTERVAL 1 day), 'eureka', 2 , 2 ,'서울'),
( DATE_SUB(SYSDATE(), INTERVAL 1 day), 'kdg', 1 , 5 ,'서울'),
( DATE_SUB(SYSDATE(), INTERVAL 1 day), 'mulcam', 3 , 5 ,'서울'),
( sysdate(), 'jaen', 1 , 1 ,'서울'),
( sysdate(), 'eureka', 1 , 20 ,'서울'),
( sysdate(), 'eureka',2 , 2 ,'서울'),
( sysdate(), 'kdg',  3 , 5 ,'서울'),
( sysdate(), 'mulcam',  1 , 5 ,'서울');


-- 게시판 (board)
create table board(
  no        int  primary key,					-- 게시물 번호
  id        varchar(30),						-- 작성자 id
  title     varchar(200),						-- 제목
  regdate   datetime default CURRENT_TIMESTAMP,	-- 작성일
  contents  varchar(3000),						-- 내용
  foreign key fk_board_id (id) references members(id)
);

-- 게시물에 올린 파일 정보(boardfile)
create table boardfile(
  no       		int auto_increment primary key,	-- 파일 번호
  rfilename		varchar(100),					-- 사용자가 올린 파일 이름
  sfilename     varchar(100),					-- 시스템에 저장된 파일 이름
  bno           int,							-- 게시물 번호
  foreign key fk_boardfile_bno (bno) references board(no)   on delete cascade
);



-- board data ----------------------------- 
insert into board(no, id, title, regdate, contents) values
(1,'jaen','hello', now(), '처음하는 게시판'),
(2, 'jaen','hello', sysdate(), '처음하는 게시판'),
(3, 'eureka','mysql이란?', sysdate(), '데이타 베이스의 한 종류이면서 공짜야'),
(4, 'eureka','hello world', sysdate(), '잘되나?');

-- boardfile data ----------------------------- 
insert into boardfile(rfilename, sfilename, bno)
values('a.txt','b.txt',1);