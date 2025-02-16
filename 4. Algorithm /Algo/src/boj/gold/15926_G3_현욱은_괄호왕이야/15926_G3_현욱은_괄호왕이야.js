const fs = require('fs');

// 백준 제출용
// let input = fs.readFileSync('/dev/stdin').toString().trim().split('\n');

// 로컬 테스트용
const filePath =
  process.platform === 'linux' ? '/dev/stdin' : __dirname + '/input.txt';
let input = fs.readFileSync(filePath).toString().trim().split('\n');

// 15926번 현우는 괄호왕이야!!
const n = Number(input.shift()); // 문자열의 길이
const str = input[0]; // 문자열
let stack = []; // 올바른 괄호 문자열인지 체크할 스택
let max = 0; // 최대 길이
let start = -1; // 현재 올바른 괄호 문자열의 시작 인덱스

for (let i = 0; i < str.length; i++) {
  // 여는 괄호의 위치를 스택에 저장
  if (str[i] === '(') {
    stack.push(i);
  }
  // 괄호 쌍인 경우: 닫는 괄호이고, 스택의 탑이 여는 괄호면 스택에서 제거
  else {
    if (stack.length > 0) {
      stack.pop();
      // 현재까지의 길이를 계산하기 위해 스택의 탑 인덱스를 저장
      let end = stack.length > 0 ? stack[stack.length - 1] : start;
      max = Math.max(max, i - end); //
    }
    // 잘못된 경우: 시작 인덱스를 현재로 초기화
    else {
      start = i;
    }
  }
}

console.log(max);
