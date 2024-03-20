class Solution {
    public String solution(String s) {
        String answer = "";
        String[] sentence = s.toLowerCase().split("");
        boolean isFirst = true;

        for(String sen : sentence){
            // 단어의 첫 문자일 경우
            if(isFirst){
                isFirst = false;
                sen = sen.toUpperCase();
            }
            
            // 공백인 경우
            if(sen.equals(" ")) {
                isFirst = true;
            }
            
            answer += sen;
        }
        
        return answer;
    }
}