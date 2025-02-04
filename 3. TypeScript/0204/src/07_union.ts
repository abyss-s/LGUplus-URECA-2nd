// union
let anyVal: number | string | boolean = 10; // number
anyVal = 'hello'; // string
anyVal = true; // boolean
// anyVal = {name: 'hello'}; // [ERROR] 변수 선언 시 지정한 타입이 아님

let lateVal: string | undefined | null = undefined;
lateVal = 'hello';

// alias
type nsb = number | string | boolean;
let anyVal2: nsb = 10;
anyVal2 = 'hello';
anyVal2 = true;

// 타입 별칭 추가
type nullNsb = nsb | null;
let nullAbleVal: nullNsb = null;
