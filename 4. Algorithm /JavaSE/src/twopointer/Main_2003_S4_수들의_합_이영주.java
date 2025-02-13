package twopointer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2003_S4_수들의_합_이영주 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[] data = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			data[i] = Integer.parseInt(st.nextToken());
		}

		// 시작점과 끝점이 첫번째 원소의 인덱스를 가리키도록 함
		int end = 0, count = 0;
		int sum = 0;

		// start 증가시키면서 포인터 이동
		for (int start = 0; start < N; start++) {
			// 현재 부분의 합이 target 보다 작다면 end를 1 증가
			while (sum < M && end < N) {
				sum += data[end];
				end++;
			}

			if (sum == M) count++;

			// 현재 부분의 합이 target 보다 크다면 start를 1 증가
			sum -= data[start]; // start가 증가하기 전에 sum에서 start 제거 필요
		}


		System.out.println(count);
	}

//	static String input = "5 5\n"
//			+ "1 2 3 4 5";
}
