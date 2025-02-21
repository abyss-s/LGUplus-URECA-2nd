package boj.gold;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * - map을 구역 나누기 문제
 * - 같은 구역인 경우 count세기
 * - 모든 노드를 1번씩만 탐색하기 때문에 dfs, bfs 모두 가능
 * - 전체 맵을 반복하면서
 * 방문하지 않은 노드라면 그 노드 부터 같은 구역인지 (dfs, bfs) 탐색하기
 * <p>
 * - 적녹색약
 * 1. 비적녹색약인을 위한 버전으로 구역 탐색하고
 * 2. 적녹색약인을 위해 visits 초기화 하고
 * map의 정보를 G->R로 바꾸거나 R->G으로 바꾼후 구역 탐색하기
 */
public class Main_10026_G5_적록색약 {
	static int N;
	static char map[][];
	static boolean visits[][];
	static int dr[] = {-1, 0, 0, 1};
	static int dc[] = {0, 1, -1, 0};

	public static void main(String args[]) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		map = new char[N][N];
		visits = new boolean[N][N];

		for (int i = 0; i < N; i++) {
			map[i] = in.readLine().toCharArray();
		}

		int dltonism = 0, nonDltonism = 0;

		// 비적녹색약인 위한 탐색
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visits[i][j]) {
					dfs(i, j, map[i][j]);
					nonDltonism++; // 구역 카운트
				}
			}
		}

		// 적녹색약인 위한 탐색
		visits = new boolean[N][N];

		// G -> R, R -> G 변경
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 'G') {
					map[i][j] = 'R'; // G를 R로 변경
				}
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visits[i][j]) {
					dfs(i, j, map[i][j]);
					dltonism++; // 구역 카운트
				}
			}
		}

		System.out.println(nonDltonism + " " + dltonism);
	}

	public static void dfs(int r, int c, char color) {
		visits[r][c] = true; // 현재 노드 방문 처리

		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d]; // 새로운 행
			int nc = c + dc[d]; // 새로운 열

			if (nr >= 0 && nc >= 0 && nr < N && nc < N) {
				// 방문하지 않았고, 현재 노드와 같은 색이라면 같은 구역이므로
				if (!visits[nr][nc] && map[nr][nc] == color) {
					dfs(nr, nc, color); // 같은 색의 노드 계속 탐색히러 다음으로 이동
				}
			}
		}
	}
}