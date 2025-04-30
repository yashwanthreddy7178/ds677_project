package pack1;

import Utility1.Util1;

public class StopWatch
{
	long start=0;
	long stop=0;
	/*main method*/
	public static void main(String[] args) 
	{
		StopWatch s1=new StopWatch();
		System.out.println("enter 0 to start ");
		int start1=Util1.getInt();
		if(start1==0)
		{
			s1.startTime();  //calling nonstatic starttime method
			
		}
		
		System.out.println("enter 1 to end");
		int stop1=Util1.getInt();
		
		if(stop1==1)
		{
			
			s1.stopTime();  //calling nonstatic stoptime method
			
		}
		
		long elapsedTime=s1.stop-s1.start; //elapsed time (stop-start)
		
		System.out.println("elapsedTime "+elapsedTime*0.001+" milliseconds");
	}
		/* stopwatch methods */
		 
		public void startTime()  //start method
		{
				
			start=System.currentTimeMillis();  //this method takes the current system time
				
		}
		public void stopTime() //stop method
		{
				
			stop=System.currentTimeMillis();  //this method takes the current system time
				
		}
	
}
