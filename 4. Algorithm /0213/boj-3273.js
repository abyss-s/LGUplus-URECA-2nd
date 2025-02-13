const fs = require('fs');
// 백준 제출용
const input = fs.readFileSync(0).toString().trim().split('\n');

// 로컬 테스트용
// const filePath =
//   process.platform === 'linux' ? 'dev/stdin' : __dirname + '/input.txt';
// let input = fs.readFileSync(filePath).toString().trim().split('\n');

let n = Number(input[0]);
let arr = input[1].split(' ').map(Number);
let x = Number(input[2]);

//console.log(arr);
arr.sort((a, b) => a - b);

let start = 0;
let end = n - 1;
let count = 0; // 조건을 만족하는 쌍의 개수
let curSum = 0;

/*
 * two-pointer 진행
 */
while (start < end) {
  // 현재 x값 선언
  curSum = arr[start] + arr[end];

  // 타겟값보다 작으면
  if (curSum < x) {
    start++; // 오른쪽으로 이동
  } // 타겟값보다 크면
  else if (curSum > x) {
    end--; // 왼쪽으로 이동
  }
  // 타켓값과 일치 => 조건을 만족하는 경우
  else {
    count++;
    start++;
    end--;
  }
}

console.log(count);
