package permutation;

import java.util.Arrays;

public class Permutation_nTTr {
	static int N;            // 원소 개수
	static int R;            // 뽑을 개수
	static int[] input;    // 입력 데이터
	static int[] numbers;    // 뽑을 순열을 담을 배열
	static int tc;

	public static void main(String[] args) {
		input = new int[]{1, 2, 3, 4};
		N = input.length;
		R = 2;
		numbers = new int[R];
		perm(0);
		System.out.printf("%dTT%d 중복 순열 개수: %d", N, R, tc);
	}

	public static void perm(int depth) {
		/*
		 * 중단 조건: 배열은 0부터 시작하므로 R-1의 위치까지 모든 원소를 뽑은 상황
		 * - depth가 R과 동일한 상황은 하나의 순열에서 모든 원소를 다 뽑은 상황이므로 종료
		 * */

		if (depth == R) {
			tc++;
			System.out.println(Arrays.toString(numbers));
			return;
		}

		// depth번째의 원소를 선택
		for (int i = 0; i < N; i++) {
			numbers[depth] = input[i];
			perm(depth + 1);
		}


	}
}
