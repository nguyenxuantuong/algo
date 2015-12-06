import java.util.*;

public class Solution {	
	static List<List<Integer>> list;
	static int[] sumE;
	static int[] A;

	static boolean[] visisted;

	public int dfs(int node) {
		List<Integer> connectedNodes = list.get(node);

		int sum = 0;
		visisted[node] = true; //mark first

		for(Integer cNode: connectedNodes) {
			if(!visisted[cNode]) {
				sum += dfs(cNode); 
			}			 
		}

		sum += A[node]; //add current node
		sumE[node] = sum; //sum of all node in subtree
		return sum;
	}

	public static void main(String[] args){
		Scanner scanner = new Scanner(System.in);

		int N = scanner.nextInt();
		visisted = new boolean[N];
		Arrays.fill(visisted, false);

		A = new int[N];
		for(int i=0; i<N; i++){
			A[i] = scanner.nextInt();
		}

		list = new ArrayList<List<Integer>>();
		for(int i=0; i<N; i++) {
			list.add(new ArrayList<Integer>());
		}

		for(int i=0; i<N-1; i++) {
			int x = scanner.nextInt()-1;
			int y = scanner.nextInt()-1;
			list.get(x).add(y);
			list.get(y).add(x);
		}	

		Solution solution = new Solution();
		sumE = new int[N];

		Arrays.fill(sumE, 0);

		solution.dfs(0);

		int totalSum = sumE[0];

		int out = Integer.MAX_VALUE;

		for(int i=1; i<N; i++) {
			int n1 = totalSum - sumE[i];
			int n2 = sumE[i];
			out = Math.min(out, Math.abs(n1-n2));
		}

		System.out.println(out);
	}
}