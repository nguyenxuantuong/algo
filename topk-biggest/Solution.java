import java.util.*;

public class Solution {
	public int[] findKMax(int[] A, int K) {
		int N = A.length;

		//min priority queue
		Queue<Integer> queue = new PriorityQueue<Integer>(new Comparator<Integer>(){
			public int compare(Integer a, Integer b) {
				return a - b;
			}
		});

		if(K >= N) {
			Arrays.sort(A);
			return A;
		}

		for(int i=0; i<K; i++) {
			queue.add(A[i]);
		}

		for(int i=K; i<N; i++) {
			int top = queue.peek(); //min element

			if(A[i] <= top) continue; //it's not big enough

			queue.poll(); //remove smallest
			queue.add(A[i]); //add new into the list
		}

		int[] out = new int[K];
		int idx = 0;

		while(!queue.isEmpty()){
			out[idx++] = queue.poll();
		}

		return out;
	}

	public static void main(String[] args){
		Solution solution = new Solution();

		Scanner scanner = new Scanner(System.in);
		int T = scanner.nextInt();

		for(int i=0; i<T; i++) {
			int N = scanner.nextInt();
			int K = scanner.nextInt();

			int[] A = new int[N];
			for(int j=0; j<N; j++) {
				A[j] = scanner.nextInt();
			}

			int[] out = solution.findKMax(A, K);

			System.out.println(Arrays.toString(out));
		}		
	}
}