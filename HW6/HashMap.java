/*
Authors: Akram Atassi, James Taylor
*/

import java.util.Map;

public class HashMap implements Map {
  
    // Defines number of buckets in the HashMap
    private final ListNode[] buckets;
  
    public HashMap(int length) {
        this.buckets = new ListNode[length];
    }
  
    // Puts a value into the HashMap depending on its key. Updates the number of function calls in profile.
    // Inputs are key, value, and the profile to be updated. Return is void.
    public void put(String key, String value, int[] profile) {  
        int idx = hash(key);                                    // Hashes the key to check for matches.
        //System.out.println(idx);
  
        ListNode node = new ListNode(key, value);               // Creates the node to be put.
        ListNode it = buckets[idx];                             // Pointer to the current bucket.
        profile[0]++;                                           // Increment profile.
  
        if( it == null ) {                                      // If HashMap empty, put without uniqueness check.
            buckets[idx] = node;
            return;
        }
		
		if (it.getKey().equals(key)) {                          // Uniqueness check, if key already exists, update the value inside.
			it.updateValue(value);
			return;
		}
        
        while( it.next != null ) {
            profile[0]++;                                       // Increment profile.
			
			if (it.next.getKey().equals(key)) {
				it.next.updateValue(value);
				return;                                         // Uniqueness check, if key already exists, update the value inside.
			}
            
			it = it.next;
        }
		
		
        it.next = node;                                       // If key not found, insert the node and create new key as value placeholder.
    }
  
    // Searches for the value associated with input key. Returns the string value. Updates the number of function calls in profile.
    public String get(String key, int[] profile) {
        int idx = hash(key);                                  // Hashes the key to check for matches.
  
        ListNode it = buckets[idx];                           // Pointer to the current bucket.
  
        if( it == null ) {
            return null;                                      // Edge case, if HashMap empty, return null early.
        }
        
        while( it != null ) {
            profile[0]++;                                     // Increment profile.                      
            if( it.getKey().compareTo(key) == 0 ) {           // If the input key is equal as the current key,
                return it.getValue();                         // return the value associated with the current key.  
            }
  
            it = it.next;                                     // Iterator.
        }
        return null;                                          // Fail case, if the key is not found, return null.
    }

    // Checks for existence of input key. If it exists, delete it and return true. If it does not, return false.
    // Updates the number of function calls in profile.
    public boolean delete(String key, int[] profile) {
        int idx = hash(key);                                    // Hashes the key to check for matches.
  
        ListNode it = buckets[idx];                             // Pointer to the current bucket.

        // if list empty  
        if( it == null ) {
            return false;
        }

        // if head is the key to delete
        if( it.getKey().compareTo(key) == 0 ) {
            buckets[idx] = it.next;
            return true;
        }

        while( it.next != null ) {
            profile[0]++;
            // using a look ahead strategy, this works because we prime
            // with the head check before looping.  Without head check
            // this will generate an exception.
            if( it.next.getKey().compareTo(key) == 0 ) {
                it.next = it.next.next;
                return true;
            }
  
            it = it.next;
        }
        return false;
    }
  
    // Clears the HashMap. No input, returns void.
    public void clear() {
        for( int i = 0; i < buckets.length; i++ ){
            buckets[i] = null;                          //Iterates while making every key-associated value equal to null.
        }
    }

    //-------------------------------------------------------------------   
    // Utilities
    //-------------------------------------------------------------------   
    /// Hash function.  DO NOT MODIFY
    /// @param key string input to be hashed
    /// @return index location of where an object should be put in the table
    private int hash(String key) {
        return Math.abs(key.hashCode() % buckets.length);
    }
}
