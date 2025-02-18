const fs = require('fs');

// 백준 제출용
let input = fs.readFileSync('/dev/stdin').toString().trim().split('\n');

// 로컬 테스트용: 입력 파일 경로 설정
// const filePath =
//   process.platform === 'linux' ? '/dev/stdin' : __dirname + '/input.txt';
// const input = fs.readFileSync(filePath).toString().trim().split('\n');

// 2961번 : 도영이가 만든 맛있는 음식
const n = Number(input[0]);
const arr = input.slice(1).map((v) => v.split(' ').map(Number));
const INF = 1e9; // 무한대
let min = INF; // 최솟값 ( 신맛과 쓴맛의 차이가 가장 작은 요리의 차이를 저장 )

// 재귀
const bt = (i, s, b) => {
  // 기저 조건
  if (i == n) {
    // 신맛과 쓴맛이 모두 0이 아닌 경우에만 최솟값 갱신
    if (s != 1 && b != 0) min = Math.min(min, Math.abs(s - b));
    return;
  }
  bt(i + 1, s * arr[i][0], b + arr[i][1]); // 재료 추가
  bt(i + 1, s, b); // 재료 추가 X
};

bt(0, 1, 0);

console.log(min);
