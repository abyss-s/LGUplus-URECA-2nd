package boj.gold;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_15686_G5_치킨_배달_이영주 {
	static int n, m; // 도시 크기, 남겨야 하는 치킨집
	static int[][] chickens; // 치킨집 위치 저장
	static int[][] houses; // 집 위치 저장
	static int chickenCount = 0; // 치킨집 개수
	static int houseCount = 0; // 집 개수
	static int INF = 100000000; // 최소 거리 계산에 사용할 상수값 선언
	static int result = INF; // 최소 거리 (답)

	// 거리 계산 함수
	public static int calcDist(int x1, int y1, int x2, int y2) {
		return Math.abs(x1 - x2) + Math.abs(y1 - y2);
	}

	// 조합으로 순회하면서 거리 계산해서 최소값 구하기
	public static void combi(int start, int count, boolean[] visited) {
		// m개 선택되었을 때
		if (count == m) {
			int totalDist = 0;       // 전체 거리
			for (int i = 0; i < houseCount; i++) {
				int minDist = INF;
				for (int j = 0; j < chickenCount; j++) {
					if (visited[j]) { // 선택된 치킨집인 경우
						int dist = calcDist(houses[i][0], houses[i][1], chickens[j][0], chickens[j][1]);
						minDist = Math.min(minDist, dist); // 최소 거리 갱신
					}
				}
				totalDist += minDist;
			}
			result = Math.min(result, totalDist); // 최소 거리 갱신
			return;
		}

		// 치킨집에 대해 모두 순회
		for (int i = start; i < chickenCount; i++) {
			visited[i] = true; // 현재 치킨집 선택
			combi(i + 1, count + 1, visited); // 다음 단계
			visited[i] = false; // 방문함
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken()); // 도시 크기
		m = Integer.parseInt(st.nextToken()); // 남길 치킨집 개수

		chickens = new int[n * n][2]; // 치킨집 배열(x, y 좌표 저장)
		houses = new int[n * n][2]; // 집 배열(x, y 좌표 저장)

		// 도시 정보 입력 처리
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				int token = Integer.parseInt(st.nextToken());
				// 집이면 집 배열에
				if (token == 1) {
					houses[houseCount++] = new int[]{i, j};
				}
				// 치킨이면 치킨집 배열에
				else if (token == 2) {
					chickens[chickenCount++] = new int[]{i, j};
				}
			}
		}

		boolean[] visited = new boolean[chickenCount]; // boolean 배열로 해당 치킨집 방문 여부 처리
		combi(0, 0, visited); // 계산
		System.out.println(result); // 최소 거리 출력
	}
}