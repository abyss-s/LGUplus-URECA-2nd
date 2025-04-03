import React, { useState } from 'react';

const IterationSample = () => {
  /*
  배열.map(callback(currentValue, index, array)
          ,[this])
  callback
    currentValue : 현재 처리하고 있는 요소
    index : 현재 처리하고 있는 요소의 index
    array : 원본 배열 
  this : callback함수에서 사용할 this

  key : 
    컴포넌트의 배열을 랜더링했을 때 어떤 원소에 변동이 있었는지 알아내기 위한 속성
    key는 컴포넌트를 식별할 유일값으로 사용해야 한다. 
    없다면 index를 사용하기 
  */

  const [name, setName] = useState(['눈사람', '얼음', '눈', '바람', '꽃', '꽃망울']);
  const [inputText, setInputText] = useState('');
  const [nextId, setNextId] = useState(7);

  const handleRemove = (index) => {
    const newNameList = name.filter((_, i) => i !== index);
    setName(newNameList);
  };

  const nameList = name.map((name, i) => (
    <li key={name} onClick={() => handleRemove(i)}>
      {name}
    </li>
  ));

  return <ul>{nameList}</ul>;
};

export default IterationSample;
