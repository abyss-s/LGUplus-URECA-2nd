package floyd;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 모든 쌍 최단 경로
 * O(n^3)
 * <p>
 * if(i==j || k==j) continue; 		코드를 넣었을 때  시간 복잡도
 * 모든 노드가 자기 노드로 가는 경우가 있으므로 이것을 빼면 O((n-1)^3)
 * <p>
 * 하지만   if(i==j || k==j) continue는 N^3만큼 수행된다. 그래서 오히려 더 시간이 오래 걸린다.
 * <p>
 * <p>
 * <p>
 * [input]
 * <p>
 * 4
 * 0 2 0 15
 * 0 0 10 4
 * 3 0 0 0
 * 0 0 7 0
 * <p>
 * ==>output
 * 0	2	12	6
 * 13	0	10	4
 * 3	5	0	9
 * 10	12	7	0
 * <p>
 * <p>
 * [input]
 * 5
 * 0 4 2 5 0
 * 0 0 1 0 4
 * 1 3 0 1 2
 * 2 0 0 0 2
 * 0 3 3 1 0
 * <p>
 * ==>==>output
 * 0	4	2	3	4
 * 2	0	1	2	3
 * 1	3	0	1	2
 * 2	5	4	0	2
 * 3	3	3	1	0
 * <p>
 * ==============================음의 가중치 그래프=========================================
 * <p>
 * 5
 * 0 4 2 5 0
 * 0 0 1 0 4
 * 1 3 0 1 2
 * -2 0 0 0 2
 * 0 -3 3 1 0
 * <p>
 * ==>
 * 0	1	2	3	4
 * 0	0	1	2	3
 * -1	-1	0	1	2
 * -2	-1	0	0	2
 * -3	-3	-2	-1	0
 */
public class Floyd {
	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/AllPairsShortest.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());
		int[][] map = new int[N][N];
		StringTokenizer tokens;
		final int MAX = Integer.MAX_VALUE >> 2;
		for (int i = 0; i < N; i++) {
			tokens = new StringTokenizer(in.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(tokens.nextToken());
				if (i != j && map[i][j] == 0) {  //연결되지 않은 경로
//					Integer.MAX_VALUE값을 주면 다른 경로 가중치를 더할 때 범위 벗어나서 값을 구하지 못함. 
					map[i][j] = MAX;
				}
			}
//			System.out.println(Arrays.toString(matrix[i]));
		}
		for (int k = 0; k < N; k++) {                //경유
			for (int i = 0; i < N; i++) {            //출발
//				if(i==k) continue; 					// 출발지와 경유지가 같다면 다음 출발지	
				for (int j = 0; j < N; j++) {        //도착
//					경유지와 목적지가 같거나 출발지가 곧 목적지라면 패스   
//					if(i==j || k==j) continue; 		
					if (map[i][j] > map[i][k] + map[k][j]) {
						map[i][j] = map[i][k] + map[k][j];
					}
				}
			}
		}

		for (int i = 0; i < N; i++) {
			System.out.println(Arrays.toString(map[i]));
		}
	}
}






