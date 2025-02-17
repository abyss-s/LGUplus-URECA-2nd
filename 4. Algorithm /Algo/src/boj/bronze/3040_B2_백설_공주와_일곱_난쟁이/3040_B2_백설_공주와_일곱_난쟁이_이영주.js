const fs = require('fs');

// 백준 제출용
let input = fs.readFileSync('/dev/stdin').toString().trim().split('\n');

// 로컬 테스트용
// const filePath =
//   process.platform === 'linux' ? '/dev/stdin' : __dirname + '/input.txt';
// let input = fs.readFileSync(filePath).toString().trim().split('\n');

// 3040번: 백설공주와 일곱난쟁이
let arr = input.map(Number);

for (let i = 0; i < 9; i++) {
  for (let j = 0; j < 9; j++) {
    if (i === j) continue;
    let sum = arr.reduce((a, c) => a + c, 0);
    sum -= arr[i] + arr[j];
    if (sum === 100) {
      arr.splice(j, 1);
      arr.splice(i, 1);
      break;
    }
  }
}

console.log(arr.join('\n'));
