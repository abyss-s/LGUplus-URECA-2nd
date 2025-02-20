package divideconquer;

/**
 * Divide Conquer (분할 정복)
 * 분할 : 해결할 문제를 여러 개의 작은 부분으로 나눈다.
 * 정복 : 나눈 작은 문제를 각각 해결한다.
 * 통합 : (필요하다면) 해결된 해답을 모은다.
 * <p>
 * ex) 머지정렬, 퀵정렬, 이진검색
 */
public class PowerTest {
	public static long powerRec(int x, int n) {
		if (n == 1) return x;
		return x * powerRec(x, n - 1);
	}

	public static long dcPower(int x, int n) {
		if (n == 0) return 1;
		if (n == 1) return x;

		long let = dcPower(x, n >> 1); // 비트마스크

		if (n % 2 == 0)
			return let * let;
		else // 홀수인 경우 한 번 더 곱해줘야 함
			return let * let * x;
	}


	public static void main(String[] args) {
//		6268685802422096249
//		System.out.println(powerRec(9, 2111111111));
		long result = 1;
		int n = 9;

		// 시퀀스하게 연산하기 때문에 21억번 박복
		for (int i = 0; i < 2111111111; i++) {
			result *= n;
		}

		// 반씩 줄여 나가기 때문에 O(logN)의 시간복잡도
		System.out.println(dcPower(9, 2111111111));

//		System.out.println(powerRec(4, 4));
//		System.out.println(dcPower(4, 4));
//		System.out.println(16*16);
	}
}













