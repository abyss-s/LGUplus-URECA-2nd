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
