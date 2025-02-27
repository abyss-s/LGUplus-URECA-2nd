-- 모든 사원의 모든 정보를 조회하시오
select *
from EMP;

-- 커미션(comm)을 받는 사원의 모든 정보를 조회하시오
select *
from EMP
where comm is not null;

-- 이름이 SM으로 시작하는 사원의 모든 정보를 조회하시오
select *
from EMP
where ENAME like "SM%";

-- 10번 부서에 근무하는 직원을 조회하시오.
select *
from EMP
where DEPTNO = 10;

-- 급여가 5000이상의 직원을 조회하시오.
select *
from EMP
where SAL >= 5000;

-- 10번 부서에 근무 하면서 업부가 CLERK인 직원을 조회하시오.
select *
from EMP
where DEPTNO = 10 and JOB = 'CLERK';

-- 상사가 없는 사원을 조회하시오
select *
from EMP
where MGR is null;

-- 상사의 사원번호를 조회하시오.
select distinct MGR
from EMP
where MGR is not null;

