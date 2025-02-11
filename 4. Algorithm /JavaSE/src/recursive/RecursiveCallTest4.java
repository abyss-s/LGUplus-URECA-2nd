package recursive;


public class RecursiveCallTest4 {
	// 피보나치 - top down
	public static int fibo(int a) {
		if (a <= 2)
			return 1;
		return fibo(a - 2) + fibo(a - 1);
	}

	public static void main(String[] args) {
		System.out.println(fibo(5));
		// 1 1 2 3 5...
	}
}
