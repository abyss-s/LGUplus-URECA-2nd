const fs = require('fs');
const path = require('path');
const filePath = path.join(__dirname, 'BaseStation1.txt');

/**
 * NxN 배열을 위한 기지국(A)와 집(H)가에 대한 정보가 주어진다.
 * 기지국은 상하좌우 1셀씩만 커버한다. 커버하지 못하는 집의 개수를 출력
 * <p>
 * [제약조건]
 * N은 3~15이하
 * <p>
 * [입력]
 * 첫줄은 테스트 케이스 수(T)가 주어진다.
 * 두번째 줄은 배열 크기(N)이 주어지고
 * 세번째 줄부터 N개의 기지국 정보가 제공된다.
 * <p>
 * [출력 결과]
 * #1 4
 * #2 5
 * #3 9
 */

try {
  let input = fs.readFileSync(filePath).toString().trim();
  const lines = input.split('\n');
  const tc = Number(lines[0]);

  let lineIndex = 1;
  for (let t = 0; t < tc; t++) {
    const N = Number(lines[lineIndex]);
    lineIndex++;

    let map = Array.from({ length: N }, (_, i) =>
      lines[lineIndex + i].trim().split(' ')
    );
    lineIndex += N;

    // 최종 출력을 저장할 변수 - 커버하지 못한 개수 세기용
    let AnswerN = 0;

    // 상하좌우 방향으로 좌표를 처리하기 위한 변수
    let dr = [-1, 1, 0, 0];
    let dc = [0, 0, -1, 1];
    let nr = 0,
      nc = 0,
      d = 4;

    //1. 기지국을 찾아 상하좌우에 커버됐다고 표시하기
    for (let r = 0; r < N; r++) {
      for (let c = 0; c < N; c++) {
        //1.2 기지국(A)를 찾는다.
        if (map[r][c] == 'A') {
          //1.3 기지국의 상하좌우의 좌표를 얻고 경계내에 있는지 검사
          for (let i = 0; i < d; i++) {
            // d => 사방(상하좌우) 처리
            nr = r + dr[i];
            nc = c + dc[i];
            // 경계 내애 있고, 해당 위치가 집이라면 기지국은 제외해야 함
            if (nr > -1 && nr < N && nc > -1 && nc < N && map[nr][nc] == 'H') {
              //1.4 상하좌우에 커버됐다고 표시한다.
              map[nr][nc] = 'X';
            }
          }
        }
      }
    }

    //2. 맵 전체를 탐색하면 커버 안된 집의 개수를 센다.
    for (let r = 0; r < N; r++) {
      for (let c = 0; c < N; c++) {
        if (map[r][c] === 'H') {
          console.log(`Covering house at: (${nr}, ${nc})`);
          AnswerN++;
        }
      }
    }
    // console.log(map);
    console.log(`#${t + 1} ${AnswerN}\n`);
  }
} catch (error) {
  console.error('Error code: ', error);
}
