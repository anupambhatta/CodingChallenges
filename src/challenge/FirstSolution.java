package challenge;

import java.util.PriorityQueue;

public class FirstSolution {

	public static void main(String[] args) {
		int[] nums = {1,3,2,4};
		System.out.println(reduceArray(nums));
		
		String s = "abc";
		String[] operations = {"1 2 L", "0 0 R", "0 2 L"};
		System.out.println(moveChars(s, operations));
		
	}
	
	/**** Question 1 *****************************
	 * Return the lowest cost of reducing an array 
	 * by adding two elements each time and taking the 
	 * sum to be new member of the array until the array 
	 * reduces to just 1 element.
	 * For e.g. [1,3,2,4]
	 * 			[3,3,4] cost => 1+2 = 3
	 * 			[6,4] 	cost => 3+3 = 6
	 * 			[10] 	cost => 6+4 = 10
	 * 			Total Cost = 3+6+10 = 19
	 * @return cost
	 */
	public static int reduceArray(int[] nums) {
		PriorityQueue<Integer> heap = getHeap(nums);
    	int cost = 0;
    	int sum = 0;
    	while(heap.size()>1) {
    		sum = heap.poll() + heap.poll();
    		cost += sum;
    		heap.add(sum);
    	}
    	return cost;
    }
	
	/****
	 * Creates and returns a priority queue from the integer array
	 */
	public static PriorityQueue<Integer> getHeap(int[] nums){
		PriorityQueue<Integer> heap = new PriorityQueue<>();
		for(int i=0; i<nums.length; i++) {
			heap.add(nums[i]);
		}
		return heap;
	}
	
	/****
	 * The operations is an array of string with instructions to 
	 * roll the chars in string s.
	 * If operations = {"1 2 L", "0 0 R", "0 2 L"}, then
	 * the chars from 1 to 2 of s are rolled by 1 spot to the left, then
	 * the chars from 0 to 0 is rolled by 1 position to the right and lastly
	 * chars from 0 to 2 are rolled by 1 position to the left.
	 * So, if s = abc then after the first iteration it becomes aab (b->a, c->b)
	 * after second iteration, it becomes bab (a->b)
	 * and after the last iteration, aza(b->a,a->z,b->a) 
	 * @param s
	 * @param operations
	 * @return string value of sChars
	 */
	public static String moveChars(String s, String [] operations) {
    	char [] sChars = s.toCharArray();
    	for(int i=0; i<operations.length; i++) {
    		 transform(sChars, operations[i]);
    	}
    	return String.valueOf(sChars);
    }
	
	public static void transform(char[] sChars, String operation) {
		String [] opsArray = operation.split(" ");
		 int a = Integer.parseInt(opsArray[0]);
		 int b = Integer.parseInt(opsArray[1]);
		 for(int j=a; j<=b; j++) {
			 sChars[j] = rotate(getDirection(opsArray[2]), sChars[j]);
		 }
	}
	
	public static int getDirection(String dir) {
		return dir.equals("L") ? -1:1;
	}
	
	/***
	 * Rotates char by x positions based, 
	 * if -ve then left if +ve then right
	 * @param x
	 * @param ch
	 * @return
	 */
    public static char rotate(int x, char ch) {
    	if((ch+x) < 97)
			 return (char)(123+x);
		if((ch+x) > 122)
			 return (char)(96+x);
    	return (char)(ch+x);
    }
}
