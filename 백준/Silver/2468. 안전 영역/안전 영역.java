import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// input
		int n = sc.nextInt();
		int[][] map = new int[n][n];
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				map[i][j] = sc.nextInt();
			}
		}
		
		// iteration dfs
		int max_height = max(map);
		int min_height = min(map);
		int max_count = 0;
		
		for (int i=min_height; i<=max_height; i++) {
			
			int[][] copy = new int[map.length][];
	        for (int j = 0; j < map.length; j++) {
	            copy[j] = Arrays.copyOf(map[j], map[j].length);
	        }
	        int count = 0;
			for(int j=0; j<n; j++) {
				for(int k=0; k<n; k++) {
					if(dfs(copy,i,j,k)>0) {
						count++;
					}
				}
			}
			max_count = max_count < count ? count : max_count;
		}
		// print
		System.out.println(max_count);
	}
	
	/* 배열의 최대 원소값 계산*/
	public static int max(int[][] map) {
		int max_num = 0;
		int len = map.length;
		for(int i=0; i<len; i++) {
			for(int j=0; j<len; j++) {
				max_num = max_num < map[i][j] ? map[i][j] : max_num; 
			}
		}
		return max_num;
	}
	/* 배열의 최소 원소값 계산*/
	public static int min(int[][] map) {
		int min_num = 101;
		int len = map.length;
		for(int i=0; i<len; i++) {
			for(int j=0; j<len; j++) {
				min_num = min_num > map[i][j] ? map[i][j] : min_num;
			}
		}
		return min_num;
	}
	
	/* dfs */
	public static int dfs(int[][] map, int h, int i, int j){
		// 배열 범위 초과 시 리턴
		if(i>=map.length || i<0 || j<0 || j>=map.length) {
			return 0;
		}
		
		// 물에 잠긴 곳이면 리턴
		if(map[i][j]<h) {
			return 0;
		}
		// 물에 잠기지 않은 곳이면, 방문 체크
		int count = 1;
		map[i][j] = h-1;
		
		// 현재 위치에서 상하좌우 탐색
		count += dfs(map, h, i-1, j);
		count += dfs(map, h, i+1, j);
		count += dfs(map, h, i, j-1);
		count += dfs(map, h, i, j+1);
		
		return count;
	}

}
