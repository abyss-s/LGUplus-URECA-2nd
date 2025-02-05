
/*사용자 인증 처리를 위한 함수 */
function login() {
  //id 입력 받을 수 있는 prompt
  var id = prompt("아이디 입력", "ssafy");

  //password 입력 받을 수 있는 prompt
  var password = prompt("비밀번호 입력", "1234");

  //id와 password확인
  if (id.length == 0) {
    alert("아이디를 입력하세요!!!");
    return;
  }
  if (password == "") {
    alert("비밀번호를 입력하세요!!!");
    return;
  }

  if (id == "ssafy" && password == "1234") {
    //인증이 되면 이미지 변경, 메뉴 변경
    document.getElementById("header_nav_confirm_off").style.display = "none";
    document.getElementById("header_nav_confirm_on").style.display = "block";
    document.querySelector(".profile_img").src = "img/profile.png";
  } else {
    //인증이 안되면 확인창 띄우기
    alert("아이디 또는 비밀번호를 확인해 주세요");
  }
}

function logout() {
  document.getElementById("header_nav_confirm_off").style.display = "block";
  document.getElementById("header_nav_confirm_on").style.display = "none";
  document.querySelector(".profile_img").src = "img/noimg.png";
}

var cnt = 0;
function allSlide(flag) {
  var sub = document.getElementsByClassName("store_item_sub");
  var storeOff = document.getElementById("store_display_off");
  var storeOn = document.getElementById("store_display_on");

  if (flag == "on") {
    for (var i = 0; i < sub.length; i++) {
      sub[i].style.display = "block";
    }
    storeOff.style.display = "block";
    storeOn.style.display = "none";
    cnt = 4;
  } else {
    for (var i = 0; i < sub.length; i++) {
      sub[i].style.display = "none";
    }
    storeOff.style.display = "none";
    storeOn.style.display = "block";
    cnt = 0;
  }
}

function slideToggle(areaid) {
  if (areaid.style.display == "none") {
    areaid.style.display = "block";
    cnt++;
  } else {
    areaid.style.display = "none";
    cnt--;
  }
  var storeOff = document.getElementById("store_display_off");
  var storeOn = document.getElementById("store_display_on");
  if (cnt == 4) {
    storeOff.style.display = "block";
    storeOn.style.display = "none";
  } else {
    storeOff.style.display = "none";
    storeOn.style.display = "block";
  }
}

function poll() {
  var votes = document.getElementsByName("vote_answer");

  var choose = "";
  var chooseCnt = 0;
  for (var i = 0; i < votes.length; i++) {
    if (votes[i].checked) {
      chooseCnt++;
      choose = votes[i].value;
      break;
    }
  }
  if (chooseCnt == 0) {
    alert("설문 항목을 선택해 주세요");
    return;
  } else {
    alert(choose + "항목을 선택했습니다.");
  }
}

function makePoll() {
  window.open("makepoll.html", "poll", "width=400,height=300,top=300,left=300");
}
