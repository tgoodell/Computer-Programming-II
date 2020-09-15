import java.math.BigInteger;

public class Stats
{
	public double time;
	public int size;
	public long compareCount;
	public long swapCount;
	
	public Stats(int size, double time, long compareCount, long swapCount)
	{
		this.time=time;
		this.size=size;
		this.compareCount=compareCount;
		this.swapCount=swapCount;
	}
	
	public static String getHeaders()
	{
		return "n,time,compareCount,swapCount";
	}
	
	public String toString()
	{
		return String.format("%d,%f,%d,%d",size,time,compareCount,swapCount);
	}
}
