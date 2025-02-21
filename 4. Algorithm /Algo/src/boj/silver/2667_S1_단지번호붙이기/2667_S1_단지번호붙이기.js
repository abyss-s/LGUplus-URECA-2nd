const fs = require('fs');

// 백준 제출용
const input = fs.readFileSync('/dev/stdin').toString().trim().split('\n');

// 로컬 테스트용
// const filePath =
//   process.platform === 'linux' ? '/dev/stdin' : __dirname + '/input.txt';
// const input = fs.readFileSync(filePath).toString().trim().split('\n');

// 2667번: 단지번호붙이기
const n = Number(input[0]);
const map = input.slice(1).map((v) => v.split('').map(Number));
const visited = Array.from({ length: n }, () => Array(n).fill(false));
let dx = [-1, 1, 0, 0];
let dy = [0, 0, -1, 1];
let totalCnt = 0;
let danzies = [];

const dfs = (x, y) => {
  let cnt = 1;
  visited[x][y] = true;
  for (let i = 0; i < 4; i++) {
    const nx = x + dx[i];
    const ny = y + dy[i];
    // 탐색 가능한 범위 내 집이 있고 방문하지 않았다면 dfs 재귀 호출
    if (nx >= 0 && ny >= 0 && nx < n && ny < n) {
      if (map[nx][ny] === 1 && !visited[nx][ny]) {
        cnt += dfs(nx, ny);
      }
    }
  }
  return cnt;
};

for (let i = 0; i < n; i++) {
  for (let j = 0; j < n; j++) {
    if (map[i][j] === 1 && !visited[i][j]) {
      danzies.push(dfs(i, j)); // 각 단지 내 집의 수를 배열에 저장
      totalCnt++;
    }
  }
}

console.log(totalCnt);
danzies.sort((a, b) => a - b).forEach((v) => console.log(v));
