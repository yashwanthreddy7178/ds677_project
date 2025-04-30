package listTest;

import java.util.ArrayList;
import java.util.List;

public class ListTest0507 {
	public static void main(String[] args) {
		
		List<Object> aaa = new ArrayList<Object>();
		
		List<String> bbb = new ArrayList<String>();
		
		List<String> ccc = new ArrayList<String>();
		
		
		//System.out.println(bbb.equals(ccc));
		
		//aaa.add(bbb);
		bbb.add("이종민");
		ccc.add("이종민");

		aaa.add(bbb);
		
		System.out.println(aaa.contains(ccc));
		
	}
}
