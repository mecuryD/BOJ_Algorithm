import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main{
	static int[] man = new int[25];
	static int[][] board = new int[5][5];
	static int[][] check = new int[5][5];
	
	public static void main(String[] args) throws IOException {
		
		/* INPUT */
		StringTokenizer stk;
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		// 빙고판
		for(int i=0; i<5; i++) {
			stk = new StringTokenizer(bf.readLine(), " ");
			for(int j=0; j<5; j++) {
				board[i][j] = Integer.parseInt(stk.nextToken());
			}
		}
		
		// 사회자
		for(int i=0; i<5; i++) {
			stk = new StringTokenizer(bf.readLine(), " ");
			for(int j=0; j<5; j++) {
				man[i*5+j] = Integer.parseInt(stk.nextToken());
			}
		}

		/* GAME START */
		for(int i=0; i<25; i++) {
			// 빙고판에 불려진 숫자 체크
			for(int j=0; j<5; j++) {
				for(int k=0; k<5; k++) {
					if (board[j][k]==man[i]) {
						check[j][k] = 1;
					}
				}
			}
			
			// 3개의 빙고가 완성되면
			if(isThreeBingo()) {
				System.out.printf("%d", i+1);
				break;
			}
		}
	}
	
	/* 빙고 개수가 3개 이상이면 true를 반환하는 메서드 */
	public static boolean isThreeBingo() {

		int idx = 0;
		int[] sum = new int[12];
		
		// 빙고 여부 확인
		for(int i=0; i<5; i++) { 
			idx = i * 2;
			sum[idx] = check[i][0] + check[i][1] + check[i][2] + check[i][3] + check[i][4];	// 행 방향		
			sum[idx+1] = check[0][i] + check[1][i] + check[2][i] + check[3][i] + check[4][i]; // 열 방향		
		}
		
		sum[10] = check[0][0] + check[1][1] + check[2][2] + check[3][3] + check[4][4]; // 왼쪽 대각선 방향
		sum[11] = check[4][0] + check[3][1] + check[2][2] + check[1][3] + check[0][4]; // 오른쪽 대각선 방향
		
		// 빙고 개수 카운트
		int count = 0;
		for(int s:sum) {
			if(s==5) {
				count++;
			}
		}
		// 빙고 개수가 3개 이상이면 true 반환
		if(count>=3) {
			return true;
		}
		return false;
	}
}