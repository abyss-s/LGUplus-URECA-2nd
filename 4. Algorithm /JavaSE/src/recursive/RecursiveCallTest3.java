package recursive;

// 팩토리얼 함수 만들기
public class RecursiveCallTest3 {
	static int N = 5;

	// bottom-up
	public static int f1(int i) {
		// 기저조건: 재귀함수를 중단하는 조건
		if (i == N)
			return N;
		return f1(i + 1) * i;
	}

	// top-down
	public static int f2(int i) {
		if (i == 1)
			return 1;
		return f2(i - 1) * i;
	}

	public static void main(String[] args) {

		System.out.println("Bottom-up Recursive: " + f1(1));
		System.out.println("Top-down Recursive: " + f2(N));
	}
}
