package permutation;

// 10P10 : 0.5초 컷
public class Permutation2_nPr2_flag {
	static long tc;                    //순열 개수
	static long count;                //반복 횟수
	static int R;                    //뽑을 개수
	static int N;                    //원소의 개수
	static int[] numbers;            //순열을 담은 배열
	static int[] input;                //입력된 데이타

	/**
	 * @param depth 뽑을 원소의 위치
	 */
	public static void permutation(int depth) {
		count++;
		//배열은 0부터 시작이므로 R-1개가 모두 뽑은 상황. idx가 R과 동일한 상황은 순열을 다 뽑은 상황 
		if (depth == R) {
			tc++;
			//순열을 뽑은 이후의 task를 작성 
//			System.out.println(Arrays.toString(numbers));
			return;
		}
		top:
		for (int i = 0; i < N; i++) {
			//중복 검사 
			for (int j = 0; j < depth; j++) {
				if (numbers[j] == input[i]) { //중복 된 경우
					continue top;
				}
			}
			numbers[depth] = input[i];
			permutation(depth + 1);
		}
	}

	public static void main(String[] args) {
		input = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
//		data = new int[] {1,2,3};
		N = input.length;
		R = input.length;
		numbers = new int[R];
		long start = System.currentTimeMillis();
		permutation(0);
		long end = System.currentTimeMillis();
		System.out.printf("tc: %d   count:%d   time:%dms%n", tc, count, end - start);

		// 시간복잡도를 공간복잡도로 바꾸기
		// boolean 배열 이용하기
	}
}





