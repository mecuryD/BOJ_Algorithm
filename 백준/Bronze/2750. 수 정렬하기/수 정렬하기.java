import java.util.Arrays;
import java.util.Scanner;

public class Main {
	
	/* Main */
	public static void main(String[] args) {
        /* input */
        Scanner sc = new Scanner(System.in);
        
        int n = sc.nextInt();
        int[] arr = new int[n];
        
        for(int i=0;i<n;i++){
            arr[i] = sc.nextInt();
        }
        
        /* sort */
        Arrays.sort(arr);
        for(int i=0;i<n;i++) {
        	System.out.println(arr[i]);
        }
	}
}
