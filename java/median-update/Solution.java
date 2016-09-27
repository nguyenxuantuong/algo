import java.util.*;

class MyTreeSet<T> extends TreeSet<T> {
	Map<T, Integer> duplicateMap = new HashMap<T, Integer>();
	private int curSize  = 0;

	@Override
	public boolean add(T num) {
		Integer count = duplicateMap.get(num);

		if(count != null) {
			duplicateMap.put(num, count+1);
		} else {			
			duplicateMap.put(num, 1);
			//only add element if not exist
			super.add(num);
		}

		++curSize;
		return true;
	}

	@Override 
	public boolean remove(Object o) {
		T num = (T)o;
		Integer count = duplicateMap.get(num);
		
		//not exist
		if(count == null) {
			return false;
		}

		if(count == 1) {
			duplicateMap.remove(num);
			//only remove if elem no longer exist
			super.remove(num);
		} else {
			duplicateMap.put(num, count - 1);
		}

		--curSize;		
		return true;
	}

	@Override 
	public int size() {
		return curSize;
	}
}

public class Solution {
	static Integer root = null;

	static MyTreeSet<Integer> leftTree = new MyTreeSet<Integer>();
	static MyTreeSet<Integer> rightTree = new MyTreeSet<Integer>();

	static int curSize() {
		return (root == null ? 0 : 1) + leftTree.size() + rightTree.size();
	}

	static void rotateLeft() {
		int rLowest = rightTree.first();
		//remove rLowest
		rightTree.remove(rLowest);
		leftTree.add(root);
		root = rLowest;
	}

	static void rotateRight() {
		int lBiggest = leftTree.last();
		//remove lBiggest
		leftTree.remove(lBiggest);
		rightTree.add(root);
		root = lBiggest;
	}

	static Double rebalanceTree() {
		int nLeft = leftTree.size(), nRight = rightTree.size();

		if(nLeft < nRight) {
 			while(nLeft < nRight) {
 				rotateLeft();
 				nLeft = leftTree.size(); 
 				nRight = rightTree.size();
 			}
		} else if(nLeft > nRight + 1) {
			while(nLeft > nRight + 1) {
				rotateRight();
				nLeft = leftTree.size();
				nRight = rightTree.size();
			}
		}

		int nNumber = curSize();

		if(nNumber % 2 == 1) {
			return new Double(root);
		} else {
			int tmp1 = root;
			int tmp2 = leftTree.last();
			return new Double(((long)tmp1 + tmp2) / 2.0);
		}
	}

	static Double addNumber(int num) {
		int nNumbers = curSize();
		
		if(nNumbers == 0) {
			root = num;
			return new Double(num);
		}
		
		if(num > root) {
			rightTree.add(num);
		} else {
			leftTree.add(num);
		}

		//after remove; doing rebalance step
		return rebalanceTree();
	}	


	static Double removeNumber(int num) { 
		int nNumbers = curSize();
		
		if(nNumbers == 0) {			
			return null;
		}

		if(nNumbers == 1) {
			if(num == root) root = null;
			return null;
		}

		if(num < root) {
			if(!leftTree.contains(num)) return null;			
			leftTree.remove(num);
		} else if(num > root) {
			if(!rightTree.contains(num)) return null;
			rightTree.remove(num);
		} else {
			int lBiggest = leftTree.last();
			leftTree.remove(lBiggest);
			root = lBiggest;
		}

		//after remove; doing rebalance step
		return rebalanceTree();
	}

	public static String formatDouble(double d) {
	    if(d == (long) d)
	        return String.format("%d",(long)d);
	    else
	        return String.format("%.1f",d);
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int N = scanner.nextInt();
		scanner.nextLine();

		for(int i=0; i<N; i++) {			
			String line = scanner.nextLine().trim();
			String[] toks = line.split(" ");
			
			if(toks.length == 2) {
				String token1 = toks[0];
				int num = Integer.parseInt(toks[1]);

				Double median = null;

				if(token1.equals("a")) {
					median = addNumber(num);
				} else if(token1.equals("r")) {
					median = removeNumber(num);
				}			

				if(median == null) {
					System.out.println("Wrong!");
				} else {
					//TODO: format the output
					System.out.println(formatDouble(median));
				}
			}			
		}

		scanner.close();
	}
}