package chapter03;

import java.util.Arrays;

public class ArrayTest3 {

	public static void main(String[] args) {
		// 배열 복사
		int[] src = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		int[] copy1 = new int[15];
		int[] copy2 = new int[3];

		/*
		 * Arraycopy
		 */

		// 전체복사
		System.arraycopy(src, 0, copy1, 0, src.length);
		System.out.println(Arrays.toString(copy1));

		// 부분 복사
		System.arraycopy(src, 2, copy2, 0, copy2.length);
		System.out.println(Arrays.toString(copy2));

		/*
		 * copyOf
		 */
		int [] copy4 = Arrays.copyOf(src, 15);
		System.out.println(Arrays.toString(copy4));
		
		/*
		 * copyOfRange
		 */
		int [] copy5 = Arrays.copyOfRange(src, 2,5);
		System.out.println(Arrays.toString(copy5));


	}

}
