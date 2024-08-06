// 다익스트라
import java.util.*;

class Solution {

    static class Node implements Comparable<Node> {
        int number, cost;

        public Node(int number, int cost) {
            this.number = number;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node n) {
            return cost - n.cost;
        }
    }

    int n;
    int[][] edge;
    List<Integer>[] graph;
    int farDistance;

    public int solution(int n, int[][] edge) {
        this.n = n;
        this.edge = edge;
        init();

        int[] dist = dijkstra();

        int answer = 0;
        for (int i = 1; i <= n; i++) {
            if (dist[i] == farDistance) {
                answer++;
            }
        }

        return answer;
    }

    public int[] dijkstra() {
        int[] dist = new int[n + 1];
        Arrays.fill(dist, 1000000);
        dist[1] = 0;
        PriorityQueue<Node> que = new PriorityQueue<>();
        que.offer(new Node(1, 0));

        while (!que.isEmpty()) {
            Node current = que.poll();

            if (dist[current.number] < current.cost) {
                continue;
            }

            for (int next : graph[current.number]) {
                if (dist[next] > current.cost + 1) {
                    dist[next] = current.cost + 1;
                    farDistance = dist[next];
                    que.offer(new Node(next, dist[next]));
                }
            }
        }

        return dist;
    }

    public void init() {
        graph = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < edge.length; i++) {
            graph[edge[i][0]].add(edge[i][1]);
            graph[edge[i][1]].add(edge[i][0]);
        }
    }
}