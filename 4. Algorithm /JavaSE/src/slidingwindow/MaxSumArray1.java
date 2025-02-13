package slidingwindow;
/**
 * 길이가 N인 배열에서 길이가 K인 서브 배열의 특정 연산시 최적의 값을 구하는 경우 
 *  
 * 정수로 이루어진 길이가N인 배열에서 길이가 K인 서브 배열의 합계가 가장 큰 서브 배열은?
 * - ex)[2,4,7,10,8,4,5,6,7,1]  
 * 
 *  ex) 회전초밥(고) 
 *  2≤N≤3,000,000
 *  2≤k≤3,000 (k≤N)
 *
 */
public class MaxSumArray1 {
	public static void main(String[] args) {
		int[] nums = {2,4,7,10,8,4,5,6,7,1};
		int max = 0, sum=0;
		int k= 4;

		
		System.out.println(max);
	}
}
