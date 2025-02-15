const fs = require('fs');

// 백준 제출용
let input = fs.readFileSync('/dev/stdin').toString().trim().split('\n');

// 로컬 테스트용
// const filePath =
//   process.platform === 'linux' ? 'dev/stdin' : __dirname + '/input.txt';
// let input = fs.readFileSync(filePath).toString().trim().split('\n');

// 요세푸스 문제
const [n, k] = input[0].split(' ').map(Number);
let queue = Array.from({ length: n }, (_, i) => i + 1);
let results = [];

for (let i = 0; i < n; i++) {
  // k번째 사람을 찾을 때까지 (k-1)번 앞의 숫자를 뒤로 보내는 과정 반복
  for (let j = 0; j < k - 1; j++) {
    queue.push(queue.shift()); // 첫 번째 요소를 꺼내서 맨 뒤로 보냄
  }
  // k번째 원소를 제거하고 결과 배열에 추가
  results.push(queue.shift());
}

console.log('<' + results.join(', ') + '>');
