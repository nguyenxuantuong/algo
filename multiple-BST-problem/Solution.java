import java.util.*;

class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

	TreeNode(int val){
		this.val = val;
	}
}

class TmpResult {
	int min;
	int max;
	boolean isBST;

	TmpResult(int min, int max, boolean isBST){
		this.min = min;
		this.max = max;
		this.isBST = isBST;
	}
}


public class Solution {	
	TreeNode readNext(Scanner scanner) {
		if(!scanner.hasNext()) return null;

		String tok = scanner.next();		

		if(!tok.equals("null")) {
			return new TreeNode(Integer.parseInt(tok));
		}

		return null;
	}

	TreeNode buildTree(Scanner scanner){
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		TreeNode root = readNext(scanner);

		if(root == null) return null;

		queue.add(root);
		while(!queue.isEmpty()){		
			TreeNode top = queue.poll();

			TreeNode left = readNext(scanner);
			TreeNode right = readNext(scanner);

			top.left = left; top.right = right;
			if(left != null) queue.add(left);
			if(right != null) queue.add(right);
		}		

		return root;
	}

	void inOrder(TreeNode node) {
		if(node == null) return;		
		inOrder(node.left);
		System.out.print(node.val + " ");
		inOrder(node.right);
	}	

	//http://www.geeksforgeeks.org/kth-largest-element-in-bst-using-o1-extra-space/
	static int nVisited = 0;
	static int kSmallest = -1;

	public void inOrderTravesal(TreeNode node, int k){	
		if(node == null || nVisited == k) return;

		if(node.left != null) inOrderTravesal(node.left, k);
		++nVisited;
		
		if(nVisited == k){
			kSmallest = node.val;
			return;
		}

		if(node.right != null) inOrderTravesal(node.right, k);
	}

	public void kSmallestElement(){
		Scanner scanner = new Scanner(System.in);		

		TreeNode root = buildTree(scanner);

		nVisited = 0;
		kSmallest = -1;
		inOrderTravesal(root, 5);

		System.out.println(kSmallest);
	}

	public TmpResult dfs(TreeNode node) {
		if(node.left == null && node.right == null) return new TmpResult(node.val, node.val, true);
		else if(node.left != null && node.right != null) {
			TmpResult tmpL = dfs(node.left);
			TmpResult tmpR = dfs(node.right);
			boolean isBST = node.val > tmpL.max && node.val < tmpR.min && tmpL.isBST && tmpR.isBST;
			return new TmpResult(tmpL.min, tmpR.max, isBST);
		} else if(node.left != null && node.right == null) {
			TmpResult tmpL = dfs(node.left);
			boolean isBST = node.val > tmpL.max && tmpL.isBST;
			return new TmpResult(tmpL.min, node.val, isBST);
		} else {
			TmpResult tmpR = dfs(node.right);
			boolean isBST = node.val < tmpR.min && tmpR.isBST;
			return new TmpResult(node.val, tmpR.max, isBST);
		}	
	}

	public boolean isValidBST(TreeNode root) {
        if(root == null) return true;
        else return dfs(root).isBST;
    }

    //short and concise
    public boolean isBST(TreeNode node, long min, long max) {
    	if(node == null) return true;
    	return node.val > min && node.val < max && isBST(node.left, min, Math.min(node.val, max)) && isBST(node.right, Math.max(node.val, min), max);
    }

    public boolean isValidBST2(TreeNode root) {
        return isBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    //in-order traversal and check if result is sorted
    public boolean isValidBST3(TreeNode root) {
    	if(root == null) return true;

    	Stack<TreeNode> stack = new Stack<TreeNode>();
    	TreeNode node = root;
    	while(node != null) {
    		stack.push(node);
    		node = node.left;
    	}

    	TreeNode lastVisited = null;

    	while(!stack.isEmpty()){
    		TreeNode top = stack.pop();

    		if(lastVisited != null && lastVisited.val >= top.val) return false;

    		lastVisited = top;

    		if(top.right != null) {
    			node = top.right;

    			while(node != null) {
    				stack.push(node);
    				node = node.left;
    			}
    		}
    	}

    	return true;
    }

	public static void main(String[] args){
		Solution solution = new Solution();
		solution.kSmallestElement();
	}
}