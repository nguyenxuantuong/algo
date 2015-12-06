import java.util.*;

class ListNode {
	int val;
	ListNode next;

	ListNode(int val){
		this.val = val;
	}
}

public class Solution {
	ListNode fastForward(ListNode node, int K) {
		int idx = 0;

		while(node != null && idx < K) {
			node = node.next;
			++idx;
		}

		return node;
	}

	List<ListNode> reverseListKNode(ListNode node, int K, boolean reverseFist) {		
		ListNode head = node; 
		ListNode tail = fastForward(head, K-1);

		//end list
		if(tail != null) tail.next = null;

		if(!reverseFist) {
			List<ListNode> tempL = new ArrayList<ListNode>();
			tempL.add(head); 
			tempL.add(tail);
			return tempL;
		}

		ListNode slow = node, fast = node.next;
		node.next = null;

		while(fast != null){
			ListNode fastNext = fast.next;
			fast.next = slow;
			slow = fast;
			fast = fastNext;
		}

		List<ListNode> tmpL = new ArrayList<ListNode>();
		tmpL.add(slow); 
		tmpL.add(node);

		return tmpL;
	}

	ListNode reverseList(ListNode node, int K, boolean reverseFist) {
		if(node == null) return null;

		//reverse k element and return new head
		ListNode nextNode = fastForward(node, K);
		ListNode nextHead = reverseList(nextNode, K, !reverseFist);

		List<ListNode> pairList = reverseListKNode(node, K, reverseFist);
		ListNode head = pairList.get(0);
		ListNode tail = pairList.get(1);

		if(tail != null) tail.next = nextHead;
		return head;
	}

	public static void main(String[] args){
		Solution solution = new Solution();

		Scanner scanner = new Scanner(System.in);
		int N = scanner.nextInt();
		int K = scanner.nextInt();

		ListNode head = null, cur = null;

		for(int i=0; i<N; i++) {
			if(i==0) {
				head = cur = new ListNode(scanner.nextInt());
			} else {
				cur.next = new ListNode(scanner.nextInt());
				cur = cur.next;
			}
		}

		ListNode node = solution.reverseList(head, K, true);

		while(node != null) {
			System.out.print(node.val + " ");
			node = node.next;
		}

		System.out.println();
	}
}