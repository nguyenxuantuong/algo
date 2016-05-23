//https://www.hackerrank.com/challenges/icecream-parlor
import java.util.*;
import java.io.*;

class Elem {
	int idx;
	int val;
	Elem(int idx, int val) {
		this.idx = idx;
		this.val = val;
	}
}

public class Solution {
	public void solution(int[] A, int M) {
		int N = A.length;

		Elem[] arr = new Elem[N];
		for(int i=0; i<N; i++)	 {
			arr[i] = new Elem(i, A[i]);
		}

		Arrays.sort(arr, new Comparator<Elem>(){
			public int compare(Elem a, Elem b) {
				return a.val - b.val;
			}
		});

		int s = 0, e = N-1;
		while(s < e) {
			int sum = arr[s].val + arr[e].val;
			if(sum < M) {
				s++;
			} else if(sum > M) {
				e--;
			} else {
				int minIdx = Math.min(arr[s].idx, arr[e].idx) + 1;
				int maxIdx = Math.max(arr[s].idx, arr[e].idx) + 1;
				System.out.println(minIdx + " " + maxIdx);
				return;
			}
		}
	}

	public static void main(String[] args){
		Scanner scanner = new Scanner(System.in);
		int T = scanner.nextInt();

		Solution solution = new Solution();

		for(int i=0; i<T; i++) {
			int M = scanner.nextInt();
			int N = scanner.nextInt();
			int[] A = new int[N];

			for(int j=0; j<N; j++) {
				A[j] = scanner.nextInt();
			}

			solution.solution(A, M);
		}

		scanner.close();
	}
}