import java.util.*;	
public class ProblemSolver{
	static Scanner scan = new Scanner(System.in);
	static Random r = new Random();
	static Problem1 p1 = new Problem1();
	static Problem2 p2 = new Problem2();
	static Problem3 p3 = new Problem3();
	static Problem4 p4 = new Problem4();
	static Problem5 p5 = new Problem5();
	static Problem6 p6 = new Problem6();
	static Problem7 p7 = new Problem7();
	static Problem8 p8 = new Problem8();
	static Problem9 p9 = new Problem9();	
	static Problem10 p10 = new Problem10();
	static Problem11 p11 = new Problem11();
	static Problem12 p12 = new Problem12();

	/* Problem 1: Given a singly linked list with all elements as integers,
	sort using insertion sort. */
	public static class Problem1{
		public static void runTest(int tests){
			for(int i = 1; i < tests+1; i++){
				System.out.println("  Test " + i + ":");

				System.out.print("\tUnsorted: ");
				Node head = makeList(r.nextInt(8) + 2);
				printList(head);

				System.out.print("\tSorted: ");
				Node sort = insertionSortLinked(head);
				printList(sort);
				System.out.println();
			}
		}

		private static Node insertionSortLinked(Node head){
			//Initailize a sorted_head that will keep trach of the sorted list
			Node sorted_head = null;
			Node cur = head; 
			Node cur_next = null;
			//iterate through until we have hit the end of the list
			while(cur != null){

				//store the node after cur
				cur_next = cur.next;

				//insert cur to sorted portion using helper method and update sorted head
				sorted_head = sortedInsert(sorted_head, cur);

				//continue stepping through
				cur = cur_next;
			}
			//when list is fully iterated, return the head (headFake.next)
			return sorted_head;
		}

		private static Node sortedInsert(Node sorted_head, Node new_node){
			//if smaller than sorted head, make head
			if(sorted_head == null || sorted_head.val >= new_node.val){
				new_node.next = sorted_head;
				return new_node;
			}else{
				//create new cur node to keep track
				Node cur = sorted_head;
				//step through until new_node > cur or end of list met
				while((cur.next != null) && (cur.next.val < new_node.val)){
					cur = cur.next;
			}

			//insert new_node into list
			new_node.next = cur.next;
			//point cur to new_node
			cur.next = new_node;
			}
			return sorted_head;
		}
	}
	/* Problem 2: Given an array with n objects colored red, white or blue, 
	sort them so that objects of the same color are adjacent, with the colors
	in the order red, white and blue. Ex, ["red", "blue", "white", "white", "blue", "red"]
	becomes ["red","red","white","white","blue","blue"]*/
	public static class Problem2{
		public static void runTest(int tests){
			for(int i = 1; i < tests+1; i++){
				System.out.println("  Test " + i + ":");

				System.out.print("\tUnsorted: ");
				String[] unsorted = makeArrS(r.nextInt(7) + 3);
				printArr(unsorted);

				System.out.print("\tSorted: ");
				String[] sort = colorMerge(unsorted, unsorted.length);
				printArr(sort);
				System.out.println();

			}
		}
	

