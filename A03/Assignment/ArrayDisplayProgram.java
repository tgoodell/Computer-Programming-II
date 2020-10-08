public class ArrayDisplayProgram
{
    public static void main(String[] args)
    {
        ArrayList list=ArrayList.shuffledList(64);
        ArrayDisplay.setList(list);
        list.selectionSort();
    }
}
