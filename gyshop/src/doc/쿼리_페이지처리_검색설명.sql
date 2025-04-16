
-- select 열이름들(구분은 ,로) from 테이블명 (조건) (정렬)
-- 일반게시판 리스트쿼리(기본)
select no, title, writer, writeDate, hit from board order by no desc;

-- 페이지 처리 : 순서를 표현할 변수를 만들고 보여주고 싶은 데이터수를 조건으로 줍니다.
-- 1. 변수지정
select @rownum := 0;

select @rownum := @rownum + 1 as rnum, no, title, writer, writeDate, hit
	from board, (select @rownum := 0) as rn order by no desc;
-- 새롭게 순서가 매겨진 테이블이 만들어 졌다고 생각하시면 됩니다.(rnum)

-- 필요한 데이터수만큼 rnum을 사용해서 가져옵니다.
select rnum, no, title, writer, writeDate, hit
 from (select @rownum := @rownum + 1 as rnum, no, title, writer, writeDate, hit
	from board, (select @rownum := 0) as rn order by no desc) as boardPage
    where rnum >= 11 and rnum <= 20;
-- 조건은 테이블안에 있는 데이터를 가지고 적용됩니다.

-- 검색
select no, title, writer, writeDate, hit from board 
	where (1=0)
		or title like '%java%'
        or content like '%java%'
        or writer like '%java%'
	order by no desc;
    
-- 검색이 끝나고 페이지 처리 진행
-- @rownum 선언    
select @rownum := @rownum + 1 as rnum, no, title, writer, writeDate, hit 
	from board, (select @rownum := 0) as rn 
	where (1=0)
		or title like '%java%'
        or content like '%java%'
        or writer like '%java%'
	order by no desc;

-- 페이지 처리
-- 검색데이터 중 rnum 1부터 10까지 데이터가 화면에 표시되도록 만들어 보세요.
select @rownum := @rownum + 1 as rnum, no, title, writer, writeDate, hit 
	from board, (select @rownum := 0) as rn 
	where (1=0)
		or title like '%java%'
        or content like '%java%'
        or writer like '%java%'
	order by no desc



-- 이미지게시판 리스트(기본쿼리 - 두가지테이블을 사용한 리스트쿼리)
select i.no, i.title, i.content, i.fileName, m.name, i.writeDate, i.hit
	from image as i, member as m 
    where i.id = m.id
    order by no desc;
    
    
    
    