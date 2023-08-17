import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class Main{
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while(true) {
			String line = br.readLine();
			if(line.equals("0")) break; // 입력의 마지막 줄
			
			char[] num = line.toCharArray();
			int len = num.length;
			int half = len/2;
			boolean res = true;
			for(int i=0; i<half; i++) {
				if(num[i]!=num[len-i-1]) { // 팰린드롬이 아닌 경우
					res = false;
					break;
				}
			}
			if(res) sb.append("yes\n");
			else sb.append("no\n");
		}
		System.out.println(sb.toString());
	}
}