import java.util.*;
import java.io.*;

class BST {
  private Node root;
  private ArrayList<Integer> arrList = new ArrayList<Integer>();

  public BST() {
    root = null;
  }

  // search
  public boolean search(int target) {
    return searchHelper(target, root);
  }
  private boolean searchHelper(int target, Node node){
    //if we hit the bottom of the tree without finding target, return false
    if (node == null){
      return false;
    }
    //if target node is found, return true
    else if(node.val == target){
      return true;
    }
    //if target is less or greater than the current node, enter left or right subtree
    if (target < node.val){
      return searchHelper(target, node.left);
    }else{
      return searchHelper(target, node.right);
    }
  }

  // insert
  public Node insert(int target) {
    root = insertHelper(root, target);
    return root;
  }
  private Node insertHelper(Node node, int data){
    if (node == null){
      node = new Node(data);
    }
    //if smaller than current node, enter left subtree
    else if(data < node.val){
      node.left = insertHelper(node.left, data);
    }
    else if(data == node.val){
      node.val = data;
    }
    //if larger than current node, enter right subtree
    else{
      node.right = insertHelper(node.right, data);
    }
    return node;
  }

  public Node delete(int target){
    root = deleteHelper(root, target);
    return root;
  }

  private Node deleteHelper(Node root, int target)
    {
      /* If the tree is empty, return*/
      if (root == null){
        return root;
      }
      /* Otherwise, recur down the tree */
      if (target < root.val){
        root.left = deleteHelper(root.left, target);
      }
      else if (target > root.val){
        root.right = deleteHelper(root.right, target);
      }
      // if val is same as root's val, then This is the node to be deleted
      else{
        // node with only one child or no child
        if (root.left == null){
          return root.right;
        }
        else if (root.right == null){
          return root.left;
        }
        // node with two children: Get the inorder successor (smallest
        // in the right subtree)
        root.val = find_next_smallest(root.right);
        // Delete the inorder successor
        root.right = deleteHelper(root.right, root.val);
      }
      return root;
    }

  // find the smallest node in the right subtree of node to be deleted
  private int find_next_smallest(Node cur){
    //step to leftmost leaf node
    while (cur.left != null){
      cur = cur.left;
    }
    return cur.val;
  }

  public Node get(int target) {
    return getHelper(root, target);
  }

  private Node getHelper(Node node, int target) {
    if (node == null) {
      return node;
    }
    //if smaller than current node, enter left subtree
    else if (target < node.val) {
      return getHelper(node.left, target);
    }
    //if larger than current node, enter right subtree
    else if (target > node.val) {
      return getHelper(node.right, target);
    }
    //if current node is target, return
    else {
      return node;
    }
  }

  // in-order traversal
  public int[] traverse() {
    traverseHelper(root);
    int[] arr = new int[arrList.size()];
    for(int i = 0; i < arrList.size(); i++){
      arr[i] = arrList.get(i).intValue();
    }
    return arr;
  }
  private void traverseHelper(Node node){
    if(node != null){
      traverseHelper(node.left);
      arrList.add(node.val);
      traverseHelper(node.right);
    }
  }

  // you are welcome to add any supporting methods
}
