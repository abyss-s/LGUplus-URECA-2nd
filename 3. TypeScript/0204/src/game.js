var Choices = {
    Rock: 0,
    Scissors: 1,
    Paper: 2,
};
var wins = 0;
var losses = 0;
var draws = 0;
var intervalId = null;
var computerValue = -1; // 초기값 설정
document.addEventListener('DOMContentLoaded', function () {
    var _a;
    (_a = document.querySelector('#btn')) === null || _a === void 0 ? void 0 : _a.addEventListener('click', startGame);
});
document.querySelectorAll('.choice').forEach(function (item) {
    item.addEventListener('click', function (event) { return userChoice(event); });
});
function startGame() {
    var viewDiv = document.querySelector('#view');
    if (!viewDiv)
        return;
    viewDiv.innerHTML = ''; // 초기화
    var images = [
        'images/rock.png',
        'images/scissors.png',
        'images/paper.png',
    ];
    intervalId = setInterval(function () {
        var randomIndex = Math.floor(Math.random() * images.length);
        computerValue = randomIndex; // 컴퓨터 값
        viewDiv.innerHTML = "<div class='result'><img src='".concat(images[randomIndex], "' /></div>");
    }, 500);
}
function userChoice(event) {
    if (intervalId) {
        clearInterval(intervalId);
    }
    // 유저값은 html data-value에서 받아오기
    var userValue = parseInt(event.currentTarget.dataset.value);
    var result = determineWinner(userValue, computerValue);
    updateScore(result);
    var resultDiv = document.querySelector('#result');
    resultDiv.innerHTML = "<h3>\uACB0\uACFC: ".concat(result, "!!</h3>");
}
function determineWinner(user, computer) {
    if (user === computer) {
        return 'draw';
    }
    else if ((user === Choices.Rock && computer === Choices.Scissors) ||
        (user === Choices.Scissors && computer === Choices.Paper) ||
        (user === Choices.Paper && computer === Choices.Rock)) {
        return 'win';
    }
    else {
        return 'loss';
    }
}
function updateScore(result) {
    var winsDom = document.querySelector('#wins');
    var lossesDom = document.querySelector('#losses');
    var drawsDom = document.querySelector('#draws');
    if (result === 'win') {
        wins++;
        winsDom.textContent = wins.toString();
    }
    else if (result === 'loss') {
        losses++;
        lossesDom.textContent = losses.toString();
    }
    else if (result === 'draw') {
        draws++;
        drawsDom.textContent = draws.toString();
    }
}
