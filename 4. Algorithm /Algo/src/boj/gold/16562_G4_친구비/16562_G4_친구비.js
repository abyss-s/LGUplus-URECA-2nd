const fs = require('fs');

// 백준 제출용
// const input = fs.readFileSync('/dev/stdin').toString().trim().split('\n');

// 로컬 테스트용
const filePath =
  process.platform === 'linux' ? 'dev/stdin' : __dirname + '/input.txt';
const input = fs.readFileSync(filePath).toString().trim().split('\n');

// 백준 16562번 친구비

/*
 *  n: 학생 수, m: 친구관계 수, k: 가지고 있는 돈
  - 먼저 친구관계를 union하고, 각 친구관계의 최소비용을 구한 후,
  - 최소비용을 모두 더한 값이 k보다 작거나 같으면 친구가 될 수 있다.
  - 친구의 친구는 친구다 = 즉, 친구 관계는 연결되어 있다.
  - 서로소 집합의 root를 공유하므로 root를 대표로 하는 cost 배열을 만들어서 최소비용 구하기
 */

const [n, m, k] = input[0].split(' ').map((i) => +i);
const friendCost = input[1].split(' ').map((i) => +i); // 친구비 입력
const friendRelation = input // 친구 관계 [v, w] 입력
  .slice(2)
  .map((real) => real.split(' ').map((i) => +i));
const parent = Array.from({ length: n + 1 }, (_, i) => i);

// union-find
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

make();
for (let i = 0; i < m; i++) {
  const [v, w] = friendRelation[i];
  union(v, w);
}

const cost = Array.from({ length: n + 1 }, () => 1e9); // 학생 별 최소 친구비
let minCost = 0;
for (let i = 1; i <= n; i++) {
  const root = find(i);
  cost[root] = Math.min(cost[root], friendCost[i - 1]);
}

for (const c of cost) {
  if (c !== 1e9) {
    minCost += c;
  }
}
console.log(minCost <= k ? minCost : 'Oh no');
