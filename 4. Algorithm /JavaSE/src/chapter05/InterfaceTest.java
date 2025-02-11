package chapter05;


import chapter04.MyDate;

/**
 * interface
 * - 상수와 추상 메서드로만 구성되어 있다.
 * => 딱히 선언하지 않아도 속성은 public static final로 선언이 되고
 * 메서드는 public abstract으로 선언된다.
 * <p>
 * - 다중 상속이 됨.   => 다형성을 위해 다중 상속이 됨.
 * 선언]
 * [public] interface 인터페이스명 [extends super인터페이스명1, ....]{ }
 * <p>
 * 구현]
 * [modifiers] class 클래스명 [extends super클래스]
 * [implements super인터페이스명1, ....]{
 * <p>
 * }
 * <p>
 * 추상 메서드 :
 * - 메서드의 선언부만 작성하고 구현을 하지 않음
 * - sub class(상속 받는 클래스, 자식 클래스)에서 추상 메서드를 반드시 Override 하도록 위임
 * => sub class에서 추상 메서드를 Override하지 않으면 컴파일 에러 발생
 */

interface Flyer extends Cloneable {
	int Type = 1; // publuc static final

	void fly(); //public abstract
}

class Human {
	String name;

	public void start() {
		System.out.println("걸어 봅시다.");
	}
}

class SuperMan extends Human implements Flyer {
	public SuperMan(String name) {
		this.name = name;
	}

	public void fly() {
		System.out.println("하늘을 날아서 지구를 구해야지");
	}

	public void stop() {
		System.out.println("그만 움직이고 쉬어야징!!");
	}
}

public class InterfaceTest {
	public static void main(String[] args) {
		MyDate d1 = new MyDate();
		d1.setYear(2024);
		// d1.name 이런식으로 접근 못함 => name 속성은 default로 지정되어 있으므로 다른 패키지에서는 접근 불가

		System.out.println(Flyer.Type); // Type은 static이므로 객체 생성 없이 접근 가능
		// Flyer.Type = 3;  // final로 선언했으므로 값을 변경할 수 없음

		
	}
}





