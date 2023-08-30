import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// [BOJ10971] 외판원 순회 2
public class Main {

	static int N;
	static int minCost = Integer.MAX_VALUE;
	static int[][] adjMatrix;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); // 도시의 수 N
		adjMatrix = new int[N][N]; // 인접 가중치 행렬
		for(int i=0; i<N; i++) {
			StringTokenizer stk = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<N; j++) {
				adjMatrix[i][j] = Integer.parseInt(stk.nextToken());
			}
		}
		
		// 방법 1. 순열
		perm(0, new boolean[N], new int[N]);
		System.out.println(minCost);
	}
	
	// 순열 생성 
	public static void perm(int cnt, boolean[] visited, int[] selected) {
		// 기저 조건
		if(cnt==N) { // 도시 N개의 순서가 결정되었다면
			if(adjMatrix[selected[N-1]][selected[0]]>0) { // 순회 여행이 가능한 경우
				minCost = Math.min(getCost(selected), minCost); // 최소 비용 업데이트
			}
			return;
		}
		
		// 실행 구문
		for(int i=0; i<N; i++) {
			if(visited[i]) continue; // 이미 여행한 지역이라면 continue
			if(cnt>0 && adjMatrix[selected[cnt-1]][i]==0) continue; // 순회할 수 없다면 continue
			selected[cnt] = i;	
			visited[i] = true;
			perm(cnt+1, visited, selected);
			visited[i] = false;
		}
	}
	
	// 여행 경비 계산
	public static int getCost(int[] selected) {
		int from, to, cost=0;
		for(int i=1; i<N; i++) {
			from = selected[i-1];
			to = selected[i];
			cost += adjMatrix[from][to];
		}
		cost += adjMatrix[selected[N-1]][selected[0]];
		return cost;
	}
}