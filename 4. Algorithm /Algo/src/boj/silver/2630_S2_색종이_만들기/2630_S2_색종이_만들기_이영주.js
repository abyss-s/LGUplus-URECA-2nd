const fs = require('fs');

// ���� �����
// let input = fs.readFileSync('/dev/stdin').toString().trim().split('\n');

// ���� �׽�Ʈ��: �Է� ���� ��� ����
const filePath =
  process.platform === 'linux' ? '/dev/stdin' : __dirname + '/input.txt';
const input = fs.readFileSync(filePath).toString().trim().split('\n');

// ���� 2630��: ������ �����
const n = Number(input[0]);
const arr2d = input.slice(1).map((line) => line.split(' ').map(Number));

let white = 0;
let blue = 0;

// ���� ������ ������ ��� ���� ������ �̷��� �ִ��� Ȯ��
const isUniform = (arr2d, x, y, size) => {
  const color = arr2d[x][y];
  for (let i = x; i < x + size; i++) {
    for (let j = y; j < y + size; j++) {
      // �ϳ��� �ٸ� ���� ������
      if (arr2d[i][j] !== color) return false;
    }
  }
  return true;
};

// ���� ���� ��� �Լ�
const recur = (x, y, size) => {
  const paper = arr2d[x][y];

  // isUniform���� �˻� ����
  if (isUniform(arr2d, x, y, size)) {
    if (paper === 1) blue++;
    else white++;
    return;
  }
  // ��� ���� ���� �ƴϸ� �� ������ ����
  else {
    const half = size / 2;
    recur(x, y, half); // 1
    recur(x, y + half, half); // 2
    recur(x + half, y, half); // 3
    recur(x + half, y + half, half); // 4
  }
};

recur(0, 0, n);
console.log(white);
console.log(blue);
