import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// [BOJ1956] 운동
public class Main {

	static final int MAX = 9999999;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine(), " ");
		int V = Integer.parseInt(stk.nextToken()); // 정점 개수
		int E = Integer.parseInt(stk.nextToken()); // 간선 개수
		
		int[][] graph = new int[V+1][V+1]; // 그래프
		
		for(int i=0; i<=V; i++) { // INF로 초기화
			for(int j=0; j<=V; j++) {
				graph[i][j] = MAX;
			}
		}
		
		for(int i=0; i<E; i++) {
			stk = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(stk.nextToken()); // a번 마을
			int b = Integer.parseInt(stk.nextToken()); // b번 마을
			int dis = Integer.parseInt(stk.nextToken()); // a → b 도로의 길이
			graph[a][b] = dis; // 단방향 그래프
		}
		
		
		// 플로이드 알고리즘으로 두 지점간의 최소 경로 계산
		for(int k=1; k<=V; k++) { // 경유지 
			for(int i=1; i<=V; i++) { // 출발지
				for(int j=0; j<=V; j++) { // 도착지
					if(i==k) continue;
					if(j==k) continue;
					graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
				}
			}
		}
		
		// 최소 사이클의 도로 길이를 구한다
		int curDis = 0;
		int minDis = MAX + MAX;
		for(int i=1; i<=V; i++) {
			for(int j=i; j<=V; j++) {
				if(i==j) continue;
				curDis = graph[i][j] + graph[j][i];
				if(curDis < MAX) { // 사이클이 형성될 수 있으면
					minDis = (curDis<minDis) ? curDis : minDis;
				}
			}
		}
		
		// 결과 출력
		if(minDis < MAX) { // 운동 경로를 찾으면	
			System.out.println(minDis);
		}else { // 운동 경로를 찾지 못하면
			System.out.println(-1);
		}
	}
}