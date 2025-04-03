import "./App.css";
import ValidationSample from "./ValidationSample";
// import ScrollBox from './ScrollBox';
// import { useRef } from "react";
function App() {
  //함수형 컴포넌트에서는  ref를 훅을 이용해서 사용해야 한다.
  // const scrollBoxRef = useRef(null);
  return (
    <div className="App">
      <ValidationSample />
      {/* 
      <ScrollBox ref={ scrollBoxRef } />
      <button onClick={() => {
        scrollBoxRef.current.scrollToBottom();
       }
      }>맨 아래로</button> */}
    </div>
  );
}

export default App;
