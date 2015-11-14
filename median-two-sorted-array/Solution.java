import java.util.*;

public class Solution {
	public double findMedianArray(int[] A) {
		int N = A.length;
		if(N == 0) return 0;

		if(N%2==1) {
			return A[N/2];
		} else {
			int idx1 = (N-2)/2;
			int idx2 = N/2;

			return ((double)A[idx1] + A[idx2]) / 2.0;
		}
	}

	public double findMedianSortedArrays(int[] A, int[] B) {
    	int N = A.length, M = B.length;

    	if(N==0 && M==0) return 0;
    	else if(N == 0) return findMedianArray(B);
    	else if(M == 0) return findMedianArray(A);

    	int L = N + M;
    	if(L==0) return 0;

    	if(L%2 == 1) {
    		return findKLargestBothArray(A, B, L/2);
    	} else {
    		int idx1 = (L-2)/2;
    		int idx2 = L/2;

    		int n1 = findKLargestBothArray(A, B, idx1);
    		int n2 = findKLargestBothArray(A, B, idx2);
    		
    		return ((double)n1 + n2) / 2;
    	}
    }

    public int findKLargestBothArray(int[] A, int[] B, int k) {
    	int N = A.length, M = B.length;

    	if(k >= 0 && k <= N+M-1){
    		int n1 = findKLargest(A, B, k);
	    	int n2 = findKLargest(B, A, k);

	    	return n1 != -1 ? n1 : n2;
    	} 

    	return -1;
    }

    public int findKLargest(int[] A, int[] B, int k) {
    	int N = A.length;
    	int M = B.length;

    	int s = 0, e = N-1;

    	while(s <= e) {    
    		int mid = (s+e)/2;    		
    		int n2 = k - mid; //A[mid] is larger than n2 number in B

    		if(n2 == 0) {
    			if(A[mid] <= B[0]) return A[mid];
    			else {
    				e = mid - 1;
    			}
    		} else if(n2 == M) {
    			if(A[mid] >= B[M-1]) return A[mid]; 
    			else {
    				s = mid + 1;
    			}
    		} else if(n2 < 0) {
    			e = mid - 1;
    		} else if(n2 > M) {
    			s = mid + 1;
    		} else {
    			if(A[mid] >= B[n2-1] && A[mid] <= B[n2]) {
    				return A[mid];
    			} else if(A[mid] < B[n2-1]) {
    				s = mid + 1;
    			} else {
    				e = mid - 1;
    			}
    		}    					    				
    	}

    	return -1;
    }

	public static void main(String[] args){
		Scanner scanner = new Scanner(System.in);

		int N = scanner.nextInt();
		int M = scanner.nextInt();

		int[] A = new int[N];
		int[] B = new int[M];

		for(int i=0; i<N; i++) {
			A[i] = scanner.nextInt();
		}

		for(int i=0; i<M; i++) {
			B[i] = scanner.nextInt();
		}

		Solution solution = new Solution();
		System.out.println(solution.findMedianSortedArrays(A, B));		
	}
}