import java.util.Arrays;
import java.util.Scanner;

public class Main{

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		/* input */
		int[] std = new int[30];
		for(int i=0; i<28; i++) {
			std[sc.nextInt()-1] = 1; 
		}
		
		/* calculate */
		for(int i=0; i<30; i++) {
			if(std[i]==0) {
				System.out.println(i+1);
			}
		}
	}
}