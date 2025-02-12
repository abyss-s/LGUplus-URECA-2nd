package recursive;

/**
 * 재귀함수
 * - 함수 내에서 해당 함수를 또 호출
 * - 반복의 depth가 랜덤일 때 사용(인자 or 배열로 depth를 컨트롤한다)
 * -
 * 기저조건  : 재귀 함수를 중단하는 조건
 * 유도파트  : 재귀 함수를 진행하는 파트
 */
public class RecursiveCallTest {

	static int N = 10;

	//Bottom -> UP
	public static void print(int num) {
		if (num > N) return;
		System.out.print(num + " ");
		print(num + 1);
	}

	public static void main(String[] args) {
		for (int i = 0; i <= N; i++) {
			System.out.print(i + " ");
		}
		System.out.println();
		print(0);
		System.out.println();

		for (int i = N; i > -1; i--) {
			System.out.print(i + " ");
		}
		System.out.println();
		print2(N);
		System.out.println();
		print3(0);
		print4(N);
	}

	public static void print2(int num) {
		if (num == -1) return;
		System.out.print(num + " ");
		print2(num - 1);
	}

	// 특정 조건을 만족할 때 재귀가 진행되는 구조
	//Bottom -> UP
	public static void print3(int num) {
		if (num <= N) return;
		System.out.print(num + " ");
		print3(num + 1);
	}

	// Top -> down
	public static void print4(int num) {
		if (num >= 0) return;
		System.out.print(num + " ");
		print4(num - 1);
	}


}

