window.onload = function () {
  document.getElementById("btn-add").addEventListener("click", function () {
    var listDiv = document.getElementById("poll-answer-list");
  
    var divEl = document.createElement("div"); // <div></div>
    divEl.setAttribute("class", "poll-answer-item");// <div class="poll-answer-item"></div>
    var inputEl = document.createElement("input");
    inputEl.setAttribute("type", "text");
    inputEl.setAttribute("name", "answer");
    var btnEl = document.createElement("button");
    btnEl.setAttribute("type", "button");
    btnEl.setAttribute("class", "button");
    btnEl.appendChild(document.createTextNode("삭제"));

    btnEl.addEventListener('click', function () {
      var parent = this.parentNode;
      listDiv.removeChild(parent);
    });

    divEl.appendChild(inputEl);
    divEl.appendChild(btnEl);

    listDiv.appendChild(divEl);
  });  


  //btn-make를 찾아서 이벤트 붙이기 
  document.querySelector("#btn-make").addEventListener('click', function(){
    //question을 찾아서 질문 내용을 얻어오기 
    var question = document.querySelector('#question')
    if(!question.value){
      alert('질문 내용을 입력해 주세요')
      question.focus();
      return;
    }
    
    //answer들을 찾아서 
    var answers = document.getElementsByName('answer');
    //answer들에 값이 다 있는지 확인
    for(var i=0; i<answers.length; i++){
      if(!answers[i].value){
        alert('답변 항목을 입력해 주세요')
        answers[i].focus();
        return
      }
    }
    //answer의 값을 배열에 추가 
    var answerArr = [];
    for(var i = 0; i<answers.length; i++){
      answerArr.push(answers[i].value)
    }


    //question과 answer들을 localStorage에 추가 
    localStorage.setItem('question', question.value);
    // JSON.stringify()  : 객체나 배열을 JSON형태의 문자열로 변경 
    localStorage.setItem('answers',JSON.stringify(answerArr));

    alert('투표를 생성합니다.')
    opener.location.reload(); //opener에 생성된 투표를 반영
    self.close();             //현재(투표)창은 닫기.
  });

};

