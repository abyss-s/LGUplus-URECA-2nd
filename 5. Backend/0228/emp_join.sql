-- 1. CLERK 업무를 담당하고 급여 1000보다 많이 받는 사원의
--    사원번호, 이름, 급여, 부서명을 조회하시오
select empno, ename, sal, dname, job
from emp e
join dept d
on e.deptno = d.deptno
where job = 'CLERK' and sal > 1000;

-- 2. 사원 이름, 급여, 근무도시명을 조회하시오
select ename, sal, loc
from emp e
join dept d
on e.deptno = d.deptno;

-- 3. DALLAS에 근무하는 사원 중 입사년도가 1981년도인 사원의 모든 정보 조회하시오
select *
from emp
join dept
using(deptno)
where loc='DALLAS' and year(hiredate)='2010';

-- 4. 이름에 'A' 문자를 포함한 사원의 사원번호, 이름, 업무, 급여, 급여등급을 조회하시오
select empno, ename, job, sal, grade
from emp
join salgrade
on sal between losal and hisal
where ename like '%A%';

-- 5. 사원번호, 이름, 급여, 급여 등급을 조회하시오
select empno, ename, sal, grade
from emp
join salgrade
on sal between losal and hisal;

-- 6. 모든 사원번호, 사원이름, 업무, 상사번호, 상사이름
select e.empno, e.ename, e.job, e.mgr, m.ename 
from emp e
left join emp m
on e.mgr = m.empno; -- 사원의 상사번호 = 상사의 사원번호 


-- 7. 게시글 번호, 작성자 아이디,  타이틀, 등록일, 내용,
-- 사용자가 올린 파일 이름, 저장된 파일 이름을 추출
select b.no, id, title, regdate, contents, rfilename, sfilename
from board b
left join boardfile bf
on b.no = bf.no;

-- 8. BLAKE를 관리자로 하는 사원의 모든 정보를 추출
select * 
from emp e, emp m
where e.mgr = m.empno and m.ename = 'BLAKE';

-- 9. 급여 등급이 1등급에 해당하는 사원의 모든 정보를 추출
select * 
from emp
left join salgrade 
on sal between losal and hisal
where grade = 1;


-- 10. GOODS 테이블에 등록된 총 상품 개수와 분류명별 상품 개수를 추출
select  if(grouping(name)=1, '등록된 총 상품 수', 
		ifnull(name,'미분류')) as '분류명' 
		,count(*) "등록된 상품 수" 
from goods g
left join category c
USING (cno)
group by name with rollup ;






