package fillCell;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * NxN 배열을 위한 기지국(A)와 집(H)가에 대한 정보가 주어진다.
 * 기지국은 상하좌우 1셀씩만 커버한다. 커버하지 못하는 집의 개수를 출력
 * <p>
 * [제약조건]
 * N은 3~15이하
 * <p>
 * [입력]
 * 첫줄은 테스트 케이스 수(T)가 주어진다.
 * 두번째 줄은 배열 크기(N)이 주어지고
 * 세번째 줄부터 N개의 기지국 정보가 제공된다.
 * <p>
 * [출력 결과]
 * #1 4
 * #2 5
 * #3 9
 */
public class BaseStation1 {
	//결과를 위한 변수    <= 여기서 선언하는 이유는 앞으로 많은 문제들이 함수를 통해 구현하는 경우가 많으므로 접근할 수 있도록 선언  
	static int AnswerN = 0;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("4. Algorithm /Algo/src/fillCell/BaseStation1.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			//테케 마다 결과 변수를 초기해줘야 한다.  그렇지 않으면 이전 값때문에 결과가 달라진다.  
			AnswerN = 0;
			int N = Integer.parseInt(br.readLine());
			//입력 처리
			char[][] map = new char[N][N];
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer((br.readLine()), " ");
				for (int j = 0; j < N; j++) {
					// String의 charAt(0)을 통해 문자열에서 특정 문자를 추출한다.
					map[i][j] = st.nextToken().charAt(0);
				}
			}

			// 상하좌우 방향으로 좌표를 처리하기 위한 변수
			int[] dr = {-1, 1, 0, 0};
			int[] dc = {0, 0, -1, 1};
			int nr = 0, nc = 0, d = 4;


			//구현하기 
			//1. 기지국을 찾아 상하좌우에 커버됐다고 표시하기
			//1.1 맵 전체를 탐색하면서
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					//1.2 기지국(A)를 찾는다.
					if (map[r][c] == 'A') {
						//1.3 기지국의 상하좌우의 좌표를 얻고 경계내에 있는지 검사
						for (int i = 0; i < d; i++) { // d => 사방(상하좌우) 처리
							nr = r + dr[i];
							nc = c + dc[i];
							// 경계 내애 있고, 해당 위치가 집이라면 기지국은 제외해야 함
							if (nr > -1 && nr < N && nc > -1 && nc < N
									&& map[nr][nc] == 'H') {
								//1.4 상하좌우에 커버됐다고 표시한다.
								map[nr][nc] = 'X';
							}
						}
					}
				}

			}
			//2. 맵 전체를 탐색하면 커버 안된 집의 개수를 센다.

			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if (map[r][c] == 'H') {
						AnswerN++;
					}
				}
			}

			//출력 
			System.out.println("#" + test_case + " " + AnswerN);
		}
	}//end main
}//end class









