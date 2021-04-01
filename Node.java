public class Node {
   int val;
   Node left, right, parent, next;

   public Node() {}

   public Node(int num) {
      val = num;
      left = null;
      right = null;
      next = null;
   }
}
