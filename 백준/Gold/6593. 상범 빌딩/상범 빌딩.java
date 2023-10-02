import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

// [BOJ6593] 상범 빌딩
public class Main {
	
	static int[] dl = {0,0,0,0,1,-1};
	static int[] dr = {1,-1,0,0,0,0};
	static int[] dc = {0,0,1,-1,0,0};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while(true) {
			StringTokenizer stk = new StringTokenizer(br.readLine(), " ");
			int L = Integer.parseInt(stk.nextToken()); // 상범 빌딩 층수
			int R = Integer.parseInt(stk.nextToken()); // 한 층의 행 개수
			int C = Integer.parseInt(stk.nextToken()); // 한 층의 열 개수
			if((L+R+C)==0) break; // 입력의 끝
	
			Pos start = null; // 시작 위치
			char[][][] map = new char[L][R][C]; // 상범 빌딩
			for(int i=0; i<L; i++) {
				for(int j=0; j<R; j++) {
					String row = br.readLine();
					for(int k=0; k<C; k++) {
						map[i][j][k] = row.charAt(k);
						if(map[i][j][k]=='S')
							start = new Pos(i,j,k);
					}
				}
				br.readLine(); // 각 층 사이에는 빈 줄이 존재한다
			}
			
			// BFS 탐색
			Queue<Pos> q = new ArrayDeque<Pos>();
			boolean[][][] visited = new boolean[L][R][C];
			
			q.offer(start);
			visited[start.l][start.r][start.c] = true; 
			
			int time = 0;
			int qSize = 0;
			boolean flag = false;
			
			while(!q.isEmpty()) {
				time++;
				qSize = q.size();
				for(int t=0; t<qSize; t++) {
					Pos cur = q.poll();
					
					int nl, nr, nc;
					for(int i=0; i<6; i++) {
						nl = cur.l + dl[i];
						nr = cur.r + dr[i];
						nc = cur.c + dc[i];
						if(nl<0 || nr<0 || nc<0 || nl>=L || nr>=R || nc>=C) continue; // 배열 범위 초과
						if(map[nl][nr][nc]=='#') continue; // 금으로 된 벽
						if(visited[nl][nr][nc]) continue; // 이미 방문한 지점
						
						// 탈출구를 찾으면 리턴
						if(map[nl][nr][nc]=='E') {
							flag = true;
							break;
						}
						// 큐에 삽입
						q.offer(new Pos(nl,nr,nc));
						visited[nl][nr][nc] = true;
					}
					
					if(flag) break;
				}
				if(flag) break;
			}
			
			if(flag) {
				System.out.println(String.format("Escaped in %d minute(s).", time));
			}else {
				System.out.println("Trapped!");				
			}
			
		}
	}
	
	static class Pos{
		int l;
		int r;
		int c;
		
		public Pos(int l, int r, int c) {
			super();
			this.l = l;
			this.r = r;
			this.c = c;
		}

		@Override
		public String toString() {
			return "Pos [l=" + l + ", r=" + r + ", c=" + c + "]";
		}
	}
}