		private static String[] colorMerge(String[] arr, int size){
			//create an ArrayList for return array and storing white/blue indecies
			ArrayList<String> sortedList = new ArrayList<String>(); 
			String[] sortedArr = new String[size];
			ArrayList<String> white = new ArrayList<String>(); 
			ArrayList<String> blue = new ArrayList<String>(); 
			//add all colors to their individual arrays, red is added to sorted as it is first either way
			for(String color : arr){
				if(color.equalsIgnoreCase("red")){
					sortedList.add(color);
				}
				else if(color.equalsIgnoreCase("white")){
					white.add(color);
				}
				else{
					blue.add(color);
				}
			}
			//fill the sorted array with remaining colors
			while(!white.isEmpty()){
				sortedList.add(white.remove(0));
			}
			
			while(!blue.isEmpty()){
				sortedList.add(blue.remove(0));
			}
			//return arraylist as an array
			return sortedList.toArray(sortedArr);
		}
	}
	/* Problem 3: Merge k sorted linked lists and return it as one sorted list.*/
	public static class Problem3{
		public static void runTest(int tests, int k){
			for(int i = 1; i < tests+1; i++){
				System.out.println("  Test " + i + ":");
				ArrayList<Node> listArr = new ArrayList<Node>();
		
				System.out.println("\tPre Merge: ");
				//populate arraylist with k sorted linked lists
				for(int j = 0; j < k ; j++){
					System.out.print("\t  List " + (j + 1) + ": ");
					Node unsorted = makeList(r.nextInt(2) + 3);
					Node sorted = p1.insertionSortLinked(unsorted);
					listArr.add(sorted);
					printList(sorted);
				}

				System.out.println();
			
				//merge k sorted linked lists to one linked list
				System.out.print("\tPost Merge: ");
				Node sorted = mergeLists1(listArr);
				printList(sorted);

				System.out.println();
			}
		}
		private static Node mergeLists1(ArrayList<Node> arr){
			//initialize data structures: PriorityQueue for sorting, ArrayList for sorted lists
			PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
			ArrayList<Integer> sorted = new ArrayList<Integer>();
			//add all elements of all linked lists to PriorityQueue
			for(int i = 0; i < arr.size(); i++){
				Node head = arr.get(i);
				Node cur = head;
				minHeap.add(cur.val);

				while(cur.next != null){
 					cur = cur.next;
					minHeap.add(cur.val);
				}
			}
			//store minHeap values in ArrayList for easy conversion to Linked List
			while(!minHeap.isEmpty()){
				sorted.add(minHeap.poll());
			}

			Node returnList = makeList(sorted);
			return returnList;
		}
	}
	/* Problem 4: Design a data structure that supports the following two operations:
	void addNum(int num) - Add a integer number from the data stream to the data structure.            
	double findMedian()  - Return the median of all elements so far.*/
	public static class Problem4{
		static PriorityQueue<Integer> minHeap; 
		static Integer[] queueArr;

		public static void runTest(int tests, int size){
			minHeap = new PriorityQueue<Integer>();
			for(int i = 1; i < tests + 1; i++){
				System.out.println("  Test " + i + ":");

				System.out.print("\tData Set: ");
				for(int j = 0; j < size; j++){
					int val = r.nextInt(10);
					addNum(val);
				}

				queueArr = queueToArr(size);

				printArr(queueArr);

				System.out.print("\tMedian: ");
				double median = findMedian();
				System.out.println(median);

			}

		} 
		//add element to the Priority Queue
		private static void addNum(int num){
			minHeap.add(num);
		}
		//convert the Priority Queue to an Integer[] for median computation
		private static Integer[] queueToArr(int size){
			Integer[] data = new Integer[size];
			for(int i = 0; i < size; i++){
				data[i] = minHeap.poll();
			}
			return data;
		}
		//use array indecies to find median, using different computation if array length is even
		private static double findMedian(){
			double median;
			int length = queueArr.length;

			if((length % 2) != 0){
				int index = (length / 2);
				median = queueArr[index];
			}
			else{
				int left = queueArr[(length / 2) - 1];
				int right = queueArr[length / 2];
				median = (left + right) / 2.0;
			}
			return median;
		}
	}
	/* Write an efficient algorithm that searches for a value in an m x n matrix. 
	This matrix has the following properties: Integers in each row are sorted from 
	left to right. The first integer of each row is greater than the last integer 
	of the previous row. */
	public static class Problem5{
		public static void runTest(int tests){
			for(int i = 0; i < tests; i++){
				int[][] matrix = {{1,3,5,7},{10,11,16,20},{23,30,34,50}};
				printArr(matrix);
				System.out.print("Target: ");
				int target = scan.nextInt();
				long pre = System.nanoTime();
				boolean result = matrixSearch(matrix, target);
				long post = System.nanoTime();
				System.out.println("\t"+result);
				if(result){
					System.out.println("\tfound in: " + (post-pre) + " nanoseconds");
				}
			}
		}
		private static boolean matrixSearch(int[][] matrix, int target){
			int rows = matrix.length;
			int cols = matrix[0].length;
			int row = 0;
			//if less than lowest value, not found
			if(target < matrix[0][0]){
				return false;
			}
			//if greater than greatest value, not found
			if(target > matrix[rows-1][cols-1]){
				return false;
			}

			//traverse to find correct row, skip unneeded rows
			for(int i = 0; i < rows; i++){
				if(target <= matrix[i][cols-1]){
					row = i;
					break;
				}
				else{
				}
			}
			//search row for target
			for(int j = 0; j < cols; j++){
				if(target == matrix[row][j]){
					return true;
				} 
			}
			return false;
		}
	}
	/* Given an array of integers, every element appears twice except for one. Find 
	that single one. */
	public static class Problem6{
		public static void runTest(int tests, int nums){
			for(int i = 0; i < tests; i++){
				int[] dataset = makeDataSet(nums);
				System.out.println();
				System.out.print("\tData set: ");
				printArr(dataset);
				System.out.println("\tSingle: " + findSingle(dataset));
				System.out.println();
			}
		}
		//add all values to a hashmap, updating their values to reflect their frequency. Then find the key with only one val.
		private static int findSingle(int[] dataset){
			HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
			for(int i = 0; i < dataset.length; i++){
				if(map.containsKey(dataset[i])){
					map.put(dataset[i], 2);
				}
				else{
					map.put(dataset[i], 1);
				}
			}
			for(int key : map.keySet()){
				if(map.get(key) == 1){
					return key;
				}
			}
			return -1;
		}
	}
	/*Given an array of integers, return indices of the two Keys such that they add 
	up to a specific target. */
	public static class Problem7{
		public static void runTest(int tests, int[] dataset){
			for(int i = 0; i < tests; i++){
				System.out.print("\tTarget: ");
				int target = scan.nextInt();
				long preArr = System.nanoTime();
				int[] resultArr = findPairArr(dataset, target);
				long postArr = System.nanoTime();
				long preHash = System.nanoTime();
				int[] resultHash = findPairHash(dataset, target);
				long postHash = System.nanoTime();
				if(resultArr[0] != resultArr[1]){
					System.out.println("\tPossible: arr[" + resultArr[0] + "] + arr[" + resultArr[1] + "]");	
				}
				else{
				System.out.println("\tImpossible \n" );
				}
				System.out.println("\tArray Implementation took: " + (postArr - preArr) + " nanoseconds");
				System.out.println("\tHashtable Implementation took: " + (postHash - preHash) + " nanoseconds\n");
			}
		}

