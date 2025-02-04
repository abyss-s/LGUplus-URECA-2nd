function printMsg(message: string, isCritical: boolean): void {
  // console.log(message);

  if (isCritical) {
    // 객체 없이 호출할 수 있는 함수는 window
    // window.alert(message);

    // 따라서 this를 그냥 호출할 경우 window를 가리킴
    // console.log를 이용해 항상 this가 어느 객체인지 확인하고 사용하기
    alert(message);
  }
}

printMsg('hello', false);
printMsg('경고', true);
