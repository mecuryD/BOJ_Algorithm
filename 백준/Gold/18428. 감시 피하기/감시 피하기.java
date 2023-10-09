import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

// [BOJ18428 감시피하기]
public class Main {
	
	static int N;
	static ArrayList<Pos> list = new ArrayList<Pos>();
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	public static void main(String[] args) throws IOException {
		// 입력 초기화
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); // 복도 크기 N
		
		char[][] map = new char[N][N]; // NxN 복도
		for(int i=0; i<N; i++) {
			char[] line = br.readLine().toCharArray();
			for(int j=0; j<N; j++) {
				map[i][j] = line[j*2];
				if(map[i][j]=='X') list.add(new Pos(i,j,-1));
			}
		}
		
		// 빈 칸 중에서 장애물을 놓을 3개의 위치를 고른다
		boolean flag = false;
		int cnt = list.size();
		loop : for(int i=0; i<cnt; i++) {
			for(int j=i+1; j<cnt; j++) {
				for(int k=j+1; k<cnt; k++) {
					// 맵 복사
					char[][] copyMap = new char[N][N];
					for(int l=0; l<N; l++) {
						for(int m=0; m<N; m++) {
							copyMap[l][m] = map[l][m];
						}
					}
					// 고른 3개의 위치를 맵에 표시한다
					copyMap[list.get(i).r][list.get(i).c] = 'O';
					copyMap[list.get(j).r][list.get(j).c] = 'O';
					copyMap[list.get(k).r][list.get(k).c] = 'O';
					// 모든 학생이 감시로부터 피할 수 있는지 확인한다
					flag = canHide(copyMap);
					if(flag) break loop; 
				}
			}
		}
		
		// 결과 출력
		if(flag) System.out.println("YES");
		else System.out.println("NO");
	}
	
	public static boolean canHide(char[][] map) {
		Queue<Pos> q = new ArrayDeque<Pos>();
		
		// 선생님의 위치를 찾는다
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j]=='T') {
					for(int k=0; k<4; k++) {
						int nr = i + dr[k];
						int nc = j + dc[k];
						while(true) {
							// 해당 방향으로 배열 끝까지 탐색했으면 break
							if(nr<0 || nr>=N || nc<0 || nc>=N) break;
							// 장애물을 만나면 해당 방향으로는 더 이상 탐색 불가하므로 break
							if(map[nr][nc]=='O') break;
							// 학생을 만났으면 감시를 피할 수 없으므로 return
							if(map[nr][nc]=='S') return false;
							// 선생을 만났으면 해당 방향으로는 더 이상 탐색할 필요가 없으므로 break
							if(map[nr][nc]=='T') break;
							// 다음 좌표 탐색
							nr += dr[k];
							nc += dc[k];
						}
					}
				}
			}
		}
		
		return true;
	}
    
	static class Pos {
		int r; // 행
		int c; // 열
		int d; // 감시 방향
		
		public Pos(int r, int c, int d) {
			super();
			this.r = r;
			this.c = c;
			this.d = d;
		}
	}
}
