package graph;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

// 인접행렬
public class AdjMatrix_dfs0 {
	private static boolean map[][];
	private static boolean visited[];
	private static int N = 0;

	//	깊이 우선 탐색
	public static void DFS(int node) {
//		깊이 우선 탐색을 위한 스택을 선언한다. 
		Stack<Integer> stack = new Stack<Integer>();

//		스택에 탐색할 노드를 추가한다. 
		stack.push(node);
//		visited[node] = true;				//넣을때 방문 처리를 하면 dfs로 탐색이 불가

//		스택에 더이상 탐색할 노드가 없을 때까지 탐색하기
		while (!stack.isEmpty()) {
//			탐색할 노드를 스택에서 추출
			int current = stack.pop();
			if (!visited[current]) {
//			방문처리하고
				visited[current] = true;
//				추출한 노드에 대한 처리 
				System.out.printf(" %c", current + 65);

//				현재 노드의 인접된 노드에 대한 방문 시도
				for (int adj = N - 1; adj >= 0; adj--) {
//					방문한 적이 없고 		인접 되어 있다면
					if (!visited[adj] && map[current][adj]) {
//						스택에 추가하기
						stack.push(adj);
					}
				}
			}
		}
	}

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("4. Algorithm /JavaSE/src/graph/AdjMatrix.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(in.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(in.readLine());
			map = new boolean[N][N];
			visited = new boolean[N];
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(in.readLine(), " ");
				for (int j = 0; j < N; j++) {
					if (Integer.parseInt(st.nextToken()) == 1) {
						map[i][j] = true;
					}
				}
			}
			DFS(0);
			System.out.println();
		}
	}
}
