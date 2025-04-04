import React, { useEffect, useState } from "react";

const Info = () => {
  const [name, setName] = useState("");
  const [nickname, setNickname] = useState("");

  /**
   * useEffect
   * 리액트 컴포넌트가 렌더링될 때마다 수행되는 hooks으로 
   * 렌더링될 때마다 특정 작업을 수행해될 일이 있는 경우 사용하는  hook  
   * 
   * componentDidMount + componentDidUpdate
   * 비동기 통신을 통해 서버에서 데이타를 가져올때 componentDidMount()에서 사용한다. 
     - 처음 렌더링이 끝난 후에 실행되므로 UI가 깜빡이는 현상을 줄일 수 있다. 
     - 만약 constructor나 render()에서 데이터를 요청하면, 
     데이터가 로드될 때마다 setState()가 호출되어 불필요한 렌더링이 발생할 수 있음.

   * useEffect(callback)     : 리액트 컴포넌트가 렌더링될 때마다 수행되는 hooks
   * useEffect(callback, []) : 맨 처림 렌더링 될 때만 수행하도록 설정
   * useEffect(callback, [state, props]) : 해당 state가 변경될 때마다 렌더링된다.
   */
  useEffect(
    (params) => {
      console.log("랜더링이 완료되었습니다.");
      console.log({ name, nickname });

      //컴포넌트가 언마운트 되기 전이나 업데이트 되기 전에  수행할 일이 있을 경우
      //return  클린업함수
      return () => {
        //이벤트 리스너 해제, 타이머 제거(clearTimeout, clearInterval), 웹소켓 연결 해제
        console.log("clean up");
        console.log(name);
      };
    },
    [name],
  );
  const onChangeName = (e) => {
    setName(e.target.value);
  };

  const onChangeNickname = (e) => {
    setNickname(e.target.value);
  };

  return (
    <div>
      <div>
        <input name="name" value={name} onChange={onChangeName} />
        <input name="nickname" value={nickname} onChange={onChangeNickname} />
      </div>
      <div>
        <div>
          <b>이름:</b> {name}
        </div>
        <div>
          <b>닉네임: </b> {nickname}
        </div>
      </div>
    </div>
  );
};

export default Info;
