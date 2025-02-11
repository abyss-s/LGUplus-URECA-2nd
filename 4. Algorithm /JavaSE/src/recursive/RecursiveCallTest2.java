package recursive;


public class RecursiveCallTest2 {
	static int N = 10;

	// bottom-up
	public static int sum1(int i) {
		// 기저조건: 재귀함수를 중단하는 조건
		if (i == N)
			return N;
		return sum1(i + 1) + i;
	}

	// top-down
	public static int sum2(int i) {
		if (i == 1)
			return 1;
		return sum2(i - 1) + i;
	}

	public static void main(String[] args) {
		int sum = 0;
		for (int i = 1; i <= N; i++) {
			sum += i;
		}
		System.out.println("Gauss: " + (N + 1) * N / 2);
		System.out.println("for: " + sum);
		System.out.println("Bottom-up Recursive: " + sum1(1));
		System.out.println("Top-down Recursive: " + sum2(N));
	}
}
