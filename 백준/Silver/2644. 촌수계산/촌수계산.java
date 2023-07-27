import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static int n;
	static int p1, p2;
	static int m;
	static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
	
	static int cnt;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk;
		
		// input 
		n = Integer.parseInt(bf.readLine());
		stk = new StringTokenizer(bf.readLine()," ");
		p1 = Integer.parseInt(stk.nextToken());
		p2 = Integer.parseInt(stk.nextToken());
		m = Integer.parseInt(bf.readLine());
		
		for(int i=0; i<=n; i++) {
			graph.add(new ArrayList<Integer>());
		}
		
		for(int i=0, r1=0, r2=0; i<m; i++) { // 관계도 저장
			stk = new StringTokenizer(bf.readLine()," ");
			r1 = Integer.parseInt(stk.nextToken());
			r2 = Integer.parseInt(stk.nextToken());
			
			graph.get(r1).add(r2);
			graph.get(r2).add(r1);
		}
		
		
		// DFS
		visited = new boolean[n+1];
		dfs(p1);
		if(cnt > 0) {
			System.out.println(cnt);
		}else {
			System.out.println("-1");
		}
			
	}
	
	public static boolean dfs(int x) {
		// 방문 처리
		visited[x] = true;
		
		// 연결된 다른 노드를 재귀적으로 처리
		for(int i=0; i<graph.get(x).size(); i++) {
			int y = graph.get(x).get(i);
			
			// 촌수를 구하려는 사람이면 리턴
			if(y==p2) {
				cnt++;
				return true;
			}
			
			// 촌수를 구하려는 사람은 아니나, 연결된 노드가 있으면 탐색
			
			if(!visited[y]) {
				if(dfs((y))) {
					cnt++;
					return true;
				}
			}
		}
		// 연결된 노드 중에 촌수를 구하려는 사람이 없는 경우
		return false;
	}
}
