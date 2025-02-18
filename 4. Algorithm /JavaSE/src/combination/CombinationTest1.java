package combination;

import java.util.Scanner;

/**
 * 25C12,13		=> 35ms		안전 조합수 : 5,200,300
 * 26C13		=> 80ms   	위험	조합수 : 10,400,600  ==> 백트레킹을 시도
 * 27C14		=> 200ms	위험	==> 백트레킹을 시도
 * 30C15  		=> 1.2초 컷  안됨 1억 5천
 * <p>
 * 조합: 서로 다른 n개의 원소에서 r개를 중복 없이, 순서를 고려하지 않고 선택
 */
public class CombinationTest1 {
	static int testcase;
	static int n;
	static int r;
	static int[] numbers;    //뽑은 r개 수의 조합을 저장할 배열
	static int[] input;        //입력된 N개의 데이타
	static int count;

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		n = scan.nextInt();
		r = scan.nextInt();
		input = new int[n];
		numbers = new int[r];
		for (int i = 0; i < n; i++) {
			input[i] = scan.nextInt();
		}

		n = 26;
		r = 13;
		input = new int[n];
		numbers = new int[r];
		for (int i = 0; i < n; i++) {
			input[i] = i + 1;
		}
		long start = System.currentTimeMillis();
		combi(0, 0);
		long end = System.currentTimeMillis();
		System.out.printf("%dC%d 조합의 수:%d  time:%d  count:%d <= %d%n", n, r, testcase, end - start, count, testcase * r);
	}

	/**
	 * @param depth 뽑은 수를 저장할 index 위치
	 * @param start 뽑을 수의 index 위치
	 */
	private static void combi(int depth, int start) {
		if (depth == r) {
			testcase++;
			// 조합이 완성됐으므로 필요한 코드를 작성
//			System.out.println(Arrays.toString(numbers));
			return;

		}
		for (int i = start; i < n; i++) {
			numbers[depth] = input[i];
			combi(depth + 1, i + 1);
		}
	}
}
