import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// [BOJ14938] 서강그라운드
public class Main {
	
	public static void main(String[] args) throws IOException {
		// 입력 초기화
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine(), " ");
		int n = Integer.parseInt(stk.nextToken()); // 지역의 개수 n
		int m = Integer.parseInt(stk.nextToken()); // 예은이의 수색 범위 m
		int r = Integer.parseInt(stk.nextToken()); // 길의 개수 r
		
		int[] items = new int[n+1]; // 각 지역의 아이템의 수 t
		stk = new StringTokenizer(br.readLine(), " ");
		for(int i=1; i<=n; i++) {
			items[i] = Integer.parseInt(stk.nextToken());
		}
		
		int[][] graph = new int[n+1][n+1]; // 지역 연결 그래프
		for(int i=0; i<=n; i++) { // INF로 초기화
			for(int j=0; j<=n; j++) {
				if(i==j) graph[i][j] = 0;
				else graph[i][j] = 9999;
			}
		}
		
		for(int i=0; i<r; i++) {
			stk = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(stk.nextToken());
			int b = Integer.parseInt(stk.nextToken());
			int weight = Integer.parseInt(stk.nextToken());
			// 양방향 그래프
			graph[a][b] = weight;
			graph[b][a] = weight;
		
		}
		
		// 플로이드 워셜 알고리즘
		for(int k=1; k<=n; k++) {
			for(int i=1; i<=n; i++) {
				for(int j=1; j<=n; j++) {
					if(i==k || j==k) continue;
					// 정점 1 ~ k를 경유지로 고려할 때, 정점 i와 정점 j 사이의 최단 경로
					graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
				}
			}
		}
		
		// 최대 아이템 개수
		int maxItems = 0;
		for(int i=1; i<=n; i++) {
			int cntItems = 0;
			for(int j=1; j<=n; j++) {
				// 수색 범위 내의 지역만 방문
				if(graph[i][j] <= m)
					cntItems += items[j];
			}
			maxItems = (maxItems < cntItems) ? cntItems : maxItems;
		}
		
		// 결과 출력
		System.out.println(maxItems);
	}
}