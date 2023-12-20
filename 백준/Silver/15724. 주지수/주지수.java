import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// [백준15724] 주지수
public class Main{

	static int N; // 영토의 행 크기
	static int M; // 영토의 열 크기
	static int K; // 궁금해하는 직사각형 범위의 개수
	
	static int[][] map; // N x M 영토 
	static int[][] rowMap; // 가로 누적합
	static int[][] colMap; // 세로 누적합
	static StringBuilder sb = new StringBuilder(); // 결과 저장
	public static void main(String[] args) throws IOException {
		// 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());
		
		map = new int[N+1][M+1];
		for(int i=1; i<=N; i++) { // 영토 입력
			stk = new StringTokenizer(br.readLine(), " ");
			for(int j=1; j<=M; j++) {
				map[i][j] = Integer.parseInt(stk.nextToken());
			}
		}
		
		// 누적 합 배열 계산
		int[][] rowMap = getRowMap();
		int[][] colMap = getColMap();
		
		K = Integer.parseInt(br.readLine()); 
		for(int i=0; i<K; i++) {
			// 직사각형 범위 입력
			stk = new StringTokenizer(br.readLine(), " ");
			int x1 = Integer.parseInt(stk.nextToken());
			int y1 = Integer.parseInt(stk.nextToken());
			int x2 = Integer.parseInt(stk.nextToken());
			int y2 = Integer.parseInt(stk.nextToken());
			// 직사각형 범위 내에 살고 있는 사람 수의 합을 구한다
			sb.append(countPeople(x1, y1, x2, y2)+"\n");		
		}
		
		// 결과 출력
		System.out.println(sb);
	}
	
	// 직사각형 범위 내에 살고 있는 사람 수의 합을 구한다
	public static int countPeople(int x1, int y1, int x2, int y2) {
		int count = 0;
		
		if(y1==y2) { // Nx1 직사각형
			count = colMap[x2][y1] - colMap[x1-1][y1];
		}else { // 1XM, NxM 직사각형
			for(int i=x1; i<=x2; i++) {
				count += rowMap[i][y2] - rowMap[i][y1-1];
			}
		}
		return count;
	}
	
	// 가로 방향 누적 합
	public static int[][] getRowMap(){
		rowMap = new int[N+1][M+1];
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=M; j++) {
				rowMap[i][j] = map[i][j] + rowMap[i][j-1];
			}
		}
		return rowMap;
	}
	
	// 세로 방향 누적 합
	public static int[][] getColMap(){
		colMap = new int[N+1][M+1];
		for(int i=1; i<=M; i++) {
			for(int j=1; j<=N; j++) {
				colMap[j][i] = map[j][i] + colMap[j-1][i];
			}
		}
		return colMap;
	}
}
