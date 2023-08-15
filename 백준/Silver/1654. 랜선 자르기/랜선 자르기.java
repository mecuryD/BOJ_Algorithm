import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class Main {
	static int K, N; // 이미 가지고 있는 랜선의 개수 K, 필요한 랜선의 개수 N
	static int maxLen; // 가장 긴 랜선의 길이
	static int res; // N개를 만들 수 있는 랜선의 최대 길이 
	static int[] arr; // 전체 랜선을 관리하는 배열
	public static void main(String[] args) throws IOException {
		// 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine(), " ");
		K = Integer.parseInt(stk.nextToken()); // 이미 가지고 있는 랜선의 개수 K
		N = Integer.parseInt(stk.nextToken()); // 필요한 랜선의 개수 N
		arr = new int[K]; // K개의 랜선 길이 저장
		for(int i=0; i<K; i++) { // K개의 랜선 길이 입력
			arr[i] = Integer.parseInt(br.readLine());
			if(maxLen<arr[i]) maxLen=arr[i]; // 가장 긴 랜선 길이 저장
		}
		// 이분 탐색
		// 1부터 maxLen까지의 길이로 랜선을 자를 수 있을 때, 최대 랜선의 길이 탐색
		long start = 1;
		long end = maxLen;
		while(start<=end) {
			long mid = (start+end) / 2; // 중간 값 계산
			long cnt = 0; 
			for(int i=0; i<K; i++) { // mid만큼 랜선을 자를 때
				cnt += arr[i]/mid; // 얻을 수 있는 랜선의 개수 저장 변수
			}
			if(cnt >= N) { // 원하는 개수의 랜선을 얻을 수 있으면
				res = (int) mid; // 현재 길이를 저장하고
				start = mid+1; //  더 큰 길이로 랜선을 잘라본다
			}else{ // 원하는 개수보다 적은 랜선을 얻는다면
				end = mid-1; // 더 작은 길이로 랜선을 잘라본다
			}
		}
		// 출력
		System.out.println(res);
	}
}