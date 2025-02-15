const fs = require('fs');

// 백준 제출용
let input = fs.readFileSync('/dev/stdin').toString().trim().split('\n');

// 로컬 테스트용
// const filePath =
//   process.platform === 'linux' ? 'dev/stdin' : __dirname + '/input.txt';
// let input = fs.readFileSync(filePath).toString().trim().split('\n');

// 2493번 탑
let n = Number(input[0]);
let towers = input[1].split(' ').map(Number);

let stack = []; // 신호를 수신하는 탑 인덱스를 저장
let results = Array(n).fill(0);

for (let i = 0; i < n; i++) {
  // 현재 탑보다 작은 탑들은 필요 없으므로 제거
  while (stack.length > 0 && towers[stack[stack.length - 1]] < towers[i]) {
    stack.pop();
  }

  if (stack.length > 0) results[i] = stack[stack.length - 1] + 1; // 탑 번호로 변경

  // 현재 탑을 스택에 저장
  stack.push(i);
}

console.log(results.join(' '));
