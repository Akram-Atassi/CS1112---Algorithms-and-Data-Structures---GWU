/*
TODO - Summarize this class
*/

// used only in balancing utilities
import java.util.List;
import java.util.Map;

import javax.swing.tree.TreeNode;

import java.util.ArrayList;

public class TreeMap implements Map {
    private TreeNode root;                                  // Parent node of the tree. Does not have parents.
    private boolean rebalance;                              // Check for if the tree needs rebalancing.

    public TreeMap( boolean rebalance ) {
        this.root = null;
        this.rebalance = rebalance;
    }

    // Puts the input value into the tree. If the key already exists, update it with the new value. If not, create a new key and put the value.
    // Updates the number of function calls in profile. Inputs are key, value, and the profile to be updated. Return is void.
    public void put( String key, String value, int[] profile ) {
        // sanity checks
        if( key == null ) return;

        // edge case - empty tree
        if( root == null ) return;
        
        // general case - non-empty tree
        TreeNode it = root;
 
        // binary search
        // The return statement controls update flow
        // if the loop condition happens, search fails
        while( it != null ) {
                profile[0]++; 
                int result = it.getKey().compareTo( key );
                
                if( result < 0 ) {
                    if( it.left == null ) {
                        it.left = new TreeNode( key, value, it );
                        break;             
                    }
                    else {
                        it = it.left;
                    }
                }
                if( result > 0 ) {
                    if (it.right == null){
                        it.right = new TreeNode (key, value, it);
                    }
                    else {
                        it = it.right;
                    }
                }
                else {
                // here when    it.key == key
                    key = value;
                } 

        }


    }
 
   
    // Searches for the input key within the tree. If it exists, returns the string value associated with the key.
    // Updates the number of function calls in profile.
    public String get(String key, int[] profile) {
        // sanity checks
        if( key == null ) return null;

        // edge case - empty tree
        if( root == null ) return null;
        
        // general case - non-empty tree
        TreeNode it = root;
 
        // binary search
        // The return statement controls update flow 
        // if the loop condition happens, search fails
        while( it != null ) {
            profile[0]++; 
            int result = it.getKey().compareTo( key );
            
            if( result < 0 ) {
                it = it.left;
            } else if( result > 0 ) {
                it = it.right;
            } else {
                // here when    it.key == key
                it.key = null;
                return true;
            } 
        }

        // if execution reaches here, the search failed
        return false; 
    }

    // Searches for the string value assocciated with the input key. If it exists, delete it and return true. If not, return false.
    // Updates the number of function calls in profile.
    public boolean delete(String key, int[] profile) {
        // sanity checks
        if( key == null ) return false;

        // edge case - empty tree
        if( root == null ) return false;

        // general case - non-empty tree
        TreeNode it = root;
 
        // The break statements control append flow
        // The return statement controls update flow 
        // The loop condition should not actually happen
        while( it != null ) {
            profile[0]++; 
            int result = it.getKey().compareTo( key );
            
            if( result < 0 ) {
                // left
                if( it.left == null ) {
                    return false;
                } else {
                    it = it.left;
                }
            } else if( result > 0 ) {
                // right
                if( it.right == null ) {
                    return false;
                } else {
                    it = it.right;
                }
            } else {
                // equal -> delete this node
                it.delete = true;
                // deleted noded are omitted during rebalance which
                // is how they are actually removed from the tree
                balance(root);
                return true;
            } 
        }
 
        // impossible to get here due to the stucture of the above logic
        return false;
    }


    // Clears the tree
    public void clear(){
        root = null;                        // If root does not exist, tree does not exist.

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
