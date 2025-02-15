package fillCell;
import java.util.Scanner;
/**
 * 배열 개수와 좌표를 입력 받아 해당 좌표에 상하좌우에 1을 표시한후 전체 배열을 화면에 출력
 * 경계 검사
 * 
 * 입력 : 
 *  배열의 개수   row좌표, column좌표
 *  ex) 3 0 0 
 * 
 */
public class FillCell03_delta {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[][] map = new int[N][N];
		int r = sc.nextInt();
		int c = sc.nextInt();
		////////////////
		/// 처리 코드 작성
		//			상 하 좌 우		
//		int[] dr= {-1, 1, 0, 0};
//		int[] dc= { 0, 0,-1, 1};
		
//				   상  하 좌  우, 좌상, 우상, 좌하 우하 
		int[] dr= {-1, 1, 0, 0, -1  , -1, 1, 1};
		int[] dc= { 0, 0,-1, 1, -1  , 1 ,-1, 1};
		
		int d = 8;
		int nr, nc; //다음 좌표
		for (int i = 0; i <d; i++) {
			nr = r + dr[i];
			nc = c + dc[i];
			//사방 경계 검사 
			if(nr>-1 && nr<N && nc>-1 && nc<N) {  
				map[nr][nc] = 1;
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
	}
}





