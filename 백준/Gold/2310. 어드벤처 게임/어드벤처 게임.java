import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// [백준2310] 어드벤처 게임
public class Main {

	static int N;
	static char[] types;
	static int[] coins;
	static boolean[] visited;
	static ArrayList<Integer>[] graph;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while(true) {			
			N = Integer.parseInt(br.readLine());
			
			// 미로의 방 수가 0개이면 입력 종료
			if(N==0) break;
			
			// 어드벤처 게임 진행
			types = new char[N+1];
			coins = new int[N+1];
			visited = new boolean[N+1];
			graph = new ArrayList[N+1];
			
			for(int i=1; i<=N; i++) {
				// 그래프 초기화
				graph[i] = new ArrayList<Integer>();
				// 방 정보 입력
				StringTokenizer stk = new StringTokenizer(br.readLine(), " ");
				types[i] = stk.nextToken().charAt(0);
				coins[i] = Integer.parseInt(stk.nextToken());
				
				// 연결된 방 정보 입력
				while(stk.hasMoreTokens()) {
					int room = Integer.parseInt(stk.nextToken());
					if(room > 0) graph[i].add(room);
				}
			}
			
			// 게임 시작
			String result = (playGame(0,1) > 0) ? "Yes" : "No";
			
			// 결과 저장
			sb.append(result+"\n");
		}
		// 최종 결과 출력
		System.out.println(sb);
	}
	
	// DFS 알고리즘으로 미로에서 1번 방에서 N번 방까지 갈 수 있는지 체크한다 
	public static int playGame(int left, int room) { // left : 남은 코인, room : 방의 숫자
		// 현재 방에 들어올 수 있는지 조건을 살펴본다
		if(types[room]=='L') { // 레프리콘이 있는 방
			if(left<coins[room]) left = coins[room];
		}else if(types[room]=='T') { // 트롤이 있는 방
			if(left<coins[room]) {
				return -1;
			}
			left -= coins[room];
		}
		
		// 현재 방이 N번 방이라면 프로그램 종료
		if(room==N) return 1;
		
		// 현재 방과 연결된 방들을 살펴본다
		for(int idx : graph[room]) {
			if(visited[idx]) continue;
			// 아직 방문하지 않은 방이라면 방문하러 간다
			visited[idx] = true;
			int result = playGame(left, idx);
			visited[idx] = false;
			// 만약 N번방까지 도달했다면 프로그램 종료
			if(result==1) return 1;
		}
		
		// 가능한 모든 방을 방문했지만 도달하지 못했으므로 -1 반환
		return -1;
	}
}
