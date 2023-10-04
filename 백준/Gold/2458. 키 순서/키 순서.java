import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{
	
	static final int MAX = 999;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(stk.nextToken()); // 학생들의 수 N
		int M = Integer.parseInt(stk.nextToken()); // 두 학생 키를 비교한 횟수 M
		
		int[][] graph = new int[N+1][N+1];
		for(int i=0; i<=N; i++) {
			for(int j=0; j<=N; j++) {
				graph[i][j] = MAX; // INF 초기화
			}
		}
		
		for(int i=0; i<=N; i++) {
			graph[i][i] = 0; // 자기 자신 초기화
		}
		
		for(int i=0; i<M; i++) {
			stk = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(stk.nextToken());
			int b = Integer.parseInt(stk.nextToken());
			graph[a][b] = 1; // 단방향 그래프
		}
		
		// 플로이드-워셜 알고리즘
		for(int k=1; k<=N; k++) {
			for(int i=1; i<=N; i++) {
				for(int j=1; j<=N; j++) {
					if(i==k) continue;
					if(j==k) continue;
					graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
				}
			}
		}
        
		// 모든 학생과 연결될 수 있는 학생만 자신의 키가 몇 번째인지 알 수 있다
		int cntStd = 0;
		for(int i=1; i<=N; i++) {
			int cnt = 0;
			for(int j=1; j<=N; j++) {
				if(i==j) continue;
				if(graph[i][j]<MAX) cnt++;
				if(graph[j][i]<MAX) cnt++;
			}
			
			if(cnt==N-1) cntStd++; // 모든 학생과 연결된다
		}
		
		// 결과 출력
		System.out.println(cntStd);
	}
}