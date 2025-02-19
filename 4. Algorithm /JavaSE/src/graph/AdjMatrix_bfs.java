package graph;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class AdjMatrix_bfs {
	static boolean map[][];
	static boolean visited[];  //노드 개수만큼만 만들기
	static int N;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("4. Algorithm /JavaSE/src/graph/AdjMatrix.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(in.readLine());
//		입력 받기 
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt((in.readLine()));
//			그래프를 담을 인접 배열 생성하기
			map = new boolean[N][N];
//			그래프를 방문 처리 하기 위한 배열 생성하기 
			visited = new boolean[N];
//			그래프 정보 입력 받기
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(in.readLine());
				for (int j = 0; j < N; j++) {
					// 인접 체크
					if (Integer.parseInt(st.nextToken()) == 1) {
						map[i][j] = true;
					}
				}
			}

			BFS(0);
			System.out.println();
		}
	}

	/**
	 * 너비 우선 탐색
	 */
	private static void BFS(int cur) {
//		너비를 우선으로 탐색하기 위한 큐 선언
		Queue<Integer> q = new ArrayDeque<>();
//		첫 노드 방문 처리
		visited[cur] = true; // 넣을 때 방문처리
//		큐에 첫 노드 추가하기
		q.offer(cur);

//		큐에 더이상 노드가 없을때까지 탐색하기
		while (!q.isEmpty()) {
//			탐색할 노드를 큐에서 추출하기
			cur = q.poll(); // 꺼내기
//			노드에 대한 처리하기
			System.out.printf("%c->", cur + 'A');

//			
//			현재 탐색하는 노드의 인접 모든 노드들을 방문시도
			for (int ad = 0; ad < N; ad++) {
//				   인접됐고				방문한적이 없다면
				if (map[cur][ad] && !visited[ad]) {
//				    방문 처리 하고 큐에 추가하기
					visited[ad] = true;
					q.offer(ad);
				}
			}
		}
	}
}


















