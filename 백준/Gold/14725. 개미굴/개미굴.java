import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;
import java.util.StringTokenizer;

// [BOJ14725] 개미굴
public class Main {

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        // 루트 노드 생성
        HashMap<String, Node> root = new HashMap<>();

        // 값 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        for(int i=0; i<N; i++){
            StringTokenizer stk = new StringTokenizer(br.readLine(), " ");
            int K = Integer.parseInt(stk.nextToken());

            // 만약 첫 번째 층이 처음 발견되었다면 루트에 추가
            String first = stk.nextToken();
            if(!root.containsKey(first)){
                root.put(first, new Node(first));
            }

            // 다음 층을 연결한다
            connect(root.get(first), stk);
        }

        // 첫 번째 층부터 결과 출력
        print(root, 0);
        System.out.println(sb);
    }

    static void print(HashMap<String, Node> floor, int idx){
        // 현재 층의 모든 굴을 탐색하며 출력
        String[] keys = sortKeys(floor.keySet());
        for(String k : keys){
            // 현재 층 출력
            for(int i=0; i<idx; i++){
                sb.append("--");
            }
            sb.append(k+"\n");
            
            // 다음 층 출력
            print(floor.get(k).child,idx+1);
        }
    }

    static String[] sortKeys(Set<String> keys){
        String[] sortedKeys = keys.toArray(new String[0]);
        Arrays.sort(sortedKeys);
        return sortedKeys;
    }

    static void connect(Node cur, StringTokenizer stk){
        while(stk.hasMoreTokens()){
            String next = stk.nextToken();

            // 자식에 없는 노드면 추가한다
            if(!cur.child.containsKey(next)){
                cur.child.put(next, new Node(next));
            }
            // 다음 노드를 탐색한다
            cur = cur.child.get(next);
        }
    }

    static class Node {
        String name;
        HashMap<String, Node> child;

        Node(String name){
            this.name = name;
            this.child = new HashMap<>();
        }
    }
}