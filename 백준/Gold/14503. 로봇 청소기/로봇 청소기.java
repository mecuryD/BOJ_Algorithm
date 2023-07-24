import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		/* input */
		int n = sc.nextInt();
		int m = sc.nextInt();
		int r = sc.nextInt();
		int c = sc.nextInt();
		int d = sc.nextInt();
		
		int[][] arr = new int[n][m];
		for(int i=0; i<n; i++) { // O(N^2)
			for(int j=0; j<m; j++) {
				arr[i][j] = sc.nextInt();	
			}
		}
		
		// 방향벡터 (시계 방향, 북동남서)
		int[] dc = {0, 1, 0, -1};
		int[] dr = {-1, 0, 1, 0};
		
		// 움직인 칸 수
		int count = 0;
		
		/* cleaning */
		
		// 빈칸=0, 벽=1, 청소된 곳=2
		while(true) {
			// 1. 현재 칸이 아직 청소되지 않은 경우, 현재 칸을 청소한다
			if(arr[r][c] == 0) {
				arr[r][c] = 2;
				count++;
			}
			
			/* 현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 있는지 체크 */
			boolean isEmpty = false;
			for(int i=0; i<4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				if(arr[nr][nc]==0) {
					isEmpty = true;
				}
			}
			
			/* 2. 빈 칸이 있는 경우 */
			if(isEmpty){ 
				// 1) 반시계 방향으로 90도 회전
				d = setDirection(d);
				// 2) 앞쪽 칸이 청소되지 않은 빈 칸인 경우, 한 칸 전진
				if(arr[r+dr[d]][c+dc[d]]==0) {
					r = r + dr[d];
					c = c + dc[d];
				}
				// 3) 1번으로 돌아간다
				continue;
			}else {
				/* 3. 빈 칸이 없는 경우 */
				// 1) 후진 가능하면 1칸 후진하고 1번으로 돌아간다
				if(arr[r-dr[d]][c-dc[d]] == 2) { 
					r = r - dr[d]; //r-0
					c = c - dc[d];////c-0
					continue;
				}else if(arr[r-dr[d]][c-dc[d]] == 1) {
					// 2) 후진 불가능하면 동작 정지
					break;
				}
			}
		}
		System.out.println(count);
	}
	
	// 현재 방향에서 반시계 방향으로 90도 회전
	public static int setDirection(int d) {
		if(d==0) {
			return 3;
		}
		return d-1;
	}
}