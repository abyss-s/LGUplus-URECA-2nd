const fs = require('fs');
const input = fs.readFileSync(0).toString().trim().split('\n');
let n = Number(input[0]);
let resArr = [];

// 로컬 테스트용
// const filePath =
//   process.platform === 'linux' ? 'dev/stdin' : __dirname + '/input.txt';
// let n = fs.readFileSync(filePath).toString().trim().split('\n');
// n = Number(n[0]);

// 오버플로우 방지
console.log((2n ** BigInt(n) - 1n).toString());

const hanoi = (cnt, from, temp, to) => {
  if (cnt === 0) return;
  hanoi(cnt - 1, from, to, temp);
  resArr.push(from + ' ' + to);
  hanoi(cnt - 1, temp, from, to);
};

// disk 수가 20을 초과할 경우 무시
if (n > 20) return;
hanoi(n, 1, 2, 3);
console.log(resArr.join('\n'));
