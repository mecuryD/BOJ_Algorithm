import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{

	static int N, M; // 종이의 세로 크기 N, 가로 크기 M
	static int maxSum = Integer.MIN_VALUE; // 테트로미노가 놓인 칸에 쓰여 있는 수들의 합의 최댓값
	public static void main(String[] args) throws IOException{
		// 입력 초기화
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());
		int[][] map = new int[N][M];
		for(int i=0; i<N; i++) {
			stk = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(stk.nextToken());
			}
		}
		
		// 모든 가능한 경우의 수에 대해 계산해본다
		tetroMinoA(map); // 파란색 테트로미노
		tetroMinoB(map); // 노란색 테트로미노
		tetroMinoC(map); // 주황색 테트로미노
		tetroMinoD(map); // 초록색 테트로미노
		tetroMinoE(map); // 분홍색 테트로미노
		
		System.out.println(maxSum);
	}
	
	// 파란색 테트로미노
	public static void tetroMinoA(int[][] map) {
		// 가로 방향
		int ei = N;
		int ej = M-3;
		for(int i=0; i<ei; i++) {
			for(int j=0; j<ej; j++){
				int sum = map[i][j] + map[i][j+1] + map[i][j+2] + map[i][j+3];
				maxSum = (maxSum < sum) ? sum : maxSum;
			}
		}
		// 세로 방향
		ei = N-3;
		ej = M;
		for(int i=0; i<ei; i++) {
			for(int j =0; j<ej; j++) {
				int sum = map[i][j] + map[i+1][j] + map[i+2][j] + map[i+3][j];
				maxSum = (maxSum < sum) ? sum : maxSum;
			}
		}
	}
	
	// 노란색 테트로미노
	public static void tetroMinoB(int[][] map) {
		int ei = N-1;
		int ej = M-1;
		for(int i=0; i<ei; i++) {
			for(int j=0; j<ej; j++) {
				int sum = map[i][j] + map[i][j+1] + map[i+1][j] + map[i+1][j+1];
				maxSum = (maxSum < sum) ? sum : maxSum;
			}
		}
	}

	// 주황색 테트로미노
	public static void tetroMinoC(int[][] map) {
		int ei = N-1;
		int ej = M-2;
		// 가로 방향
		for(int i=0; i<ei; i++) {
			for(int j=0; j<ej; j++) {
				int sumA = map[i][j] + map[i][j+1] + map[i][j+2] + map[i+1][j];
				int sumB = map[i][j] + map[i][j+1] + map[i][j+2] + map[i+1][j+2];
				int sumC = map[i+1][j] + map[i+1][j+1] + map[i+1][j+2] + map[i][j+2];
				int sumD = map[i+1][j] + map[i+1][j+1] + map[i+1][j+2] + map[i][j];
				
				maxSum = (maxSum < sumA) ? sumA : maxSum;
				maxSum = (maxSum < sumB) ? sumB : maxSum;
				maxSum = (maxSum < sumC) ? sumC : maxSum;
				maxSum = (maxSum < sumD) ? sumD : maxSum;
			}
		}
		// 세로 방향
		ei = N-2;
		ej = M-1;
		for(int i=0; i<ei; i++) {
			for(int j=0; j<ej; j++) {
				int sumA = map[i][j] + map[i+1][j] + map[i+2][j] + map[i+2][j+1];
				int sumB = map[i][j] + map[i+1][j] + map[i+2][j] + map[i][j+1];
				int sumC = map[i][j+1] + map[i+1][j+1] + map[i+2][j+1] + map[i][j];
				int sumD = map[i][j+1] + map[i+1][j+1] + map[i+2][j+1] + map[i+2][j];
				
				maxSum = (maxSum < sumA) ? sumA : maxSum;
				maxSum = (maxSum < sumB) ? sumB : maxSum;
				maxSum = (maxSum < sumC) ? sumC : maxSum;
				maxSum = (maxSum < sumD) ? sumD : maxSum;
			}
		}
	}
	
	// 초록색 테트로미노
	public static void tetroMinoD(int[][] map) {
		int ei = N-2;
		int ej = M-1;
		// 세로 방향
		for(int i=0; i<ei; i++) {
			for(int j=0; j<ej; j++) {
				int sumA = map[i][j] + map[i+1][j] + map[i+1][j+1] + map[i+2][j+1];
				int sumB = map[i][j+1] + map[i+1][j] + map[i+1][j+1] + map[i+2][j];
				maxSum = (maxSum < sumA) ? sumA : maxSum;
				maxSum = (maxSum < sumB) ? sumB : maxSum;
			}
		}
		// 가로 방향
		ei = N-1;
		ej = M-2;
		for(int i=0; i<ei; i++) {
			for(int j=0; j<ej; j++) {
				int sumA = map[i][j+1] + map[i][j+2] + map[i+1][j] + map[i+1][j+1];
				int sumB = map[i][j] + map[i][j+1] + map[i+1][j+1] + map[i+1][j+2];
				maxSum = (maxSum < sumA) ? sumA : maxSum;
				maxSum = (maxSum < sumB) ? sumB : maxSum;
			}
		}
	}

	// 분홍색 테트로미노
	public static void tetroMinoE(int[][] map) {
		int ei = N-1;
		int ej = M-2;
		// 가로 방향
		for(int i=0; i<ei; i++) {
			for(int j=0; j<ej; j++) {
				int sumA = map[i][j] + map[i][j+1] + map[i][j+2] + map[i+1][j+1];
				int sumB = map[i+1][j] + map[i+1][j+1] + map[i+1][j+2] + map[i][j+1];
				maxSum = (maxSum<sumA) ? (sumA<sumB ? sumB:sumA) : (maxSum<sumB ? sumB : maxSum);
			}
		}
		// 세로 방향
		ei = N-2;
		ej = M-1;
		for(int i=0; i<ei; i++) {
			for(int j=0; j<ej; j++) {
				int sumA = map[i+1][j] + map[i][j+1] + map[i+1][j+1] + map[i+2][j+1];
				int sumB = map[i][j] + map[i+1][j] + map[i+2][j] + map[i+1][j+1];
				maxSum = (maxSum<sumA) ? (sumA<sumB ? sumB:sumA) : (maxSum<sumB ? sumB : maxSum);
			}
		}
	}
}
