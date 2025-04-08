import React, { useMemo, useRef, useState } from 'react';

const getAverage = (numbers) => {
  if (numbers.length === 0) return 0;
  if (numbers.length === 1) return numbers[0];

  const sum = numbers.reduce((a, b) => {
    console.log('a:', a, 'b:', b);
    return a + b;
  });
  return sum / numbers.length;
};

const Average2 = () => {
  const [list, setList] = useState([]);
  const [number, setNumber] = useState('');
  /*
  useMemo(Callback, [state])
  - component 내에서 state에 대한 계산 처리를 할 때 
  - 변경됐을 때만 다시 계산하고 변경되지 않은 경우에는 기존의 값을 재사용
   */
  const avg = useMemo(() => getAverage(list), [list]);
  const prevOnChange = useRef();
  const prevOnClick = useRef();

  /*
  react에서는 함수형 Component는 렌더링 될 때마다 컴포넌트 함수를 실행시키므로 
  onChange, onClick  함수는 렌더링될 때마다 새로 생성이된다. 
  ==> useCallback()을 사용한다. 
  */
  const onChange = (e) => {
    setNumber(e.target.value);
  };
  const onClick = (params) => {
    const nextList = list.concat(parseInt(number));
    setList(nextList);
    setNumber('');
  };
  console.log(' onChange :', prevOnChange.current === onChange);
  console.log(' onClick :', prevOnClick.current === onClick);
  prevOnChange.current = onChange;
  prevOnClick.current = onClick;
  return (
    <div>
      <input value={number} onChange={onChange} />
      <button onClick={onClick}>등록</button>
      <ul>
        {list.map((value, index) => (
          <li key={index}>{value}</li>
        ))}
      </ul>
      <div>평균값 : {avg}</div>
    </div>
  );
};

export default Average2;
