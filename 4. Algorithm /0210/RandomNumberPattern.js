const fs = require('fs');
const filePath = 'RandomNumberPattern.txt';

/**
 * 가로, 세로의 길이가 N인 N x N 정사각 행렬이 있다.
 * 이 행렬의 각각의 값으로서 0에서 9 사이의 수가 무작위로 넣어진다고 하자.
 * 이 때 그 행렬 내에서 주위를 둘러싸고 있는 다른 모든 수들 보다
 * 큰 수와 작은 수가 각각 몇 개인지를 구하는 프로그램을 작성하여라
 * <p>
 * [제한 조건]
 * 1. N은 3 이상 50 이하의 정수이다.
 * 2. 행렬의 가장자리에 있는 수는 8개의 수로 둘러싸여 있지 않으므로
 * "주위를 둘러싸고 있는 다른 모든 수들보다 큰 수, 혹은 작은 수"가 될 수 없다.
 * [입력]
 * 입력은 다음과 같이 구성되어 있다.
 * 첫 번째 줄에는 테스트 케이스의 개수 T가 주어진다.
 * 그 다음 T 개의 테스트 케이스가 차례로 주어진다.
 * 각 테스트 케이스는 다음과 같이 구성 되어 있다.
 * 첫 줄에 정 사각 행렬의 크기 N이 주어진다.
 * 그 다음 N 줄에 걸쳐 정 사각 행렬의 각 행의 값이 순서대로 주어진다.
 * [출력]
 * 각 줄은 #x로 시작하고 (x는 테스트 케이스 번호) 공백을 하나 둔 다음,
 * 문제에서 요구한 큰 수의 개수,
 * 그리고 작은 수의 개수를 공백을 두어 차례로 출력한다.
 * <p>
 * [결과]
 * #1 6 4
 * #2 1 1
 */

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
