package p.boj.dfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 *   준비 단계
 *   1.  1,1 ~ R, C까지 좌표지만 좌표로 딱히 다른 계산을 하지 않고 최대 이동 거리만 나오므로 
 *   0,0 ~ R-1, C-1 좌표를 사용한다. 
 *   2. 방문 배열을 [R][C]만큼 할 필요 없이 [26]개의 일차원 배열을 사용한다. 
 *   ==> 이미 이동한 알파벳을 또 이동 할 수 없으므로 알파벳에 대한 방문 처리만 하면 된다. 
 *   3. 방문한 알파벳을 체크하기 위해 map은 char배열로 해서 -'A' 한 위치에서 방문 체크를 해도 되지만 
 *      편히 쓰기 위해 int[][]map을 선언한다.   
 *   4. 방문시 이미 방문한 알파벳이라면 뒤로 돌아가서 다른 길을 탐색해야 하므로 DFS로 탐색한다. 
 *   
 *   
 *   1. 입력 받기 
 *      맵 데이타 입력 받을 때 int[][] 이므로 -'A' 해준다.
 *   2. 0, 0좌표에서 이동 거리를 1로 해서 dfs로 탐색한다. 현재 알파벳도 count한다. 
 *   	2.1. 현재 위치의 알파벳을 방문 처리하고 max값을 갱신한다. 
 *   	2.2. max값이 26이면 더 탐색 할 필요가 없다.  (BT)
 *   	2.3. 사방을 탐색 시도  
 *        2.2.1 경계내에 있고 이동했던 알파벳이 아니면
 *              다음 좌표로 이동한다. 
 *      2.4. 다른 경로로 이동해 보기 위해 현재 알파벳에 대한 방문을 false로 한다. 
 *   
 *   3. max 값을 출력한다. 
 *    
 *  904ms
 */
public class Main_1987_G4_알파벳 {
	static int R, C;
    static int[][] map;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = { 0, 0,-1, 1};
    static int max = 0;
    static boolean[] alpha;
    
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st= new StringTokenizer(in.readLine(), " ");
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        
        //board를 입력받는다.
        map = new int[R][C];
        for(int i = 0; i < R; i++) {
            String str = in.readLine();
            for(int j = 0; j < C; j++) {
                map[i][j] = str.charAt(j) - 'A';
            }
        }
        alpha = new boolean[26]; //알파벳을 이전에 방문했는지 여부 체크.
        dfs(0, 0, 1);
        System.out.println(max);
    }
    
    public static void dfs(int r, int c, int len) {
    	int cur = map[r][c]; 
        alpha[cur] = true; 
        max = Math.max(max, len);
        if(max==26) return ;
        for(int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if(nr >-1 && nr < R && nc >-1 &&  nc < C && alpha[map[nr][nc]] == false) {
               
            	dfs(nr, nc, len + 1);
            }
        }
        alpha[cur] = false;
    }
}
