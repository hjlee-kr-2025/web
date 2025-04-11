-- 기존 쿼리 : 페이지처리 없이 리스트 가져옴`
 select	no, title, 
	date_format(startDate, '%Y-%m-%d') as startDate, 
	date_format(endDate, '%Y-%m-%d') as endDate, 
	date_format(writeDate, '%Y-%m-%d') as writeDate, hit 
	from notice
    order by startDate desc;


-- 페이지 처리
-- 1. 순서대로 새로운 번호를 부여합니다.
 select	
	@rownum := @rownum + 1 as rnum,
	no, title, 
	date_format(startDate, '%Y-%m-%d') as startDate, 
	date_format(endDate, '%Y-%m-%d') as endDate, 
	date_format(writeDate, '%Y-%m-%d') as writeDate, hit 
	from notice, (select @rownum := 0) as rn
    order by startDate desc;


-- 2. 우리가 필요한 데이터만 다시 가져옵니다.
select no, title, startDate, endDate, writeDate, hit 
from
 (select	
	@rownum := @rownum + 1 as rnum,
	no, title, 
	date_format(startDate, '%Y-%m-%d') as startDate, 
	date_format(endDate, '%Y-%m-%d') as endDate, 
	date_format(writeDate, '%Y-%m-%d') as writeDate, hit 
	from notice, (select @rownum := 0) as rn
    order by startDate desc) as pageNotice;


-- 3. 시작데이터와 끝데이터를 정한다.
select no, title, startDate, endDate, writeDate, hit 
from
 (select	
	@rownum := @rownum + 1 as rnum,
	no, title, 
	date_format(startDate, '%Y-%m-%d') as startDate, 
	date_format(endDate, '%Y-%m-%d') as endDate, 
	date_format(writeDate, '%Y-%m-%d') as writeDate, hit 
	from notice, (select @rownum := 0) as rn
    order by startDate desc) as pageNotice
    where rnum >= 1 and rnum <= 10
    ;

-- 전체 데이터 수를 가져오는 쿼리 (notice)
select count(*) from notice;
