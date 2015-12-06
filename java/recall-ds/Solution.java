import java.util.*;

class ListNode {
	int val;
	ListNode next;

	ListNode(int val, ListNode next){
		this.val = val;
		this.next = next;
	}
}

class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

	TreeNode(int val, TreeNode left, TreeNode right) {
		this.val = val;
		this.left = left;
		this.right = right;
	}
}

public class Solution {
	static final int DIM = 10;

	public static void main(String[] args){
		//scanner IO
		Scanner scanner = new Scanner(System.in);
		scanner.close();

		//sort using comparator
		int[][] A = new int[DIM][2];
		Arrays.sort(A, new Comparator<int[]>(){
			public int compare(int[] x, int[] y){
				return x[1] - y[1];
			}
		});

		//stack 
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(1); stack.peek(); stack.pop(); stack.isEmpty(); 

		//queue
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(1); queue.peek(); queue.poll(); queue.isEmpty();

		//heap or priority queue
		Queue<Integer> pQueue = new PriorityQueue<Integer>(new Comparator<Integer>(){
			public int compare(Integer a, Integer b) {
				return a - b;
			}
		});

		for(int i=0; i<10; i++) {
			pQueue.add(1);
		}

		if(pQueue.peek() != 1) {
			System.out.println("Not a min heap");
		}

		//hash map
		Map<Integer, Integer> hMap = new HashMap<Integer, Integer>();
		hMap.put(1, 10);

		//check exist
		if(!hMap.containsKey(1)){
			System.out.println("not contain key");
		}

		//iterate through map
		for(Map.Entry<Integer, Integer> entry : hMap.entrySet()){
			int key = entry.getKey();
			int val = entry.getValue();
		}

		for(Integer key : hMap.keySet()){

		}

		//hset
		HashSet<Integer> hSet = new HashSet<Integer>();
		hSet.add(1); 
		hSet.contains(1);

		for(Integer e : hSet) {
			if(!hSet.contains(e)) {	
				System.out.println("invalid hset");
			}
		}

		//list
		List<Integer> list = new ArrayList<Integer>();
		for(int i=0; i<DIM; i++) {
			list.add(i);
		}

		for(int i=0; i<DIM; i++) {
			if(list.get(i) != i) {
				System.out.println("invalid list");
			}
		}

		Collections.sort(list, new Comparator<Integer>(){
			public int compare(Integer a, Integer b) {
				return b - a;
			}
		});

		for(int i=0; i<DIM; i++) {
			if(list.get(i) != DIM-1-i) {
				System.out.println("invalid");
			}
		}

		String s = "test";
		String t = "tmp";

		if(s.compareTo(t) == 0) {
			System.out.println("invalid ops");
		}

		//Red-Black Tree
		TreeSet<Integer> treeSet = new TreeSet<Integer>();
		for(int i=0; i<=10; i++) {
			treeSet.add(i);
		}

		int n1 = treeSet.first(); //0
		int n2 = treeSet.last(); //10
		int n3 = treeSet.floor(11); //10 >=
		int n4 = treeSet.ceiling(-1); //0 <=
		int n5 = treeSet.lower(5); //4
		int n6 = treeSet.higher(4); //5

		//travel
		int idx = 0;
		for(int elem: treeSet) {
			if(idx != elem) {
				System.out.println("invalid treeset traversal");
			}
			++idx;
		}

		if(n1 != 0 || n2 != 10 || n3 != 10 || n4 != 0 || n5 != 4 || n6 !=5){
			System.out.println("invalid tree set:" + n1 + ":" + n2 + ":" + n3 + ":" + n4 + ":" + n5 + ":" + n6);
		}

		TreeMap<Integer, Integer> treeMap = new TreeMap<Integer, Integer>();
		for(int i=10; i>=0; i--) {
			treeMap.put(i, i+1);
		}

		idx = 0;
		for(Map.Entry<Integer, Integer> entry : treeMap.entrySet()) {
			int key = entry.getKey();

			if(key != idx) {
				System.out.println("invalid entry");
			}

			++idx;
		}

		Map.Entry<Integer, Integer> tmpE = treeMap.ceilingEntry(4); //4
		//System.out.println(tmpE.getKey() + ":" + tmpE.getValue()); //4:5

		treeMap.containsKey(4);
		if(!treeMap.containsValue(11)){
			System.out.println("invalid tree");
		}

		BitSet bs = BitSet.valueOf(new long[]{4});
		if(bs.length() != 3 && bs.size() != 64){
			System.out.println("invalid bs length");
		}
		
	}
}