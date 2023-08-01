import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int n, m;
	static int gen, num;
	static int[] arr;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 입력
		StringTokenizer stk = new StringTokenizer(br.readLine());
		n = Integer.parseInt(stk.nextToken()); // 스위치 개수
		
		arr = new int[n];
		stk = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<n; i++) { // 각 스위치 상태
			arr[i] = Integer.parseInt(stk.nextToken());
		}
		
		stk = new StringTokenizer(br.readLine(), " ");
		m = Integer.parseInt(stk.nextToken()); // 학생 수
		
		// 스위치 상태 처리
		for(int i=0; i<m; i++) { // 학생 정보 입력과 동시에 스위치 상태 처리
			stk = new StringTokenizer(br.readLine(), " ");
			gen = Integer.parseInt(stk.nextToken()); // 학생 정보 입력
			num = Integer.parseInt(stk.nextToken());
			if(gen==1) { // 남학생이면
				man(num);
			}else { // 여학생이면
				woman(num-1);
			}
		}
		
		// 출력
		StringBuilder sb = new StringBuilder();
		for(int i=1; i<=n; i++) {
			System.out.print(arr[i-1] + " ");
			if(i%20==0) {
				System.out.println("");
			}
		}
		System.out.println(sb.toString());
	}
	
	public static void man(int num) { // 남학생의 경우
		for(int i=0; i<n; i++) {
			if((i+1) % num == 0) { // num의 배수라면
				arr[i] = 1 - arr[i]; // 스위치 상태 반전
			}
		}
	}
	public static void woman(int num) { // 여학생의 경우
		arr[num] = 1 - arr[num]; // 받은 수의 스위치는 무조건 스위치 상태 반전
		
		int idx = 1;
		while(true) {
			if((num-idx)<0 || (num+idx)>=n) { // 양 옆의 스위치가 범위를 초과하면
				break; // 반복 종료
			}
			
			if(arr[num-idx]!=arr[num+idx]) { // 두 개의 스위치가 비대칭이면 반복 종료
				break;
			}else {
				arr[num-idx] = 1 - arr[num-idx]; // 스위치 상태 반전
				arr[num+idx] = 1 - arr[num+idx]; // 스위치 상태 반전
				idx++; // 다음 대칭 범위 탐색
			}
		}
	}
}
