import java.util.*;

class Solution {
	static final int MAX_BIT = 64; //using 64 bits

	public boolean[] getBitSetPositive(long n) {
		boolean[] out = new boolean[MAX_BIT];

		int idx = 0;

		while(n > 0) {
			out[idx++] = n % 2 == 1 ? true : false;
			n = n >> 1;
		}

		return out;
	}

	public boolean[] getBitSet(long n) {		
		if(n >= 0) return getBitSetPositive(n);

		boolean[] out = getBitSetPositive(-1 * n);
		for(int i=0; i<MAX_BIT; i++) {
			out[i] = !out[i];
		}

		int idx = 0;
		while(idx<MAX_BIT && out[idx]){			
			out[idx++] = false;		
		}

		if(idx<MAX_BIT) out[idx] = true;
		return out;
	}

	public long bitSetToNumberPositive(boolean[] bitSet){
		long out = 0;

		for(int i=0; i<MAX_BIT; i++){
			if(bitSet[i]) {
				out += (long)Math.pow(2, i);
			}
		}

		return out;
	}

	public long bitSetToNumber(boolean[] bitSet){
		//positive number
		if(!bitSet[MAX_BIT-1]) {
			return bitSetToNumberPositive(bitSet);
		}

		//minus 1 first
		int idx = 0;
		while(idx < MAX_BIT && !bitSet[idx]) {
			bitSet[idx++] = true;			
		}

		if(idx < MAX_BIT) bitSet[idx] = false;
		for(int i=0; i<MAX_BIT; i++) {
			bitSet[i] = !bitSet[i];
		}

		return -1 * bitSetToNumberPositive(bitSet);
	}

	public int solutionCustomBitSet(int[] A){
		int N = A.length;

		int[] bitSum = new int[MAX_BIT];
		Arrays.fill(bitSum, 0);

		for(int i=0; i<A.length; i++) {
			//convert number to bitset
			boolean[] bitSet = getBitSet(A[i]);

			for(int j=0; j<MAX_BIT; j++){
				bitSum[j] += bitSet[j] ? 1 : 0;
			}
		}

		boolean[] out = new boolean[MAX_BIT];
		for(int i=0; i<MAX_BIT; i++) {
			out[i] = bitSum[i] % 3 == 0 ? false : true;
		}

		// BitSet out = new BitSet();
		// for(int i=0; i<MAX_BIT; i++) {
		// 	out.set(i, bitSum[i] % 3 == 0 ? false : true);
		// }

		//return (int)out.toLongArray()[0];
		return (int)bitSetToNumber(out);
	}

	public int solution(int[] A) {
		int N = A.length;

		int[] bitSum = new int[MAX_BIT];
		Arrays.fill(bitSum, 0);

		for(int i=0; i<A.length; i++) {
			//convert number to bitset
			BitSet bs = BitSet.valueOf(new long[]{A[i]}); 

			for(int j=0; j<MAX_BIT; j++){
				bitSum[j] += bs.get(j) ? 1 : 0;
			}
		}

		BitSet out = new BitSet();
		for(int i=0; i<MAX_BIT; i++) {
			out.set(i, bitSum[i] % 3 == 0 ? false : true);
		}

		return (int)out.toLongArray()[0];
	}
	
	public int findSingleElement(int[] A) {
		int out = 0;

		int N = A.length;
		
		for(int i=0; i<32; i++){
			int sum = 0;
			int tmp = 1 << i;

			for(int j=0; j<N; j++) {
				if((tmp & A[j]) != 0) {
					sum++;
				}				
			}				

			if(sum % 3 != 0) {
				out = out | tmp;
			}
		}				

		return out;
	}

	public int solutionBit(int[] A){
		int N = A.length;

		int ones = 0; int twos = 0;

		for(int i=0; i<N; i++){
			int tmp = twos & A[i]; //those bit will become zero after the steps
			twos = (twos | (ones & A[i])) & (~tmp);
			ones = (ones ^ A[i]) & (~tmp);
		}

		return ones;
	}

	public static void main(String[] args){
		Scanner scanner = new Scanner(System.in);

		int T = scanner.nextInt();
		Solution solution = new Solution();		

		for(int x = 0; x < T; x++) {
			int N = scanner.nextInt();

			int[] A = new int[N];
			for(int i=0; i<N; i++) {
				A[i] = scanner.nextInt();
			}
			
			System.out.println(solution.solutionBit(A));
			System.out.println(solution.findSingleElement(A));
			//System.out.println(solution.solutionCustomBitSet(A));				
			//System.out.println(solution.solution(A));
		}		

		int[] A = new int[]{1, 1, Integer.MAX_VALUE, 1};
		//System.out.println(solution.solutionCustomBitSet(A));
	}
}