import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static int w, h;
	public static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		while (true) {

			/* input */

			// 지도의 너비, 높이 입력
			StringTokenizer stk = new StringTokenizer(bf.readLine(), " ");
			w = Integer.parseInt(stk.nextToken());
			h = Integer.parseInt(stk.nextToken());

			// 입력의 마지막 줄이므로 break
			if (w == 0 && h == 0) {
				break;
			}

			// 지도 입력
			map = new int[h][w];
			for (int i = 0; i < h; i++) {
				stk = new StringTokenizer(bf.readLine(), " ");
				for (int j = 0; j < w; j++) {
					map[i][j] = Integer.parseInt(stk.nextToken());
				}
			}

			/* dfs */
			int count = 0;
			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					if (map[i][j] == 1) {
						dfs(i, j);
						count++;
					}
				}
			}
			System.out.println(count);
		}
	}
    
	 public static void dfs(int r, int c) {
		 // 방문 체크
		 if(map[r][c] == 1){
			 map[r][c] = 0;
		}
	 
		 // 근처 탐색
		 int[] dr = {-1,+1,0,0,-1,-1,+1,+1};
		 int[] dc = {0,0,-1,+1,-1,+1,-1,+1};
		 for(int i=0; i<8; i++) {
			 // 8방향 탐색
			 int nr = r + dr[i];
			 int nc = c + dc[i];
			 
			 // 지도 범위에서 벗어나면 리턴
			 if(nr<0 || nr>=h || nc<0 || nc>=w) {
				 continue;
			 }
			 
			 if(map[r+dr[i]][c+dc[i]] == 1) {
				 dfs(r+dr[i],c+dc[i]);
			 }
		 }
	 }
}
