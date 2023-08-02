import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int N, M; // 수의 개수 N, 합을 구해야 하는 횟수 M
	static int res; // 최종 결과를 담는 변수
	static int start, end; // 합을 구해야 하는 구간의 시작과 끝
	static int[] arr, table; // table[0]부터 table[i]까지의 누적합을 담는 배열
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 값 입력
		StringTokenizer stk = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());
		
		arr = new int[N];
		table = new int[N];

		stk = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(stk.nextToken());
			if(i==0) {
				table[i] = arr[i];
			}else {
				table[i] = arr[i] + table[i-1];
			}
		}
		

		// table을 사용하여 구간 합 출력
		for(int i=0; i<M; i++) {
			// 구간 정보 입력
			stk = new StringTokenizer(br.readLine(), " ");
			start = Integer.parseInt(stk.nextToken())-1;
			end = Integer.parseInt(stk.nextToken())-1;
			
			// 누적 합 출력
			res = (start==0)? table[end] : table[end]-table[start-1];
			System.out.println(res);
		}
	}
}