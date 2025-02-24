const fs = require('fs');

// 백준 제출용
// let input = fs.readFileSync('/dev/stdin').toString().trim().split('\n');

// 로컬 테스트용
const filePath =
  process.platform === 'linux' ? 'dev/stdin' : __dirname + '/input.txt';
let input = fs.readFileSync(filePath).toString().trim().split('\n');

/*
 * 큐를 2개 사용한 버전
 * 1. 물 이동 위치 저장 큐
 * 2. 고슴도치 큐 + 방문여부 처리할 배열 선언
 */

// 백준 3055번: 탈출
const [r, c] = input[0].split(' ').map((i) => +i);
const map = input.slice(1).map((i) => i.split(''));
const dx = [-1, 1, 0, 0];
const dy = [0, 0, -1, 1];
let waterQueue = []; // 물 위치 저장
let hedgehogQueue = []; // 고슴도치 위치 저장
let visited = Array.from({ length: r }, () => Array(c).fill(false)); // 고슴도치 방문 여부 저장
let time = 0; // 최종 출력할 답

// 초기 위치 설정
for (let i = 0; i < r; i++) {
  for (let j = 0; j < c; j++) {
    if (map[i][j] === '*') {
      waterQueue.push([i, j]);
    }
    if (map[i][j] === 'S') {
      hedgehogQueue.push([i, j]);
      visited[i][j] = true;
    }
  }
}

const bfs = () => {
  // 고슴도치가 이동할 수 있는 동안 물 이동부터 퍼져서 이동
  while (hedgehogQueue.length) {
    let wCount = waterQueue.length;

    for (let w = 0; w < wCount; w++) {
      const [x, y] = waterQueue.shift();
      for (let i = 0; i < 4; i++) {
        const [nx, ny] = [x + dx[i], y + dy[i]];
        if (
          nx < 0 ||
          nx >= r ||
          ny < 0 ||
          ny >= c ||
          map[nx][ny] === 'X' ||
          map[nx][ny] === 'D'
        )
          continue;
        if (map[nx][ny] === '.') {
          map[nx][ny] = '*';
          waterQueue.push([nx, ny]);
        }
      }
    }

    // 고슴도치 이동
    let hCount = hedgehogQueue.length;
    if (hCount === 0) return 'KAKTUS';

    for (let h = 0; h < hCount; h++) {
      const [x, y] = hedgehogQueue.shift();
      for (let i = 0; i < 4; i++) {
        const [nx, ny] = [x + dx[i], y + dy[i]];
        if (
          nx < 0 ||
          nx >= r ||
          ny < 0 ||
          ny >= c ||
          map[nx][ny] === 'X' ||
          map[nx][ny] === '*' ||
          visited[nx][ny]
        )
          continue;
        if (map[nx][ny] === 'D') return time + 1; // 비버굴 도착
        // 고슴도치 이동 처리
        visited[nx][ny] = true;
        hedgehogQueue.push([nx, ny]);
      }
    }
    time++;
  }

  return 'KAKTUS';
};

console.log(bfs());
