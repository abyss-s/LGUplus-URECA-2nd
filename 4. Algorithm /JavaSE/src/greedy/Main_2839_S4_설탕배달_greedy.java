package greedy;

import java.util.Arrays;
import java.util.Scanner;

public class Main_2839_S4_설탕배달_greedy {
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();

		// int minVinyl = 5001;
		// 그리디
		// for (int i = 0, size = n / 5; i <= size; i++) {
		// 	int r = n - 5 * i;
		// 	if (r % 3 == 0) {
		// 		minVinyl = Math.min(minVinyl, i + r / 3);
		// 	}
		// }
		// System.out.println(minVinyl == 5001 ? -1 : minVinyl
		// );

		// dp
		int[] dp = new int[n + 5];
		Arrays.fill(dp, 5001);
		dp[3] = 1;
		dp[5] = 1;
		for (int i = 6; i <= n; i++) {
			dp[i] = Math.min(dp[i - 3], dp[i - 5]) + 1;
		}
		System.out.println(dp[n] >= 5000 ? -1 : dp[n]);

	} // end of main
} // end of class