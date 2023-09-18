import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{

	public static void main(String[] args) throws IOException {
		// 입력 초기화
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine(), " ");
		int R = Integer.parseInt(stk.nextToken()); // 방의 행 크기
		int C = Integer.parseInt(stk.nextToken()); // 방의 열 크기
		int T = Integer.parseInt(stk.nextToken()); // 소요 시간
		
		int[][] map = new int[R][C]; // RxC 크기의 방
		for(int i=0; i<R; i++) {
			stk = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<C; j++) {
				map[i][j] = Integer.parseInt(stk.nextToken());
			}
		}
		
		// 공기청정기의 위치를 찾는다
		int[] air = new int[2];
		for(int i=0; i<R; i++) {
			if(map[i][0]==-1) {
				air[0] = i;
				air[1] = i+1;
				break;
			}
		}
		
		// T초동안 미세먼지 확산 및 공기청정기 동작
		for(int t=1; t<=T; t++) {
			// 미세먼지 확산
			map = spreadDust(map, R, C);
			
			// 공기청정기 동작
			onAirCondition(map, air, R, C);
		}
		
		
		// T초가 지난 후 방에 남아 있는 미세먼지의 양 출력
		int dustSum = 0;
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				dustSum += map[i][j];
			}
		}
		
		System.out.println(dustSum+2); // 공기청정기 값 복원
	}
	
	
	// 미세먼지 확산
	public static int[][] spreadDust(int[][] map, int R, int C){
		int[] dr = {-1,1,0,0}; // 4방향 벡터
		int[] dc = {0,0,-1,1}; // 4방향 벡터
		
		// 맵 배열 복사
		int[][] copyMap = new int[R][C];
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				copyMap[i][j] = map[i][j];
			}
		}
		// 각 칸에 대해 반복
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				// 확산될 수 있는 미세먼지가 아닌 경우
				if(map[i][j] < 5) 
					continue; 
				// 미세먼지 확산
				int nr, nc, spread;
				for(int k=0; k<4; k++) {
					nr = i + dr[k];
					nc = j + dc[k];
					if(nr<0 || nc<0 || nr>=R || nc>=C) continue; // 배열 범위 초과
					if(map[nr][nc]==-1) continue; // 공기청정기가 있는 칸으로는 확산 불가
					spread = map[i][j] / 5; // 확산되는 미세먼지의 양
					copyMap[nr][nc] += spread; // 미세먼지 확산
					copyMap[i][j] -= spread;
				}
			}
		}
		
		
		// 미세먼지가 확산된 결과 반환
		return copyMap;
	}
	
	// 공기청정기 작동
	public static void onAirCondition(int[][] map, int[] air, int R, int C){
		int top = air[0];
		
		for (int x = top - 1; x > 0; x--) {
            map[x][0] = map[x - 1][0];
        }
 
        for (int y = 0; y < C - 1; y++) {
            map[0][y] = map[0][y + 1];
        }
 
        for (int x = 0; x < top; x++) {
            map[x][C - 1] = map[x + 1][C - 1];
        }
 
        for (int y = C - 1; y > 1; y--) {
            map[top][y] = map[top][y - 1];
        }
 
        map[top][1] = 0; // 공기청정기로 나가는 곳이므로 먼지는 0이다.
 
        int bottom = air[1]; // 공기청정기 밑 부분좌표며, 시계방향으로 진행
 
        for (int x = bottom + 1; x < R - 1; x++) {
            map[x][0] = map[x + 1][0];
        }
 
        for (int y = 0; y < C - 1; y++) {
            map[R - 1][y] = map[R - 1][y + 1];
        }
 
        for (int x = R - 1; x > bottom; x--) {
            map[x][C - 1] = map[x - 1][C - 1];
        }
 
        for (int y = C - 1; y > 1; y--) {
            map[bottom][y] = map[bottom][y - 1];
        }
 
        map[bottom][1] = 0; // 공기청정기로 나가는 곳이므로 먼지는 0이다.
	}
}