		//step through arr finding all unique sums, checking them against the target
		private static int[] findPairArr(int[] arr, int target){
			int[] targetArr = new int[2];
			for(int i = 0; i < arr.length - 1; i++){
				for(int j = i + 1; j < arr.length; j++){
					int sum = arr[i] + arr[j];
					if(sum == target){
						targetArr[0] = i;
						targetArr[1] = j;
					}
				}
			}
			return targetArr;
		}

		//increase effciency by populating Hashtable while searching for key that comletes necessary pair 
		private static int[] findPairHash(int[] arr, int target){
			int[] result = new int[2];
        	HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        	for (int i = 0; i < arr.length; i++) {
            	if (map.containsKey(target - arr[i])) {
                	result[0] = i;
                	result[1] = map.get(target - arr[i]);
                	return result;
            	} else {
                	map.put(arr[i], i);
            	}
        	}
        
        	return result;
    	}
	}
	/*Given a string, sort it in decreasing order based on the frequency of characters.*/
	public static class Problem8{
		public static void runTest(int tests){
			for(int i = 0; i < tests; i++){
				System.out.print("\tInput String: ");
				String input = scan.next();
				System.out.println("\n\tSorted: " + frequencySort(input));
			}
		}

		private static String frequencySort(String input){
			HashMap<Character,Integer> map = new HashMap<Character,Integer>();
			char[] arr = input.toCharArray();
			char[] frequency = new char[input.length()]; 
			char[] sorted = new char[input.length()]; 

			//add each char in input string to hashmap, updating values to respresent frequency;
			for(char c : arr){
				if(map.containsKey(c)){
					map.put(c, (map.get(c) + 1));
				}
				else{
					map.put(c, 1);
				}
			}
			//in decending order of frequency, add chars to a sorted char[]
			int sortedIndex = 0;
			Set<Character> set = map.keySet();
			for(int i = input.length(); i > 0; i--){
				for(char c : set){
					if(map.get(c) == i){
						for(int j = 0; j < map.get(c); j++){
							sorted[sortedIndex] = c;
							sortedIndex++;
						}
					}
				}
			}
			String toString = new String(sorted);
			return toString;
		}	
	}
	/*Given a binary search tree with non-negative values, find the minimum absolute difference between values of any two nodes.*/
	public static class Problem9{
		static BST tree;
		static Node testRoot;
		static Node parent;
		static int min; 
		public static void runTest(int tests, int size){
			for(int i = 0; i < tests; i++){
				System.out.println();

				tree = new BST();
				testRoot = null;
				parent = null;
				min = Integer.MAX_VALUE;
				for(int j = 0; j < size; j++){
					 testRoot = tree.insert(r.nextInt(100));
				}
				System.out.print("\n\tTree: ");
				printArr(tree.traverse());
				int minDiff = getMinDiff(testRoot);
				System.out.println("\n\tMinimum Absolute Difference: " + minDiff + "\n");

			}

		}

