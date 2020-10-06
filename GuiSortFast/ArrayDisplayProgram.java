public class ArrayDisplayProgram
{
	public static void main(String[] args)
	{
		ArrayList list=ArrayList.shuffledList(16);
		ArrayDisplay.setList(list);
		list.insertionSort();
		//ArrayDisplay.update();
	}
}
