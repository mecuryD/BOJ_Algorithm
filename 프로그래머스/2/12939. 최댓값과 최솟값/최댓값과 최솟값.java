class Solution {
    public String solution(String s) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        
        String[] arr = s.split(" ");
        for(String a : arr){
            int n = Integer.parseInt(a);
            min = (n < min) ? n : min;
            max = (max < n) ? n : max;
        }
        return min + " " + max;
    }
}