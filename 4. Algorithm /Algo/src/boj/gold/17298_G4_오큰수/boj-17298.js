const fs = require('fs');

// 백준 제출용
let input = fs.readFileSync('/dev/stdin').toString().trim().split('\n');

// 로컬 테스트용
// const filePath =
//   process.platform === 'linux' ? 'dev/stdin' : __dirname + '/input.txt';
// let input = fs.readFileSync(filePath).toString().trim().split('\n');

// 17298번 오큰수
let n = Number(input[0]);
let numbers = input[1].split(' ').map(Number);

let stack = [];
let results = Array(n).fill(-1);

for (let i = 0; i < n; i++) {
  // 오른쪽에서 가장 가까운 큰 수를 찾는 로직
  while (stack.length > 0 && numbers[stack[stack.length - 1]] < numbers[i]) {
    let idx = stack.pop();
    results[idx] = numbers[i]; // 해당 위치 수 찾아서 저장
  }

  stack.push(i);
}

console.log(results.join(' '));
