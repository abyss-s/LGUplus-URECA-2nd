import React, { Component } from "react";

/*
  1.JSX - camelcase표현식을 사용한다.
  backgroud-color   ==> backgroundColor
  class             ==> className
  tabindex          ==> tabIndex
*/
class App extends Component {
  //component의 함수를 사용할 때는  this.함수명으로 사용해야 한다.
  formatName(user) {
    return user.firstName + " " + user.lastName;
  }

  render() {
    // function formatName(user) {
    //   return user.firstName + " " + user.lastName;
    // }
    const user = {
      firstName: "React",
      lastName: "Component",
    };

    const type = "class";

    const unDfine = undefined;
    //2.JSX에서는  style을 DOM 요소에 문자열 형태가 아닌 객체로 만들어 {}로 설정해야 한다.
    const style = {
      backgroundColor: "black",
      color: "pink",
      fontSize: "50px",
      fontWeight: "bold",
      padding: 6,
    };
    return (
      <div>
        {/*3. JSX 문법 주석   {//주석 } */}
        {/*
          4. JSX - HTML code , {Javascript 표현식} 
          5. 여러개의 요소를 이용해 Component를 만들경우 반드시 하나의 요소로 감싸줘야 한다. (root 태그 필수)
        */}
        <div style={style}>
          {" "}
          Hello {/*formatName(user)*/}
          {
            //6. 조건부 렌더링
            //6-1. 조건부 연산자(? :) 사용
            // jsx 내부의 js 표현식에서는 if문을 사용할 없으므로 조건부 연산자(? :)를 사용해야 한다.
            // if(user){
            // }
            // user != null ? this.formatName(user) : ""
            //6-2. and 연산자를 이용한 조건부 랜더링
           
          }
          -{  }
          {
            //5-3. || 연산자를 이용하여 undifined  렌더링 하지 않기
            
          }
        </div>
      </div>
      // <h1></h1>                //error 발생
    );
  }
}

export default App;
