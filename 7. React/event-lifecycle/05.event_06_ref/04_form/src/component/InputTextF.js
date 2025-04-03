import React, { useState } from "react";

function InputTextF(props) {
  const [value, setValue] = useState("");

  const handleSubmit = (e) => {
    alert(value);
    /*
      preventDefault() : 기본 돈작을 실행하지 않도록 지정하는 함수 

      submit : form양식이 서버에 전송 안됨.
      checkbox : check가 안됨.
      input  : 키 입력이 원하는 데이타 외에는 입력 칸을 채우는 것을 방지 
      */
    e.preventDefault();
  };
  const handleChange = (e) => {
    const { key } = e;
    console.log("handleChange:", key);

    /*
    정규식 표현  
    ^  : 문자열의 시작
    $  : 문자열의 끝
    범위 지정
    [a-z] : 소문자 a~z 중 하나
    [A-Z] : 대문자 A~Z 중 하나
    [0-9] : 숫자 0~9 중 하나
    [a-zA-Z0-9] : 영문 대소문자 + 숫자
    */
    if (
      !/^[a-zA-Z0-9]$/.test(key) &&
      !["Backspace", "Shift", "CapsLock", "Control", "Alt", "ArrowLeft", "ArrowRight"].includes(key)
    ) {
      e.preventDefault();
      alert("영문자 대,소문자와 숫자만 입력하세요.");
    } else {
      setValue(e.target.value);
    }
  };
  return (
    <form onSubmit={handleSubmit}>
      <label>
        Name:
        <input
          type="text"
          value={value}
          onChange={(e) => setValue(e.target.value)}
          onKeyDown={handleChange}
        />
      </label>
      <input type="submit" value="Submit" />
    </form>
  );
}

export default InputTextF;
