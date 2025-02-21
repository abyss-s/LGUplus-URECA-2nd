const fs = require('fs');

// 백준 제출용
// const input = fs.readFileSync('/dev/stdin').toString().trim().split('\n');

// 로컬 테스트용
const filePath =
  process.platform === 'linux' ? '/dev/stdin' : __dirname + '/input.txt';
const input = fs.readFileSync(filePath).toString().trim().split('\n');

// 백준 10026번: 적록색약
const n = Number(input[0].trim());
const grid = input.slice(1).map((row) => row.split(''));
const visited = Array.from({ length: n }, () => Array(n).fill(false));

let dltonism = 0; // 적록색약 구역 수
let nonDltonism = 0; // 비적록색약 구역 수
let dx = [-1, 1, 0, 0]; // 상우
let dy = [0, 0, -1, 1]; // 하좌

const bfs = (startX, startY, isDltonism) => {
  const queue = [[startX, startY]];
  const color = grid[startX][startY];
  while (queue.length > 0) {
    const [x, y] = queue.shift();

    for (let i = 0; i < 4; i++) {
      // 사방 탐색
      const nx = x + dx[i];
      const ny = y + dy[i];

      if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
      if (visited[nx][ny]) continue;

      // 적록색약
      if (isDltonism) {
        // R과 G를 같은 색으로 처리
        if (
          (color === 'R' || color === 'G') &&
          (grid[nx][ny] === 'R' || grid[nx][ny] === 'G')
        ) {
          visited[nx][ny] = true;
          queue.push([nx, ny]);
        } else if (color === 'B' && grid[nx][ny] === 'B') {
          visited[nx][ny] = true;
          queue.push([nx, ny]);
        }
      }
      // 비적록색약이면 그냥 같은 색만 탐색
      else {
        if (color === grid[nx][ny]) {
          visited[nx][ny] = true;
          queue.push([nx, ny]);
        }
      }
    }
  }
};

for (let i = 0; i < n; i++) {
  for (let j = 0; j < n; j++) {
    if (!visited[i][j]) {
      bfs(i, j, false);
      nonDltonism++;
    }
  }
}

// 초기화
for (let i = 0; i < n; i++) {
  for (let j = 0; j < n; j++) {
    visited[i][j] = false;
  }
}

for (let i = 0; i < n; i++) {
  for (let j = 0; j < n; j++) {
    if (!visited[i][j]) {
      bfs(i, j, true);
      dltonism++;
    }
  }
}

console.log(nonDltonism + ' ' + dltonism);
