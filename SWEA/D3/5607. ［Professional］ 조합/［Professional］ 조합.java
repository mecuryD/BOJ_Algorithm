import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// [SWEA5607] 조합
public class Solution {

	static int K = 1234567891;
	static long[] fact;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
     		
		// 1. factorial 계산
		fact = new long[1000001];
		fact[0] = 1L;
        for(int i=1; i<=1000000; i++)
				fact[i] = fact[i-1] * i % K;
        
		for(int t=1; t<=T; t++) {
			StringTokenizer stk = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(stk.nextToken());
			int R = Integer.parseInt(stk.nextToken());

			// 2. (N!) x ((R!)(N-R)!)^(K-2) 계산
			long A = fact[N];
			long B = (fact[R] * fact[N-R]) % K;
			long C = pow(B, K-2);
			long res = A * C % K;
			
			System.out.println(String.format("#%d %d", t, res));
		}
	}
	
	// 분할 정복 활용하여 거듭 제곱을 계산한다
	public static long pow(long n, long p) {
		if(p==1)
			return n;
		
		long x = pow(n, p/2) % K;
		
		if(p % 2 == 0) {
			return x * x % K;
		}else {
			return ((x * x) % K * n) % K;
		}
	}
}