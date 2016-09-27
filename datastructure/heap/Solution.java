//https://www.hackerrank.com/challenges/qheap1
import java.util.*;


public class Solution {
	public static final int MAXQ = 100000;

    static int[] A = new int[MAXQ];
    int end = 0;
    
    void swap(int[] A, int x, int y) {
    	int tmp = A[x];
    	A[x] = A[y];
    	A[y] = tmp;
    }    

    void heapify(int i) {
    	while(i > 0) {
			int p = (i - 1) / 2;
			if(A[p] > A[i]) {
				swap(A, p, i);
				i = p;
			} else {
				break;
			}
		}
    }

    void downheap(int i) {
    	while(true) {
    		int lc = 2*i+1;
    		int rc = 2*i+2;
    		if(lc >= end && rc >= end) break;
    		int idx = lc >= end ? rc : (rc >= end ? lc : (A[lc] > A[rc] ? rc : lc));
    		if(A[idx] < A[i]) {
    			swap(A, idx, i);
    			i = idx;
    		} else {
    			break;
    		}
    	}
    }

    void add(int x) {    	
		A[end] = x;
		heapify(end);
		end++;		
    }
    
    void remove(int x) {    	
    	if(end == 0) return;

		for(int i=0; i<end; i++) {
			if(A[i] == x) {
				swap(A, i, end-1);
				end--;
				heapify(i);				
				downheap(i);
				break;
			}
		}        
    }

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int Q = scanner.nextInt();
		
		Solution solution = new Solution();

		for(int i=0; i<Q; i++) {
			int t = scanner.nextInt();			
			switch(t) {
				case 1:
					solution.add(scanner.nextInt());
				break;
				case 2:
					solution.remove(scanner.nextInt());
				break;
				case 3: 
					System.out.println(A[0]);
				break;
			}
		}

		scanner.close();
	}
}