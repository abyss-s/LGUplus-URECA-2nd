/*
react에서 이미지 로딩하기 
1. public 경로에 있는 이미지 
   - 경로명으로 바로 접근할 수 있다. 
   - Webpack에 의해 번들링이 되지 않기 때문에 이미지가 많거나 프로젝트가 커질수록 관리가 어려워진다. 

2. src/assets 경로에 있는 이미지
   - Webpack과 같은 모듈 번들러를 사용하여 이미지를 처리할 수 있으며 이미지를 최적화하고 번들에 포함시킬 수 있다.
   - 사용방법 
     2.1  import를 통해 상수로 사용하기 
     import 상수명 from '경로'
     <img src={상수명} alt="My Image" />
     ex)
     import myImage from '../assets/images/my-image.jpg';
     <img src={myImage} alt="My Image" />

     2.2 require() 함수를 이용하여 module import하기 
     require(이미지경로)
*/
import React from 'react';
import Comment from './Comment';
const comment = {
  date: new Date(),
  text: "I hope you enjoy learning React!",
  author: {
    name: "Hello Kitty",
    avatarUrl: require("./assets/images/kitty.jpg"),
  },
};

const App = () => {
  return (
    <div>
      <Comment date={comment.date} text={comment.text} author={comment.author}></Comment>
    </div>
  );
};

export default App;