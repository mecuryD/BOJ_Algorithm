// [프로그래머스] 정수 삼각형
class Solution {
    public int solution(int[][] triangle) {
        int height = triangle.length;
        for(int i=1; i<height; i++){
            for(int j=0; j<i+1; j++){
                int max = (0<j) ? triangle[i-1][j-1] : 0;
                max = (j<i) ? Math.max(max, triangle[i-1][j]) : max;
                triangle[i][j] += max;
            }
        }
        
        int result = 0;
        for(int i=1; i<height; i++){
            result = (result < triangle[height-1][i]) ? triangle[height-1][i] : result;
        }
        return result;
    }
}