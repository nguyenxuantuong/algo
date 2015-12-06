//http://www.geeksforgeeks.org/rearrange-a-given-linked-list-in-place/

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

	public ListNode reverseList(ListNode head){
		if(head == null || head.next == null) return head;
		ListNode fast = head.next, slow = head;
		slow.next = null;

		while(fast != null){
			ListNode tmp = fast.next;
			fast.next = slow;
			slow = fast;
			fast = tmp;
		}

		return slow;
	}

	public ListNode reArrangeList(ListNode head){
		if(head == null || head.next == null || head.next.next == null) return head;
		ListNode fast = head;
		ListNode slow = head;
		
		while(fast.next != null && fast.next.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}

		ListNode head2 = slow.next; 
		slow.next = null;

		ListNode head3 = head;
		ListNode head4 = reverseList(head2);

		ListNode outHead = new ListNode(0);
		ListNode cur = outHead;

		// int idx = 0;
		// while(head3 != null || head4 != null){
		// 	if(head3 == null){
		// 		cur.next = head4;
		// 		head4 = head4.next;
		// 		cur = cur.next;
		// 	} else if(head4 == null) {
		// 		cur.next = head3;
		// 		head3 = head3.next;
		// 		cur = cur.next;
		// 	} else {
		// 		if(idx % 2 == 0) {
		// 			cur.next = head3;
		// 			head3 = head3.next;
		// 			cur = cur.next;
		// 		} else {
		// 			cur.next = head4;
		// 			head4 = head4.next;
		// 			cur = cur.next;
		// 		}	

		// 		idx++;
		// 	}
		// }

		while(head3 != null || head4 != null){
			if(head3 != null) {
				cur.next = head3;			
				head3 = head3.next;
				cur = cur.next;
			}

			if(head4 != null) {
				cur.next = head4;
				head4 = head4.next;
				cur = cur.next;
			}
		}

		return outHead.next;
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

	public static void main(String[] args){
		Solution solution = new Solution();

		Scanner scanner = new Scanner(System.in);
		
		ListNode head = solution.buildList(scanner.nextLine());
		
		solution.printList(solution.reArrangeList(head));

		scanner.close();
	}
}