package chapter01;

// 형변환(Cast) - boolean 빼고 됨.

public class CastTest {

	public static void main(String[] args) {
		byte b1 = 17;
		short s1 = b1;
		int i1 = s1;
		long l1 = i1;
		float f1 = i1;
		float f2 = 2222222222l;
		double d1 = 2222222222l;
		System.out.println("f2: " + f2);
		System.out.println("d1: " + d1);

		char c1 = 'a';
		int i2 = c1;
		System.out.println("i2: " + i2);

		float f3 = (float) d1;
		long l3 = (long) f3;
		System.out.println("l3: " + l3);
		int i3 = (int) l3;
		// 음수 나오는 이유: 8byte 앞에 4byte는 제거된 후, 남은 4Byte의 첫 bit가 1인경우 음수로 표현한다.
		System.out.println("i3: " + i3);
		short s3 = (short) i3;
		System.out.println("s3: " + s3);
		byte b3 = (byte) s3;
		System.out.println("b3: " + b3);
		char c3 = (char) s3;
		System.out.println("c3: " + c3);
		s3 = (short)c3;
		System.out.println("s3"+s3);
		
		
		
		
	}

}
