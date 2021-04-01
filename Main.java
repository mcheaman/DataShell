import java.util.*;

public class Main{
	static Scanner scan = new Scanner(System.in);
	static Random r = new Random();
	static ProblemSolver p = new ProblemSolver();

	public static void main(String[] args){
		intro();
		userTests();
	}

	// Program Description and input handling
	private static void intro(){
		System.out.println("CSCI 241 Practice Problems");
		boolean choiceMade = false;
		while(!choiceMade){
			System.out.print("Display Problem List? (y/n)  ");
			String displayChoice = scan.next();

			if(displayChoice.equalsIgnoreCase("y")){
				displayProblemList();
				choiceMade = true;
			}
			else if(displayChoice.equalsIgnoreCase("n")){
				choiceMade = true;
			}
			else{
				System.out.println("Invalid input");
			}
		}
		System.out.println();
	}
	private static void displayProblemList(){
		System.out.println("\nProblem 1:  Given a singly linked list with all elements as integers,sort using insertion sort.\n");
		System.out.println("Problem 2:  Given an array with n objects colored red, white or blue, sort them so that objects of\n            the same color are adjacent, with the colors in the order red, white and blue.\n");
		System.out.println("Problem 3:  Merge k sorted linked lists and return it as one sorted list.\n");
		System.out.println("Problem 4:  Design a data structure that supports the following two operations: \n            void addNum(int num) - Add a integer number from the data stream to the data structure.\n            double findMedian()  - Return the median of all elements so far.\n");
		System.out.println("Problem 5:  Write an efficient algorithm that searches for a value in an m x n matrix. This matrix\n            has the following properties: Integers in each row are sorted from left to right. The\n            first integer of each row is greater than the last integer of the previous row.\n");
		System.out.println("Problem 6:  Given an array of integers, every element appears twice except for one. Find that single\n            one.\n");
		System.out.println("Problem 7:  Given an array of integers, return indices of the two Keys such that they add up to a\n            specific target.\n");
		System.out.println("Problem 8:  Given a string, sort it in decreasing order based on the frequency of characters.\n");
		System.out.println("Problem 9:  Given a binary search tree with non-negative values, find the minimum absolute difference\n            between values of any two nodes.\n");
		System.out.println("Problem 10: Given the root node of a binary search tree, return the sum of values of all nodes with\n            value between L and R (inclusive). The tree is guaranteed to have unique values.\n");
		System.out.println("Problem 11: Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding\n            up all the values along the path equals the given sum. Note: A leaf is a node with no\n            children. \n");
		System.out.println("Problem 12: Given a coordinate (sr, sc) representing the starting pixel (row and column) of the flood\n            fill, and a pixel value newColor, 'flood fill' the image.\n");
	}
	private static void userTests(){
		while(true){
			System.out.print("Problem Choice (1/2/3 etc) or q to quit:  ");
			String userChoice = scan.next();
			if(userChoice.equalsIgnoreCase("1")){
				System.out.println("\nProblem 1: Given a singly linked list with all elements as integers, sort using insertion sort.\n");
				System.out.print("How many tests: ");
				int tests = scan.nextInt();
				p.p1.runTest(tests);
			}
			else if(userChoice.equalsIgnoreCase("2")){
				System.out.println("\nProblem 2: Given an array with n objects colored red, white or blue, sort them so that objects \n           of the same color are adjacent, with the colors in the order red, white and blue.\n");
				System.out.print("How many tests: ");
				int tests = scan.nextInt();
				p.p2.runTest(tests);
				
			}
			else if(userChoice.equalsIgnoreCase("3")){
				System.out.println("\nProblem 3: Merge k sorted linked lists and return it as one sorted list.\n");
				System.out.print("How many tests: ");
				int tests = scan.nextInt();
				System.out.print("How many lists to merge: ");
				int lists = scan.nextInt();

				p.p3.runTest(tests,lists);
			}
			else if(userChoice.equalsIgnoreCase("4")){
				System.out.println("\nProblem 4: Design a data structure that supports the following two operations: \n           void addNum(int num) - Add a integer number from the data stream to the data structure.            double findMedian()  - Return the median of all elements so far.\n");
				System.out.print("How many tests: ");
				int tests = scan.nextInt();
				System.out.print("Data Set Size: ");
				int setSize = scan.nextInt();

				p.p4.runTest(tests,setSize);

			}
			else if(userChoice.equalsIgnoreCase("5")){
				System.out.println("\nProblem 5: Write an efficient algorithm that searches for a value in an m x n matrix. This matrix\n           has the following properties: Integers in each row are sorted from left to right. The\n           first integer of each row is greater than the last integer of the previous row.\n");
				System.out.print("How many tests: ");
				int tests = scan.nextInt();
				p.p5.runTest(tests);
			}
			else if(userChoice.equalsIgnoreCase("6")){
				System.out.println("Problem 6: Given an array of integers, every element appears twice except for one. Find that single           one.\n");
				System.out.print("How many tests: ");
				int tests = scan.nextInt();
				System.out.print("Numbers in set: ");
				int nums = scan.nextInt();

				p.p6.runTest(tests, nums);
			}
			else if(userChoice.equalsIgnoreCase("7")){
				System.out.println("Given an array of integers, return indices of the two keys such that they add up to a specific\ntarget.\n");
				System.out.print("How many tests: ");
				int tests = scan.nextInt();
				int[] dataset = p.makeArrI(10);
				System.out.println();
				System.out.print("\tData set: ");
				p.printArr(dataset);
				p.p7.runTest(tests, dataset);

			}
			else if(userChoice.equalsIgnoreCase("8")){
				System.out.println("Given a string, sort it in decreasing order based on the frequency of characters.\n");
				System.out.print("How many tests: ");
				int tests = scan.nextInt();
				p.p8.runTest(tests);
			}
			else if(userChoice.equalsIgnoreCase("9")){
				System.out.println("Problem 9: Given a binary search tree with non-negative values, find the minimum absolute difference\n           between values of any two nodes.\n");
				System.out.print("How many tests: ");
				int tests = scan.nextInt();
				System.out.print("Tree size: ");
				int size = scan.nextInt();				
				p.p9.runTest(tests, size);
			}
			else if(userChoice.equalsIgnoreCase("10")){
				System.out.println("Problem 10: Given the root node of a binary search tree, return the sum of values of all nodes with\n            value between L and R (inclusive). The tree is guaranteed to have unique values.\n");
				System.out.print("How many tests: ");
				int tests = scan.nextInt();
				System.out.print("Tree size: ");
				int size = scan.nextInt();				
				p.p10.runTest(tests, size);
			}
			else if(userChoice.equalsIgnoreCase("11")){
				System.out.println("Problem 11: Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding\n            up all the values along the path equals the given sum.\n");
				System.out.print("How many tests: ");
				int tests = scan.nextInt();
				p.p11.runTest(tests);
			}
			else if(userChoice.equalsIgnoreCase("12")){
				System.out.println("Problem 12: Given a coordinate (sr, sc) representing the starting pixel (row and column) of the flood\n            fill, and a pixel value newColor, 'flood fill' the image.\n");
				System.out.print("How many tests: ");
				int tests = scan.nextInt();
				p.p12.runTest(tests);

			}
			else if(userChoice.equalsIgnoreCase("13")){
				System.out.println("Not Yet Implemented");
			}
			else if(userChoice.equalsIgnoreCase("q")){
				break;
			}
			else{
				System.out.println("Invalid");
			}
		}
	}

}










