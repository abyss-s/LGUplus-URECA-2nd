package jungol.gold;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

// 정올 1141번. 불쾌한 날
// https://jungol.co.kr/problem/1141
public class Main_1141_G5_불쾌한_날 {
	public static void main(String[] args) throws Exception {
		//입출력받기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		int[] cows = new int[n];  // 각 소들의 키를 저장할 배열

		long count = 0;
		Stack<Integer> stack = new Stack<>();  // 자신을 볼 수 있는 소들의 키를 저장할 스택

		for (int i = 0; i < n; ++i) {
			cows[i] = Integer.parseInt(br.readLine());

			// 스택에서 자신보다 크거나 같은 소들은 제거
			// (자신의 키보다 작은 소들만 볼 수 있다.)
			while (!stack.isEmpty() && cows[i] >= stack.peek()) {
				stack.pop();
			}

			// 현재 소를 볼 수 있는 소를 카운트에 추가
			if (!stack.isEmpty()) {
				count += stack.size();
			}

			// 현재 소를 스택에 추가
			stack.push(cows[i]);
		}

		System.out.println(count);
	}
}





