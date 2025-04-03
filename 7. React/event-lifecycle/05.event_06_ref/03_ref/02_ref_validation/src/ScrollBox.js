/*
    overflow 상황에서 
    clientHeight : 요소 - (스크롤+보더)
    scrollHeight : 요소 내부의 content 크기
    scrollTop : content의 맨 처음 부터 현재 화면에 보이기 전까지
*/
const style = {
  border: "1px solid black",
  height: "300px",
  width: "300px",
  overflow: "auto", //요소의 콘텐츠가 요소보다 클때 처리 방식
  position: "relative",
};
const innerStyle = {
  width: "100%",
  height: "1000px",
  background: "linear-gradient(white, black)",
};
