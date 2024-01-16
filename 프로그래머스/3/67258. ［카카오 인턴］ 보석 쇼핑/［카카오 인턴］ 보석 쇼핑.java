// 모든 종류의 보석을 포함하는 가장 짧은 구간을 리턴
// 가장 짧은 구간 중에서도 시작 진열대 번호가 가장 작은 구간을 리턴

import java.util.*;

class Solution {
    
    static int start;           // 최단 구간 시작 위치
    static int end;             // 최단 구간 끝 위치
    static int minLen = 999999;    // 최단 구간의 길이
    
    public int[] solution(String[] gems) {        
        // HashSet으로 보석 종류 개수를 구한다
        int setLen = new HashSet<String>(Arrays.asList(gems)).size();
        
        // 투 포인터를 이용하여 모든 보석을 1개 이상 포함하는 구간을 찾는다
        int left = 0;
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        
        for(int right=0; right<gems.length; right++){
            // 보석을 새로 발견하면 맵에 등록하고, 기존 보석이면 수량을 늘린다
            if(map.containsKey(gems[right])){
                map.put(gems[right], map.get(gems[right])+1);
            }else{
                map.put(gems[right], 1);
            }
            
            // 현재 left 위치의 보석이 2개 이상이면, 1개가 남을 때까지 반복
            while(map.get(gems[left]) > 1){
                map.put(gems[left], map.get(gems[left])-1);  // 보석 1개 제거
                left++;  // 시작 인덱스 증가
            }
            
            // 모든 종류의 보석을 1개 이상 가지고 최소 구간이라면, 최소값 업데이트
            if(map.size() == setLen && minLen > (right-left)){
                // 구간 시작, 끝 위치
                start = left;
                end = right;
                // 구간 길이
                minLen = right-left;
            }
            
        }
        return new int[]{start+1, end+1};
    }
}