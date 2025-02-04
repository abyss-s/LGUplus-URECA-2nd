// 튜플 선언
const myTuple: [string, number] = ['lyj', 25];
console.log('myTuple: ', myTuple);
// myTuple.push('hello'); // readonly가 없다면 데이터가 추가될 수 있음.

// 함수에서 튜플을 인자로 받을 수 있다.
function printMyTuple(name: string, info: [string, number]): void {
  console.log(`[${name}]`, ...info);
}

printMyTuple('tuple test: ', myTuple);

// 튜플을 반환하는 함수
function fetchUser(): [string, number] {
  return ['uplus', 2];
}

let returnTuple = fetchUser();
console.log(returnTuple);

// 튜플의 결과값을 구조분해할당
const [nameT, ageT] = fetchUser();
console.log(`name: ${nameT}, ageT: ${ageT}`);
