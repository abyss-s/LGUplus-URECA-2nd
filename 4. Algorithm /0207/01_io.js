const fs = require('fs');
// const filePath =
//   process.platform === 'linux' ? 'dev/stdin' : __dirname + '/input.txt';
// console.log(filePath);
const filePath =
  '/Users/lh372/Documents/source/LGUplus-URECA-2nd/4. Algorithm /0207/input.txt';

try {
  let input = fs.readFileSync(filePath).toString().trim().split('\n');

  const tc = Number(input[0]);
  const testcases = [];
  for (let i = 1; i <= tc; i++) {
    // i 번째 라인의 데이터를 공백을 기준으로 분리하고,
    // .map을 이용해서 바로 숫자로 변경
    const temp = input[i].split(' ').map(Number);
    const testcase = { N: temp[0], data: temp.slice(1) };
    testcases.push(testcase);
  }

  console.log(testcases);
} catch (error) {
  console.error('에러 코드: ', error);
}
