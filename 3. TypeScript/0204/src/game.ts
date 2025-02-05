const Choices = {
  Rock: 0,
  Scissors: 1,
  Paper: 2,
};

let wins: number = 0;
let losses: number = 0;
let draws: number = 0;
let intervalId: number = 0;
let computerValue: number = 0;

document.addEventListener('DOMContentLoaded', () => {
  document.querySelector('#btn')?.addEventListener('click', startGame);
});

document.querySelectorAll('.choice').forEach((item) => {
  item.addEventListener('click', (event) => userChoice(event as MouseEvent));
});

function startGame(): void {
  const viewDiv = document.querySelector('#view');
  if (!viewDiv) return;

  viewDiv.innerHTML = ''; // 초기화

  const images: string[] = [
    'images/rock.png',
    'images/scissors.png',
    'images/paper.png',
  ];

  intervalId = setInterval(() => {
    const randomIndex = Math.floor(Math.random() * images.length);
    computerValue = randomIndex; // 컴퓨터 값
    viewDiv.innerHTML = `<div class='result'><img src='${images[randomIndex]}' /></div>`;
  }, 500);
}

function userChoice(event: MouseEvent): void {
  if (intervalId) {
    clearInterval(intervalId);
  }

  // 유저값은 html 파일의 data-value에서 받아오기
  const userValue = parseInt(
    (event.currentTarget as HTMLElement).dataset.value!
  );
  const result = determineWinner(userValue, computerValue);
  updateScore(result);

  const resultDiv = document.querySelector('#result') as HTMLElement;
  resultDiv.innerHTML = `<h3>결과: ${result}!!</h3>`;
}

function determineWinner(user: number, computer: number): string {
  if (user === computer) {
    return 'draw';
  } else if (
    (user === Choices.Rock && computer === Choices.Scissors) ||
    (user === Choices.Scissors && computer === Choices.Paper) ||
    (user === Choices.Paper && computer === Choices.Rock)
  ) {
    return 'win';
  } else {
    return 'loss';
  }
}

function updateScore(result: string): void {
  const winsDom = document.querySelector('#wins') as HTMLSpanElement;
  const lossesDom = document.querySelector('#losses') as HTMLSpanElement;
  const drawsDom = document.querySelector('#draws') as HTMLSpanElement;

  if (result === 'win') {
    wins++;
    winsDom.textContent = wins.toString();
  } else if (result === 'loss') {
    losses++;
    lossesDom.textContent = losses.toString();
  } else if (result === 'draw') {
    draws++;
    drawsDom.textContent = draws.toString();
  }
}

// /*
// 강사님 답안 코드
// */

// //컴퓨터가 선택한 가위바위보를 화면에 표시하기 위한 이미지 배열
// let images:string[]=['rock.png','scissors.png','paper.png'];

// //컴퓨터의 다음 이미지를 선택하기 위한 변수
// let choiceImage:number=0;

// //이미지를 순서대로 화면에 표시하기 위해 현재 표시되는 이미지 상태를 위한 변수
// const state:{ 바위: number,
//   가위: number,
//   보: number} = {
//   바위: 0,
//   가위: 1,
//   보: 2
// };

// //setInterval를 종료하기 위한 id
// let interval:number|undefined=undefined;

// //컴퓨터 이미지를 화면에 표시하기 위한 함수
// function showComputerImage() {
//   interval = setInterval(function () {
//     //현재 상태에 따라 다음 상태로 변화 하기 위한 분기
//     if (choiceImage === state.바위) {
//         choiceImage = state.가위
//     } else if (choiceImage === state.가위) {
//         choiceImage = state.보;
//     } else {
//         choiceImage = state.바위;
//     }
//     //새로 선택된 상태를 이용해서 화면에 이미지 변경 시키기
//     document.querySelector("#computer")?.setAttribute('src',`${images[choiceImage]}`)
//   }, 200);
// }

// //가위 바위 보 승패를 가리기 위한 점수 판
//                     // 바위 가위 보
// const score:number[] = [0 , 1 , -1];

// let playTime:number = 0;
// let winNumber:number = 0;
// let loseNumber:number = 0;
// let drawNumber:number = 0;

// //승패 결과를 표시 하기위한 변수
// let result:HTMLElement|null=null;

// window.onload= function(){
//     //화면에 컴퓨터 이미지를 표시하기 위한 함수 호출
//     showComputerImage()
//     //결과를 출력하기 위한 div
//     const result = document.getElementById('result');
//     //결과를 초기화 하는 버튼
//     const init = document.getElementById('init');

//     init==null?'':init.addEventListener('click', initRecord);

//     //가위 바위 보 이미지들에 이벤트 추가하기
//     document.querySelectorAll('.btn').forEach(function (btn) {
//         //typescipt에는 이벤트 함수에서 this가 자동으로 bind되지 않으므로 함수 인자에 표시해야 한다.
//         //이벤트 함수 첫번째 인자는 this, 두번째 인자는 event가 전달된다.
//         btn.addEventListener('click', function(this:Element){
//             //사용자가 선택했으니 컴퓨터 이미지는 종료시킨다.
//             clearInterval(interval);
//             playTime++;

//             //최가화 버튼이 화면에 표시되게 display 변경하기
//             init==null?'':init.style.display = 'inline-block';

//             console.log('this:',this.id)

//             // console.log(e.target==null?null:e.target)
//             // 선택한 이미지를 점수 계산하기 위해 score에 따른 값으로 변경한다.
//             let myPick:number =0;
//             if(this.id=='바위'){
//               myPick=0;
//             }else if(this.id=='가위'){
//               myPick=1;
//             }else if(this.id=='보'){
//               myPick=2;
//             }

//             const myScore = score[myPick];
//             const computerScore = score[choiceImage];
//             const scoreGap = myScore - computerScore;
//             if (scoreGap === 0) {
//                 alert('비겼다 !');
//                 drawNumber++;
//             } else if ([-1, 2].includes(scoreGap)) {
//                 alert('이겼다 !');
//                 winNumber++;
//             } else {
//                 alert('졌다 !');
//                 loseNumber++;
//             }
//             showComputerImage();
//             result==null?'':result.innerHTML = "플레이 횟수 = " + playTime + " 게임" + "<br>" + "<br>" +
//                 winNumber + " 승 " + loseNumber + " 패 " + drawNumber + " 무";
//         });
//     });
// }
// function initRecord() {
//   playTime = 0;
//   winNumber = 0;
//   loseNumber = 0;
//   drawNumber = 0;
//   result==null?'': result.innerHTML = "플레이 횟수 = " + playTime + " 게임" + "<br>" + "<br>" +
//         winNumber + " 승 " + loseNumber + " 패 " + drawNumber + " 무";
// }
