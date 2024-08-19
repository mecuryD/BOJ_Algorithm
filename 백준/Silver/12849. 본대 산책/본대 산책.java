import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// [백준] 본대 산책
public class Main {
	
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int D = Integer.parseInt(br.readLine());
		
		// 테이블 정의하기
		long[] table = new long[] {1,0,0,0,0,0,0,0};
		int MOD = 1000000007;
		
		// 다이나믹 프로그래밍
		for(int t=1; t<=D; t++) {
			long[] tmp = new long[8];
			tmp[0] = (table[1] + table[2]) % MOD;
			tmp[1] = (table[0] + table[2] + table[3]) % MOD;
			tmp[2] = (table[0] + table[1] + table[3] + table[4]) % MOD;
			tmp[3] = (table[1] + table[2] + table[4] + table[5]) % MOD;
			tmp[4] = (table[2] + table[3] + table[5] + table[6]) % MOD;
			tmp[5] = (table[3] + table[4] + table[7]) % MOD;
			tmp[6] = (table[4] + table[7]) % MOD;
			tmp[7] = (table[5] + table[6]) % MOD;
			
			table = tmp;
		}
		// 출력
		System.out.print(table[0]);
	}
}