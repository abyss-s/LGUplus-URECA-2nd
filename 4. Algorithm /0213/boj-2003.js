const fs = require('fs');
// 백준 제출용
const input = fs.readFileSync(0).toString().trim().split('\n');

// 로컬 테스트용
// const filePath =
//   process.platform === 'linux' ? 'dev/stdin' : __dirname + '/input.txt';
// let input = fs.readFileSync(filePath).toString().trim().split('\n');

let n = Number(input[0].split(' ')[0]);
let m = Number(input[0].split(' ')[1]);
let data = input[1].split(' ').map(Number);

let end = 0;
let count = 0;
let sum = 0;

/*
 * two-pointer 진행
 */
for (let start = 0; start < n; start++) {
  // 현재 부분의 합이 target 보다 작다면 end를 1 증가
  while (sum < m && end < n) {
    sum += data[end];
    end++;
  }

  if (sum == m) count++;

  // 현재 부분의 합이 target 보다 크다면 start를 1 증가
  sum -= data[start];
}

console.log(count);
