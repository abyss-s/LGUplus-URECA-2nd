package twopointer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Two-Point Algorithm(투 포인터 알고리즘) :
 * - 1차원 배열에서 각자 다른 원소를 가리키고 있는 2개의 포인터(start-end, left-right)를 조작해가면서
 * 원하는 값을 찾을 때 까지 탐색하는 알고리즘이다.
 * - 리스트에 순차적으로 접근해야 할 때 두 개의 점(포인트)의 위치를 기록하면서 처리한다.
 * <p>
 * ex)//		특정한 합을 가지는 부분 연속 수열 찾기
 * <p>
 * - 어떤 숫자들의 리스트가 주어질 때, 해당 리스트의 연속 수열의 합이 특정 값을 가지는 것을 확인하는 문제
 * 1. 시작점과 끝점이 첫번째 원소의 인덱스를 가리키도록 함
 * 2.
 * 2.1 현재 부분의 합이 target과 같다면 카운트
 * 2.2 현재 부분의 합이 target 보다 작다면 end를 1 증가
 * 2.3 현재 부분의 합이 target 보다 크다면 start를 1 증가
 * C. 모든 경우를 확인할 때 까지 2번을 반복
 * 즉, start가 end와 같을 때 (start==end) 는, 크기가 0인 아무것도 포함하지 않는 부분 배열을 뜻하며, 다음의 과정은 start < target 인 동안 반복한다.
 * - 부분합이 target 이상이거나 이미 end==target인 경우 start+1
 * 그렇지 않다면 end+1
 * 부분합이 target과 같다면 result +1
 * start와 end를 증가시키는 방향으로 변화시켜 가면서 부분 배열의 합이 정확히 target이 되는 경우를 센다.
 * N칸의 1차원 배열이 있을 때, 부분 배열 중 그 원소의 합이 target이 되는 경우의 수를 구하기에는 구간 합을 구간 배열로 O(1)로 구한다고 해도 경우의 수는 O(N^2)이다. 하지만 two-pointer algorithm을 사용하면 O(N) 으로 줄일 수 있다.
 * <p>
 * Two-Pointer 시간복잡도
 * 매 루프마다 항상 두 포인터 중 하나는 1씩 증가한다.
 * 각 포인터를 start, end라고 했을 때 start와 end는 최대 N까지 증가할 수 있고, 항상 start <= end 이다.
 * 둘이 증가하는 과정은 최대 N번만 반복된다.
 * O(N^2) 가 걸리는 문제를 O(N)에 해결할 수 있음
 * <p>
 * 입력
 * 5 5     //N M(서브셋의 합)
 * 1 2 3 4 5
 * <p>
 * 결과 2   [2,3] [5]
 */
public class Main_2003_S4_수들의_합_2 {

	public static void main(String[] args) throws Exception {
//		BufferedReader br = new BufferedReader(new StringReader(input));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// 데이타 입렵 받기 
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		// N개의 데이타를 입력 받을 배열 생성 
		// N개의 데이타 입렵 받기 
		int[] data = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			data[i] = Integer.parseInt(st.nextToken());
		}

//		1. 시작점과 끝점이 첫번째 원소의 인덱스를 가리키도록 함
		int end = 0, count = 0;
		int sum = 0;


//		2.
//		   2.1 현재 부분의 합이 target과 같다면 카운트
//		if (sum == M) count++;

		// start 증가시키면서 포인터 이동
		for (int start = 0; start < N; start++) {
			// 2.2 현재 부분의 합이 target 보다 작다면 end를 1 증가
			// for 들이 startf를 증가시킴
			while (sum < M && end < N) {
				sum += data[end];
				end++;
			}

			if (sum == M) count++;

			// 2.3 현재 부분의 합이 target 보다 크다면 start를 1 증가
			sum -= data[start]; // start가 증가사히겆에 sum에서 start 제거 필요
		}


		// start를 차례대로 증가시키며 반복

		System.out.println(count);
	}

	static String input = "5 5\n"
			+ "1 2 3 4 5";
}
