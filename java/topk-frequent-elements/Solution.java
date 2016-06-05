//https://leetcode.com/problems/top-k-frequent-elements/
import java.util.*;

class ItemFrequency {
	int key;
	int freq;	

	ItemFrequency(int key, int freq) {
		this.key = key;
		this.freq = freq;
	}
}

public class Solution {
	public List<Integer> findTopKFrequent(int[] A, int K) {
		int N = A.length;
		List<Integer> out = new ArrayList<Integer>(K);
		if(N == 0) return out;

		HashMap<Integer, Integer> hashMap = new HashMap<Integer, Integer>();
		for(int i=0; i<N; i++) {
			Integer freq = hashMap.get(A[i]);
			if(freq == null) {
				hashMap.put(A[i], 1);
			} else {
				hashMap.put(A[i], freq+1);
			}
		}

		Queue<ItemFrequency> queue = new PriorityQueue<ItemFrequency>(K, new Comparator<ItemFrequency>(){
			public int compare(ItemFrequency a, ItemFrequency b) {
				return b.freq - a.freq;
			}
		});

		for(Map.Entry<Integer, Integer> entry : hashMap.entrySet()) {
			queue.add(new ItemFrequency(entry.getKey(), entry.getValue()));
		}
		
		int idx = 0;
		while(idx < K) {
			out.add(queue.poll().key);
			++idx;
		}

		return out;
	}

	public static void main(String[] args){
		Scanner scanner = new Scanner(System.in);
		Solution solution = new Solution();

		int T = scanner.nextInt();		

		for(int i=0; i<T; i++) {
			int N = scanner.nextInt();		
			int K = scanner.nextInt();

			int[] A = new int[N];

			for(int j=0; j<N; j++) {
				A[j] = scanner.nextInt();				
			}

			System.out.println(solution.findTopKFrequent(A, K).toString());
		}		
	}
}