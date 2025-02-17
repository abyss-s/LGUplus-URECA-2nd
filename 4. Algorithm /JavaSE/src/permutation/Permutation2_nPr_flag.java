package permutation;

import java.util.Arrays;
public class Permutation2_nPr_flag {
	static long tc;					//순열 개수
	static long count;				//반복 횟수 
	static int  R;					//뽑을 개수			
	static int  N;					//원소의 개수
	static int[] numbers;			//순열을 담은 배열
	static int[] input;				//입력된 데이타 
	static boolean[] isSelected;	//원소가 사용했는지  중복 체크 배열
	/**
	 * 
	 * @param depth 뽑을 원소의 위치 
	 */
	public static void permutation(int depth) {
		count++;		
		//배열은 0부터 시작이므로 R-1개가 모두 뽑은 상황. idx가 R과 동일한 상황은 순열을 다 뽑은 상황 
		if(depth == R) {
			tc++;
			//순열을 뽑은 이후의 task를 작성 
//			System.out.println(Arrays.toString(numbers));
			return ;
		}
		top:
		for (int i = 0; i < N; i++) {
			//중복 검사 - input[i] 번째 원소가 사용 중인지 검사 
			if(isSelected[i]) continue;			//사용 중인 경우 배제
			numbers[depth] = input[i];			//원소 사용
			isSelected[i]  = true;				//사용한 원소를 선택했다 표시 
			permutation(depth+1);
			//다음 순열을 뽑으러 가야 하므로 현재 원소에 대한 표시를 초기화 해야 한다. 
			isSelected[i]  = false;
		}
	}
	
	public static void main(String[] args) {
		input = new int[] {1,2,3,4,5,6,7,8,9,10};
//		input = new int[] {1,2,3};
		N = input.length;
		R = input.length;
		numbers = new int[R];
		//원소에 대해 사용 여부를 표시할 배열이므로 원소수와 동일하게 선언해야 한다. 
		isSelected= new boolean[N];
		long start = System.currentTimeMillis();
		permutation(0);
		long end = System.currentTimeMillis();
		System.out.printf("tc: %d   count:%d   time:%dms%n", tc, count, end-start);
	}
}





