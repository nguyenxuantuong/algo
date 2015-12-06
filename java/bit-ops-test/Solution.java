import java.util.*;

public class Solution {
	//check if two number has same sign (both negative or positive)
	public boolean hasSameSign(int a, int b) {
		return ((a ^ b) & (1 << 31)) != 0 ? true : false;
	}

	//count all number of setbit in number (O(nlog(n)) solution)
	public int totalSetBit(int n) {
		int out = 0;

		for(int i=0; i<=n; i++){
			for(int j=0; j<32; j++){
				out += (i & (1 << j)) != 0 ? 1 : 0;
			}
		}

		return out;
	}

	//swap n bit between position p1, p2 of the number x
	public int swapBitNumber(int x, int p1, int p2, int n) {
		BitSet bs = BitSet.valueOf(new long[]{x});

		for(int i=0; i<n; i++) {
			boolean tmpBit = bs.get(p1 + i);
			bs.set(p1 + i, bs.get(p2 + i));
			bs.set(p2 + i, tmpBit);
		}

		return (int)bs.toLongArray()[0];
	}
	
	//swap by using raw bit manipulation methods
	public int swapBitNumber2(int x, int p1, int p2, int n) {
		int set1 = (x >> p1) & ((1 << n) - 1);
		int set2 = (x >> p2) & ((1 << n) - 1);

		int tmp = set1 ^ set2;
		int xorTmp = (tmp << p2) | (tmp << p1);

		//return x ^ xorTmp;

		//no extra-variable
		return x ^ (((set1 ^ set2) << p2) | ((set1 ^ set2) << p1));
	}

	//sum 2 number without arithmetic operations
	public int sumTwoNumber(int x, int y) {
		int out = 0;
		int carry = 0;

		for(int i=0; i<32; i++) {
			int bit1 = (x >> i) & 1;
			int bit2 = (y >> i) & 1;

			int bit = bit1 ^ bit2 ^ carry;
			out = out | (bit << i);

			//update carry
			carry = (bit1 & carry) | (bit2 & carry) | (bit1 & bit2);
		}

		return out;
	}

	//bitwise operation to add 1 to number
	public int addOne(int x) {
		return -(~x); //-x = ~x + 1 -- in 2 complement forms		
	}

	//find max, min using bit manipulation
	public int maxTwoNumber(int x, int y) {
		return x - ((x-y) & ((x-y) >> 31)); //NOT: keep the bracket ()
	}

	public int minTwoNumber(int x, int y){
		return y + ((x-y) & ((x-y) >> 31));
	}

	public void printBinaryString(int x) {
		for(int i=31; i>=0; --i) {
			if ((x & (1 << i)) != 0) {
				System.out.print(1);
			} else {
				System.out.print(0);
			}
		}

		System.out.println();
	}

	public int positionOfSetBit(int n){
		return n == 0 ? 0 : (n % 2 == 1 ? 1 : 1 + positionOfSetBit(n >> 1));
	}

	public static void main(String[] args){
		Solution solution = new Solution();	

		//check has same sign method
		// System.out.println(solution.hasSameSign(-1, 100) + ":" + solution.hasSameSign(1, -100) 
		// 	+ ":" + solution.hasSameSign(-100, -200) + ":" + solution.hasSameSign(1, 100));

		//check total setbit method
		// int[] input = new int[]{3,6,7,8};
		// for(int i=0; i<input.length; i++){
		// 	System.out.println(solution.totalSetBit(input[i]));
		// }

		//check swap bit method
		// System.out.println(solution.swapBitNumber(47, 1, 5, 3) + ":" + solution.swapBitNumber(28, 0, 3, 2));
		// System.out.println(solution.swapBitNumber2(47, 1, 5, 3) + ":" + solution.swapBitNumber2(28, 0, 3, 2));		

		//sum of two number				
		// for(int i=0; i<10; i++) {
		// 	for(int j=0; j<10; j++) {
		// 		if(solution.sumTwoNumber(i, j) != i + j) {
		// 			System.out.println(i + ":" + j + ":" + solution.sumTwoNumber(i, j));
		// 		}
		// 	}
		// }

		//System.out.println(solution.minTwoNumber(2, 6) + ":" + solution.maxTwoNumber(-2, -6));
		//System.out.println(solution.addOne(1));

		//solution.printBinaryString(5);
		System.out.println(solution.positionOfSetBit(19));
	}
}

