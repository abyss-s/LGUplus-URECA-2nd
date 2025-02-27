
CREATE TABLE `book` (
  `isbn` char(12) NOT NULL,						-- 책 번호
  `title` varchar(100) NOT NULL,				-- 책 제목
  `author` varchar(50) NOT NULL,				-- 저자
  `price` int NOT NULL,							-- 가격
  `describ` text,								-- 책 설명
  `img` varchar(100) DEFAULT NULL,				-- 이미지
  PRIMARY KEY (`isbn`)
);


-- 사용자 정보
CREATE TABLE `userinfo` (						
  `id` varchar(50),								-- 아이디
  `name` varchar(45) NOT NULL,					-- 이름
  `pass` varchar(100) NOT NULL,					-- 비번
  PRIMARY KEY (`id`)
);

insert into userinfo (id, name, pass) values('eureka','유레카','1234');

select * from user;
commit;