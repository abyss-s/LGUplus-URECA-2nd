package boj.silver;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * ���� ��ġ�� (1, 1) ���� ��ġ (N,M) �̹Ƿ�
 * �迭�� N+1, M+1 �� ���� 1,1 ���� �����ϰ�  1,1���� Ž��
 * <p>
 * ������ ��ġ�� ���� �ٸ� ó���� ����, �ּ� �Ÿ����� ����ϸ� �ǹǷ�
 * N, M �迭�� ���� 0,0���� Ž���ؼ� N-1, M-1�� �����ϸ� �ȴ�.
 * <p>
 * �� ��� ��ġ���� ī��Ʈ�� ���� �Ѵ�.
 * <p>
 * ����ġ�� ���� �ּ� �Ÿ�(���)�����̹Ƿ� �ƹ��� BFS!!
 */
public class Main_2178_S1_�̷�Ž�� {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][M];
		boolean[][] visited = new boolean[N][M];

//		�� ������ �Է� �޴´�.
		for (int i = 0; i < N; i++) {
			String line = in.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = line.charAt(j) - '0';
			}
		}

//		4���� Ž���� ���� delta �迭 ����
		int[] dr = {-1, 1, 0, 0};
		int[] dc = {0, 0, -1, 1};

//		BFS�� ���� ť ����
		Queue<int[]> q = new ArrayDeque<int[]>();
//		ù ��ġ�� �Ÿ��� ť�� �ְ� �湮 ó��, ������) ���� ��ġ���� ī��Ʈ�ؾ� �Ѵ�!!
		q.offer(new int[]{0, 0, 1}); // r,c,distance
		visited[0][0] = true;

//		��� Ž���� ���� ���� ��ǥ�� ������ǥ �Ÿ��� ���� ���� �����ϱ�
		int r, c, dist, nr, nc;

//		ť�� Ž���� ��尡 ���������� Ž�� �õ�
		while (!q.isEmpty()) {
//			��� �ϳ� �����ϱ�
			int[] cur = q.poll();
//			���� ��ǥ�� ���� ���� ���� r,c,dist ������ ���
			r = cur[0];
			c = cur[1];
			dist = cur[2];
//			����� ���鼭 ���� ��ǥ�� ���ϰ�
			for (int i = 0; i < 4; i++) {
				nr = r + dr[i];
				nc = c + dc[i];

//				��賻�� �ְ� �湮���� �ʰ� �̵��� �� �ִٸ�
				if (nr >= 0 && nr < N && nc >= 0 && nc < M && !visited[nr][nc] && map[nr][nc] == 1) {
					// �湮 ó��
					visited[nr][nc] = true;

					// ���� ��ǥ�� ���� ��ġ�� ���
					if (nr == N - 1 && nc == M - 1) {
						System.out.println(dist + 1);
						return; // ��ġ�� ����
					}

					// ���� ��ġ�� �ƴϸ� ���� ��ǥ�� Ž���ϱ� ���� ť�� �ֱ�
					q.offer(new int[]{nr, nc, dist + 1});
				}
			}
		}
	}
}