import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int L,C;
	static char[] arr, res; 
	static boolean[] isVowel;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		// 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine(), " ");
		L = Integer.parseInt(stk.nextToken()); // 암호의 길이
		C = Integer.parseInt(stk.nextToken()); // 문자의 개수
		
		arr = new char[C]; // 암호에 들어갈 수 있는 문자 관리
		isVowel = new boolean[C]; // 모음 여부 관리

		String line = br.readLine();
		for(int i=0; i<C; i++) arr[i] = line.charAt(i*2);
		
		Arrays.sort(arr); // 사전식 정렬 (Character 클래스에서 이미 Comparable을 구현)
		for(int i=0; i<C; i++) { // 모음 여부 저장
			if(arr[i]=='a' || arr[i]=='e'|| arr[i]=='i' || arr[i]=='o' || arr[i]=='u') {
				isVowel[i]=true;
			}
		}

		res = new char[L]; // 결과를 담을 String 배열
		comb(0, 0, 0); // 모든 조합을 구해준다
		
		// 결과 출력
		System.out.print(sb.toString());
	}
	
	public static void comb(int len, int cnt, int start) { // len : 현재 선택된 문자의 개수, cnt : 현재 선택된 모음의 개수
		// 기저 구문
		if(len==L) { // 길이 L의 암호가 완성되면
			if(cnt>0 && cnt<=L-2) { // 모음 최소 1개, 자음 최소 2개인 경우에만				
				for(int i=0; i<L; i++) { // 결과에 담는다
					sb.append(res[i]);
				}
				sb.append("\n");
			}
			return;
		}
		
		// 실행 구문
		for(int i=start; i<C; i++) {
			res[len] = arr[i];
			if(isVowel[i]) {
				comb(len+1, cnt+1, i+1);
			}else {				
				comb(len+1, cnt,  i+1);
			}
		}
	}
}