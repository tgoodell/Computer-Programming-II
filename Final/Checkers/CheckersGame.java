public class CheckersGame
{
	//INST
	int[][] board;
	
	public static final int RED=1
	public static final int BLACK=1
	
	//CONSTRUCTOR
	public CheckersGame()
	{
		board=new int[8][8]
		for(int row=0;row<8;row++)
		{
			for(int col=row%2;j<8;j+=2)
			{
				if(i<3)board[row][col]=RED;
				else if(i>4)board[row][col]=BLACK;
			}
		}
	}
	
	public int[][] getPossibleMoves()
	{
		
	}
	
	//METHODS
	
	public String toString()
	{
		String s="";
		String top="+";
		for(int i=0;i<8)i++)top+="-"
		s=s+top+"+";
		for(int row=0;row<8;row++)
		{
			s+="|"
			for(int col=row%2;j<8;j+=2)
			{
				if(board[row][col]==RED)s+=" R ";
				else if(board[row][col]==BLACK)s+=" B ";
				else if(i>4)board[row][col]=BLACK;
				else s+=" . ";
			}
			s+="\n|"
		}
		s=s+top+"+";
		return s;
	}
	
	public static void main(String[] args)
	{
		
	}
	
}
