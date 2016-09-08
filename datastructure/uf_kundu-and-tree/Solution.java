//https://www.hackerrank.com/challenges/kundu-and-tree
//NOTE: common mistake is calculate mod of 2x: sum2 = (A[i]A[j] + A[j]A[i]) % MOD; and then final sum2 / 2;
//it is wrong because sum2 is not 2x anymore but (%MOD); you would need to reverse mod: FIND x such as 2*x === sum2 (mod MOD)
import java.util.*;

public class Solution {
	static int[] parent;
	static int[] count;
	static int[] rank;

	static long MOD = 1000000007L;

	static int FIND(int x) {
		if(x != parent[x])  {
			parent[x] = FIND(parent[x]);
		}

		return parent[x];
	}

	static void UNION(int x, int y) {
		int XX = FIND(x);
		int YY = FIND(y);

		if(XX == YY) return;

		if(rank[XX] > rank[YY]) {
			int tmp = XX;
			XX = YY;
			YY = tmp;
		}

		if(rank[XX] == rank[YY]) rank[YY]++;

		//merge
		parent[XX] = YY;
		count[YY] = count[YY] + count[XX];
	}

	static long sum1(int[] A) {
		long sum = 0;

		for(int i=0; i<A.length; i++) {
			sum += A[i];
		}

		return sum % MOD;		
	}

	static long sum2(int[] A) {
		int N = A.length;

		long sum1 = sum1(A);
		long sum = 0;

		for(int i=0; i<N; i++) {
			sum = (sum + A[i] * (sum1 - A[i])) % MOD;
		}

		if(sum % 2 == 0){
			return (sum / 2) % MOD;
		}
		
		return ((sum + MOD) / 2) % MOD;
	}

	static long sum3(int[] A) {
		int N = A.length;

		long sum1 = sum1(A), sum2 = sum2(A);
		long sum = 0;

		for(int i=0; i<N; i++) {
			long tmp = (sum2 - A[i] * (sum1 - A[i])) % MOD;
			sum = (sum + A[i] * tmp) % MOD;
		}		
		
		for(int i=0; i<3; i++) {
			long tmp = (sum + i*MOD);
			if (tmp % 3 == 0) {
				return tmp / 3;
			}
		}

		return -1;
	}

	public static void main(String[] args){
		Scanner scanner = new Scanner(System.in);

		Solution solution = new Solution();

		int N = scanner.nextInt();
		scanner.nextLine();

		parent = new int[N];
		count  = new int[N];
		rank = new int[N];

		for(int i=0; i<N; i++) {
			parent[i] = i;
			rank[i] = count[i] = 1;
		}

		for(int i=0; i<N-1; i++) {
			String line = scanner.nextLine();
			String[] tokens = line.split(" ");

			if(tokens.length == 3) {
				int x = Integer.parseInt(tokens[0]) - 1;
				int y = Integer.parseInt(tokens[1]) - 1;				
				String color = tokens[2];				
				if(color.equals("b")) {
					UNION(x, y);
				}
			}			
		}

		Set<Integer> set = new HashSet<Integer>();

		for(int i=0; i<N; i++) {
			set.add(FIND(i));
		}

		int[] a = new int[set.size()];
		int idx = 0;

		for(Integer s : set) {
			a[idx++] = count[FIND(s)];
		}		


		System.out.println(sum3(a));

		scanner.close();		
	}
}