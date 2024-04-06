import java.util.*;

// [프로그래머스] 여행경로
class Solution {
    
    static int count = 0;
    static Map<String, ArrayList<Node>> graph = new HashMap<>();
    
    public String[] solution(String[][] tickets) {
    	// 티켓 정보를 그래프로 변환
        for(String[] t : tickets){
            String from = t[0];
            String to = t[1];
            
            // 그래프 연결
            if(!graph.containsKey(from)) graph.put(from, new ArrayList<Node>());
            graph.get(from).add(new Node(to, false));
            count++;
        }
        
        // 각 출발 공항에서의 항공권 정렬
        List<String> list = new ArrayList<>(graph.keySet());
        for(String depart : list){
            Collections.sort(graph.get(depart), new Comparator<Node>(){
                @Override
                public int compare(Node n1, Node n2) {
                    return n1.name.compareTo(n2.name);
                }
            });    
        }
        
        String[] result = new String[count+1];
        result[0] = "ICN";
        search(result[0], 1, result);  
            
        return result;
    }
    
    public boolean search(String from, int selected, String[] result){
        // 만약 모든 항공권이 사용되었다면 종료
        if(selected == count + 1) return true;
        
        // 출발 국가에서 아직 사용하지 않은 항공권을 탐색한다
        if(!graph.containsKey(from)) return false;
        List<Node> list = graph.get(from);
        for(Node to : list){
        	
            // 이미 사용한 항공권은 패스
            if(to.visited) continue;
            
            // 사용하지 않은 항공권이라면 경로를 탐색한다
            result[selected] = to.name;
            to.visited = true;
            if(search(to.name, selected + 1, result)) return true;
            to.visited = false;
        }
        return false;
    }
    
    static class Node {
        
        String name;
        boolean visited;
        
        public Node(String name, boolean visited){
            this.name = name;
            this.visited = visited;
        }
        
    }
}