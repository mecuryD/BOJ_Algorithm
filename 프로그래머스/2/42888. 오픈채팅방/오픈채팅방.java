// [프로그래머스] 오픈채팅방
import java.util.*;

class Solution {
    
    public String[] solution(String[] record) {
        // 기록 각각에 대해 (아이디-닉네임) 쌍을 해쉬로 관리한다
        Map<String, String> nickname = new HashMap<String, String>();
        for(String r : record){
            String[] chat = r.split(" ");
            if(!chat[0].equals("Leave")){
                nickname.put(chat[1], chat[2]);
            }
        }
        
        // 관리자가 최종적으로 확인하는 메세지를 배열에 추가한다
        ArrayList<String> result = new ArrayList<String>();
        for(String r : record){
            String[] chat = r.split(" ");
            if(chat[0].equals("Enter")) result.add(nickname.get(chat[1]).concat("님이 들어왔습니다."));
            else if(chat[0].equals("Leave")) result.add(nickname.get(chat[1]).concat("님이 나갔습니다."));
        }
        
        return result.toArray(new String[0]);
    }
    
    // 입퇴장 정보 클래스
    public class Info {
        String id; // 유저 ID
        int state; // 0:입장, 1:퇴장
        
        public Info(String id, int state){
            this.id = id;
            this.state = state;
        }
    }
}