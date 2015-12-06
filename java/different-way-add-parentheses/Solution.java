// https://leetcode.com/problems/different-ways-to-add-parentheses/
import java.util.*;

class TmpResult {
	List<Integer> list;	

	TmpResult(){
		list = new ArrayList<Integer>();
	}

	TmpResult(List<Integer> list){
		this.list = list;
	}
}

class Solution {	
	static TmpResult[][] dict;	
	static boolean[][] marked;

	public boolean singleNumber(char[] sc, int s, int e){
		for(int i=s; i<=e; i++) {
			if(sc[i] == '+' || sc[i] == '-' || sc[i] == '*') {
				return false;
			}
		}	
		return true;
	}

	public List<Integer> diffWaysToComputeSub(char[] sc, int s, int e) {
		if(marked[s][e]){
			return dict[s][e].list;
		}

		//operator
		List<Integer> outL = new ArrayList<Integer>();

		if(singleNumber(sc, s, e)) {			
			StringBuffer sb = new StringBuffer();
			for(int i=s; i<=e; i++){
				sb.append(sc[i]);
			}

			outL.add(Integer.parseInt(sb.toString()));			
		} else {
			for(int i=s; i<=e; i++) {
				if(sc[i] == '+' || sc[i] == '-' || sc[i] == '*') {
					List<Integer> list1 = diffWaysToComputeSub(sc, s, i-1);
					List<Integer> list2 = diffWaysToComputeSub(sc, i+1, e);				

					for(int x=0; x<list1.size(); x++){
						for(int y=0; y<list2.size(); y++) {
							int tmp1 = list1.get(x), tmp2 = list2.get(y);
							int tmp = 0;

							switch(sc[i]){
								case '+':
									tmp = tmp1 + tmp2;
									break;
								case '-':
									tmp = tmp1 - tmp2;
									break;
								case '*': 
									tmp = tmp1 * tmp2;
									break;
								default: 
									break;
							}

							outL.add(tmp);
						}
					}
				}
			}
		}					

		marked[s][e] = true;
		dict[s][e] = new TmpResult(outL);

		return outL;
	}

	public List<Integer> diffWaysToCompute(String input) {
        char[] sc = input.toCharArray();
        int N = sc.length;

        if(N == 0) return new ArrayList<Integer>();

        dict = new TmpResult[N][N];
        marked = new boolean[N][N];
        
        for(int i=0; i<N; i++){
        	Arrays.fill(marked[i], false);
        }

        List<Integer> tmpL = diffWaysToComputeSub(sc, 0, N-1);
        Collections.sort(tmpL);

        return tmpL;
    }

	public static void main(String[] args){
		Solution solution = new Solution();

		Scanner scanner = new Scanner(System.in);

		int T = scanner.nextInt();
		scanner.nextLine();

		for(int i=0; i<T; i++){			
			String input = scanner.nextLine();			

			List<Integer> list = solution.diffWaysToCompute(input);			

			for(int j=0; j<list.size(); j++) {
				if(j==list.size()-1) {
					System.out.print(list.get(j));
				} else {
					System.out.print(list.get(j) + ",");
				}			
			}

			System.out.println();
		}		
	}
}