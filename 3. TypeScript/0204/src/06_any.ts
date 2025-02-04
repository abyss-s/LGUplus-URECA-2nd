let noAnyValue = 10; // number 타입으로 추론
// anyValue = 'hello'; // number 이외의 다른 타입의 값을 대입할 수 없다.

// 변수의 타입을 변경해야 하는 경우 => any, union 이용!!
// 자유로운 만큼 위험하므로 가능한 사용하지 않는 것이 좋다.
let anyValue: any = 10;
console.log(typeof anyValue);

anyValue = 'hello';
console.log(typeof anyValue);
