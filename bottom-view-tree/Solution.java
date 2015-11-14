import java.util.*;

class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

	TreeNode(int val){
		this.val = val;
	}
}

class NodeBV {
	int val;
	int distance;
	int levelIdx;

	NodeBV(int val, int distance, int levelIdx) {
		this.val = val;
		this.distance = distance;
		this.levelIdx = levelIdx;
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

	public int[] getBottomView(TreeNode root) {
		if(root == null) return new int[]{};

		Queue<TreeNode> nodeQueue = new LinkedList<TreeNode>();
		Queue<Integer> distanceQueue = new LinkedList<Integer>();

		nodeQueue.add(root);
		distanceQueue.add(0);

		int levelIdx = 0;

		List<NodeBV> list = new ArrayList<NodeBV>();

		while(!nodeQueue.isEmpty()) {
			TreeNode node = nodeQueue.poll();
			int distance = distanceQueue.poll();

			NodeBV tmp = new NodeBV(node.val, distance, levelIdx++);
			list.add(tmp);

			if(node.left != null) {
				nodeQueue.add(node.left);
				distanceQueue.add(distance - 1);
			}

			if(node.right != null) {
				nodeQueue.add(node.right);
				distanceQueue.add(distance + 1);
			}
		}

		Collections.sort(list, new Comparator<NodeBV>(){
			public int compare(NodeBV a, NodeBV b) {
				if(a.distance > b.distance)  return 1;
				else if(a.distance < b.distance) return -1;
				else {
					return a.levelIdx - b.levelIdx;
				}
			}
		});

		int N = list.size();

		int idx =0;

		List<Integer> out = new ArrayList<Integer>();

		while(idx < N) {
			NodeBV nodeBV = list.get(idx);
			int distance = nodeBV.distance;
			while(idx < N && list.get(idx).distance == distance) {
				idx++;
			}
			out.add(list.get(idx-1).val);
		}

		int[] outA = new int[out.size()];
		for(int i=0; i<out.size(); i++) {
			outA[i] = out.get(i);
		}

		return outA;
	}

	public int[] getBottomViewTreeMap(TreeNode root) {
		if(root == null) return new int[]{};

		Queue<TreeNode> nodeQueue = new LinkedList<TreeNode>();
		Queue<Integer> distanceQueue = new LinkedList<Integer>();

		nodeQueue.add(root);
		distanceQueue.add(0);

		Map<Integer, Integer> map = new TreeMap<Integer, Integer>();

		while(!nodeQueue.isEmpty()){
			TreeNode node = nodeQueue.poll();
			int distance = distanceQueue.poll();

			map.put(distance, node.val);

			if(node.left != null) {
				nodeQueue.add(node.left);
				distanceQueue.add(distance-1);
			}

			if(node.right != null) {
				nodeQueue.add(node.right);
				distanceQueue.add(distance+1);
			}
		}

		int[] out = new int[map.size()];
		int idx = 0;

		for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
			int key = entry.getKey();
			int value = entry.getValue();
			out[idx++] = value;
		}

		return out;
	}

	public int[] getBottomViewTreeMapSameLevel(TreeNode root) {
		if(root == null) return new int[]{};

		Queue<TreeNode> nodeQueue = new LinkedList<TreeNode>();
		Queue<Integer> distanceQueue = new LinkedList<Integer>();
		Queue<Integer> levelQueue = new LinkedList<Integer>();

		nodeQueue.add(root);
		distanceQueue.add(0);
		levelQueue.add(0);

		Map<Integer, ArrayList<Integer>> map = new TreeMap<Integer, ArrayList<Integer>>();
		Map<Integer, Integer> mapLevel = new TreeMap<Integer, Integer>();

		while(!nodeQueue.isEmpty()){
			TreeNode node = nodeQueue.poll();
			int distance = distanceQueue.poll();
			int level = levelQueue.poll();

			if(!map.containsKey(distance)) {
				ArrayList<Integer> tmpL = new ArrayList<Integer>();
				tmpL.add(node.val);
				map.put(distance, tmpL);
				mapLevel.put(distance, level);
			} else {
				int oldLevel = mapLevel.get(distance);
				ArrayList<Integer> tmpL = map.get(distance);

				if(level != oldLevel) {
					tmpL = new ArrayList<Integer>();
						
				} 
				
				tmpL.add(node.val);				
				map.put(distance, tmpL);
				mapLevel.put(distance, level);
			}			

			if(node.left != null) {
				nodeQueue.add(node.left);
				distanceQueue.add(distance-1);
				levelQueue.add(level+1);
			}

			if(node.right != null) {
				nodeQueue.add(node.right);
				distanceQueue.add(distance+1);
				levelQueue.add(level+1);
			}
		}

		List<Integer> arrList = new ArrayList<Integer>();

		for(Map.Entry<Integer, ArrayList<Integer>> entry : map.entrySet()) {
			int key = entry.getKey();
			List<Integer> value = entry.getValue();
			
			for(Integer a : value) {
				arrList.add(a);
			}
		}

		int[] out = new int[arrList.size()];		

		for(int i=0; i<arrList.size(); i++) {
			out[i] = arrList.get(i);
		}

		return out;
	}

	public static void main(String[] args){
		Scanner scanner = new Scanner(System.in);
		Solution solution = new Solution();
		
		TreeNode root = solution.buildTree(scanner);
			//solution.inOrder(root);
		System.out.println(Arrays.toString(solution.getBottomViewTreeMapSameLevel(root)));		

		String s = "a b";		
		char[] sc = s.toCharArray();
		System.out.println(sc[1] == '%');
	}
}