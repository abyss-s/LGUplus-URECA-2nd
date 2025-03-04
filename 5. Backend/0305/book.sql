
drop table if exists  book cascade;
CREATE TABLE book (
  isbn		char(12) NOT NULL,					-- 책 번호
  title		varchar(100) NOT NULL,				-- 책 제목
  author	varchar(50) NOT NULL,				-- 저자
  price 	int NOT NULL,						-- 가격
  describ 	text,								-- 책 설명
  img 		varchar(100) DEFAULT NULL,			-- 이미지
  PRIMARY KEY (isbn)
);

insert into book (isbn,title,author,price,describ,img)
values
('979-11','스프링 부트 3 백엔드 개발자 되기','신선영',32000,'자바 백엔드 개발자가 되고 싶다면',''),
('979-11-57','모던 자바스크립트 Deep Dive','이웅모',45000,'자바스크립트의 기본 개념과 동작 원리',''),
('979-11-91','코딩 테스트 합격자 되기','김희성',42000,'빈출 문제로 철저하게 대비하세요',''),
('979-11-5664','디자인 시스템 실무','이영주',26000,'UI/UX 디자인 툴 피그마로 구현한다.',''),
('979-11-1','git','동글양',32000,'실전 프로젝트와 동일한 github','');
commit;