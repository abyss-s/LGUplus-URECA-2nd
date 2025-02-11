package recursive;

public class RecursiveCallTest {
	/*
	재귀함수: 함수 내에서 해당 함수를 또 호출
		- 모든 for 문을 재귀함수로 바꿀 수 있다.
		- 반복의 depth가 랜덤일 때 사용 <= 인자나 배열로 컨트롤한다.
			- 반복문을 몇 번 돌려야할지 정확히 알고 있다면? 반복문
		- 기저조건: 재귀함수를 중단하는 조건
		- 유도파트: 재귀함수를 진행하는 파트
	 */

	static int N = 10;

	// bottom-up
	public static int sum1(int i) {
		// 기저조건: 재귀함수를 중단하는 조건
		if (i == N)
			return N;
		return sum1(i + 1) + i;
	}

	public static void main(String[] args) {
		int sum = 0;
		for (int i = 1; i <= N; i++) {
			sum += i;
		}
		System.out.println("Gauss: " + (N + 1) * N / 2);
		System.out.println("for: " + sum);
		System.out.println("Recursive: " + sum1(1));
	}
}
