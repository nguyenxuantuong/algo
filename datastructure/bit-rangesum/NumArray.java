//https://leetcode.com/problems/range-sum-query-mutable/
import java.util.*;

public class NumArray {
	public int[] BIT;
	public int[] nums;
	int n;

    public NumArray(int[] nums) {
		this.nums = nums;	         	       	
		n = nums.length;

		BIT = new int[n+1];

		for(int i=0; i<n; i++) {
			//using 1-index
			init(i+1, nums[i]);
		}
    }

    //this function only update the BIT array
    void init(int i, int delta) {
    	for(int x=i; x<=n; x+=(x&(-x))) {
			BIT[x] += delta; 
		}
    }

    void update(int i, int val) {
    	int delta = val - nums[i];
    	nums[i] = val;

		init(i+1, delta);
    }
	
	public int getSum(int i) {
		int sum = 0;

		for(int x=i; x>0; x-=(x&(-x))) {
			sum += BIT[x];
		}

		return sum;
	}   
	 
    public int sumRange(int i, int j) {
        return getSum(j+1) - getSum(i);
    }

    public static void main(String[] args) {
    	NumArray solution = new NumArray(new int[]{1, 3, 5});
    	System.out.println(solution.getSum(2));
    	System.out.println(solution.sumRange(0, 2));
    	solution.update(1, 2);
    	System.out.println(solution.sumRange(0, 2));
    }
}
