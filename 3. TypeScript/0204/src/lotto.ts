// DOM이 로드된 이후에 이벤트리스너를 실행
document.addEventListener('DOMContentLoaded', () => {
  document.querySelector('#btn')?.addEventListener('click', game);
});

function game(): void {
  // lotto 번호를 위한 배열 선언
  let lotto: number[] = [];
  while (lotto.length < 6) {
    let num: number = parseInt(Math.floor(Math.random() * 45) + 1 + ''); // 1 - 45 사이의 난수 발생 시키기
    // 같은 수 배제하기
    if (lotto.indexOf(num) == -1) lotto.push(num);
  }

  lotto.sort(function (a, b) {
    return a - b;
  });

  let view: string = '';
  let i: number = 0;

  let intervalId = setInterval(function () {
    // 로또 길이를 넘어가면 종료
    if (i >= lotto.length) {
      clearInterval(intervalId);
      return;
    }
    view += `<div class='ball ball${parseInt(
      Math.floor(lotto[i] / 10) + ''
    )}'>${lotto[i]}</div>`; // i++을 view에 포함하여 i 증가
    i++;

    let viewDiv = document.querySelector('#view');
    viewDiv == null ? '' : (viewDiv.innerHTML = view);
  }, 1000);
}
