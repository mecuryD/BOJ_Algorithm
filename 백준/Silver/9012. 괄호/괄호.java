import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

	static int T;
	static String[] lines;
	static Stack<Character> stack;
	
	public static void main(String[] args) throws IOException {
		// 1. 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine()); // 테스트 케이스
		lines = new String[T]; // 전체 괄호 문자열 입력
		for(int i=0; i<T; i++) {
			lines[i] = br.readLine();
		}
		
		// 2. 각 테스트 케이스에 대해 VPS 판별
		for(int i=0; i<T; i++) { 
			String res = "YES";
			int len = lines[i].length();
			stack = new Stack<Character>();
			
			// 괄호 짝이 맞는지 확인
			for(int j=0; j<len; j++) {
				char cur = lines[i].charAt(j);
				if(cur=='(') {
					stack.push(cur);
				}else if(stack.isEmpty()) { // 2-1. 여는 괄호가 부족하거나, 닫는 괄호가 많은 경우
					res = "NO";
					break;
				}else {
					stack.pop();
				}
			}
			
			if(!stack.isEmpty()) { // 2-2. 닫는 괄호가 부족한 경우
				res = "NO";
			}
			System.out.println(res);
		}
	}
}