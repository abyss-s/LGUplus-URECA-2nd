import "./App.css";

/*
함수 컴포넌트와 클래스 컴포넌트
1. 컴포넌트를 JavaScript 함수를 작성할 수 있다.
2. Component 클래스를 상속 받아 작성할 수 있다. 

 */

// function App() {
//   return (
//     <div className="App">
//       <h1> Hello React!!! + Eureka </h1>
//     </div>
//   );
// }

import React, { Component } from "react";

class App extends Component {
  render() {
    return (
      <div>
        <hi>Hello React!!! + Eureka </hi>
      </div>
    );
  }
}
export default App;
