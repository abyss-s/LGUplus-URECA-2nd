package chapter01;

public class Test {
	// main 함수: 프로그램의 시작점 
	public static void main(String[] args) {
	// print:  명령창에 출력, 엔터 없이 옆으로 이어서 출력
	System.out.print("hello ");
	System.out.print("world ");

	// println: 명령창에 출력 후 엔터()까지
	System.out.println("하이하이");
	System.out.println("하이하이");
	System.out.printf("저는 %s이고 나이는 %d입니다.%n", "유플러스", 2);
	
	// 문자열 + anytype, anyType + 문자
	String name = "lyj";
	int age = 25;
	System.out.println("제 이름은 "+name+" age: "+age);
	
	// 문자열은 변경할 수 없다. 
	// + 연산 시 기존의 객체를 버리고 생성 -> 성능 저하의 원인 
	// StringBuilder를 이용한다
	
	StringBuilder sb = new StringBuilder(100);
	// append(): 저장된 내용의 맨 끝에 추가 
	sb.append("hello");
	sb.append("world");
	sb.append("java");
	
	
	}
}