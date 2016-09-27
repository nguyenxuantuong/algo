//This contain the original algorithm as MIT courses
import java.util.*;

class TreeNode {
	public static enum COLOR {RED, BLACK};

	int val;
	TreeNode left;
	TreeNode right;
	TreeNode parent;
	public COLOR color;

	TreeNode(int val) {
		this.val = val;
	}

	TreeNode(int val, COLOR color) {
		this.val = val; 
		this.color = color;
	}

	public boolean isRed() {
		return this.color == COLOR.RED;
	}
}

public class RedBlack {
	public int nTwists = 0;

	void inOrder(StringBuffer sb, TreeNode node) {
		if(node == null) return;		
		sb.append(node.val + ":" + (node.color == TreeNode.COLOR.RED ? "RED" : "BLACK") + ",");
		inOrder(sb, node.left);		
		inOrder(sb, node.right);
	}		

	public void recoloringNode(TreeNode node) {
		node.color = TreeNode.COLOR.RED;
		node.right.color = node.left.color = TreeNode.COLOR.BLACK;
	}

		//NOTE: this kind of rotation is different from AVL rotate in the sense that 
	// you will need to keep track of parent of node; and then assign parent.left = node | parent.right = node
	// also; because there are both pointer to parent; and to child; remember to update both of them; 
	public TreeNode rRotate(TreeNode node) {
		TreeNode nP = node.parent;
		boolean isLeft = nP != null && nP.left == node;

		TreeNode nL = node.left;
		node.left = nL.right;

		//NOTE: don't forget to assign parent of nL.right here -- quite tricky
		if(nL.right != null) nL.right.parent = node;

		nL.right = node;
		nL.parent = node.parent;
		node.parent = nL;

		if(isLeft) nP.left = nL; 
		else if(nP != null) nP.right = nL;

		return nL;
	}

	public TreeNode lRotate(TreeNode node) {
		TreeNode nP = node.parent;
		boolean isLeft = nP != null && nP.left == node;

		TreeNode nR = node.right;
		node.right = nR.left;
		if(nR.left != null) nR.left.parent = node;

		nR.left = node;
		nR.parent = node.parent;
		node.parent = nR;

		if(isLeft) nP.left = nR; 
		else if(nP != null) nP.right = nR;
		
		return nR;
	}

	public TreeNode insert(TreeNode node, int key) {
		if(node == null) {
			return new TreeNode(key, TreeNode.COLOR.RED);
		}

		//insert 
		TreeNode curNode = node;

		//newly inserted node
		TreeNode newNode = null;

		while(true) {
			if(curNode.val > key) {
				if(curNode.left == null) {
					newNode = new TreeNode(key, TreeNode.COLOR.RED);
					newNode.parent = curNode;
					curNode.left = newNode;
					break;
				} else curNode = curNode.left;
			} else {
				if(curNode.right == null) {
					newNode = new TreeNode(key, TreeNode.COLOR.RED);
					newNode.parent = curNode;
					curNode.right = newNode;
					break;
				} else curNode = curNode.right;
			}
		}

		TreeNode x = newNode;		

		while(x.parent != null && x.isRed() && x.parent.isRed()) {
			TreeNode gParent = x.parent.parent;

			//on right branches
			if(x.parent == gParent.right) {
				if(gParent.left != null && gParent.left.isRed()) {
					recoloringNode(gParent);
					x = gParent;
				} else if(x == x.parent.right) {
					gParent = lRotate(gParent);		
					gParent.right.color = gParent.left.color = TreeNode.COLOR.RED;
					gParent.color = TreeNode.COLOR.BLACK;
					x = gParent;
					++nTwists;					
				} else {
					rRotate(x.parent);
					gParent = lRotate(gParent);
					gParent.right.color = gParent.left.color = TreeNode.COLOR.RED;
					gParent.color = TreeNode.COLOR.BLACK;
					x = gParent;
					nTwists += 1;
				}
			} else {
				if(gParent.right != null && gParent.right.isRed()) {
					recoloringNode(gParent);
					x = gParent;
				} else if(x == x.parent.left) {
					gParent = rRotate(gParent);
					gParent.right.color = gParent.left.color = TreeNode.COLOR.RED;
					gParent.color = TreeNode.COLOR.BLACK;
					x = gParent;
					++nTwists;		
				} else {
					lRotate(x.parent);
					gParent = rRotate(gParent);					
					gParent.right.color = gParent.left.color = TreeNode.COLOR.RED;
					gParent.color = TreeNode.COLOR.BLACK;
					x = gParent;
					nTwists += 1;
				}
			}
		}

		if(x.parent == null) return x; 
		else return node;
	}

	public int numTwists(int[] keys) {
		if(keys.length == 0) return 0;
		TreeNode root = null;

		for(int i=0; i<keys.length; i++) {
			root = insert(root, keys[i]);			
			root.color = TreeNode.COLOR.BLACK;
			StringBuffer sb = new StringBuffer();
			inOrder(sb, root);
			System.out.println(sb.toString());
		}

		return nTwists;
	}

	public static void main(String[] args){
		//14,3,4,11,2,1,12,6,9,7,13,8 
		int[] A = new int[]{ 5,10,15,14, 3, 4, 11, 2, 1, 12, 6, 9, 7, 13, 8 };
		RedBlack solution = new RedBlack();
		System.out.println(solution.numTwists(A));
	}
}