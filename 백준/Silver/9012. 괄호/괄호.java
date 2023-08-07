import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

	static int T, cnt;
	
	public static void main(String[] args) throws IOException {
		// 1. 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine()); // 테스트 케이스

		// 2. 각 테스트 케이스에 대해 VPS 판별
		for(int i=0; i<T; i++) { 
			cnt = 0;
			String res = "YES";
			char[] line = br.readLine().toCharArray();
			int len = line.length;
			
			// 괄호 짝이 맞는지 확인
			for(int j=0; j<len; j++) {
				char cur = line[j];
				if(cur=='(') {
					cnt++;
				}else if(cur==')'){
					cnt--;
					if(cnt<0) { // 괄호 짝이 맞지 않다는 것
						break;
					}
				}
			}
			
			if(cnt != 0) { // 2-2. 닫는 괄호가 부족한 경우
				res = "NO";
			}
			System.out.println(res);
		}
	}
}