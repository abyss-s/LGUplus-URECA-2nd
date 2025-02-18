package combination;

import java.util.Scanner;

/**
 * 조합
 * - 부분집합(재귀)에서 R개를 선택(인자로 선택한 개수 유지)하여 조합을 구함
 * <p>
 * 26C13
 * 총 경우의 수 : 10400600
 * count : 77509463
 * time : 106
 */
public class CombinationTest2 {
	static int N, R, totalCnt;
	static int[] input, numbers;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
//		N = sc.nextInt();
//		R = sc.nextInt();
		N = 25;
		R = 13;
		input = new int[N];
		numbers = new int[R];
		for (int i = 0; i < N; i++) {
//			input[i] = sc.nextInt();
			input[i] = i + 1;
		}
		long start = System.currentTimeMillis();
		combi(0, 0);
		long end = System.currentTimeMillis();
		System.out.println("총 경우의 수 : " + totalCnt);
		System.out.println("count : " + count);
		System.out.println("time : " + (end - start));
	}

	static int count;

	/**
	 * nCr  =>  n-1Cr-1  + N 번째   선택 한 경우
	 * n-1Cr              선택 안한 경우
	 *
	 * @param depth 선택할 데이타를 저장할 index
	 * @param r     선택한 데이타 개수, index
	 */
	private static void combi(int depth, int r) {
		if (r == R) {
//			System.out.println(Arrays.toString(numbers));
			totalCnt++;
			return;
		}

		if (depth >= N) return;

		// 현재 원소를 선택
		numbers[r] = input[depth];
		combi(depth + 1, r + 1);

		// 현재 원소를 비선택
		combi(depth + 1, r);
	}
}









