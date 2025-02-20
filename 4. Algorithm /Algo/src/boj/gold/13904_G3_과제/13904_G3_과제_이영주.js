const fs = require('fs');

// 백준 제출용
// let input = fs.readFileSync('/dev/stdin').toString().trim().split('\n');

// 로컬 테스트용
const filePath =
  process.platform === 'linux' ? 'dev/stdin' : __dirname + '/input.txt';
let input = fs.readFileSync(filePath).toString().trim().split('\n');

// 백준 13904번: 과제
const n = Number(input[0]);
const hwInfo = input.slice(1).map((v) => v.split(' ').map(Number));

hwInfo.sort((a, b) => {
  if (a[1] === b[1]) {
    return b[0] - a[0]; // 점수가 같다면 마감일 기준 내림차순 정렬
  }
  return b[1] - a[1]; // 점수 기준 내림차순 정렬
});

let maxDay = Math.max(...hwInfo.map((v) => v[0])); // 가장 늦게 끝나는 과제 마감일(최대값)
let totalScore = 0;
const scedule = new Array(maxDay + 1).fill(0); // 날짜별 과제 점수 저장 배열

for (const [d, w] of hwInfo) {
  // 마감일부터 처리
  for (let i = d; i > 0; i--) {
    // 해당 날짜에 과제가 없으면 점수 저장
    if (scedule[i] === 0) {
      scedule[i] = w;
      totalScore += w;
      break;
    }
  }
}

console.log(totalScore);
