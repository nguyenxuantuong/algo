import java.util.*;

class Point {
	int x; 
	int y;
	int idx;

	Point(int x, int y, int idx){
		this.x = x;
		this.y = y;
		this.idx = idx;
	}
}

class MinDistance{
	int idx1;
	int idx2;

	double distance;
	Point[] sortedArr;

	public MinDistance(int idx1, int idx2, double distance, Point[] sortedArr){
		this.idx1 = idx1;
		this.idx2 = idx2;
		this.distance = distance;
		this.sortedArr = sortedArr;
	}
}

class Solution {	
	public double findDistance(Point a, Point b) {
		return Math.sqrt(Math.pow(a.x-b.x, 2) + Math.pow(a.y-b.y, 2));
	}

	public Point[] merge(Point[] arr1, Point[] arr2) {
		int N = arr1.length;
		int M = arr2.length;

		int L = N + M;
		Point[] out = new Point[L];

		int idx = 0;

		int idx1 = 0, idx2 = 0;
		while(idx1 < N || idx2 < M) {
			if(idx1 == N) out[idx++] = arr2[idx2++];
			else if(idx2 == M) out[idx++] = arr1[idx1++];
			else {
				if(arr1[idx1].y > arr2[idx2].y) {
					out[idx++] = arr2[idx2++];
				} else {
					out[idx++] = arr1[idx1++];
				}
			}
		}

		return out;
	}

	public MinDistance findMinDistance(Point[] points, int s, int e){
		//TODO: initial condition
		int N = points.length;		

		if(s == e) return new MinDistance(-1, -1, Double.MAX_VALUE, new Point[]{points[s]});			

		int mid = (s + e) / 2;

		MinDistance minDis1 = findMinDistance(points, s, mid);
		MinDistance minDis2 = findMinDistance(points, mid+1, e);

		int tIdx1 = -1, tIdx2 = -1;
		double d = 0;

		if(minDis1.distance > minDis2.distance) {
			d = minDis2.distance;
			tIdx1 = minDis2.idx1;
			tIdx2 = minDis2.idx2;
		} else {
			d = minDis1.distance;
			tIdx1 = minDis1.idx1;
			tIdx2 = minDis1.idx2;
		}

		double dl = points[mid].x - d;
		double dr = points[mid].x + d;		

		Point[] larr = minDis1.sortedArr;
		Point[] rarr = minDis2.sortedArr;

		Point[] marr = merge(larr, rarr);		

		//search for every points inside dl->dr
		List<Point> list = new ArrayList<Point>();

		for(int i=0; i<marr.length; i++) {
			if(marr[i].x >= dl && marr[i].x <= dr){
				list.add(marr[i]);
			}
		}

		int M = list.size();
		
		for(int i=0; i<M; i++) {
			for(int j=1; j<=15; j++) {
				if(i+j <= M-1) {
					double tmpD = findDistance(list.get(i), list.get(i+j));
					if(d > tmpD){
						d = tmpD;						
						tIdx1 = list.get(i).idx;
						tIdx2 = list.get(i+j).idx;
					}
				}
			}
		}

		return new MinDistance(tIdx1, tIdx2, d, marr);
	}

	public static void main(String[] args){
		Solution solution = new Solution();

		Scanner scanner = new Scanner(System.in);

		int N = scanner.nextInt();

		Point[] points = new Point[N];

		for(int i=0; i<N; i++){			
			points[i] = new Point(scanner.nextInt(), scanner.nextInt(), i);
		}

		//sort by x-coordinate
		Arrays.sort(points, new Comparator<Point>(){
			public int compare(Point a, Point b) {
				return a.x - b.x;
			}
		});

		MinDistance result = solution.findMinDistance(points, 0, N-1);
		int idx1 = Math.min(result.idx1, result.idx2);
		int idx2 = Math.max(result.idx1, result.idx2);
		//double roundDistance = Math.round(result.distance * Math.pow(10, 6)) / Math.pow(10, 6);

		System.out.printf("%d %d %.6f\n", idx1, idx2, result.distance);		
	}
}