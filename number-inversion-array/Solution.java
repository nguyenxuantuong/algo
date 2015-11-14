// https://codility.com/programmers/task/array_inversion_count
import java.util.*;

class TmpResult {
	int nInversion;
	int[] sortedArr;

	TmpResult(int nInversion, int[] sortedArr) {
		this.nInversion = nInversion;
		this.sortedArr = sortedArr;
	}
}

class Solution {
	public static final int MAX_NUM = 1000000000;

	public int[] mergeArray(int[] A, int[] B){
		int N = A.length;
		int M = B.length;

		int idx1 = 0, idx2 = 0;
		int[] out = new int[N + M];
		int idx = 0;

		while(idx1 < N || idx2 < M) {
			if(idx1 == N) {
				out[idx++] =B[idx2++];
			} else if(idx2 == M) {
				out[idx++] = A[idx1++];
			} else if(A[idx1] < B[idx2]){
				out[idx++] = A[idx1++];
			} else {
				out[idx++] = B[idx2++];
			}
		}

		return out;
	}

	public TmpResult getNumberOfInversion(int[] A, int s, int e) {
		int N = A.length;

		int mid = (s+e)/2;

		if(s == e) return new TmpResult(0, new int[]{A[s]});

		TmpResult result1 = getNumberOfInversion(A, s, mid);
		TmpResult result2 = getNumberOfInversion(A, mid+1, e);

		int n1 = result1.nInversion;
		int n2 = result2.nInversion;

		int[] arr1 = result1.sortedArr;
		int[] arr2 = result2.sortedArr;
		
		//find inversion btw arr1, arr2		
		int idx = 0;
		long count = 0;

		for(int i=0; i<arr1.length; i++) {
			while(idx < arr2.length && arr1[i] > arr2[idx]){
				++idx;
			}

			count += idx;

			if(count > MAX_NUM) {
				break;
			}
		}

		long nInversion = 0;
		
		if(n1 == -1 || n2 == -1) {
			nInversion = -1;
		} else {
			nInversion = (long)n1 + n2 + count;
		}

		nInversion = nInversion > MAX_NUM ? -1 : nInversion;

		int[] arr = mergeArray(arr1, arr2);		
		
		return new TmpResult((int)nInversion, arr);		
	}

	public int solution(int[] A) {
		if(A.length==0 || A.length==1) return 0;
		return getNumberOfInversion(A, 0, A.length-1).nInversion;
	}

	public static void main(String[] args){
		Scanner scanner = new Scanner(System.in);

		int N = scanner.nextInt();

		int[] A = new int[N];
		for(int i=0; i<N; i++) {
			A[i] = scanner.nextInt();
		}

		Solution solution = new Solution();
		System.out.println(solution.solution(A));
	}
}