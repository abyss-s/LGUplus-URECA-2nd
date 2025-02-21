const fs = require('fs');

// 백준 제출용
const input = fs.readFileSync('/dev/stdin').toString().trim().split('\n');

// 로컬 테스트용
// const filePath = process.platform === 'linux' ? '/dev/stdin' : __dirname + '/input.txt';
// const input = fs.readFileSync(filePath).toString().trim().split('\n');

const solution = (input) => {
  const [n, m] = input[0].split(' ').map(Number);
  const maze = input.slice(1).map((row) => row.split('').map(Number));
  bfs(maze, n, m);
};

const bfs = (maze, n, m) => {
  const dx = [-1, 1, 0, 0]; // 상하
  const dy = [0, 0, -1, 1]; // 좌우

  const q = [];
  const visited = Array.from({ length: n }, () => Array(m).fill(false));
  q.push([0, 0]); // 시작 위치 (0, 0)
  visited[0][0] = true; // 시작점 방문 처리
  maze[0][0] = 1; // 시작점 거리 1로 초기화

  while (q.length > 0) {
    const [x, y] = q.shift(); // pop

    // 종료 조건
    if (x === n - 1 && y === m - 1) {
      console.log(maze[x][y]);
      return; // 바로 종료
    }

    for (let i = 0; i < 4; i++) {
      const nx = x + dx[i];
      const ny = y + dy[i];

      // 범위를 벗어나거나 이동 불가능한 경우
      if (
        nx < 0 ||
        ny < 0 ||
        nx >= n ||
        ny >= m ||
        maze[nx][ny] === 0 ||
        visited[nx][ny]
      ) {
        continue;
      }

      maze[nx][ny] = maze[x][y] + 1; // 현재 거리 + 1로 갱신
      visited[nx][ny] = true; // 방문 처리
      q.push([nx, ny]); // 큐에 추가
    }
  }
};

solution(input);
