const fs = require('fs');
// 백준 제출용
// const input = fs.readFileSync(0).toString().trim().split('\n');

// 로컬 테스트용
const filePath =
  process.platform === 'linux' ? 'dev/stdin' : __dirname + '/input.txt';
let input = fs.readFileSync(filePath).toString().trim().split('\n');

let n = Number(input[0]);
let arr = input[1].split(' ').map(Number);

arr.sort((a, b) => a - b);

let count = 0; // 조건을 만족하는 쌍의 개수

/*
 * two-pointer 진행
 * - 어떤 수가 다른 두 수의 합으로 나타낼 수 있는지 확인
 */
for (let i = 0; i < n; i++) {
  let temp = arr[i]; // 어떤 수
  let sum = 0;

  // 투포인터 선언
  let start = 0;
  let end = n - 1;

  // 다 찾을때까지 반복
  while (start < end) {
    // 현재 값은 사용 불가 => 건너뛰기
    if (start == i) {
      start++;
      continue;
    }
    if (end == i) {
      end--;
      continue;
    }

    sum = arr[start] + arr[end];

    if (sum < temp) {
      start++;
    } else if (sum > temp) {
      end--;
    } else {
      count++;
      // console.log('sum 확인: ' + sum);
      break; // 다른 두 수를 찾았다면 루프를 종료
    }
  }
}

console.log(count);
