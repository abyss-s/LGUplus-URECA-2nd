package subset;

import java.util.Scanner;

/**
 *
 */
public class SubsetTest4 {
	static int testcase;
	static int n;
	static int r;
	static int[] numbers;    //뽑은 r개 수의 조합을 저장할 배열
	static int[] input;        //입력된 N개의 데이타
	static int count;

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		n = scan.nextInt();
		input = new int[n];
		numbers = new int[n];
		for (int i = 0; i < n; i++) {
			input[i] = scan.nextInt();
		}
		subset(0, 0);
		System.out.printf("testcase:%d  count:%d %n", testcase, count);
	}

	/**
	 * 조합 코드를 응용한 subset
	 *
	 * @param depth 뽑은 수를 저장할 index 위치
	 * @param start 뽑을 수의 index 위치
	 */
	private static void subset(int depth, int start) {
		System.out.print("[");
		for (int i = 0; i < n; i++) {
			// 선택한 것만 출력
			if (numbers[i] > 0)
				System.out.print(numbers[i] + "");

		}
		System.out.print("]");

		for (int i = start; i < n; i++) {
			// 선택했을 경우
			numbers[depth] = input[i];
			subset(depth + 1, i + 1);

			// 비선택인 경우
			numbers[depth] = 0;
//			count++;
		}
	}
}
