import java.util.*;

class Position {
	char c;
	int idx;

	Position(char c, int idx) {
		this.c = c;
		this.idx = idx;
	}
}

public class Solution {
	public int longestValidParentheses(String s) {
    	char[] sc = s.toCharArray();

    	Stack<Position> stack = new Stack<Position>();

    	int N = sc.length;
    	int[] maxP = new int[N];

    	if(N == 0) return 0;
    	
    	for(int i=0; i<sc.length; i++) {
    		char c = sc[i];

    		if(c == '(') {
    			stack.push(new Position(c, i));
    			maxP[i] = 0;
    		} else {
    			if(stack.isEmpty()) {
    				maxP[i] = 0;
    			} else {
    				Position top = stack.pop();
    				int idx = top.idx;
    				int L = i - idx + 1;
    				maxP[i] = L + (idx >= 1 ? maxP[idx-1] : 0);
    			}
    		}
    	}

    	int out = Integer.MIN_VALUE;

    	for(int i=0; i<N; i++) {
    		out = Math.max(out, maxP[i]);
    	}

    	return out;
    }

    public static void main(String[] args){
    	Solution solution = new Solution();

    	String s = new String("");
		System.out.println(solution.longestValidParentheses(s));
    }
}