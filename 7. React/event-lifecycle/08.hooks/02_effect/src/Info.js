import React from "react";

const Info = () => {
  /**
   * 리액트 컴포넌트가 렌더링될 때마다 수행되는 hooks
   * componentDidMount + componentDidUpdate
   * useEffect(callback)     : 리액트 컴포넌트가 렌더링될 때마다 수행되는 hooks
   * useEffect(callback, []) : 맨 처림 렌더링 될 때만 수행하도록 설정
   * useEffect(callback, [state, props]) : 해당 state가 변경될 때마다 렌더링된다.
   */

  return (
    <div>
      <div>
        <input name="name" value="" onChange="" />
        <input name="nickname" value="" onChange="" />
      </div>
      <div>
        <div>
          <b>이름:</b>
        </div>
        <div>
          <b>닉네임: </b>
        </div>
      </div>
    </div>
  );
};

export default Info;
