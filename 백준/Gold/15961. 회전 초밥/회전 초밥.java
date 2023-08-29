import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// [BOJ15961] 회전 초밥
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(stk.nextToken()); // 접시 개수 N
		int d = Integer.parseInt(stk.nextToken()); // 초밥 가짓수 d
		int k = Integer.parseInt(stk.nextToken()); // 연속해서 먹는 접시의 수 k
		int c = Integer.parseInt(stk.nextToken()); // 쿠폰 번호 c
	
		int[] sushiList = new int[N]; // 회전 방향을 따라갈 때 N개의 초밥 종류
		int[] sushiCnt = new int[d]; // 현재 k개의 접시에 있는 초밥 각각의 개수
		for(int i=0; i<N; i++) { // N개의 초밥 입력, O(N)
			sushiList[i] = Integer.parseInt(br.readLine());
		}
	
		int idx;
		int cnt = 0;
		// 첫 번째 접시부터 k개의 접시에 놓인 각 초밥의 개수 카운트
		for(int i=0; i<k; i++) {
			idx = sushiList[i]-1;
			if(sushiCnt[idx]==0) cnt++; // 현재 접시에 놓인 초밥의 종류 +1
			sushiCnt[idx]++; // 해당 종류의 초밥의 개수 +1
		}
		
		int res = cnt;
		// 각 경우의 수에 대해 접시에 놓인 초밥의 종류 개수 계산, O(N)
		for(int i=k; i<k+N; i++) {
			// 선택이 취소된 접시에 대해 개수 조정
			idx = sushiList[i-k]-1;
			--sushiCnt[idx];
			if(sushiCnt[idx]==0) // 해당 종류의 초밥이 접시에서 없어졌다면
				cnt--; // 초밥의 종류 개수 -1
			
			// 새롭게 선택된 현재 접시에 대해 개수 조정
			idx = sushiList[i%N] - 1;
			if(sushiCnt[idx]==0) // 해당 종류의 초밥 종류가 새롭게 접시에 등장했다면
				cnt++; // 초밥의 종류 개수 +1
			sushiCnt[idx]++;
			
			// 쿠폰에 적힌 번호의 초밥이 있는지 확인하고 최대 가짓수 업데이트
			if(sushiCnt[c-1]==0) // 초밥 종류가 1개 더 늘어난다
				res = (res<cnt+1) ? cnt+1 : res;
			else
				res = (res<cnt) ? cnt : res;

			// 만약 res = k+1면 최대 개수만큼 먹을 수 있으므로 break;
			if(res==k+1) break;
		}
		
		System.out.println(res);
	}
}