import React, { Component } from "react";
/**
 * ref
 * react에서 DOM요소에 직접 접근할 수 있도록 연결하는 기능
 * - input 요소에 focus 주기
 * - 스크롤 박스 조작하기
 * - canvas 요소에 그림 그리기
 *
 * 클래스형 컴포넌트
 * 형식 1]
 * <요소  ref={ ref =>{this.input=ref}}/>
 *
 * 형식 2]
 * 속성명 =  React.createRef();
 * <요소  ref={this.속성명}/>
 *
 * 함수형
 * const 변수명 = useRef(null);
 * <요소  ref={변수명}/>
 */
export default class OneWayBind extends Component {
  inputPW = React.createRef();
  inputId = React.createRef();
  state = {
    id: "",
    pw: "",
  };

  handleClick = () => {
    const id = this.inputId.current.value;
    const pw = this.inputPW.current.value;
    this.setState({ id, pw });
    this.inputId.current.value = "";
    this.inputPW.current.value = "";
  };

  render() {
    return (
      <div>
        {/* JSX에서 label을 작성할 때 for가 아닌 htmlFor를 사용한다.  */}
        <label htmlFor="id">아이디:</label>
        <input type="text" id="id" ref={this.inputId}></input>
        <label htmlFor="password">password:</label>
        <input type="password" id="password" ref={this.inputPW}></input>
        <button onClick={this.handleClick}>검증하기</button>
      </div>
    );
  }
}
