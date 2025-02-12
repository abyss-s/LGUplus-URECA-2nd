const fs = require('fs');
// 백준 제출용
let N = fs.readFileSync('/dev/stdin').toString().trim().split('\n');

// 로컬 테스트용
// const filePath =
//   process.platform === 'linux' ? 'dev/stdin' : __dirname + '/input.txt';
// let N = fs.readFileSync(filePath).toString().trim().split('\n');

N = Number(N[0]);

console.log('어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.');
recursice_func(N, '');

function recursice_func(n, prefix) {
  if (n === 0) {
    console.log(prefix + '"재귀함수가 뭔가요?"');
    console.log(prefix + '"재귀함수는 자기 자신을 호출하는 함수라네"');
    console.log(prefix + '라고 답변하였지.');
    return;
  }
  console.log(prefix + '"재귀함수가 뭔가요?"');
  console.log(
    prefix +
      '"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.'
  );
  console.log(
    prefix +
      '마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.'
  );
  console.log(
    prefix +
      '그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어."'
  );
  recursice_func(n - 1, prefix + '____');
  console.log(prefix + '라고 답변하였지.');
}
