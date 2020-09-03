import java.util.*;
import java.awt.*;

public class sorting {
	public static void main (String[] args) {
		int[] nums={5,7,6,2,3,4,8,1};
		int length = nums.length-1;
		
		for (int k=0;k<7;k++) {
			for (int i=0;i<length;i++) {
				if (nums[i]>nums[i+1]) {
					int temp=nums[i];
					nums[i]=nums[i+1];
					nums[i+1]=temp;
				}
			}
			System.out.println(Arrays.toString(nums));
		}
		
		System.out.println();
		
		nums=add(nums,9);
		
		
		System.out.println(Arrays.toString(nums));
		
		// ArrayList list=new ArrayList()
		// list.add(9);
		// list.get(0);
		// System.out.println(list)
		
	}
	
	public static int[] add(int[] nums, int value) {
		int[] nums2 = new int[nums.length+1];
		for (int i= 0;i<nums.length;i++) {
			nums2[i]=nums[i];
		}
		
		nums2[nums2.length-1] = value;
		return nums2;
	}
}

class ArrayList {
	//instance
	int[] list;
	
	//constructor
	public ArrayList() {
		list=new int[0];
	}
	
	//methods
	
	public void add (int value) {
		int[] newlist = new int[list.length+1];
		for (int i= 0;i<list.length;i++) {
			newList[i]=list[i];
		}
		
		newList[list.length] = value;
		list=newList;
	}
	
	public int get (int index) {
		return list[index];
	}
	
	public String toString() {
		return "I am an array list!";
	}
}
