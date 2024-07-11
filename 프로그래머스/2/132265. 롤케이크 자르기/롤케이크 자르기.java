import java.util.*;

// [프로그래머스] 롤케이크
class Solution {
    
    int result = 0;
    
    public int solution(int[] topping) {
        // 롤케이크를 자르지 않은 상태에서 시작
        Map<Integer, Integer> right = new HashMap<Integer, Integer>();
        for(int t : topping){
            if(!right.containsKey(t)){
                right.put(t, 1);
                continue;
            }
            right.put(t, right.get(t)+1);
        }
        
        
        // i번째 조각의 오른쪽을 기준으로 차례대로 잘라보면서 공평한지 확인한다
        int len = topping.length;
        Map<Integer, Integer> left = new HashMap<Integer, Integer>();
        
        for(int i=0; i<len; i++){
            // 동생으로부터 조각을 뺏는다
            int currentTopping = topping[i];
            int currentRight = right.get(currentTopping);
            if(currentRight==1){
                right.remove(currentTopping);
            }else{
                right.put(currentTopping, currentRight-1);
            }
            
            // 조각을 형에게 넘겨준다
            if(!left.containsKey(currentTopping)){
                left.put(currentTopping, 1);
            }else{
                left.put(currentTopping, left.get(currentTopping)+1);
            }
            
            // 공평하게 나누어졌는지 확인한다
            if(left.keySet().size() == right.keySet().size()){
                result++;
            }
        }
        return result;
    }
}