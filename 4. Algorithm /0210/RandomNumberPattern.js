const fs = require('fs');
const filePath = 'RandomNumberPattern.txt';

try {
  let input = fs.readFileSync(filePath).toString().trim();
  const lines = input.split('\n');
  const tc = Number(lines[0]);

  let lineIndex = 1;
  for (let t = 0; t < tc; t++) {
    const N = Number(lines[lineIndex]);
    lineIndex++;

    let matrix = Array.from({ length: N }, () => new Array(N).fill(0));

    for (let i = 0; i < N; i++) {
      matrix[i] = lines[lineIndex].split(' ').map(Number);
      lineIndex++;
    }

    let Answer1 = 0;
    let Answer2 = 0;

    for (let r = 1; r < N - 1; r++) {
      for (let c = 1; c < N - 1; c++) {
        let cur = matrix[r][c];
        let neighbors = [
          matrix[r - 1][c - 1],
          matrix[r - 1][c],
          matrix[r - 1][c + 1],
          matrix[r][c - 1],
          matrix[r][c + 1],
          matrix[r + 1][c - 1],
          matrix[r + 1][c],
          matrix[r + 1][c + 1],
        ];

        let isLargest = neighbors.every((num) => cur > num);
        let isSmallest = neighbors.every((num) => cur < num);

        if (isLargest) Answer1++;
        if (isSmallest) Answer2++;
      }
    }

    console.log(`#${t + 1} ${Answer1} ${Answer2}`);
  }
} catch (error) {
  console.error('Error code: ', error);
}
