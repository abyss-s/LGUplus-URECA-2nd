/**
 * ref
 * react에서 DOM요소에 직접 접근할 수 있도록 연결하는 기능
 * - input 요소에 focus 주기
 * - 스크롤 박스 조작하기
 * - canvas 요소에 그림 그리기
 *
 * 클래스형 컴포넌트
 * 형식 1]
 * <요소  ref={ ref =>{this.input=ref}}/>
 *
 * 형식 2]
 * 속성명 =  React.createRef();
 * <요소  ref={this.속성명}/>
 *
 * 함수형
 * const 변수명 = useRef(null);
 * <요소  ref={변수명}/>
 */
