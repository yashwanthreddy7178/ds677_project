class Check
{
	int a,result=0,i;
	public float Average(int a)
	{
		for(i=1;i<=a;i++)
		{
			result =result+i;
		}
		float avg=(result/a);
		return avg;
	}
 static void main(String args[])// without public it will compile but not run
{
	Check obj=new Check();
	Scanner s= new Scanner(System.in);
	System.out.println("Enter Number");
	int a=s.nextInt();
	System.out.println(obj.Average(a));
}
}
/*class Check
{
	int a,result=0,i;
	public float Average(int a)
	{
		for(i=1;i<=a;i++)
		{
			result =result+i;
		}
		float avg=(result/a);
		return avg;
	}
public void main(String args[])// it says that main method should be static
{
	Check obj=new Check();
	Scanner s= new Scanner(System.in);
	System.out.println("Enter Number");
	int a=s.nextInt();
	System.out.println(obj.Average(a));
}
}*/
/*class Check
{
	int a,result=0,i;
	public float Average(int a)
	{
		for(i=1;i<=a;i++)
		{
			result =result+i;
		}
		float avg=(result/a);
		return avg;
	}
public static void main()// it says that main method not foound
{
	Check obj=new Check();
	Scanner s= new Scanner(System.in);
	System.out.println("Enter Number");
	int a=s.nextInt();
	System.out.println(obj.Average(a));
}
}*/
