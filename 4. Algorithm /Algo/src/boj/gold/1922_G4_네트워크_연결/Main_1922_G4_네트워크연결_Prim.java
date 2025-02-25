package p.boj.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * Solution_D4_3124_최소스패닝트리  
 * 408ms     
 */
public class Main_1922_G4_네트워크연결_Prim {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(in.readLine());
		int M = Integer.parseInt(in.readLine());
		
		int[][] map = new int[N+1][N+1];
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(in.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			map[from][to] = w;
			map[to][from] = w;
		}
		
		boolean[] visited = new boolean[N+1];
		int[] minEdge = new int[N+1];
		Arrays.fill(minEdge, Integer.MAX_VALUE);
		
		
		minEdge[1] = 0;
		int min,minVertex;
		long result=0;
		
		//시작정점을 뺀 나머지 정점 수 만큼 반복
		for(int c = 1; c <= N; c++) {
			min = Integer.MAX_VALUE;
			minVertex = -1;  //index 정점에서 출발하는 모든 간선에 대해 dist를 업데이트 
			//step1. 미 방문(비트리) 정점 중 최소 간선 비용의 정점을 선택 
			for(int i = 1; i <= N; i++) {
				if( !visited[i] && minEdge[i] < min ) {
					min = minEdge[i];		//최소 비용 update
					minVertex = i;			//최소 비용의 정점 선택
				}
			}
			
			if(minVertex == -1) break;
			result += min;				//신장 트리 비용 누적
			visited[minVertex] = true;  //트리 정점에 포함하기 위해 방문 체크
			
//			step2. 새롭게 트리 정점에 확장된 정점 기준으로 비트리 정점들과의 간선 비용을 고려해서 최적 업데이트
			for(int i = 1; i <= N; i++) {
				//방문 안한 정점  중             연결이 되어 있고      minEdge에 저장된 값보다  가중치가 낮다면
				if( !visited[i] && map[minVertex][i]!=0 && map[minVertex][i]< minEdge[i] ) {
					minEdge[i] = map[minVertex][i];
				}
			}
		}
		System.out.println(result);
	}
}


