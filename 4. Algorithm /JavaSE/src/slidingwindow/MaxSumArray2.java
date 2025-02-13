package slidingwindow;
/**
 * Sliding Window
 * - 배열이나 리스트 요소의 일정 범위(k개) 값을 비교할 때 사용하면 유용한 알고리즘 
 * - 서브 배열의 요소를 순회하다 보면 중복되는 요소들이 존재하고 이 중복된 요소를 재사용하는 방법이 슬라이딩 윈도우 알고리즘
 * - 방법
 *   1. k개의 서브 요소를 원하는 방법으로 처리한다.
 *   2. 윈도우 시작분을 빼고, 윈도우 맨 끝에 새요소를 추가 
 * 
 * - Sliding Window 시간 복잡도 
 *   O(N)
 *   ex) 회전초밥(고) 
 *  	2≤N≤3,000,000
 *  	2≤k≤3,000 (k≤N)
 *      반복 횟수 : 3,000,000 안전
 *   
 * - ex)[2,4,7,10,8,4,5,6,7,1]  
 *   정수로 이루어진 배열에서 길이가 4인 서브 배열의 합계가 가장 큰 서브 배열은?
 * 
 * 
 * 
 */
public class MaxSumArray2 {

	public static void main(String[] args) {
		int[] nums = {2,4,7,10,8,4,5,6,7,1};
		int max = 0, sum=0;
		int k= 4;
		
	
//		1. k개만큼 sum을 구한다. 
	
		max = sum;
//		2. N-k만큼 반복 돌면서 SW 적용
//			sum에서 window start에 대한 처리 하기 
//			sum에 window end 대한 처리 
//			max구하기
		System.out.println(max);
	}
}
