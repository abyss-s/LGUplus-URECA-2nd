const fs = require('fs');

// 백준 제출용
// const input = fs.readFileSync('/dev/stdin').toString().trim().split('\n');

// 로컬 테스트용
const filePath =
  process.platform === 'linux' ? 'dev/stdin' : __dirname + '/input.txt';
const input = fs.readFileSync(filePath).toString().trim().split('\n');

// 백준 1922번: 네트워크 연결
const n = +input[0];
const m = +input[1];
const edgeList = input.slice(2).map((li) => li.split(' ').map((i) => +i));
for (const edge of edgeList) {
  const [a, b, c] = edge;
  edge[0] = a;
  edge[1] = b;
  edge[2] = c;
}

// union-find
const parent = Array.from({ length: n + 1 }, (_, i) => i);
const make = () => {
  for (let i = 1; i <= n; i++) {
    parent[i] = i;
  }
};
const find = (a) => {
  if (parent[a] === a) return a;
  return (parent[a] = find(parent[a]));
};
const union = (a, b) => {
  const rootA = find(a);
  const rootB = find(b);
  if (rootA !== rootB) {
    parent[rootB] = rootA;
    return true;
  }
  return false;
};

// 크루스칼
let result = 0;
make();
edgeList.sort((a, b) => a[2] - b[2]);
for (const edge of edgeList) {
  const [a, b, c] = edge;
  if (union(a, b)) {
    result += c;
  }
}
console.log(result);
