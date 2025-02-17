package permutation;

/**
 * Swap 순열
 * - 배열의 첫 값부터 순서대로 하나씩 바꾸며 모든 값을 한번씩 swap 한다.
 * <p>
 * - depth를 기준 인덱스로 하여 depth 보다 인덱스가 작은 값들은 그대로 고정하고
 * depth 이상의 인덱스들만 swap을 진행한다.
 * - 순열을 따로 구하지 않아도 된다
 * - nPn, nPr 모두 됨.  단 nPr시 전체 배열에서 앞에 r개만 사용
 */
// 10P10 : 0.02초 컷		(안전) 
public class Permutation2_nPr4_swap {
	static long tc;                    //순열 개수
	static long count, swcnt;        //반복 횟수
	static int R;                    //뽑을 개수
	static int N;                    //원소의 개수
	static int[] input;                //입력된 데이타

	/**
	 * @param depth swap할 대상
	 */
	public static void permutation(int depth) {
		//배열은 0부터 시작이므로 R-1개가 모두 뽑은 상황. idx가 R과 동일한 상황은 순열을 다 뽑은 상황
		// 스왑 순열은 R-2 까지 swap하면 순열이 결정된다.
		if (depth == R - 1) { // 순열이 완성된 경우
			tc++;
			//순열을 뽑은 이후의 task를 작성 
//			System.out.println(Arrays.toString(input));
			return;
		}
		// 순열이 완성되지 않았다면
		for (int i = depth; i < N; i++) {
			swap(i, depth);
			permutation(depth + 1);
			// 다음 순열을 만들기 위해서 원복하기
			swap(i, depth);
		}
	}

	/*
	 * 암기합시당!
	 */
	public static void swap(int i, int j) {
		int temp = input[i];
		input[i] = input[j];
		input[j] = temp;

	}

	public static void main(String[] args) {
//		input = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
		input = new int[]{1, 2, 3};
		N = input.length;
		R = input.length;
		long start = System.currentTimeMillis();
		permutation(0);
		long end = System.currentTimeMillis();
		System.out.printf("tc: %d   count:%d  swcnt:%d  time:%dms%n", tc, count, swcnt, end - start);
	}
}





