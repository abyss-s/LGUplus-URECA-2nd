package boj.gold;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main_1781_G2_컵라면_이영주 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		List<Problem> problems = new ArrayList<>();

		// 문제 입력받기
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int deadline = Integer.parseInt(st.nextToken());
			int ramen = Integer.parseInt(st.nextToken());
			problems.add(new Problem(deadline, ramen));
		}

		// 데드라인 기준 문제를 오름차순 정렬
		Collections.sort(problems);

		// 우선순위큐 선언 컵라면 기준 내림차순 정렬
		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
		int result = 0;
		int idx = 0;

		for (int day = N; day > 0; day--) {
			while (idx < N && problems.get(idx).deadline >= day) {
				pq.offer(problems.get(idx).ramen);
				idx++;
			}
			if (!pq.isEmpty()) {
				result += pq.poll();
			}
		}

		System.out.println(result);
	}

	static class Problem implements Comparable<Problem> {
		int deadline, ramen;

		Problem(int deadline, int ramen) {
			this.deadline = deadline;
			this.ramen = ramen;
		}

		@Override
		public int compareTo(Problem p) {
			return this.deadline - p.deadline;
		}
	}
}

