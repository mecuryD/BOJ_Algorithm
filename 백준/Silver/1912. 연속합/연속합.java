import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// [백준] 연속합
public class Main {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[] arr = new int[N];
		StringTokenizer stk = new StringTokenizer(br.readLine());
		arr[0] = Integer.parseInt(stk.nextToken());
		for(int i=1; i<N; i++) {
			int val = Integer.parseInt(stk.nextToken());
			arr[i] = Math.max(val, arr[i-1] + val);
		}
		
		// 최대값을 찾아 결과 출력
		int max = Integer.MIN_VALUE;
		for(int i=0; i<N; i++) {
			max = Math.max(max, arr[i]);
		}

		System.out.print(max);
	}
}