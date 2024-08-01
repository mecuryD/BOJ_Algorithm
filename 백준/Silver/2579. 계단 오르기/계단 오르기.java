import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// [백준] 계단 오르기
public class Main {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[] S = new int[N+1];
		for(int i=1; i<=N; i++) {
			S[i] = Integer.parseInt(br.readLine());
		}
		
		// 테이블 정의
		int[] table = new int[N+1];
		table[1] = S[1];
		if(N>1) table[2] = S[1] + S[2];
		if(N>2) table[3] = Math.max(S[1], S[2]) + S[3];
		
		// 다이나믹 프로그래밍
		for(int i=4; i<=N; i++) {
			table[i] = Math.max(S[i-1] + table[i-3], table[i-2]) + S[i];
		}
		
		// 결과 출력
		System.out.print(table[N]);
	}
}