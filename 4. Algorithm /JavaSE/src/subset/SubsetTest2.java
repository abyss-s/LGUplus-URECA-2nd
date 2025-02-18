package subset;

import java.util.Arrays;

public class SubsetTest2 {
	static String[] input = "abcd".split("");
	;
	static int N = input.length;
	static int[] subset;

	public static void main(String[] args) {
//		N=21;
//		input = new String[N];
		subset = new int[N];
//		for (int i = 0; i < N; i++) {
//			input[i] = (char)('a'+i)+"";
//		}
		long stime = System.currentTimeMillis();

		/*
		 * subset의 개수는 2의 n승 개이므로
		 * 0 : 원소를 선택 안함    1:원소를 선택함
		 *
		 * 0      0000:  원소 하나도 선택안한 서브셋
		 * 1      0001:  맨 끝의 원소 하나를 선택한 서브셋
		 * 2      0010:  맨 끝에서 두번째 원소 하나를 선택한 서브셋
		 * 3      0011:  맨 끝의 두개의 원소를 선택한 서브셋
		 * ...
		 * size-1 1111 : 모든 원소를 선택한 서브셋
		 *
		 * 결국 0 ~ 2^n-1까지 수의 bit를   1은 선택 0은 비선택으로  하면   수 자체가 부분 집합이 된다.
		 * 수에서 bit값이 1인지를 확인해서 부분집합으로 표현하자
		 *
		 * 시간 복잡도  2^n*n
		 *
		 */

		// 2의 n승만큼 돌기
		for (int i = 0, size = 1 << N; i < size; i++) {
			// i가 subset이므로 i를 이진수로 표현해서 0이면
			// bit 하나가 0이면 해당 원소를 선택하지 않음, 1이면 선택한 것

			// bit 하나가 원소에 대한 선택 여부를 나타내므로 모든 비트를 다 확인할 필요 없이 원소개수만 확인하면 된다.

			for (int j = 0; j < N; j++) {
//				if ((i & i << j) != 0) {
//					subset[j] = 1;
//				}
				subset[j] = (i >> j) & 1;
			}
			// 부분집합이 완성됐으므로 부분집합 이후 필요한 코드 작성
			print();
			// 다음 부분집합 구하기 위해
			Arrays.fill(subset, 0);
		}

		long etime = System.currentTimeMillis();
		System.out.printf("%dms\n", etime - stime);

	}

	public static void print() {
		System.out.print("[");
		for (int i = 0; i < N; i++) {
//			System.out.print(subset[i]+" ");
			if (subset[i] == 1)
				System.out.print(input[i] + " ");
		}
		System.out.println("]");
	}
}




