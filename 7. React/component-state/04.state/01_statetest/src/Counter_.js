/*
 state 
  - 컴포넌트 내부에서 사용하는 상태 값
  - 변경할 때는 Component가 제공하는 setState()함수를 통해서만 변경한다. 
    ==> 객체를 state로 사용하는 경우 불변성을 유지해야 한다.  
    ==> state로 사용하는 객체를 직접 변경하는 경우 객체는 같은 객체이므로 
        react는 변경된 내용을 감지하지 못해 re-rendering이 안될 수 있으므로 
        deep copy를 통해 새로운 객체를 만들어서 내용을 변경 후 setState()로 변경해야 re-rendering된다.

  Component에서 사용하는 Variable과  Props와 State의 차이점
  variable  
    - Component 내에서 const, let을 통해 선언한 변수
    - 값이 변경되도 react가 인지하지 못하므로 re-rendering이 되지 않는다. 

  props
    - 부모 Component에서 자식 컴포넌트를 사용할때 값을 전달하는 수단. 
    - 부모 Component가 rendering될때 자식 Component도 rendering 된다. 

  state
    - Component 내에서 사용하는 상태 값 setState()함수를 통해 변경됐을 때 
      re-rendering된다.
*/
import React, { useState } from 'react';

const Counter = () => {
    /*
        함수형 Component에서 state 사용
        형식 = const[state명, setter] = useState(기본값);
        setter : set + state명    
    */
    const [count, setCount] = useState(0);

    const onIncrease = () => {
        setCount(count + 1);
    };
    const onDecrease = () => {
        setCount(count - 1);
    };

    return (
        <div>
            <h1>Fun Count : {count}</h1>
            <button onClick={onIncrease}>+</button>
            <button onClick={onDecrease}>-</button>
        </div>
    );
};

export default Counter;