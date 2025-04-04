import React, { Component } from "react";
/**
 * 라이프 사이클 콜백 함수가 두번씩 실행되는 이유
 * - StrictMode를 사용할 경우 개발 모드에서 부작용(side effect) 감지를 위한 React의 의도적인 동작으로
 *   특정 라이프 사이클 콜백만 두번씩 실행 시킨다.
 * - 배포를 하게되면 정상적으로 한번씩만 수행된다.
 *
 * 배포하기
 * npm run build
 *
 * 서버 설치하기
 * npm install -g serve
 *
 * 서버 실행하기
 * serve -s build         http://localhost:3000 으로 접속할 수 있다.
 */
class LifeCycleSample extends Component {
  state = {
    number: 0,
    color: null,
  };
  myRef = null; // ref를 설정할 부분
  constructor(props) {
    super(props);
    console.log("LifeCycleSample.constructor");
  }

  /*
  부모에서 전달하는 props의 값 변화로  state가 변하는 경우 사용하는 callback
  부모에서 전달하는 props의 값이 변할때 자동 호출된다. 
  ==> 현재 이함수가 없다면 color가 변하지 않는다.
  */
  static getDerivedStateFromProps(nextProps, prevState) {
    console.log("LifeCycleSample.getDerivedStateFromProps");
    if (nextProps.color !== prevState.color) {
      return { color: nextProps.color };
    }
    return null;
  }
  /*
  컴포넌트가 마운트 된 후에 호출되는  callback

  - 비동기 통신을 통해 서버에서 데이타를 가져올때 componentDidMount()에서 사용한다. 
     - 처음 렌더링이 끝난 후에 실행되므로 UI가 깜빡이는 현상을 줄일 수 있다. 
     - 만약 constructor나 render()에서 데이터를 요청하면, 
     데이터가 로드될 때마다 setState()가 호출되어 불필요한 렌더링이 발생할 수 있음.
  */
  componentDidMount() {
    console.log("componentDidMount");
  }

  /*
    props or state가 변경됐을 때 re-rendering을 할지 여부를 지정하는 메서드
    override하지 않으면 true로 리턴된다. 
  */
  shouldComponentUpdate(nextProps, nextState) {
    console.log("shouldComponentUpdate", nextProps, nextState);
    // 숫자의 마지막 자리가 4면 리렌더링하지 않습니다.
    return nextState.number % 10 !== 4;
  }

  componentWillUnmount() {
    console.log("componentWillUnmount");
  }
  
  handleClick = () => {
    this.setState({
      number: this.state.number + 1,
    });
  };

  /*
  render()에서 만들어진 결과물을 브라우져에 반영하기 전에 호출되는 callback
  DOM에서 직접 정보를 읽고, 업데이트 후에도 해당 정보를 유지해야 할 때 사용
  ex)스크롤 위치 유지, 애니메이션 동기화, 동적인 레이아웃 변경 시 활용 가능
  */
  getSnapshotBeforeUpdate(prevProps, prevState) {
    console.log(
      "getSnapshotBeforeUpdate   prevProps.color:",
      prevProps.color,
      " this.props.color",
      this.props.color
    );
    if (prevProps.color !== this.props.color) {
      return this.myRef.style.color;
    }
    return null;
  }
  /**
   * re-redering을 완료한 후 실행된다.
   */
  componentDidUpdate(prevProps, prevState, snapshot) {
    console.log("componentDidUpdate", prevProps, prevState);
    if (snapshot) {
      console.log("업데이트되기 직전 색상: ", snapshot);
    }
  }
  render() {
    console.log("render");
    const style = {
      color: this.state.color,
    };
    return (
      <div>
        {
          //일부러 error 발생 시켜 ErrorBoundary가 실행되는 지 확인
          // this.props.missing.value
        }
        <h1 style={style} ref={(ref) => (this.myRef = ref)}>
          {this.state.number}
        </h1>
        <p>color: {this.state.color}</p>
        <button onClick={this.handleClick}>더하기</button>
      </div>
    );
  }
}
export default LifeCycleSample;
