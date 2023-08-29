import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// [BOJ12891] DNA 비밀번호
public class Main {
	static int res = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine(), " ");	
		int S = Integer.parseInt(stk.nextToken()); // DNA 문자열 길이 S
		int P = Integer.parseInt(stk.nextToken()); // 부분 문자열 길이 P
		char[] line = br.readLine().toCharArray(); // DNA 문자열
		
		int[] min = new int[4];
		int[] cur = new int[4];
		stk = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<4; i++) {
			min[i] = Integer.parseInt(stk.nextToken()); // A, C, T, G 최소 개수
		}
		
		// 비밀번호 종류의 수 카운트, 슬라이딩 윈도우
		for(int i=0; i<P; i++) { // 첫 번째 부분 문자열에 존재하는 각 문자의 개수 계산
			if(line[i]=='A') cur[0]++;
			else if(line[i]=='C') cur[1]++;
			else if(line[i]=='G') cur[2]++;
			else if(line[i]=='T') cur[3]++;
		}
		
		if(canUse(min, cur)) res++; // 첫 번재 부분 문자열을 만들 수 있다면 res+1

		// 나머지 부분 문자열을 만들 수 있는지 확인
		int idx;
		int len = S-P;
		for(int i=1; i<=len; i++) {
			// 맨 앞에 있는 문자를 제거
			idx = i-1;
			if(line[idx]=='A') cur[0]--;
			else if(line[idx]=='C') cur[1]--;
			else if(line[idx]=='G') cur[2]--;
			else if(line[idx]=='T') cur[3]--;
			
			// 맨 뒤에 있는 문자를 새롭게 추가
			idx = i+P-1;
			if(line[idx]=='A') cur[0]++;
			else if(line[idx]=='C') cur[1]++;
			else if(line[idx]=='G') cur[2]++;
			else if(line[idx]=='T') cur[3]++;
			
			// 최소 개수 만족 시 결과 +1
			if(canUse(min, cur)) res++;
		}
		
		System.out.println(res);
	}
	
	public static boolean canUse(int[] min, int[] cur) {
		for(int i=0; i<4; i++) { // 각 문자의 개수가 최소 개수 이상인지 확인
			if(cur[i] < min[i]) return false;
		}
		return true;
	}
}