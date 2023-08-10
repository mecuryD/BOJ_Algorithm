import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static int N, M, K;
	public static int res = Integer.MAX_VALUE;
	public static int[][] map;
	public static Command[] commands;
	public static boolean[] isSelected;
	
	public static int[] dx = {1,0,-1,0};
	public static int[] dy = {0,1,0,-1};
	
	public static void main(String[] args) throws IOException {
		// 입력
		input();
		
		// 명령어 순서에 대한 순열 생성 (재귀 이용)
		isSelected = new boolean[K];
		permutation(0, map);
		
		// 출력
		System.out.println(res);
	}
	
	public static void input() throws IOException{ // 값 입력 파트
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(stk.nextToken()); // N x M 배열
		M = Integer.parseInt(stk.nextToken()); // N x M 배열
		K = Integer.parseInt(stk.nextToken()); // 명령어의 개수
		
		map = new int[N][M]; 
		for(int i=0; i<N; i++) { // 배열 A 입력 
			stk = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(stk.nextToken());
			}
		}
		
		commands = new Command[K];
		for(int i=0; i<K; i++) { // K개의 명령어 입력
			stk = new StringTokenizer(br.readLine(), " ");
			int r = Integer.parseInt(stk.nextToken())-1; // 기준 행 저장
			int c = Integer.parseInt(stk.nextToken())-1; // 기준 열 저장
			int s = Integer.parseInt(stk.nextToken()); // 회전 범위 크기
			commands[i] = new Command(r,c,s); // 명령어를 Command 객체로 저장
		}
	}
	
	public static void permutation(int cnt, int[][] arr) { // 명령어 순서
		// 기저 구문
		if(cnt==K) { // K개의 명령어가 모두 수행 완료되었다면
			for(int i=0; i<N; i++) { // 배열 A의 값 계산
				int sum = 0;
				for(int j=0; j<M; j++) { // i번째 행의 합 계산
					sum += arr[i][j];
				}
				res = (sum<res) ? sum : res; // 최솟값 갱신
			}
//			
//			for(int i=0; i<N; i++) {
//				for(int j=0; j<M; j++) { 
//					System.out.print(arr[i][j] + " ");
//				}
//				System.out.println();
//			}
			return;
		}
		
		// 실행 구문 (TODO)
		for(int i=0; i<K; i++) {
			if(isSelected[i]) { // 이미 수행된 명령어라면
				continue;
			}
			
			int[][] tmp = new int[N][M]; // map은 바뀌면 안되므로 복사하여 사용
			for(int j=0; j<N; j++) { // 행 단위 복사
				System.arraycopy(arr[j], 0, tmp[j], 0, M);
			}
			
			int r = commands[i].r;
			int c = commands[i].c;
			int s = commands[i].s;
			tmp = rotate(tmp, r-s, c-s, 2*s+1, 2*s+1); // 현재 명령어에 대해 회전 수행
			isSelected[i] = true;
			permutation(cnt+1, tmp);
			isSelected[i] = false;
		}
	}
	
	public static int[][] rotate(int[][] arr, int offX, int offY, int lenX, int lenY) { // 명령어 인덱스
		int loop = Math.min(lenX, lenY) / 2; // 내부에 존재하는 루프의 수
		for(int i=0; i<loop; i++) { // 각 루프에 대해 회전
			int dir = 0; // 회전 방향
			int x = offX+i; // 시작 행
			int y = offY+i; // 시작 열
			int tmp = arr[x][y]; // 시작 위치의 값 저장
			
			int sx = offX+i; // 첫 행
			int sy = offY+i;  // 첫 열
			int ex = offX+lenX-i; // 마지막 행
			int ey = offY+lenY-i; // 마지막 열
			
			while(dir<4) {
				int nx = x + dx[dir]; // 방향 따라 1칸 이동
				int ny = y + dy[dir]; // 방향 따라 1칸 이동
				if(nx<sx || nx>=ex || ny<sy || ny>=ey) { // 범위 초과 시
					dir++;
					continue;
				}
				arr[x][y] = arr[nx][ny];
				x = nx;
				y = ny;
			}
			arr[sx][sy+1] = tmp; // 시작 위치 값을 아래 칸에 마지막으로 넣어 준다
		}
		
		return arr;
	}
}

class Command {
	int r;
	int c;
	int s;
	public Command(int r, int c, int s) {
		this.r = r;
		this.c = c;
		this.s = s;
	}
}