		private static int getMinDiff(Node root){
			minDiffTraverse(root);
			return min;
		}

		private static void minDiffTraverse(Node node){
			//if node is null, traversal has reached past the leaf level
			if(node != null){
				//step to leftmost leaf
				minDiffTraverse(node.left);

				//update minimum difference if applicable
				if(parent != null && Math.abs(node.val - parent.val) < min){
					min = Math.abs(node.val - parent.val);
				}
				//update parent
				parent = node;

				//step right
				minDiffTraverse(node.right);
			}

		}
	}
	/*Given the root node of a binary search tree, return the sum of values of all nodes with value between L and R (inclusive). The
	tree is guaranteed to have unique values.*/
	public static class Problem10{
		static BST tree;
		static Node testRoot;
		static int sum;
		public static void runTest(int tests, int size){
			for(int i = 0; i < tests; i++){
				System.out.println();

				tree = new BST();
				testRoot = null;
				for(int j = 0; j < size; j++){
					 testRoot = tree.insert(r.nextInt(100));
				}
				System.out.print("\n\tTree: ");
				printArr(tree.traverse());
				System.out.print("\tLower Bound: ");
				int lChoice = scan.nextInt();
				System.out.print("\tUpper Bound: ");
				int rChoice = scan.nextInt();
				sum = getSum(testRoot, lChoice, rChoice);
				System.out.println("\n\tSum: " + sum + "\n");

			}

		}
		private static int getSum(Node root, int l, int r){
			Node lNode = tree.get(l);
			Node rNode = tree.get(r);
			getSumHelper(root, lNode, rNode);
			return sum;
		}

