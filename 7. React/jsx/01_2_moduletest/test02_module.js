// export default는 모듈을 내보낼 대상은 단 한개이고 이름은 지정하지 않는다.  


export default {
  title: '계산기 모듈',
  add(i, j) {
    return i + j;
  },
  sub(i, j) {
    return i - j;
  },
};
