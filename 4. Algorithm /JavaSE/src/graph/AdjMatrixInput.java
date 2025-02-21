package graph;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
//import java.util.LinkedList;
import java.util.StringTokenizer;

/*
 * Node 수가 1000개인 경우까지는.. 여유 있게 인접 행렬로 표시할 수 있기 때문에
 * 데이터가 인접 리스트처럼 link 정보가 입력 되도 행렬로 처리 하면 된다.
 * 
 * link 정보로 주어질 때 주의점
 * ⇒ 그래프가 단방향인 경우 “from → to”만 처리
 * ⇒ 그래프가 무향(양방향)인 경우
 * ⇒ 연결 정보가 1개만 들어오는 경우.. “from → to”, “to → from” 처리를 해야 한다.
 */

public class AdjMatrixInput {
	static boolean map[][];
	static boolean visited[];  //노드 개수만큼만 만들기
	static int N, link;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/graph/AdjList.txt"));
		BufferedReader in =  new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(in.readLine());
//		입력 받기 
		for (int tc = 1; tc <=T; tc++) {
			N = Integer.parseInt(in.readLine());
			link = Integer.parseInt(in.readLine());
//			그래프를 담을 인접 배열 생성하기 	
			map = new boolean[N][N];
//			그래프를 방문 처리 하기 위한 배열 생성하기 
			visited=new boolean[N];
//			그래프 정보 입력 받기
			for (int i = 0; i < link; i++) {
				StringTokenizer st = new StringTokenizer(in.readLine()," ");
				int from = Integer.parseInt(st.nextToken()); //노드 정보
				int to = Integer.parseInt(st.nextToken()); //인접 정보
				map[from][to] = true;
				//무향(양방향)인데 link 정보가 1개만 입력 된다면..
				// map[to][from] = true;
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
		visited[cur] = true;
//		큐에 첫 노드 추가하기 
		q.offer(cur);
		
//		큐에 더이상 노드가 없을때까지 탐색하기 
		while(!q.isEmpty()) {
//			탐색할 노드를 큐에서 추출하기
			cur = q.poll();
//			visited[cur] = true;
//			노드에 대한 처리하기
			System.out.printf("%c->", cur+'A');
//			
//			현재 탐색하는 노드의 인접 모든 노드들을 방문시도
			for (int ad = 0; ad <N; ad++) {
//				   인접됐고				방문한적이 없다면
				if(map[cur][ad] && !visited[ad]) {
//				    방문 처리 하고 큐에 추가하기
					visited[ad] = true;
					q.offer(ad);
				}
			}
		}
	}
}


















