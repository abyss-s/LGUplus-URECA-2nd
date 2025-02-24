const fs = require('fs');

// 백준 제출용
// const input = fs.readFileSync('/dev/stdin').toString().trim().split('\n');

// 로컬 테스트용
const filePath =
  process.platform === 'linux' ? 'dev/stdin' : __dirname + '/input.txt';
let input = fs.readFileSync(filePath).toString().trim().split('\n');

/*
 * 큐 하나로 처리. flag를 통해 물과 고슴도치 구분
 * 물이 먼저 이동하고 고슴도치가 이동하도록 함
 */

// 백준 3055번: 탈출
const [r, c] = input[0].split(' ').map(Number);
const map = input.slice(1).map((i) => i.split(''));
const dx = [-1, 1, 0, 0];
const dy = [0, 0, -1, 1];
let queue = [];
let visited = Array.from({ length: r }, () => Array(c).fill(false));

for (let i = 0; i < r; i++) {
  for (let j = 0; j < c; j++) {
    if (map[i][j] === '*') {
      queue.push(['*', i, j]);
    }
  }
}

for (let i = 0; i < r; i++) {
  for (let j = 0; j < c; j++) {
    if (map[i][j] === 'S') {
      queue.push(['S', i, j]);
      visited[i][j] = true;
    }
  }
}

const bfs = () => {
  let time = 0;
  while (queue.length) {
    let size = queue.length;

    for (let s = 0; s < size; s++) {
      const [flag, x, y] = queue.shift();

      for (let i = 0; i < 4; i++) {
        const nx = x + dx[i];
        const ny = y + dy[i];

        if (nx < 0 || nx >= r || ny < 0 || ny >= c) continue;

        if (flag === '*' && map[nx][ny] === '.') {
          // 물 이동 (빈곳으로 확산)
          map[nx][ny] = '*';
          queue.push(['*', nx, ny]);
        } else if (flag === 'S') {
          // 고슴도치 이동
          if (map[nx][ny] === 'D') return time + 1; // 비버굴 도착
          if (map[nx][ny] === '.' && !visited[nx][ny]) {
            // 방문 처리
            visited[nx][ny] = true;
            queue.push(['S', nx, ny]);
          }
        }
      }
    }
    time++;
  }

  return 'KAKTUS';
};

console.log(bfs());
