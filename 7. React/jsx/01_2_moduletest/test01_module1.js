/*
export 
- 모듈 내보내기 
- 변수나 함수 객체 앞에 붙이면 외부 모듈에서 해당 변수나, 함수 , 객체를 import해서 사용할 수 있다. 

형식 1] 
export 변수
export 함수
export 객체

형식 2]
변수 선언
함수 선언
객체 선언
export { 변수명, 함수명, 객체명 }
*/

export const title = '계산기 모듈';

export function add(i, j) {
  return i + j;
}
export function sub(i, j) {
  return i - j;
}

