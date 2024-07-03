import java.util.*;

// [프로그래머스] 전력망을 둘로 나누기
class Solution {
    
    static int result = 100;
    
    public int solution(int n, int[][] wires) {
        // 그래프 초기화
        List<Integer>[] graph = new LinkedList[n+1];
        for(int i=1; i<=n; i++){
            graph[i] = new LinkedList<Integer>();
        }
        
        for(int[] wire : wires){
            graph[wire[0]].add(wire[1]);
            graph[wire[1]].add(wire[0]);
        }
        
        // 각 전선을 끊으며 송전탑의 개수를 비교한다
        for(int[] wire : wires){
            // 전선을 끊는다
            graph[wire[0]].remove(graph[wire[0]].indexOf(wire[1]));
            graph[wire[1]].remove(graph[wire[1]].indexOf(wire[0]));
            
            // 한 쪽의 송전탑 개수를 파악한다
            int left = bfs(n, graph);
            int diff = Math.abs(n - left * 2);
            result = (diff < result) ? diff : result;
                
            // 다음 반복을 위해 전선을 복원한다
            graph[wire[0]].add(wire[1]);
            graph[wire[1]].add(wire[0]);
            
        }
        
        return result;
    }
    
    // 너비 우선 탐색을 통해 송전탑 개수를 파악한다
    public int bfs(int n, List<Integer>[] graph){
        Queue<Integer> queue = new ArrayDeque<Integer>();
        boolean[] visited = new boolean[n+1];
        int count = 1;
        
        queue.offer(1);
        visited[1] = true;
        
        while(!queue.isEmpty()){
            // 큐에서 송전탑 하나를 꺼낸다
            int node = queue.poll();
            
            // 아직 방문하지 않은 인근 송전탑을 큐에 넣는다
            for(int next : graph[node]){
                if(!visited[next]){
                    queue.offer(next);
                    visited[next] = true;
                    count++;
                }
            }
        }
        
        return count;
    }
    
}