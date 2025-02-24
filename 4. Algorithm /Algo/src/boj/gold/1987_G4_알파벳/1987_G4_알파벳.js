const fs = require('fs');

// 백준 제출용
// const input = fs.readFileSync('/dev/stdin').toString().trim().split('\n');

// 로컬 테스트용
const filePath =
  process.platform === 'linux' ? 'dev/stdin' : __dirname + '/input.txt';
const input = fs.readFileSync(filePath).toString().trim().split('\n');

const [r, c] = input[0].split(' ').map(Number);
const map = input
  .slice(1)
  .map((row) => row.split('').map((char) => char.charCodeAt(0)));
const dx = [-1, 1, 0, 0];
const dy = [0, 0, -1, 1];
const visited = Array(26).fill(false);
let maxCount = 1;

const dfsBt = (x, y, count) => {
  maxCount = Math.max(maxCount, count);

  for (let i = 0; i < 4; i++) {
    const nx = x + dx[i];
    const ny = y + dy[i];

    if (nx < 0 || nx >= r || ny < 0 || ny >= c) continue;

    const alpha = map[nx][ny] - 65;
    if (visited[alpha]) continue;

    // 백트래킹
    visited[alpha] = true;
    dfsBt(nx, ny, count + 1);
    visited[alpha] = false;
  }
};

visited[map[0][0] - 65] = true;
dfsBt(0, 0, 1);
console.log(maxCount);
