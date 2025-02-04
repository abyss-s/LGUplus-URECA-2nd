/*
객체
*/
let myInfo: {
  name: string;
  height: number;
  isConditionGood: boolean;
  gender?: string; // 선택적 속성, 없으면 undefined
} = {
  name: 'lyj',
  height: 164,
  isConditionGood: true,
};

console.log('myInfo:', myInfo);
// myInfo: { name: 'lyj', height: 164, isConditionGood: true }
console.log('myInfo.gender:', myInfo.gender);
// myInfo.gender: undefined

/*
인터페이스
*/
interface User {
  age: number;
  name: string;
}

var p1: User = {
  age: 2,
  name: 'uplus',
};
var p2: User = {
  age: 25,
  name: 'lyj',
};

function getUser(user: User): void {
  console.log(user);
}

getUser(p1);

/*
인터페이스 확장
*/
interface Vip extends User {
  hobby: string;
}

var cust: Vip = {
  age: 30,
  name: 'abc',
  hobby: 'coding',
};
console.log(cust);
