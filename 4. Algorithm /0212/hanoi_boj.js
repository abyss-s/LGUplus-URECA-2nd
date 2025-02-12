const fs = require('fs');
// const input = fs.readFileSync(0).toString().trim().split('\n');
// let n = Number(input[0]);

// 로컬 테스트용
const filePath =
  process.platform === 'linux' ? 'dev/stdin' : __dirname + '/input.txt';
let n = fs.readFileSync(filePath).toString().trim().split('\n');
n = Number(n[0]);

console.log(BigInt(2 ** n - 1));

const hanoi = (cnt, from, temp, to) => {
  if (cnt === 0) return;
  hanoi(cnt - 1, from, to, temp);
  console.log(from + ' ' + to);
  hanoi(cnt - 1, temp, from, to);
};

hanoi(n, 1, 2, 3);
