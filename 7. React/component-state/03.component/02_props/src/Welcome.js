/* 
Props 
- 데이타 전달하는 방식
- element에 작성된 attibute값이나 content는 props를 통해 자식 Component에 전달된다.

<element key='value'>content</element>
ex)<Welcome name='React' />

자식 Component에서 Props로 전달 받기 

함수형   : arguement로 전달된 props 사용
class형 : 상속받은 React.Component의 this.porps 사용
*props : readOnly
*/

//부모로 부터 props값이 전달되지 않을때 기본값
// import React from "react";
// import PropTypes from "prop-types";

// const Welcome = (props) => {
//   return (
//     <div>
//       <h1 style={props.style}> Hello, {props.name}</h1>
//       <h2>content:{props.children}</h2>
//     </div>
//   );
// };

import React, { Component } from "react";
import PropTypes from "prop-types";
class Welcome extends Component {
  render() {
    const { name, style, children } = this.props;
    return (
      <div>
        <h1 style={style}> Hello, {name}</h1>
        <h2>content:{children}</h2>
      </div>
    );
  }
}

//부모로 부터 props 값이 전달되지 않을 때 사용할 기본값을 선언
Welcome.defaultProps = {
  name: "react",
  style: {
    backgroundColor: "gray",
    color: "aqua",
    fontSize: "30px",
    fontWeight: "bold",
    padding: 6,
  },
};

Welcome.propTypes = {
  name: PropTypes.string.isRequired,
  style: PropTypes.object,
};

export default Welcome;