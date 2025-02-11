package chapter05;

/**
 * Object : 모든 객체들은 Object이다.
 * instance : 특정 클래스로 부터 생성된 객체
 * <p>
 * static : 동적인 java에게 정적인 특성을 부여
 * - 클래스가 로딩될 때 한번 수행됨.
 * - 객체 생성없이 클래스명으로 접근 가능
 * Class명.속성명	        Class명.메서드명(~)
 */
class Counter {            //Outer class, Enclosing class
	static int scount;
	int icount;

	Counter() {
		System.out.println("생성자 수행됨. ");
		scount++;
		icount++;
	}

	public void print() {
		System.out.println("scount : " + scount);
		System.out.println("icount : " + icount);
	}

	public static void sprint() {
		System.out.println("scount : " + scount);
		//		static 메서드에서는   non-static에 접근 불가
		//		System.out.println("icount : "+icount);
	}
}

public class StaticTest {
	public static void main(String[] args) {
		//		객체 생성 없이 접근 가능
		System.out.println(Counter.scount);

		Counter c1 = new Counter();
		c1.print();
	}
}







