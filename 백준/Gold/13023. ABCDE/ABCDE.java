import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// [백준 13023번] ABCDE
public class Main{
    static int N, M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(stk.nextToken()); // 사람의 수 N
        M = Integer.parseInt(stk.nextToken()); // 친구 관계의 수 M
        ArrayList<Integer>[] map = new ArrayList[N]; // 친구 관계 그래프
        for(int i=0; i<N; i++) { // 연결리스트로 그래프 표현
        	map[i] = new ArrayList<Integer>();
        }
        boolean[] visited = new boolean[N]; // 방문 관리 배열
        
        int a, b;
        for(int i=0; i<M; i++) { // M개의 친구 관계를 그래프에 반영
            stk = new StringTokenizer(br.readLine(), " ");
            a = Integer.parseInt(stk.nextToken());
            b = Integer.parseInt(stk.nextToken());
            map[a].add(b); // a의 친구 관계에 b 추가
            map[b].add(a); // b의 친구 관계에 a 추가
        }
        
        int res=0;
        loop : for(int i=0; i<N; i++) { // N명에 대해 반복하면서
        	visited = new boolean[N];
        	visited[i] = true;
        	if(dfs(map, visited, 0, i)) {
        		res = 1;
        		break;
        	}
        }
        System.out.println(res);
    }
    
    // map : 친구 관계 그래프, visited[] : 친구 관계 확인 여부 체크
    // cnt : 지금까지 선택된 친구 관계 수, num : 확인해야 할 친구의 번호
    public static boolean dfs(ArrayList<Integer>[] map, boolean[] visited, int cnt, int num) {
        // 기저 구문
        if(cnt==4) return true; // 조건에 맞는 친구 관계의 존재가 확인되었을 경우
        
        for(int idx : map[num]) { // 인접리스트는 인접 여부 확인이 필요없다
        	if(!visited[idx]) { // 방문하지 않았다면
        		visited[idx] = true;
        		if(dfs(map, visited, cnt+1, idx)) return true; // 조건에 해당하는 관계가 있으면 true 리턴
        		visited[idx] = false; // 원상 복원
        	}
        }
        return false;
    }
}