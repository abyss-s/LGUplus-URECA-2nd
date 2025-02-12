const fs = require('fs');
// const input = fs.readFileSync(0).toString.trim().split('\n');
// let n = Number(input[0]);

// 로컬 테스트용
const filePath =
  process.platform === 'linux' ? 'dev/stdin' : __dirname + '/input.txt';
let n = fs.readFileSync(filePath).toString().trim().split('\n');
n = Number(n[0]);

let res = 0;
let resArr = [];

const hanoi = (cnt, from, temp, to) => {
  if (cnt == 0) return;
  hanoi(cnt - 1, from, temp, to);
  // console.log(`${cnt}번째 원판: ${from}번 장대 -> ${to}번 장대`);
  resArr.push(from + ' ' + to);
  hanoi(cnt - 1, temp, to, from);
  res++;
};

hanoi(n, 1, 2, 3);
console.log(res);
console.log(resArr.join('\n'));
