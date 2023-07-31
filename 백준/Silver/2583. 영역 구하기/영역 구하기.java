import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	public static int m, n, k;
	public static int[][] map;
	public static int x1, y1, x2, y2;
	
	public static void main(String[] args) throws IOException {
		/* 입력 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine(), " ");
		m = Integer.parseInt(stk.nextToken()); // 모눈종이의 세로 길이
		n = Integer.parseInt(stk.nextToken()); // 모눈종이의 가로 길이
		k = Integer.parseInt(stk.nextToken()); // 직사각형의 개수
		
		map = new int[m][n]; 		   // 모눈종이
		for(int i=0; i<k; i++) { 			   // K개의 직사각형을 모눈종이에 표시
			stk = new StringTokenizer(br.readLine(), " ");
			x1 = Integer.parseInt(stk.nextToken());
			y1 = Integer.parseInt(stk.nextToken());
			x2 = Integer.parseInt(stk.nextToken());
			y2 = Integer.parseInt(stk.nextToken());
			
			for(int j=y1; j<y2; j++) {			// 직사각형을 모눈종이에 표시
				for(int k=x1; k<x2; k++) {
					map[j][k] = 1;
				}
			}
		}
		
		/* 분리된 영역의 개수와 넓이 계산*/
		int cnt = 0;
		int area = 0;
		ArrayList<Integer> areas = new ArrayList<Integer>();
		
		for(int i=0; i<m; i++) {
			for(int j=0; j<n; j++) {
				if(map[i][j]==0) {
					areas.add(dfs(i,j));
					cnt++;
				}
			}
		}
		
		/* 결과 출력 */
		areas.sort((Integer a, Integer b)->{return a.compareTo(b);});
		System.out.println(cnt);
		for(int i=0; i<cnt; i++) {			
			System.out.print(areas.get(i)+" ");
		}
		

	}
	
	public static int dfs(int row, int col) {
		int res = 1;
		
		// 범위 벗어나면 탐색을 중지하고 리턴
		if(row<0 || row>=m || col<0 || col>=n) {return 0;}
		
		// 이미 방문했거나, 직사각형이 존재하는 곳이라면 리턴
		if(map[row][col]==1) {return 0;}
		
		// 방문 체크
		map[row][col] = 1;
		
		// 주변 영역 탐색
		res += dfs(row-1,col); // 북
		res += dfs(row,col+1); // 동
		res += dfs(row+1,col); // 남
		res += dfs(row,col-1); // 서
		
		return res;
	}
}