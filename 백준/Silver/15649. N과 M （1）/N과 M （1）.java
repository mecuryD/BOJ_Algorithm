import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// [방법2] StringBuilder를 사용하는 방법
public class Main {

	static int n,m;				// 사용자 입력
	static int idx;				// arr의 현재 인덱스
	static int[] arr;			// 만들어진 수열을 담는 배열
	static boolean[] isUsed;	// 숫자가 사용되었는지 체크하는 배열
	static StringBuilder sb = new StringBuilder(); // 수열을 담는 배열
	
	public static void main(String[] args) throws IOException {
		// 사용자 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(stk.nextToken());
		m = Integer.parseInt(stk.nextToken());
		
		arr = new int[m];
		isUsed = new boolean[n];
		
		// 수열 생성
		perm(m,0);
		System.out.println(sb.toString());
	}
	
	public static void perm(int cnt, int idx) { // 매 번 변경되는 값 관리
		// 종료 구문
		if(cnt==0) {
			for(int i=0; i<m; i++) {
				sb.append(arr[i] + " ");
			}
			sb.append("\n");
			return;
		}
		
		// 실행 구문
		for(int i=0; i<n; i++) {
			// 이미 사용된 번호라면 패스
			if(isUsed[i]) { 
				continue;
			}
			
			// 재귀 호출
			arr[idx] = i+1;
			isUsed[i] = true;
			perm(cnt-1, idx+1);
			isUsed[i] = false;
		}
	}
}