package boj.silver;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 시작 위치가 (1, 1) 도착 위치 (N,M) 이므로
 * 배열을 N+1, M+1 로 만들어서 1,1 부터 저장하고  1,1부터 탐색
 * <p>
 * 하지만 위치에 따른 다른 처리가 없고, 최소 거리수만 출력하면 되므로
 * N, M 배열을 만들어서 0,0부터 탐색해서 N-1, M-1에 도착하면 된다.
 * <p>
 * 단 출발 위치부터 카운트를 세야 한다.
 * <p>
 * 가중치가 없는 최소 거리(비용)문제이므로 아묻따 BFS!!
 */
public class Main_2178_S1_미로탐색 {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][M];
		boolean[][] visited = new boolean[N][M];

//		맵 정보를 입력 받는다.
		for (int i = 0; i < N; i++) {
			String line = in.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = line.charAt(j) - '0';
			}
		}

//		4방향 탐색을 위한 delta 배열 선언
		int[] dr = {-1, 1, 0, 0};
		int[] dc = {0, 0, -1, 1};

//		BFS를 위한 큐 생성
		Queue<int[]> q = new ArrayDeque<int[]>();
//		첫 위치와 거리를 큐에 넣고 방문 처리, 주위점) 시작 위치부터 카운트해야 한다!!
		q.offer(new int[]{0, 0, 1}); // r,c,distance
		visited[0][0] = true;

//		사방 탐색을 위한 현재 좌표와 다음좌표 거리를 위한 변수 선언하기
		int r, c, dist, nr, nc;

//		큐에 탐색할 노드가 없을때까지 탐색 시도
		while (!q.isEmpty()) {
//			노드 하나 추출하기
			int[] cur = q.poll();
//			현재 좌표를 편히 쓰기 위하 r,c,dist 변수에 담기
			r = cur[0];
			c = cur[1];
			dist = cur[2];
//			사방을 돌면서 다음 좌표를 구하고
			for (int i = 0; i < 4; i++) {
				nr = r + dr[i];
				nc = c + dc[i];

//				경계내에 있고 방문하지 않고 이동할 수 있다면
				if (nr >= 0 && nr < N && nc >= 0 && nc < M && !visited[nr][nc] && map[nr][nc] == 1) {
					// 방문 처리
					visited[nr][nc] = true;

					// 다음 좌표가 도착 위치인 경우
					if (nr == N - 1 && nc == M - 1) {
						System.out.println(dist + 1);
						return; // 마치고 종료
					}

					// 도착 위치가 아니면 다음 좌표를 탐색하기 위해 큐에 넣기
					q.offer(new int[]{nr, nc, dist + 1});
				}
			}
		}
	}
}