package chapter01;

public class BitOperateTest {

	public static void main(String[] args) {
		int d = 10; // 10진수
		int b = 0b1000;// 2진수
		int o = 010; // 8진수
		int h = 0x08; // 16진수
		System.out.println(b);
		System.out.println(o);
		System.out.println(h);

		// abcdfeg
		int key = 0b1010010; // a,c,f가 있는지 확인
		int word1 = 0b1110010;
		int word2 = 0b1110100;
		System.out.println(Integer.toBinaryString(key & word1));
		System.out.println((key & word1) == key);
		System.out.println((key & word2) == key);

		// bit shift
		System.out.println(b << 2);
		System.out.println(b >> 2);

		// ^(xor): 두 비트가 같으면 0 다르면 1 ==> 토글 효과
		int pw = 0b11001101;
		int salt = 0b10110011;
		int encoding = pw ^ salt;
		System.out.println(Integer.toBinaryString(encoding));
		System.out.println(Integer.toBinaryString(encoding ^ salt));

		// abcdefgh
		int checkbit = 0b00000000;

	}
}
