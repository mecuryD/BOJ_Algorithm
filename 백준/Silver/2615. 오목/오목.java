import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	public static int res = 0;
	public static int rock = 0;
	public static int [][] map = new int[19][19];
	
	public static void main(String[] args) throws IOException {
		StringTokenizer stk;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 바둑판 입력
		for(int i=0; i<19; i++) {
			stk = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<19; j++) {
				map[i][j] = Integer.parseInt(stk.nextToken());
			}
		}
		
		// 바둑판의 각 칸에 대해 승리 여부 체크
		int res = 0;
		int resX = 0;
		int resY = 0;
		Loop1:
		for(int i=0; i<19; i++) {
			for(int j=0; j<19; j++) {
				if(map[i][j]!=0) {
					// 바둑알이 존재하면 4방향 탐색
					rock = map[i][j];
					res = search(i,j);
					if(res>0) {
						resY = i+1;
						resX = j+1;
						break Loop1;
					}
				}
			}
		}
		
		System.out.println(res);
		if(res>0) {
			System.out.println(resY + " " + resX);
		}
	}
	
	public static int search(int r, int c) {
		// 연속된 돌의 개수를 담는 변수
		int cnt = 0;
		
		// 4개 방향 : 오른쪽, 아래, 오른쪽 위, 오른쪽 아래
		int[] dr = {0, 1, -1, 1};
		int[] dc = {1, 0, 1, 1};
		
		// 4개 방향 탐색
		for(int i=0; i<4; i++) {
			// 위치 업데이트 변수
			int nr = r;
			int nc = c;
			
			// 해당 방향에 4칸이 존재하는지 확인
			if((nr + 4*dr[i] > 18)||(nr + 4*dr[i] < 0)) {
				continue;
			}
			if((nc + 4*dc[i] > 18)||(nc + 4*dc[i] < 0)) {
				continue;
			}
			
			// 현재 방향에 연속된 5개의 돌이 있는지 확인
			cnt = 0;
			for(int j=0; j<4; j++) {
				nr += dr[i];
				nc += dc[i];
				
				if(map[nr][nc]==rock) {
					cnt++;
				}
			}
			
			// 연속된 5개의 돌이 없는 경우, 다음 방향 탐색
			if(cnt<4) {
				continue;
			}
			
			// 연속된 5개의 돌이 있는 경우, 육목인지 확인
			res = rock;
			
			// 정방향 육목 체크
			nr = nr + dr[i];
			nc = nc + dc[i];
			if(nr>=0 && nr<19 && nc>=0 && nc<19) { // 육목
				if(map[nr][nc]==rock) {
					res = 0;
				}
			}
			
			// 역방향 육목 체크
			nr = r - dr[i];
			nc = c - dc[i];
			if(nr>=0 && nr<19 && nc>=0 && nc<19) { // 육목
				if(map[nr][nc]==rock) {
					res = 0;
				}
			}
			if(res>0) {
				break;
			}
		}
		return res;
	}

}