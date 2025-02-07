package chapter03;

import java.util.Arrays;

/*
하나의 변수에 여러개의 공간을 할당
동일한 타입의 데이터를 저장, 관리
*/

public class ArrayTest1 {

	public static void main(String[] args) {
		int[] a1, a2; // 둘 다 1차
		int b1, b2[]; // 둘다 1차
		int[] c1, c2[]; // c1 - 1차원, c2 - 2차
		int[] array = new int[3];

		for (int i = 0, size = array.length; i < size; i++) {
			System.out.printf("array[%d]: %d%n", i, array[i]);
		}

		// array[0]: 0
		// array[1]: 0
		// array[2]: 0
		
		// 자바 제공 유틸 이용해서 더 간단히 출력 가능 
		System.out.println(Arrays.toString(array));
		
		int[] array2 = {1,2,3,4,5};
		System.out.println(Arrays.toString(array2));

	}

}
