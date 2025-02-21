const fs = require('fs');

// 백준 제출용
const input = fs.readFileSync('/dev/stdin').toString().trim().split('\n');

// 로컬 테스트용
// const filePath =
//     process.platform === 'linux' ? '/dev/stdin' : __dirname + '/input.txt';
// let input = fs.readFileSync(filePath).toString().trim().split('\n');

// 백준 7576번: 토마토
const [m, n] = input[0].split(' ').map(Number);
const tomatoes = input.slice(1).map((row) => row.split(' ').map(Number));
let q = [];
const dx = [1, -1, 0, 0];
const dy = [0, 0, 1, -1];
let day = 0;

for (let i = 0; i < n; i++) {
  for (let j = 0; j < m; j++) {
    if (tomatoes[i][j] === 1) {
      q.push([i, j]);
    }
  }
}

let front = 0;

while (front < q.length) {
  const size = q.length - front; // 현재 day에 익힐 수 있는 토마토 개수

  for (let i = 0; i < size; i++) {
    const [x, y] = q[front++];

    for (let j = 0; j < 4; j++) {
      const nx = x + dx[j];
      const ny = y + dy[j];
      if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
      if (tomatoes[nx][ny] !== 0) continue;
      tomatoes[nx][ny] = 1; // 익힘 처리
      q.push([nx, ny]);
    }
  }
  day++;
}

// 모든 토마토가 익었는지 확인
for (let i = 0; i < n; i++) {
  for (let j = 0; j < m; j++) {
    if (tomatoes[i][j] === 0) {
      console.log(-1);
      return;
    }
  }
}

console.log(day - 1);
