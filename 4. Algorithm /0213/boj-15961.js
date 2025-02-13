const fs = require('fs');
// 백준 제출용
const input = fs.readFileSync(0).toString().trim().split('\n');
let [n, d, k, c] = input[0].split(' ').map(Number);

// 로컬 테스트용
// const filePath =
//   process.platform === 'linux' ? 'dev/stdin' : __dirname + '/input.txt';
// let input = fs.readFileSync(filePath).toString().trim().split('\n');
// let [n, d, k, c] = input[0].split(' ').map(Number);

// 초밥 종류 입력 처리
let plates = input.slice(1).map(Number);
for (let i = 0; i < n; i++) {
  plates.push(plates[i]);
}

let isSelected = new Array(d + 1).fill(0); // 초밥 중복 체크용 배열
let max = 0,
  curMax = 0;

/*
 * sliding window 초기화(첫번째 윈도우)
 */
for (let i = 0; i < k; i++) {
  if (isSelected[plates[i]] == 0) {
    curMax++;
  }
  isSelected[plates[i]]++;
}

// 쿠폰 적용
isSelected[c] == 0 ? (max = curMax + 1) : (max = curMax);

/*
 * 투포인터 sliding window 이동 (두번째 윈도우부터 끝까지)
 */
for (let i = 1; i < n; i++) {
  let start = i - 1; // window start
  let end = (i + k - 1) % n; // window end

  // start 처리 (삭제)
  isSelected[plates[start]]--; // 윈도우에서 나가기
  if (isSelected[plates[start]] == 0) curMax--;

  // end 처리 (추가)
  if (isSelected[plates[end]] == 0) curMax++;
  isSelected[plates[end]]++;

  // 쿠폰 적용
  isSelected[c] == 0
    ? (max = Math.max(max, curMax + 1))
    : (max = Math.max(max, curMax));
}

console.log(max);
