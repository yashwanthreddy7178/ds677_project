import java.io.BufferedInputStream;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.*;
public class Main
{

	public static void main(String[] args) throws Exception
	{
		//BufferedReader br=new BufferedReader(new FileReader("c:\\in.txt"));
		Scanner cin=new Scanner(new BufferedInputStream(System.in));
		while(true)
		{
	
			int n1=cin.nextInt();
			int n2=cin.nextInt();
			int n=cin.nextInt();
			if(n1==0&&n2==0&&n==0)
				break;
			int divsor=n-n1-n2;
			HashMap<BigDecimal,Integer> bd=new HashMap< BigDecimal,Integer>();
			for(int i=0;i<n;i++)
			{
				
				BigDecimal td=new BigDecimal(cin.next());
				Integer ti=bd.get(td);
				if(ti!=null)
					ti++;
				else ti=1;
				bd.put(td,ti);
			}
			BigDecimal [] p=new BigDecimal[bd.size()];
			bd.keySet().toArray(p);
			Arrays.sort(p);
			BigDecimal re=new BigDecimal("0");
			int findex=0;
			int fnum=0;
			int lindex=p.length-1;
			int lnum=0;
			while(true)
			{
				int t=bd.get(p[findex]);
				if(n2>=t)
				{
					n2-=t;
					findex++;
				}
				else
				{
					fnum=t-n2;
					re=re.add(p[findex].multiply(new BigDecimal(""+fnum)));
					break;
				}
			}
			while(true)
			{
				int t=bd.get(p[lindex]);
				if(n1>=t)
				{
					n1-=t;
					lindex--;
				}
				else
				{
					lnum=t-n1;
					re=re.add(p[lindex].multiply(new BigDecimal(""+lnum)));
					break;
				}
			}
		
			for(int i=findex+1;i<lindex;i++)
				re=re.add(p[i].multiply(new BigDecimal(""+bd.get(p[i]))));
			re=re.divide(new BigDecimal(""+divsor),6,BigDecimal.ROUND_HALF_UP);
			System.out.println(re);
			bd=null;
			System.gc();
		}
	}
	
}
