//http://www.geeksforgeeks.org/dynamic-programming-set-18-partition-problem/
import java.util.*;

class Pair {
	int x; 
	int y;
	Pair(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

class Solution {
	Map<Pair, Boolean> dict = new HashMap<Pair, Boolean>();

	public boolean canPartionSub(int[] A, int k, int sum) {
		if(k==0) return A[0] == sum;		

		//load from dictionary
		if(dict.containsKey(new Pair(k, sum))) return dict.get(new Pair(k, sum));

		boolean tmp = canPartionSub(A, k-1, sum) | canPartionSub(A, k-1, sum - A[k]);

		//keep in dictionary to avoid recalculation
		dict.put(new Pair(k-1, sum), tmp);

		return tmp;
	}

	//check if a set can be partion into 2 set of equal sum
	public boolean canPartion(int[] A){		
		int N = A.length;
		if(N==0) return false;

		int sum = 0;

		for(int i=0; i<N; i++){
			sum += A[i];
		}

		if(sum % 2 == 1) return false;

		int nSum = sum / 2;

		return canPartionSub(A, N-1, nSum);
	}

	public static void main(String[] args){
		Scanner scanner = new Scanner(System.in);

		int T = scanner.nextInt();

		for(int x=0; x<T; x++) {
			int N = scanner.nextInt();

			int[] A = new int[N];
			for(int i=0; i<N; i++) {
				A[i] = scanner.nextInt();
			}

			Solution solution = new Solution();
			System.out.println(solution.canPartion(A));
		}
	}
}