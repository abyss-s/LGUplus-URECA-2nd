package permutation;

import java.util.Scanner;

/**
 * 순열
 * - 서로 다른 n개의 원소에서 r개를 순서를 고려하여 선택(나열)하는 것
 */

public class PermutationFor {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int N = scan.nextInt(); // 원소 개수

		int[] data = new int[N];
		for (int i = 0; i < N; i++) {
			data[i] = scan.nextInt();
		}

		/*
		 *  중복 순열: 원소를 중복선택
		 * - 시간 복잡도:  nTTr
		 *
		 * */

		int cnt = 0;

		for (int i = 0; i < N; i++) { // 첫번째 원소를 반복
			for (int j = 0; j < N; j++) { // 두번째 원소를 반복
				if (i != j) // 두번째 원소는 첫번째 원소와 같아서는 안됨
					for (int k = 0; k < N; k++) { // 3번째 원소를 반복
						if (i != k && j != k) { // 세번째 요소는 첫번째, 두번째 요소와 같아서는 안됨
							cnt++;
							System.out.printf("%d %d %d%n", data[i], data[j], data[k]);
						}
					}
			}
		}
		System.out.printf("%dP3 순열의 개수: %d%n", N, N, cnt);
	}
}
