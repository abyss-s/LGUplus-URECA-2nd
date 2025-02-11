package chapter06;

/**
 * Wrapper
 * - 자바의 기본타입(Primitive)을 객체로 사용할 수 있도록 제공하는 클래스
 * byte 	=> Byte
 * short 	=> Short
 * char		=> Character
 * int		=> Integer
 * long		=> Long
 * float	=> Float
 * double	=> Double
 */
public class WrapperTest {

	public static void main(String[] args) {
		/*
		 * parseXXX(String)
		 * 인자로 전달된 문자열을 해당 Wrapper의 Format으로 변경하는 함수
		 * 주의점 format 이 맞지 않는 경우 NumberFormatException 발생
		 *
		 * ex) Integer.parseInt("3.14")		Integer.parseInt("a")
		 * */

		/*
		 * Character의 isXXXX('')
		 * 인자로 전달된 문자가 해당 타입인지 검사하는 함수
		 * ex) Character.isDigit('1')
		 */

		System.out.println(Character.isDigit('1'));
		System.out.println(Character.isDigit('a'));

		int d = '1';
		if (d > -1 && d < 10)
			System.out.println("숫자입니다.");
		else
			System.out.println("숫자가 아닙니다.");

		// auto boxing: 기본타입 primitive를 자동으로 wrapper로 변경해줌
		Integer inum = 100;

		// auto unboxing: wrapper를 자동으로 primitive로 변경
		int num = inum;

		
	}

}
