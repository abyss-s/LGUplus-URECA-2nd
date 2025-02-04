/*
타입 명시하기
*/

let one: number = 1;
let myName: string = 'uplus';
let trueOrFalse: boolean = true;
let bigData: bigint = 123n;

/* 
타입 유니온에서 현재 변수에 값이 없을 때 사용 
*/

let undefinedVal: undefined = undefined;
// [ERROR] undefinedVal = 10;
let nullVal: null = null;
// [ERROR] nullVal = 10;

/*
symbol - unique
*/
let symbolVal: symbol = Symbol('symbol');
// 같은 값으로 만든 데이터를 비교해도 false
console.log(symbolVal === Symbol('symbol'));
