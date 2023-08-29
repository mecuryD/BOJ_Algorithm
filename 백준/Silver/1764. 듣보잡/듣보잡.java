import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

// [BOJ1764] 듣보잡
public class Main {

	public static void main(String[] args) throws IOException {
		// 입력 초기화
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(stk.nextToken()); // 듣도 못한 사람의 수
		int M = Integer.parseInt(stk.nextToken()); // 보도 못한 사람의 수
		
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		for(int i=0; i<N; i++) { // 듣도 못한 사람 입력
			map.put(br.readLine(), true);
		}
		
		// 듣도 보도 못한 사람의 수 계산, 해쉬 맵
		int cnt=0;
		ArrayList<String> list = new ArrayList<String>();
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<M; i++) { // 보도 못한 사람 입력
			String name = br.readLine();
			if(map.containsKey(name)) { // 만약 듣도 못한 사람에도 해당하면
				cnt++; // 듣도 보도 못한 사람의 수 +1
				list.add(name); // 듣도 보도 못한 사람의 이름 저장				
			}
		}

		Collections.sort(list); // 듣도 보도 못한 사람들을 사전 순으로 정렬
		
		// 결과 출력
		for(int i=0; i<cnt; i++)
			sb.append(list.get(i)+"\n");
		System.out.println(cnt);
		System.out.println(sb);
	}
}