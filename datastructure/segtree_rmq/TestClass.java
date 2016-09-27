//https://www.hackerearth.com/practice/data-structures/advanced-data-structures/segment-trees/tutorial/
//this is 4*N memory solution; 2*N solution can be found at: http://codeforces.com/blog/entry/18051
import java.util.*;

class TestClass {
    static int[] rA;    

    //build segment tree using bottom-up approaches
    public static void buildSegmentTree(int[] A, int start, int end, int idx) {
        int mid = (start + end) / 2;

        if(start == end) {            
            rA[idx] = A[start];
        } else {
            buildSegmentTree(A, start, mid, 2*idx+1);
            buildSegmentTree(A, mid+1, end, 2*idx+2);
            rA[idx] = Math.min(rA[2*idx+1], rA[2*idx+2]);
        }        
    }

    public static int queryMin(int[] A, int start, int end, int idx, int l, int r) {
        if(l > end || r < start) return Integer.MAX_VALUE;
        if(l <= start && r >= end) return rA[idx];

        int mid = (start + end) / 2;

        if(start == end) {
            return rA[idx];
        } else {
            int tmpL = queryMin(A, start, mid, 2*idx+1, l, r);
            int tmpR = queryMin(A, mid+1, end, 2*idx+2, l, r);
            return Math.min(tmpL, tmpR);
        }
    }

    public static void update(int[] A, int start, int end, int idx, int x, int y) {
        if(start == end) {
            //NOTE: careful on this
            if(start == x) {
                A[x] = y;
                //Need to update rA array on corresponding index also
                rA[idx] = y;
            }
            return;
        }   

        int mid = (start + end) / 2;      

        if(x <= mid) {
            update(A, start, mid, 2*idx+1, x, y);                        
        } else {
            update(A, mid+1, end, 2*idx+2, x, y);
        }

        //update from bottom-up upwards
        rA[idx] = Math.min(rA[2*idx+1], rA[2*idx+2]);
    }

    public static void main(String args[] ) throws Exception {     
        Scanner scanner = new Scanner(System.in);        
        
        int N = scanner.nextInt();
        int Q = scanner.nextInt();

        int[] A = new int[N];

        for(int i=0; i<A.length; i++) {
            A[i] = scanner.nextInt();
        }

        //NOTE: it requires 4*N not 2*N
        rA = new int[4*N];
        buildSegmentTree(A, 0, N-1, 0);        

        scanner.nextLine();

        for(int i=0; i<Q; i++) {
            String line = scanner.nextLine();
            String[] tokens = line.split(" ");
            
            if(tokens.length != 3 || tokens[0].length() != 1) continue;

            char c = tokens[0].charAt(0);

            if(c == 'q') {
                int l = Integer.parseInt(tokens[1]) - 1;
                int r = Integer.parseInt(tokens[2]) - 1;                
                System.out.println(queryMin(A, 0, N-1, 0, l, r));
            } else if(c == 'u') {
                int x = Integer.parseInt(tokens[1]) - 1;
                int y = Integer.parseInt(tokens[2]);
                update(A, 0, N-1, 0, x, y);
            }
        }

        scanner.close();                    
    }
}
