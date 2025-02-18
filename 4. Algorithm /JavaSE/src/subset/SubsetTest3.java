package subset;

import java.util.Scanner;

/**
 * @author 시간 복잡도 2^n
 */
public class SubsetTest3 {
	static int N, totalCnt;
	static int[] input;
	static int[] numbers;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = 20;
		input = new int[N];
		numbers = new int[N];
		for (int i = 0; i < N; i++) {
			input[i] = i + 1;
		}
		long start = System.currentTimeMillis();
		subset(0, 0);
		long end = System.currentTimeMillis();
		System.out.printf("총 경우의 수 : %d 시간 : %dms", totalCnt, end - start);
	}

	/*
	 * depth: 재귀 깊이
	 * len: 부분집합에서 선택한 원소 수(부분집합의 원소 수)
	 */
	private static void subset(int depth, int len) {
		// 모든 원소에 대해서 선택과 비선택을 통해 부분집합을 만들기 때문에
		// // depth의 모든 비트가 for문에
		if (depth == N) {
			// sunstant가 완성됨
			totalCnt++;

			// copyOFRange 배열: 시작 위치부터 여러까지 배열 복사
//			System.out.println(Arrays.toString(Arrays.copyOfRange(numbers, 0, len)));
			return;
		}

		// 원소 선택
		numbers[len] = input[depth];
		subset(depth + 1, len + 1);

		// 원소 비선택
		subset(depth + 1, len);
	}
}
