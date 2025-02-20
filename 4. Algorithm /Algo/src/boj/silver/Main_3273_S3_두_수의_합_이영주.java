package boj.silver;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_3273_S3_두_수의_합_이영주 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int[] data = new int[n]; // 수열 배열

		// 수열 내 원소 입력 처리
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			data[i] = Integer.parseInt(st.nextToken());
		}

		int x = Integer.parseInt(br.readLine()); // target: x

		Arrays.sort(data);

		int start = 0; // 포인터1
		int end = n - 1; // 포인터2
		int count = 0; // 조건을 만족하는 쌍의 개수
		int curSum = 0; // 현재 포인터의 값 합계

		// two-pointer 진행
		while (start < end) {
			curSum = data[start] + data[end];

			// 타겟값보다 작으면
			if (curSum < x) {
				start++; // 오른쪽으로 이동
			} // 타겟값보다 크면
			else if (curSum > x) {
				end--; // 왼쪽으로 이동
			}
			// 타켓값과 일치 => 조건을 만족하는 경우
			else {
				count++;
				start++;
				end--;
			}
		}

		System.out.println(count); // 결과 출력
	}
}