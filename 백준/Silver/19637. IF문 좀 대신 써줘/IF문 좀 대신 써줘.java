import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(stk.nextToken());
		int M = Integer.parseInt(stk.nextToken());
		
		Map<Integer, String> map = new HashMap<Integer, String>();
		List<Integer> limits = new ArrayList<Integer>();
		for(int i=0; i<N; i++) {
			stk = new StringTokenizer(br.readLine());
			String title = stk.nextToken();
			int limit = Integer.parseInt(stk.nextToken());
			
			if(map.containsKey(limit)) continue;
			limits.add(limit);
			map.put(limit, title);
		}
		
		
		// 각 캐릭터에 맞는 칭호 출력
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<M; i++) {
			int power = Integer.parseInt(br.readLine());
			
			// 이진 탐색
			int start = 0;
			int end = limits.size()-1;
			String title = map.get(limits.get(start));
			
			while(start<=end) {
				int mid = (start + end) / 2;
				
				if(limits.get(mid) < power) {
					start = mid+1;
				}
				else {
					end = mid-1;
					title = map.get(limits.get(mid));
				}
			}
			
			sb.append(title).append("\n");
		}
		
		
		// 결과 출력
		System.out.print(sb);
	}
    
}