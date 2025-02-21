package boj.gold;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_1781_G2_컵라면_이영주 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		PriorityQueue<int[]> probs = new PriorityQueue<>((a, b) -> {
			int result = a[0] - b[0];
			return result == 0 ? b[1] - a[1] : result;
		});

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int deadline = Integer.parseInt(st.nextToken());
			int ramen = Integer.parseInt(st.nextToken());

			probs.offer(new int[]{deadline, ramen});
		}

		// 선택한 문제의 컵라면 수를 담을 우선순위큐 (기본적으로 오름차순 정렬)
		PriorityQueue<Long> choose = new PriorityQueue<>();

		while (!probs.isEmpty()) {
//	        과제를 꺼내와 컵라면 수PQ의 size 와 문제 데드라인을 비교한다.
//	            ==> 컵라면 수 PQ의 size == 단위 시간 == 데드라인
			int[] prob = probs.poll();
			int deadline = prob[0];
			int ramen = prob[1];

			if (choose.size() < deadline) { // 선택한 문제수가 많으면
				choose.offer((long) ramen); // 현재 문제를 처리할 수 있으므로 choose에 넣을 수 있다.
			} else { // 선택한 문제수가 많으면 deadline 안에 풀 수 없음
				if (choose.peek() < ramen) {  // 현재 문제의 라면 개수보다 이미 선택한 라면 개수 중 작은것이 있다면
					choose.poll(); // 작은 걸 빼고 현재 문제의 라면을 넣는다.
					choose.offer((long) ramen);
				}
			}
		}

		// 과제 문제와 달리 long 타입 으로 처리해야 한다.
		long result = 0L;
		for (Long ramen : choose) {
			result += ramen;
		}
		System.out.println(result);
	}
}

