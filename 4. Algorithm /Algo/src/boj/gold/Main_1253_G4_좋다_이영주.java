package boj.gold;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_1253_G4_좋다_이영주 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];

		// 수열의 원소 입력 처리
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(arr);

		int count = 0;

		// 어떤 수가 다른 수 두 개의 합으로 나타낼 수 있는지 확인
		for (int i = 0; i < n; i++) {
			int temp = arr[i]; // 어떤 수
			int sum = 0;

			// 투포인터 선언
			int start = 0;
			int end = n - 1;

			while (start < end) {
				// 더이상 찾을 수 없으면 다음 쌍 찾으러 포인터 이동
				if (start == i) {
					start++;
					continue;
				}
				if (end == i) {
					end--;
					continue;
				}

				sum = arr[start] + arr[end];

				if (sum < temp) {
					start++;
				} else if (sum > temp) {
					end--;
				} else {
					count++;
					break;
				}
			}
		}

		// 결과 출력
		System.out.println(count);
	}
}