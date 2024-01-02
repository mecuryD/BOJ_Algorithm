import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// [백준17135] 캐슬디펜스
public class Main {

	static int N; // 격자판 행의 수
	static int M; // 격자판 열의 수
	static int D; // 궁수의 공격 거리 제한
	static int[][] original; // N x M 격자판
	static int[][][] distance; // 궁수-격자판 거리맵
	
	static int enemyAll = 0; // 적의 수
	static int result = 0; // 궁수의 공격으로 제거할 수 있는 적의 최대 수
	public static void main(String[] args) throws IOException{
		// 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());
		D = Integer.parseInt(stk.nextToken());
		
		original = new int[N][M];
		for(int i=0; i<N; i++) { // 격자판 입력
			stk = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<M; j++) {
				original[i][j] = Integer.parseInt(stk.nextToken());
				enemyAll +=  original[i][j]; // 적이 총 몇 명 있는지 기록한다
			}
		}
		
		distance = canAttack(); // 궁수를 놓을 수 있는 각 위치와 격자판 사이 거리를 계산한다
		
		// 궁수 3명의 위치를 고른다
		for(int i=0; i<M; i++) {
			for(int j=i+1; j<M; j++) {
				for(int k=j+1; k<M; k++) {
					// 고른 위치에 대해 캐슬 디펜스 게임 시작
					int score = playGame(new int[] {i, j, k});
					
					// 제거할 수 있는 적의 최대 수 업데이트
					result = (result < score) ? score : result;
				}
			}
		}
		
		// 결과 출력
		System.out.print(result);
	}
	
	// 캐슬 디펜스 게임
	public static int playGame(int[] archors) {
		int score = 0;
		int enemy = enemyAll;
		
		int[][] map = copy(); // 원본 맵을 복사
		int[][] attack = new int[3][2]; // 공격할 적의 위치
		
		while(enemy > 0) { // 모든 적이 격자판에서 제외될 때까지 게임을 진행한다
			// 3명의 궁수가 공격할 적을 찾는다
			for(int i=0; i<3; i++) { 
				attack[i] = attackEnemy(archors[i], map);
			}
		
			// 궁수들이 동시에 공격한다
			for(int i=0; i<3; i++) {
				// 공격받은 적은 게임에서 제외된다
				if(attack[i] != null && map[attack[i][0]][attack[i][1]] > 0) {
					map[attack[i][0]][attack[i][1]] = 0; 
					score++;
					enemy--;
				}
			}
			// 적 이동
			enemy = moveEnemy(enemy, map);
		}
		return score;
	}
	
	// 궁수 공격 메서드
	public static int[] attackEnemy(int archor, int[][] map) {
		int[] pos = null;
		int minDis = Integer.MAX_VALUE;
		
		for(int i=N-1; i>=0; i--) {   
			for(int j=0; j<M; j++) {  
				// 가장 가까운 적 중에서 가장 왼쪽에 있는 적을 찾는다
				if(distance[archor][i][j] <= D && map[i][j] > 0) { // 사정 거리 내에 있고 가깝다면
					if(distance[archor][i][j] == minDis && j < pos[1]) { // 같은 거리 내의 적이 여러 명이면 
						pos = new int[] {i, j}; // 왼쪽에 가까운 적을 표적으로 바꾼다
						
					}
					
					if(distance[archor][i][j] < minDis) {
						pos = new int[] {i, j};
						minDis = distance[archor][i][j];
					}
				}
			}
		}
		
		return pos;
	}
	
	// 적 이동 메서드
	public static int moveEnemy(int enemy, int[][] map) {
		// 성이 있는 칸으로 이동하는 적을 격자판에서 제외시칸다
		for(int col=0; col<M; col++) {
			enemy -= map[N-1][col];
		}
		// 나머지 적을 이동시킨다
		for(int row=N-1; row>0; row--) {
			for(int col=0; col<M; col++) {
				map[row][col] = map[row-1][col];
			}
		}
		map[0] = new int[M];
		return enemy;
	}
	
	// 궁수-격자판 거리 계산 메서드
	public static int[][][] canAttack(){
		int[][][] disMap = new int[M][N][M];
		for(int i=0; i<M; i++) { 
			// i번째 궁수와 격자판 각 위치의 거리를 표시
			for(int j=0; j<N; j++) {
				for(int k=0; k<M; k++) {
					disMap[i][j][k] = Math.abs(N-j) + Math.abs(i-k); 
				}
			}
		}
		
		return disMap;
	}
	
	// 맵 복사 메서드
	public static int[][] copy(){
		int[][] arr = new int[N][M];
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				arr[i][j] = original[i][j];
			}
		}
		return arr;
	}
}
