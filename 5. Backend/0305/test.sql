use test;
drop table if exists  boardfile cascade;
drop table if exists  board 	cascade;
drop table if exists  orders 	cascade;
drop table if exists  goods 	cascade;
drop table if exists  category 	cascade;
drop table if exists  members 	cascade;

-- ureca 스키마에서 이미 참조하고 있기때문에, 테스트 스키마에서 삭제되지 않음.