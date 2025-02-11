package chapter01;

public class BitMaskTest {

	public static void main(String[] args) {
		// bit mask
		int aa = 0b1000;
		int bb = 0b0010;
		aa &= bb; // 0000 1 => 0 masking
		aa |= bb; // 1010 0 => 1 masking

		/*
		 * 1.공집합과 꽉 찬 집합 구하기 A = 0; // 32개의 원소가 모두 0이므로 공집합 
		 * n = 10; // 10개인 원소 
		 * A = (1<<n)-1; // 꽉 찬 집합
		 * 
		 * 			000000001 << 10 = 1000000000
		 * 			1000000000 -   1   = 111111111
		 */
		int n = 10;
		int A = (1 << n) - 1;
		System.out.println(Integer.toBinaryString(A));
		
		// 2. 특정 위치에 1이 있는지 체크 => &
		// and &
		int a1 = 0b1000;
		int b1 = 0b0010;
		int c1 = 0b1110;
		System.out.println(Integer.toBinaryString(a1 & b1));
		System.out.println(Integer.toBinaryString(a1 & c1));
		System.out.println(Integer.toBinaryString(b1 & c1));

		/*
		 *  3. k번째 위치에 원소 추가(1로 마스킹) 
		 *  k번째는 뒤에서부터 세기 (0번째부터)
		 *  
		 * */
		// | or 두비트가 모두 0일때만 0, 나머지는 1
		System.out.println(Integer.toBinaryString(a1 | b1));
		System.out.println(Integer.toBinaryString(a1 | c1));
		System.out.println(Integer.toBinaryString(b1 | c1));
		
		A = 0;
		int k = 5;
		A |= (1<<k);
		System.out.println(Integer.toBinaryString(A));
		
		/*
		 *4. 원소 삭제: k번째 위치에 있는 원소를 삭제 (0으로 만들기) 
		 * 
		 * */
		A&= ~(1<<k);
		System.out.println(Integer.toBinaryString(A));
		
		/*
		 *  5.마지막 1의 위치 찾기 
		 *  A&-A : A의 인지수에서 1의 마지막 위치를 찾기  
		 *  2의보수 -> 앞까지 반전하다가 마지막 1부터는 동일함. 
		 * */
	     A = 0b1100111000;
	 	 System.out.println(Integer.toBinaryString(A));
	     System.out.println(Integer.toBinaryString(A&-A));
	     
	     /*
	      * 6. 최소 원소 지우기
	      * A & (A-1)
	      * 
	      * */
	 	 System.out.println(Integer.toBinaryString(A));
	     System.out.println(Integer.toBinaryString(A & (A-1)));
	     
	     
	    String[] str = {"a", "b", "c", "d"};
	     /*
	      *  7. 모든 부분 집합 순회하기 
	      */
	    System.out.println("부분 집합 출력 ");
	     int s = 0b1101;
	     for(int subset =s; subset !=0; subset=(subset-1)&s) {
	    	 System.out.println(Integer.toBinaryString(subset));
	     }
	     
	     
	}

}
