import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException{
		// 입력 초기화
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(stk.nextToken());
		int M = Integer.parseInt(stk.nextToken());
		
		String[] hm = new String[N+1];
		HashMap<String, Integer> hm2 = new HashMap<String, Integer>();
		
			
		for(int i=1; i<=N; i++) {
			String name = br.readLine();
			hm[i] = name;
			hm2.put(name, i);
		}
		
		// 도감 찾기
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<M; i++) {
			String line = br.readLine();
			char first = line.charAt(0);
			char last = line.charAt(line.length()-1);
			
			// 문자열이면
			if(first>='A' && first<='Z') {
				sb.append(hm2.get(line)+"\n");
			}else if(last>='A' && last<='Z') {
				sb.append(hm2.get(line)+"\n");
			}else {
				sb.append(hm[Integer.valueOf(line)]+"\n");
			}
		}
		System.out.print(sb);
	}
}