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

// /*
// 강사님 답안 코드
// */

// //로또 생성 버튼에 이벤트 추가하기
// document.querySelector('#btn')?.addEventListener('click', game);

// //버튼이 클릭됐을 때를 위한 handler 함수
// function game(): void {
//   //lotto 번호를 위한 배열 선언
//   let lotto: number[] = [];

//   //lotto 번호 6개 생성하기
//   while (lotto.length < 6) {
//     let num: number = parseInt(Math.random() * 45 + 1 + ''); //1 - 45 사이의 난수 발생 시키기
//     //같은 수 배제하기  : 새로 뽑은 번호가 로또 배열에 없으면
//     if (lotto.indexOf(num) == -1) lotto.push(num); //배열에 새로운 번호 추가하기
//   }

//   //랜덤하게 뽑은 번호를 순서에 맞게 정렬하기
//   lotto.sort(function (a, b) {
//     return a - b;
//   });

//   //로또 번호를 출력하기 위한 문자열
//   let view: string = '';

//   let i: number = 0; //출력할 로또 번호를 위한 인덱스

//   //1초 마다 로또 번호를 출력하기 위한 처리
//   let intervalId = setInterval(function () {
//     //배열의 개수와 출력한 index가 같으면 모두 출력한 경우 이므로 setInterval 종료
//     if (lotto.length == i) {
//       clearInterval(intervalId);
//       return;
//     }

//     //로또 번호에 클래스 추가( 현재번호/10 한 번호가 클래스 번호)
//     view += `<div class='ball ball${parseInt(lotto[i] / 10 + '')}'>${
//       lotto[i++]
//     }</div>`;
//     let viewDiv = document.querySelector('#view');
//     viewDiv == null ? '' : (viewDiv.innerHTML = view);
//   }, 1000);
// }
