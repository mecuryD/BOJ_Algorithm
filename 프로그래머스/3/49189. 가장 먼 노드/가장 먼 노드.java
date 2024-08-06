import java.util.*;

// [프로그래머스] 가장 먼 노드
class Solution {
    public int solution(int n, int[][] edge) {
        // 그래프 생성
        ArrayList<Integer>[] graph = new ArrayList[n+1];
        for(int i=1; i<=n; i++){
            graph[i] = new ArrayList<Integer>();
        }
        
        for(int i=0; i<edge.length; i++){
            graph[edge[i][0]].add(edge[i][1]);
            graph[edge[i][1]].add(edge[i][0]);
        }
        
        // BFS 탐색
        int[] distance = new int[n+1];
        Arrays.fill(distance, 99999);
        distance[1] = 0;
        int maxDis = 0;
        
        Queue<int[]> queue = new ArrayDeque<int[]>();
        queue.offer(new int[]{1, 0});
        
        while(!queue.isEmpty()){
            // 큐에서 노드 하나를 꺼낸다
            int[] cur = queue.poll();
            
            // 아직 방문하지 않은 노드만 큐에 삽입한다
            for(int node : graph[cur[0]]){
                if(distance[node] == 99999){
                    distance[node] = cur[1] + 1;
                    queue.offer(new int[]{node, distance[node]});
                    maxDis = (maxDis < distance[node]) ? distance[node] : maxDis;
                }
            }
        }
        
        // 가장 먼 노드의 개수를 구한다
        int count = 0;
        for(int i=1; i<=n; i++){
            if(distance[i]==maxDis) count++;
        }
        
        return count;
    }
}