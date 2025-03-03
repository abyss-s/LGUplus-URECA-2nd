-- 부서별   사원수,  급여 평균, 커미션을 받는 사원 수를 조회 하시오
select  deptno, count(*), avg(sal), count(comm)
from emp
group by deptno;

-- 업무별   평균 급여, 최저 급여, 최고 급여를 조회 하시오
select job, avg(sal), min(sal), max(sal) 
from emp
group by job;

-- 부서별, 업무별 통계  : 사원수,  급여 평균, 커미션을 받는 사원 수를 조회 하시오
select deptno, job, count(*), avg(sal), count(comm)
from emp
group by deptno, job;

-- 부서별 총 급여가 10000이하인 부서의 총 사원수, 총 급여를 조회
select deptno, count(*), sum(sal) as totalSum 
from emp
group by deptno having totalSum <= 10000;

-- 부서별 총 급여를 조회,  급여가 1000이상인 사원들의 급여 합계를 구함.
select deptno, count(*), sum(sal) as totalSum
from emp
where sal >= 1000
group by deptno order by deptno;

-- 상품별 수량이 10개 초과인 제품에 대해 상품별 총 판매 수량 조회
select gno, count(*), sum(quantity) as orders
from orders
group by gno having orders > 10;

-- 건당 판매 수량이 2개 이상인 판매 건수를 상품별 조회
select gno, count(quantity) as orders
from ORDERS
where quantity >= 2
group by gno;
