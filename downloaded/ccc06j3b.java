import java.util.*;
public class ccc06j3b {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		
		System.out.println(Character.getNumericValue('s'));
		//10 a
		//35 z
		//28 s
		while(true) {
			String s = sc.nextLine();
			if(s.equals("halt")) {
				break;
			}
			else {
				char prev = ' ';
				for(int i = 0; i<s.length(); i++) {
					char c = s.charAt(i);
					int numericValue = Character.getNumericValue(c);
					
				}
			}
		}
		
		

	}

}
