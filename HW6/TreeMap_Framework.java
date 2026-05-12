/*
TODO - Summarize this class
*/

// used only in balancing utilities
import java.util.List;       
import java.util.ArrayList;

public class TreeMap implements Map {
  
    // TODO - Document all instance variables
    private TreeNode root;
    private boolean rebalance;

    public TreeMap( boolean rebalance ) {
        this.root = null;
        this.rebalance = rebalance;
    }

    /// TODO - Document this method
    public void put( String key, String value, int[] profile ) {
        //TODO: write this method

    }
 
   
    /// TODO - Document this method
    public String get(String key, int[] profile) {
        // TODO: write this method
 
        return null; 
    }

    /// TODO - Document this method
    public boolean delete(String key, int[] profile) {
        // TODO: write this method

        return false;

    }

    /// TODO - Document this method
    public void clear(){
        // TODO: write this method

    }
  
  
    //-------------------------------------------------------------------   
    // Utilities
    //-------------------------------------------------------------------   
    /// The entry point for balancing the entire tree to maintain optimal 
    /// insert and search efficiency
    private void balance() {
        root = balance(root);
    }

    /// Rebalance a given subtree given a local root node
    /// Note: This algorithm focuses on correctness and is not the most 
    /// efficient algorithm available. Please look up different algorithms 
    /// that solve the balancing problem.
    /// @param root the root of the subtree to balance
    /// @return the new root of the subtree after balancing
    private TreeNode balance(TreeNode root) {
        List<TreeNode> nodes = new ArrayList<TreeNode>();
        //Sorts tree from given root
        populate(root, nodes);
        //Return null if root has no children
        if(nodes.size() == 0) return null;
  
        return balance(nodes, root, 0, nodes.size() - 1);
    }
    
 
    /// Recursive helper in the balancing operation to support balance.
    /// @param nodes a list of nodes
    /// @param parent the parent node of this subtree
    /// @param start the start index within the list
    /// @param end the end index within the list
    /// @return the local root after balancing is performed on the subtree
    private TreeNode balance(List<TreeNode> nodes, TreeNode parent, int start, int end) {
        int mid = (start + end) / 2;
        TreeNode node = nodes.get(mid);
        node.parent = parent;
        if(start == end){
            node.left = null;
            node.right = null;
            return node;
        }
        //Recursively balance tree on left and right children using
        //middle node as root
        if(!(mid - 1 < start)) {
            node.left = balance(nodes, node, start, mid - 1);
        } else {
            node.left = null;
        }
  
        if(!(mid + 1 > end)) {
            node.right = balance(nodes, node, mid + 1, end);
        } else {
            node.right = null;
        }
  
        return node;
    }
 
    /// Recursive helper in the balancing operation to put listitems into
    /// the tree
    /// @param the root of the subtree to balance
    /// @param the list of nodes to balance
    private void populate(TreeNode node, List<TreeNode> list) {
        if(node == null) return;
        populate(node.left, list);
        if( !node.delete ) {
            list.add(node);
        }
        populate(node.right, list);
    }
}
