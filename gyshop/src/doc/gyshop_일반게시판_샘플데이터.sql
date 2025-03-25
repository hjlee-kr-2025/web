use gyshop;


-- 일반게시판 sample 데이터 입력
insert into board (title, content, writer, pw)
	values ('gyshop개발', '1차 일반게시판 게발중', '이현진', '1111');
insert into board (title, content, writer, pw)
	values ('java study', '자바프로그래밍 입문', '박은종', '1111');
insert into board (title, content, writer, pw)
	values ('이지스퍼블리싱 출판사', 'Do it! 시리즈', '이지스', '1111');
insert into board (title, content, writer, pw)
	values ('MySQL', 'MySQL 8.4 버전 사용중', '나디비', '1111');
insert into board (title, content, writer, pw)
	values ('홈페이지 오픈', 'gyshop open 예정', '김고양', '1111');    
    
select * from board;