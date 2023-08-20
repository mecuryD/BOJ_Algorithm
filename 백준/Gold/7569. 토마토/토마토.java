import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

// [Boj7569] 토마토
public class Main {
	
	// 토마토가 익는데 걸리는 일수
	static int day = -1;
	// 6방 탐색
	static int[] dz = {0,0,1,-1,0,0}; 
	static int[] dx = {1,-1,0,0,0,0};
	static int[] dy = {0,0,0,0,1,-1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine(), " ");
		int M = Integer.parseInt(stk.nextToken());
		int N = Integer.parseInt(stk.nextToken());
		int H = Integer.parseInt(stk.nextToken());
		
		// 3차원 토마토 상자 입력
		// BFS 알고리즘을 사용하기 위해, 입력을 받으면서 익은 토마토의 좌표를 큐에 넣어준다
		int cnt = 0; // 안 익은 토마토 개수
		int map[][][] = new int[H][N][M];
		Queue<int[]> q = new ArrayDeque<int[]>(); // 큐 생성
		for(int i=0; i<H; i++) { // z축
			for(int j=0; j<N; j++) { // x축
				stk = new StringTokenizer(br.readLine(), " ");
				for(int k=0; k<M; k++) { // y축
					map[i][j][k] = Integer.parseInt(stk.nextToken());
					if(map[i][j][k]==0) { // 안 익은 토마토는 개수를 세어주고
						cnt++;
					}else if(map[i][j][k]==1) { // 익은 토마토는 큐에 넣어준다
						q.offer(new int[] {i,j,k});
					}
				}
			}
		}
		
		if(cnt==0) { // 모든 토마토가 익어있는 상태라면 0을 출력
			System.out.println("0");
		}else { // 그렇지 않다면 BFS 알고리즘을 사용해 최소 며칠이 걸리는지 계산
			// Depth 별로 반복문을 수행하며
			// 더 이상 익을 수 있는 토마토가 없으면 종료한다
			while(!q.isEmpty()) {
				day++; 
				// 모든 토마토를 익히기 위해 필요한 일수를 1만큼 증가
				int len = q.size();
				for(int i=0; i<len; i++) { // Depth 별로 수행
					int[] tmt = q.poll(); // 익은 토마토를 하나 꺼내고
					
					int nx,ny,nz;
					for(int j=0; j<6; j++) { // 6방 탐색
						nz = tmt[0] + dz[j];
						nx = tmt[1] + dx[j];
						ny = tmt[2] + dy[j];
						if(nz<0 || nz>=H || nx<0 || nx>=N || ny<0 || ny>=
								M) continue; // 배열 범위 초과라면 continue
						if(map[nz][nx][ny]!=0) continue; // 안 익은 토마토가 아니라면 continue
						// 안 익은 토마토에 대해서만 아래의 코드가 수행된다
						map[nz][nx][ny]=1; // 방문 처리, 토마토가 하루가 지나 익는다
						q.offer(new int[] {nz,nx,ny}); // 다른 토마토를 익히기 위해 큐에 넣어준다
						cnt--; // 익지 않은 토마토의 개수가 1만큼 감소
					}
				}
			}
	
			if(cnt==0) { // 모든 토마토가 익은 경우
				System.out.println(day);
			}else { // 아직 익지 못한 토마토가 남은 경우
				System.out.println("-1");				
			}
		}	
	}
}