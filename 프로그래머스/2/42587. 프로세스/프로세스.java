import java.util.*;

// [프로그래머스] 프로세스
class Solution {
    
    public int solution(int[] priorities, int location) {
        // 우선순위 별로 몇 번 등장하는지 MAP에 저장한다
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        Queue<Process> queue = new ArrayDeque<Process>();
        
        int n = 0;
        for(int priority : priorities){
            int count = (map.containsKey(priority)) ? (map.get(priority)+1) : 1;
            map.put(priority, count);
            queue.offer(new Process(n++, priority));
        }
        
        // 존재하는 우선순위를 리스트로 변환한다 
        List<Integer> keyList = new ArrayList<Integer>(map.keySet());
        Collections.sort(keyList);
            
        // 규칙에 따라 프로세스를 관리한다
        int count = 0;
        int index = keyList.size() - 1;
        int maxPriority = keyList.get(index);
        
        while(index >= 0){
            // 프로세스 하나를 꺼낸다
            Process cur = queue.poll();
            
            // 우선순위가 더 높은 프로세스가 있다면 큐에 다시 넣는다
            if(cur.p < maxPriority){
                queue.offer(cur);
                continue;
            }
            
            // 없다면 실행하고 종료시킨다
            // 특정 프로세스인 경우
            count++;
            if(cur.n==location) return count;
            
            // 특정 프로세스가 아닌 경우
            int left = map.get(cur.p);
            if(left > 1){
                map.put(cur.p, left-1);
            }else if(left == 1){
                map.remove(cur.p);
                maxPriority = keyList.get(--index);
            }
        }
        return count;
    }
    
    class Process {
        int n;
        int p;
        
        public Process(int n, int p){
            this.n = n;
            this.p = p;
        }
    }
}