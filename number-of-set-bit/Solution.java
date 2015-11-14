//http://www.geeksforgeeks.org/count-total-set-bits-in-all-numbers-from-1-to-n/
import java.util.*;

public class Solution {
	public int totalSetBitSlow(int n) {
		int out = 0;

		for(int i=0; i<=n; i++){
			for(int j=0; j<32; j++){
				out += (i & (1 << j)) != 0 ? 1 : 0;
			}
		}

		return out;
	}

	public int totalSetBitFast(int n){
		BitSet bs = BitSet.valueOf(new long[]{n});

		int L = bs.length();

		int count = 0;

		int nOnes = 0;
		for(int i=L-1; i>=0; i--){
			//this bit is set
			if(bs.get(i)) {
				count += (i >= 1 ? (int)Math.pow(2, i-1) * i : 0) + nOnes * (int)Math.pow(2,i);										 
				nOnes++;
			}
		}

		return count + nOnes;
	}

	public static void main(String[] args){
		Solution solution = new Solution();		
		for(int i=0; i<10000; i++){
			int fastNumber = solution.totalSetBitFast(i);
			int slowNumber = solution.totalSetBitSlow(i);

			if(fastNumber != slowNumber){
				System.out.println(i + ":fast:" + fastNumber + ":" + slowNumber);
			}
		}
	}
}