		private static void getSumHelper(Node node, Node l, Node r){
			//if node is null, traversal has reached past the leaf level
			if(node != null){
				//step left
				getSumHelper(node.left, l, r);

				//update sum if node val is within bounds
				if((node.val >=  l.val) && (node.val <= r.val)){
					sum += node.val;
				}
				//step right
				getSumHelper(node.right, l, r);

			}
		}
	}
	/*Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values along the 
	path equals the given sum. Note: A leaf is a node with no children. */
	public static class Problem11{
		public static void runTest(int tests){
			for(int i = 0; i < tests; i++){
				Node root = binTree11();
				System.out.println();
				System.out.print("\n\tTree: ");
				printTree(root);
				System.out.print("\tTarget: ");
				int target = scan.nextInt();
				boolean sum = sumPath(root, target);
				System.out.println("\n\tPath found: " + sum + "\n");

			}
		}
		private static boolean sumPath(Node root, int target){
			return sumPathDFS(root,target,0);
		}
		private static boolean sumPathDFS(Node node, int target, int sum){
			//if leaf level hit
			if(node.left == null && node.right == null){
				sum += node.val;
				if(sum == target){
					return true;
				}
			}
			//if has left child
			else if(node.right == null){
				return sumPathDFS(node.left, target, sum+node.val);
			}
			//if has right child
			else if(node.left == null){
				return sumPathDFS(node.right, target, sum+node.val);
			}
			//has both children
			else{
				return sumPathDFS(node.left, target, sum + node.val) || sumPathDFS(node.right, target, sum + node.val);
			}
			return false;
		}
	}
	/*Given a coordinate (sr, sc) representing the starting pixel (row and column) of the flood fill, and a pixel value newColor, 
	'flood fill' the image.*/
	public static class Problem12{
		public static void runTest(int tests){
			for(int i = 0; i < tests; i++){
				System.out.println();
				System.out.print("\n\tPre-Fill: \n");
				int[][] image = {{1,1,1,0},{0,0,1,1},{1,0,1,0},{1,1,0,1}};
		        printArr(image);
		        System.out.print("\tRow (0-3):  ");
		        int row = scan.nextInt(); 
		        System.out.print("\n\tColumn (0-3):  ");
		        int col = scan.nextInt(); 
		        System.out.print("\n\tFill color: ");
		        int newColor = scan.nextInt(); 
		        System.out.print("\n\tPost-Fill: ");
		        int[][] imageFill = floodFill(image, row, col, newColor);
		        printArr(imageFill);

			}
		}
		private static int[][] floodFill(int[][] image, int sr, int sc, int newColor){
			int color = image[sr][sc];
			if(color != newColor){
				floodFillDFS(image, sr, sc, color, newColor);
			}
			return image;
		}
		private static void floodFillDFS(int[][] image, int row, int col, int color, int newColor){
			if(row < 0 || row >= image.length || col < 0 || col >= image[0].length){
				return;
			}
			else{
				if(image[row][col] == color){
					image[row][col] = newColor;
					floodFillDFS(image, row+1, col, color, newColor);
					floodFillDFS(image, row-1, col, color, newColor);
					floodFillDFS(image, row, col+1, color, newColor);
					floodFillDFS(image, row, col-1, color, newColor);
				}
			}
		}
	}
	// Data Structure creation and display
	public static void printList(Node head){
		System.out.print(head.val+ " --> ");
		Node cur = head;
		while(cur.next != null){
			cur = cur.next;
			if(cur.next != null){
				System.out.print(cur.val + " --> ");
			}else{
				System.out.print(cur.val);
			}
		}
		System.out.println();
	}
	public static Node makeList(int length){
		Node head = new Node(r.nextInt(20));
		Node cur = head;
		for(int i = 1; i < length; i++){
			cur.next = new Node(r.nextInt(20));
			cur = cur.next;
		}
		return head;
	}
	public static Node makeList(ArrayList<Integer> arrList){
		Node head = new Node(arrList.get(0));
		Node cur = head;
		for(int i = 1; i < arrList.size(); i++){
			cur.next = new Node(arrList.get(i));
			cur = cur.next;
		}
		return head;
	}
	public static String[] makeArrS(int size){
		String[] colorArr = new String[size];
		for(int i = 0; i < size; i++){
			int color = r.nextInt(3);
			if(color == 0){
				colorArr[i] = "Red";
			}
			else if(color == 1){
				colorArr[i] = "White";
			}
			else{
				colorArr[i] = "Blue";
			}
		}
		return colorArr;
	}
	public static int[] makeArrI(int size){
		int[] arr = new int[size];
		for(int i = 0; i < size; i++){
			arr[i] = r.nextInt(20);
		}
		return arr;
	}
	public static int[] makeDataSet(int nums){
		int[] arr = new int[(nums*2) - 1];
		int single = r.nextInt(nums) + 1;
		int num = 1;
		for(int i = 1; i < ((nums*2) - 1); i++){
			if(num == single){
				arr[i] = num;
				num +=1;
			}
			else{
			arr[i-1] = num;
			arr[i] = num;
			num += 1;
			i++;
			}
		}
		arr[(single*2) - 2] = single;
		return arr;
	}
	public static Node binTree11(){
		Node root = new Node(5);
		root.left = new Node(4);
		root.right = new Node(8);
		root.left.left = new Node(11);
		root.left.right = new Node(17);
		root.left.right.left = new Node(5);
		root.left.right.right = new Node(2);
		root.left.left.left = new Node(7);
		root.left.left.right = new Node(2);
		root.right.left = new Node(13);
		root.right.right = new Node(4);
		root.right.right.left = new Node(11);
		root.right.right.right = new Node(3);
		root.right.left.left = new Node(1);
		root.right.left.right = new Node(9);

		return root;
	}
	public static void printTree(Node root){  
	    // Pass initial space count as 0  
	    printTreeHelper(root, 0);  
	}  
	public static void printTreeHelper(Node root, int space){  
	    // Base case  
	    if (root == null)  
	        return;  
	  
	    // Increase distance between levels  
	    space += 10;  
	  
	    // Process right child first  
	    printTreeHelper(root.right, space);  
	  
	    // Print current node after space  
	    // count  
	    System.out.print("\n");  
	    for (int i = 10; i < space; i++)  
	        System.out.print(" ");  
	    System.out.print(root.val + "\n");  
	  
	    // Process left child  
	    printTreeHelper(root.left, space);  
	}  
	public static void printArr(String[] arr){
		for(String color : arr){
			System.out.print(color + " ");
		}
		System.out.println();
	}
	public static void printArr(Integer[] arr){
		for(int val : arr){
			System.out.print(val + " ");
		}
		System.out.println();
	}
	public static void printArr(int[] arr){
		for(int val : arr){
			System.out.print(val + " ");
		}
		System.out.println("");
	}
	public static void printArr(char[] arr){
		for(char val : arr){
			System.out.print(val + " ");
		}
		System.out.println("");
	}
	public static void printArr(int[][] arr){
		System.out.println();
		for(int i = 0; i < arr.length; i++){
			for(int j = 0; j < arr[0].length; j++){
				System.out.print("\t" + arr[i][j] + " ");
			}
			System.out.println("\n");

		}
		System.out.println();
	}

}
