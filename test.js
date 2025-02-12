const fs = require('fs');
let input = fs.readFileSync(0).toString().trim().split('\n');

n = Number(input[0]);
m = Number(input[1]);

// let arr2d = Array.from({length: n}, ()=> Array(m).fill(0));
let arr2d = Array(n)
  .fill(0)
  .map(() => Array(m).fill(0));

for (let i = 0; i < n; i++) {
  for (let j = 0; j < m; j++) {
    if (j % 2 == 1) {
      arr2d[i][j] = j * 2 - i;
    } else {
      arr2d[i][j] = i * 2 - j + 1;
    }
  }
}

for (let r of arr2d) {
  console.log(r.join(' '));
}
