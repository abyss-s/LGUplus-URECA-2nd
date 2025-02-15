const fs = require('fs');

// 백준 제출용
let input = fs.readFileSync('/dev/stdin').toString().trim().split('\n');

// 로컬 테스트용
// const filePath =
//   process.platform === 'linux' ? 'dev/stdin' : __dirname + '/input.txt';
// let input = fs.readFileSync(filePath).toString().trim().split('\n');

// 6198번 옥상 정원 꾸미기
let n = Number(input.shift());
let buildings = input.map(Number);
let stack = [];
let count = 0;

for (let i = 0; i < n; i++) {
  while (stack.length > 0) {
    if (stack[stack.length - 1] <= buildings[i]) stack.pop();
    else break;
  }

  count += stack.length;

  stack.push(buildings[i]);
}

console.log(count);
