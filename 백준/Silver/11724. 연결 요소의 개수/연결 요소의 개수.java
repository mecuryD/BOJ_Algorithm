import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	// 루트 배열 생성
	public static void make() {
		parents = new int[N+1];
		for(int i=1; i<=N; i++) {
			parents[i] = i;
		}
	}

	// 루트 노드 탐색
	public static int find(int u) {
		if(parents[u]==u) return u;
		else return parents[u] = find(parents[u]); // 경로 압축
	}
	
	// 동일한 집합으로 합친다
	public static void union(int u, int v) {
		// 각각의 루트노드 탐색
		u = find(u);
		v = find(v); 
		// u가 속한 집합을 v가 속한 집합으로 합쳐준다
		parents[u] = v;
	}
	
	static int N, M;
	static int[] parents;
	public static void main(String[] args) throws IOException {
		// 입력 초기화
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());
		
		make(); // 유니온 파인드 배열 생성
		for(int i=0; i<M; i++) {
			stk = new StringTokenizer(br.readLine(), " ");
			int u = Integer.parseInt(stk.nextToken()); // 간선 정보 입력
			int v = Integer.parseInt(stk.nextToken());
			union(u,v); // 집합을 합쳐준다
		}
		
		// 연결 요소의 개수 카운트
		int root, cnt=0;
		boolean[] visited = new boolean[N+1];
		for(int i=1; i<=N; i++) {
			root = find(i); // i번째 정점의 루트 노드를 찾아서 방문 처리
			visited[root] = true;
		}
		
		for(boolean b : visited) {
			if(b) cnt++;
		}
		
		// 결과 출력
		System.out.println(cnt);
	}
}