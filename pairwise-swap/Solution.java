//http://www.geeksforgeeks.org/pairwise-swap-elements-of-a-given-linked-list-by-changing-links/
import java.util.*;

class ListNode {
	int val;
	ListNode next;

	ListNode(int val){
		this.val = val;
	}
}

public class Solution {
	public ListNode buildList(String line) {
		String[] tokens = line.split(" ");
		ListNode head = null;
		ListNode cur = null;

		for(int i=0; i<tokens.length; i++) {
			ListNode tmp = new ListNode(Integer.parseInt(tokens[i]));
			if(cur == null) {
				cur = tmp;
				head = cur;
			}
			else {
				cur.next = tmp;
				cur = cur.next;
			}
		} 

		return head;
	}

	public void printList(ListNode head){
		while(head != null){
			if(head.next != null) {
				System.out.print(head.val + "->");	
			} else {
				System.out.print(head.val);
			}
			
			head = head.next;
		}

		System.out.println();
	}

	public ListNode pairwiseSwap(ListNode head){
		if(head == null || head.next == null) return head;

		ListNode newHead = new ListNode(0);
		newHead.next = head;

		ListNode pre = newHead, slow = pre.next, fast = pre.next.next;
		while(pre.next != null && pre.next.next != null) {
			ListNode tmp = fast.next;
			pre.next = fast;
			fast.next = slow;
			slow.next = tmp;

			//move forward
			pre = pre.next.next;
			if(pre.next != null) slow = pre.next;
			if(pre.next != null && pre.next.next != null) fast = pre.next.next;
		}

		return newHead.next;
	}

	public static void main(String[] args){
		Solution solution = new Solution();

		Scanner scanner = new Scanner(System.in);
		
		ListNode head = solution.buildList(scanner.nextLine());
		
		solution.printList(solution.pairwiseSwap(head));

		scanner.close();
	}
}