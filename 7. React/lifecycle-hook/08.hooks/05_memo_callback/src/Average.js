import React, { useState } from 'react';

/*
  render함수에서 호출되고 있기 때문에 list가 변하지 않았는데도 
  input 양식의 값이 변경되면(값을 입력, 수정만 해도) number state가 변경되므로 
  render 함수가 호출된다. ==> getAverage 함수도 호출된다. 
  ==>  useMemo로 변경됐을 때만 호출되게 수정
*/
const getAverage = (numbers) => {
  if (numbers.length === 0) return 0;
  if (numbers.length === 1) return numbers[0];

  /*
  array.reduce(callback, initialValue)
    callback(accumulator, current, currentIndex, array)
      accumulator : reduce를 한 이전 결과, 처음인 경우  initialValue가 있으면 initialValue 
      current : 처리할 현재 요소
      currentIndex : 처리할 현재 요소의 index,  initialValue가 있으면 0, 없으면 1부터 시작
      array : reduce를 시작한 배열 
*/
  const sum = numbers.reduce((a, b) => {
    console.log('a:', a, 'b:', b);
    return a + b;
  });
  return sum / numbers.length;
};

const Average = () => {
  //input으로 입력 받은 데이타를 저장하는 list  ==> 평균을 구할 대상
  const [list, setList] = useState([]);
  //input양식과 양방향 binding할
  const [number, setNumber] = useState('');

  const onChange = (e) => {
    setNumber(e.target.value);
  };

  const onClick = (params) => {
    const nextList = list.concat(parseInt(number));
    setList(nextList);
    setNumber('');
  };

  return (
    <div>
      <input value={number} onChange={onChange} />
      <button onClick={onClick}>등록</button>
      <ul>
        {list.map((value, index) => (
          <li key={index}>{value}</li>
        ))}
      </ul>
      <div>평균값 : {getAverage(list)}</div>
    </div>
  );
};

export default Average;
