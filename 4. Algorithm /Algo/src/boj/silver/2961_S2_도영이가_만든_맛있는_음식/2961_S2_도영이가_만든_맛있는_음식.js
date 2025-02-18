const fs = require('fs');

// ���� �����
let input = fs.readFileSync('/dev/stdin').toString().trim().split('\n');

// ���� �׽�Ʈ��: �Է� ���� ��� ����
// const filePath =
//   process.platform === 'linux' ? '/dev/stdin' : __dirname + '/input.txt';
// const input = fs.readFileSync(filePath).toString().trim().split('\n');

// 2961�� : �����̰� ���� ���ִ� ����
const n = Number(input[0]);
const arr = input.slice(1).map((v) => v.split(' ').map(Number));
const INF = 1e9; // ���Ѵ�
let min = INF; // �ּڰ� ( �Ÿ��� ������ ���̰� ���� ���� �丮�� ���̸� ���� )

// ���
const bt = (i, s, b) => {
  // ���� ����
  if (i == n) {
    // �Ÿ��� ������ ��� 0�� �ƴ� ��쿡�� �ּڰ� ����
    if (s != 1 && b != 0) min = Math.min(min, Math.abs(s - b));
    return;
  }
  bt(i + 1, s * arr[i][0], b + arr[i][1]); // ��� �߰�
  bt(i + 1, s, b); // ��� �߰� X
};

bt(0, 1, 0);

console.log(min);
