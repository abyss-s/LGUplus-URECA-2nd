const fs = require('fs');

// 백준 제출용
let input = fs.readFileSync('/dev/stdin').toString().trim().split('\n');

// 로컬 테스트용
// const filePath =
//   process.platform === 'linux' ? 'dev/stdin' : __dirname + '/input.txt';
// let input = fs.readFileSync(filePath).toString().trim().split('\n');

// 15649번 N과 M(1)
const [n, m] = input[0].split(' ').map(Number);
let visited = Array.from({ length: n }, () => false);
let results = Array(m).fill(0);

const bt = (depth) => {
  if (depth === m) {
    console.log(results.join(' '));
    return;
  }
  for (let i = 0; i < n; i++) {
    if (!visited[i]) {
      visited[i] = true;
      results[depth] = i + 1;
      bt(depth + 1);
      visited[i] = false;
    }
  }
  return;
};

bt(0);
