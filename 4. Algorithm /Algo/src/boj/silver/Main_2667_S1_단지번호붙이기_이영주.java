package boj.silver;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;


public class Main_2667_S1_단지번호붙이기_이영주 {
	static int N;
	static int no = 1;
	static int[][] map;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};

	public static void main(String[] args) throws Exception {
		// System.setIn(new FileInputStream("4. Algorithm /Algo/src/boj/silver/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			String line = in.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = line.charAt(j) - '0';
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 1) {
					no++;
					dfs(i, j, no);
				}
			}
		}
		--no;
		System.out.println(no);
		int[] count = new int[no + 2];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] > 0) {
					count[map[i][j]]++;
				}
			}
		}
		Arrays.sort(count);

		for (int i = 2; i <= no + 1; i++) {
			System.out.println(count[i]);
		}
	}

	public static void dfs(int x, int y, int no) {
		map[x][y] = no;
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (nx >= 0 && nx < N && ny >= 0 && ny < N && map[nx][ny] == 1) {
				dfs(nx, ny, no);
			}
		}
	}
}

