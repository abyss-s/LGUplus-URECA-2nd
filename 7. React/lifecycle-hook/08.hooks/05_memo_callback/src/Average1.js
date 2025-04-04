/*
input 양식에서 입력된 데이타의 평균을 구해 화면에 출력하는 기능 

input에 값이 입력 될때마다 onchange가 호출되어  state(number)가 변해서  re-rendering된다.
그래서 getAverage() 함수도 매번 수행된다. 
값이 등록되지 않고 수정만 해도 매번 수행된다.
*/


/*
  array.reduce(callback, initialValue)
    callback(accumulator, current, currentIndex, array)
      accumulator : reduce를 한 이전 결과, 처음인 경우  initialValue가 있으면 initialValue 
      current : 처리할 현재 요소
      currentIndex : 처리할 현재 요소의 index,  initialValue가 있으면 0, 없으면 1부터 시작
      array : reduce를 시작한 배열 
*/


/*  
    <div>
      <input value=''  onChange='' />
      <button onClick=''>등록</button>
      <ul>
        
          <li key=''></li>
        
      </ul>
      <div>
        <b>평균값:</b>
      </div>
    </div>
*/
  
