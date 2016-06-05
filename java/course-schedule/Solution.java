import java.util.*;

public class Solution {
	List<ArrayList<Integer>> graph;
	boolean[] marked;
	boolean[] done;
	Stack<Integer> stack = new Stack<Integer>();

	public boolean dfs(int i) {
		ArrayList<Integer> edges = graph.get(i);

		marked[i] = true;
		done[i] = false;

		for(int j=0; j<edges.size(); j++) {
			int v = edges.get(j);

			if(!marked[v]) {
				boolean tmp = dfs(v);
				if(!tmp) return tmp;
			} else if(!done[v]) {
				//contain backtrack edge
				return false;
			}
		}

		done[i] = true;
		stack.push(i);

		return true;
	}

	public int[] findOrder (int N, int[][] P) {
		graph = new ArrayList<ArrayList<Integer>>();
		marked = new boolean[N];
		done = new boolean[N];

		Arrays.fill(marked, false);
		Arrays.fill(done, false);

		for(int i=0; i<N; i++) {
			graph.add(new ArrayList<Integer>());
		}

		for(int i=0; i<P.length; i++) {
			int v1 = P[i][0];
			int v2 = P[i][1];
			graph.get(v2).add(v1);
		}

		for(int i=0; i<N; i++) {
			if(!marked[i]) {
				boolean tmp = dfs(i);
				if(!tmp) return new int[]{};
			}			
		}

		int[] out = new int[N];
		int idx = 0;
		while(!stack.isEmpty()) {
			out[idx++] = stack.pop();
		}
		return out;
	}

	public static void main(String[] args){
		Scanner scanner = new Scanner(System.in);
		int N = scanner.nextInt();
		int L = scanner.nextInt();

		int[][]P = new int[L][2];
		
		for(int i=0; i<L; i++) {
			P[i][0] = scanner.nextInt();
			P[i][1] = scanner.nextInt();
		}


		Solution solution = new Solution();

		System.out.println(Arrays.toString(solution.findOrder(N, P)));

		scanner.close();
	}
}