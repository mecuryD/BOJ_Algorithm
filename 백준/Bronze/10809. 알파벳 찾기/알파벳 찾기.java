import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main{
	public static void main(String[] args) throws IOException{
		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		 String str = br.readLine();
		 for(int i='a';i<='z'; i++) {
			 int exist = -1;
			 for(int j=0; j<str.length(); j++) {
				 if(str.charAt(j)==i) {
					 exist = j;
					 break;
				 }
			 }	
			 System.out.print(exist + " ");
		 }
	}
}