import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N;
	static int minDiff = Integer.MAX_VALUE;
	static int[] S, B;
	static boolean[] isSelected;
	public static void main(String[] args) throws IOException {
		StringTokenizer stk;
		
		// 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); // 재료의 개수 입력
		S = new int[N]; // 신맛 저장
		B = new int[N]; // 쓴맛 저장

		for(int i=0; i<N; i++) { // 각 재료에 대한 신맛, 쓴맛 입력
			stk = new StringTokenizer(br.readLine(), " ");
			S[i] = Integer.parseInt(stk.nextToken());
			B[i] = Integer.parseInt(stk.nextToken());
		}
		
		// 각 부분집합에 대해 신맛, 쓴맛 계산하면서 차이가 가장 적은 요리를 가려낸다
		isSelected = new boolean[N];
		subset(0,0);
		System.out.println(minDiff);
		
		
	}
	
	public static void subset(int cnt, int sel) {
		if(cnt==N) { // 부분집합이 완성된 경우
			// 공집합인 경우는 고려하지 않는다
			if(sel==0) {
				return;
			}
			// 해당 요리의 신맛과 쓴맛 계산
			int sumS=1; // 신맛은 곱으로 계산
			int sumB=0; // 쓴맛은 합으로 계산
			for(int i=0; i<N; i++) {
				if(isSelected[i]) { // 해당 재료가 들어갔을 때만 반영
					sumS *= S[i];
					sumB += B[i];
				}
			}
			// 지금까지의 부분집합 중에서 신맛과 쓴맛의 차이가 가장 작은 경우, minDiff 업데이트
			int diff = (sumS > sumB)? sumS-sumB : sumB-sumS;
			if(diff<minDiff) {
				minDiff = diff;
			}
			return;
		}
		
		// 해당 원소의 선택 여부에 따라 부분집합을 생성한다
		isSelected[cnt] = true; // 선택되는 경우
		subset(cnt+1, sel+1); 
		isSelected[cnt] = false; // 선택되지 않는 경우
		subset(cnt+1, sel);
	}
}