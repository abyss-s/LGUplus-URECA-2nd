import React, { useState } from 'react';

const IterationSample = () => {
  const [names, setNames] = useState([
    { id: 1, text: '눈사람' },
    { id: 2, text: '얼음' },
    { id: 3, text: '눈' },
    { id: 4, text: '바람' }
  ]);
  const [inputText, setInputText] = useState('');
  const [nextId, setNextId] = useState(5);

	//state의 객체를 변경할 때는 deep copy 후 변경된 내용을 setState함수를 통해 변경해야 한다.
  //push()  : 배열에 직접 추가하기 때문에 리액트의 상태 불변성이 깨진다. 
  //          배열의 내부 데이터만 변경되서 React가 상태 변경을 감지하지 못해서 
  //          랜더링이 정상적으로 발생하지 않을 수 있다. 
  //names.push({ id: nextId, text: inputText });
  //배열.concat() : 기존의 배열을 직접 수정하지 않고 새로운 배열을 생성해서 반환한다.
     
   
   /*
	  배열.map(callback(currentValue, index, array)
	          ,[this])
	  callback
	    currentValue : 현재 처리하고 있는 요소
	    index : 현재 처리하고 있는 요소의 index
	    array : 원보 배열 
	  this : callback함수에서 사용할 this
	
	  key : 
	    컴포넌트의 배열을 랜더링했을 때 어떤 원소에 변동이 있었는지 알아내기 위한 속성
	    key는 컴포넌트를 식별할 유일값으로 사용해야 한다. 
	    없다면 index를 사용하기 
  */
  return (
    <div>
      <input type='text' value='' onChange='' />
      <button onClick=''>추가</button>
      <ul></ul>
    </div>  
  );
};

export default IterationSample;
