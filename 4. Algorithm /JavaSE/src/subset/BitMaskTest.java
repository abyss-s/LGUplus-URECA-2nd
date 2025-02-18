package subset;

public class BitMaskTest {
	public static void main(String[] args) {
//		>> N : 비트를 N번 오른쪽으로 이동   => 2의 N승으로 나눈 효과
//		0000 1000      8>>2
//      0000 0010	   			
//		int n = 8;
//		System.out.println("8>>2 :"+ (n>>2));
		
//		<< N : 비트를 N번 왼쪽으로 이동   => 2의 N승으로 곱한 효과
//		System.out.println("8<<2 :"+ (n<<2));
//		
//		for (int i = 0; i <5; i++) {
//			System.out.println(1 << i);   // 0000 0001
//		}
		
//		i  & 1 << j  숫자 i의 j번째 bit가 1인지 아닌지...
		for (int i = 0; i <5; i++) {
			printBit(i);
		}
	}
	static void printBit(int i) {
		for (int j = 7; j >=0; j--) {
			System.out.printf("%d", (i & 1 << j) != 0 ? 1:0 );
		}
		System.out.println();
	}
}







