package chapter01;

public class DataTypeTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int num = 100;
		String str = "hello";
		System.out.println(str.charAt(2));
		System.out.println("hello".charAt(2));

		byte b1 = 1;
		byte b2 = 2;
		int b3 = b1 + b2;
		// byte b3 = b1 + b2;
		byte b4 = (byte) (b1 + b2);
		long ll = 222222222222l;

		// 정수 / 정수 = 정수 => 몫을 구함
		// 실수 / 정수, 정수/실수 => 실수
		System.out.println(5 / 2);
		System.out.println(5.0 / 2);
		System.out.println(5 / 2.0);
		int num1 = 5;
		int num2 = 2;
		System.out.println((double) num1 / num2);
		System.out.println(num1 / (double) num2);

		float f1 = 3.14f;
		double d2 = 3.14;

		// 0~1사이의 무한 실수가 있기 때문에 double은 8Byte로, float는 4Byte로 근사치를 표현한다.
		float f2 = 65536.123456789f;
		double d3 = 65536.123456789;
		System.out.println(f2); // 근사치로 나타남
		System.out.println(d3);

		// 문자 ' '
		char c1 = 'a';
		char c2 = 'b';

		/*
		 * 숫자, 영문자, 특수기호는 ASCII코드이므로
		 */
		System.out.println(c1);
		System.out.println(c2 - c1);
		System.out.println((int) 'A');
		System.out.println((int) 'a');

		// a일때 1칸, b일때 2칸, c일때 3칸 점
		char state = 'A';
		int jump = state - 'A' + 1;
		System.out.println("jump: " + jump);
	}

}
