// [프로그래머스] 스킬 트리
// 입력 / 선행 스킬 순서 skill
// 입력 / 유저들이 만든 스킬트리 배열 skill_trees
// 출력 / 가능한 스킬 트리 개수
// 선행 스킬 순서를 Queue에 삽입해놓고, peek값과 동일하면 넘어간다
// 뭔가 위상 정렬 같기ㅗ ㅎ나데 8ㅅ8

import java.util.*;

class Solution {
    
    static int result = 0;
    static List<Character> skillOrder = new ArrayList<>();
    
    public int solution(String skill, String[] skill_trees) {
        // 스킬 순서를 파싱해서 char 배열에 저장
        char[] charSkill = skill.toCharArray();
        for(char c : charSkill){
            skillOrder.add(c);
        }
        
        // 각 스킬 트리를 살펴보며 가능한지 체크
        for(String skillTree : skill_trees){
            if(isPossible(skillTree)){
               result++; 
            }
        }
        
        return result;
    }
    
    
    boolean isPossible(String skillTree){
        // 스킬 트리를 문자 배열로 변환
        char[] charSt = skillTree.toCharArray();
        int lenSt = charSt.length;
        int idx = 0;
        
        // 각 문자 배열이 큐에 존재하는 지 확인하고, 존재하면 피크 값과 비교한다
        for(int i=0; i<lenSt; i++){
            if(skillOrder.contains(charSt[i])){
                // 선행 스킬 학습이 필요하다면
                if(skillOrder.get(idx) != charSt[i]){
                    return false;
                }
                
                // 선행 스킬 학습이 이미 되어있다면
                idx++;
            }
        }
        
        return true;
    }
}