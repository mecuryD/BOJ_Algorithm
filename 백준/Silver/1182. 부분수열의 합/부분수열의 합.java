import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, S;
	static int res = 0; // 합이 S가 되는 부분 수열의 개수
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(stk.nextToken()); // 정수의 개수 N
		S = Integer.parseInt(stk.nextToken()); // 부분 수열의 합 S
		int[]arr = new int[N]; // 정수 관리
		boolean[] isSelected = new boolean[N]; // 정수의 선택 여부 관리
		stk = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<N; i++) { // N개의 정수 입력
			arr[i] = Integer.parseInt(stk.nextToken());
		}
		// 부분 수열의 개수를 구한다
		subset(arr, isSelected, 0, 0, 0);
		System.out.println(res);
	}

	public static void subset(int[] arr, boolean[] isSelected, int cnt, int cntSel,int sum) {
		if(cnt==N) {
			if(sum==S && cntSel!=0) {
				res++; 
			}
			return;
		}
		isSelected[cnt] = true;
		subset(arr, isSelected, cnt+1, cntSel+1, sum+arr[cnt]);
		isSelected[cnt] = false;
		subset(arr, isSelected, cnt+1, cntSel, sum);
	}
}