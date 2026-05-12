/*--------------------------------------------------------------------------
GWU - CS1112 Data Structures and Algorithms - Spring 2026

authors: Akram Atassi, Charles Peeke
--------------------------------------------------------------------------*/
public class LinkedList implements MusicCatalog {

    // For a linked-list based list, the head pointer
    private CatalogItem head;
    // The counter to track the number of elements in the list 
    private int count;

    // Parameterless Constructor
    public LinkedList() {
        count = 0;
        head = null;
    }

    // Appends song to the end of the LinkedList. Song is the input. Returns void.
    public void add(Song song) {
        CatalogItem node = new CatalogItem(song);

        if (head == null) {
            head = node;                        // Set node value to head for iteration
        } else {
            CatalogItem current = head;
            while (current.next != null) {
                current = current.next;         // Iterate until we reach the end of the LL
            }
            current.next = node;                // Set the value of the first null element in the LL to the input
        }

        count++;                                // Increase value of count
    }

    // Removves song from the head of the LL. Returns the removed song. No input.
    public Song remove() {
        if (head == null) return null;          // edge case, if LL does not exist, return null.

        Song temp = null;                       // Temporary holder for return.

        temp = head.getSong();
        head = head.next;                       // Remove complete.
        count--;                                // Decrease count.

        return temp;                            // Decrease value of count.
    }  

    // Removes first appearance of input song from the LL. Returns the removed song.
    public Song remove(Song song) {
        CatalogItem prev = null;
        CatalogItem current = head;

        while (current != null) {               // Edge case check, if LL does not exist; fail case.
            if (current.getSong() == song) {    
                if (prev == null) {
                    head = current.next;        // If the current is still the head, remove it.
                } else {
                    prev.next = current.next;
                }
                count--;                        // Decrease value of count.
                return current.getSong();
            }
            prev = current;                     // If the current is not the head, remove the previous.
            current = current.next;             // Remove current to avoid null element in the LL.
        }
        return null;                            // Fail case.
    }
    
    // Clears the LL. No input, return is void.
    public void clear() {
        head = null;                            // Sets head to null, deleting the LL.
        count = 0;                              // Sets value of count to 0.

    }
    
    // Checks if the LL is empty. No input, return is boolean.
    public boolean isEmpty() {
        return count == 0;                      // Returns conditional of count. If count = 0, returns true. If not, false.

    }

    
    // Returns value of count. No input.
    public int count() {
        return count;
    
    }
    
    // Seaches for value of a song depending on its index. Input is the index. Returns the Song found.
    public Song get(int i) {
        CatalogItem node = head;
        int index = 0;

        while(node != null){                // Edge case check, checks for existence of LL. If does not exist, fail case.
            if(index == i){                 
                return node.getSong();      // If found, return.
            }
            index++;                        // Increase value of iterator.
            node = node.next;
        }

        return null;                        // Fail case. Return null.
    }

    // Checks for existence of song depending on value of input song. Returns boolean.
    public boolean contains(Song song) {
        CatalogItem node = head;

        while(node != null){                // Edge case check, checks for existence of LL. If does not exist, fail case.
            if (node.getSong().equals(song)) {
                return true;                // If song is found, return true.
            }
            node = node.next;
        }

        return false;                       // Fail case. Return true.
    }

    //-----------------------------------------------------------------
    // Utilities
    //-----------------------------------------------------------------

    /// TODO Any private helper functions go here.  They must be documented


    /// Returns a truth value indicating whether the catalog's structural
    /// integrity remains valid.  If the integrity is no longer valid,
    /// then the catalog should be invalidated and usage should not be 
    /// trusted
    /// @return true if the catalog integrity is valid; otherwise, false
    public boolean isIntegrityValid() {
        if(count < 0) {
            return false;
        }
        if(count == 0 && head == null) {
            return true;
        }
        if(count == 1 && head != null && head.next == null) {
            return true;
        }

        int n = 1;
        CatalogItem it = head;
        while(it.next != null) {
            it = it.next;
            n++;
        }

        if(n != count) {
            return false;
        }

        return true;
    }

    /// Returns a string that contains information about the list and the 
    /// contents of the list.  This is mostly useful for visual debugging 
    /// @return a string containing information about the contents of the 
    ///         catalog
    public String toString() {
        String s = "";
        s = "LinkedList::count=" + count(); 
        s += ", isEmpty=" + isEmpty(); 
        s += ", ["; 
        CatalogItem it = head;
        while(it != null) {
            if(it != head) {
                s += ", ";
            }
            s += it.getSong().getTitle();
            s += " | ";
            s += it.getSong().getYear();
            it = it.next;
        }
        s += "]";

        return s; 
    }

    /// Returns the earliest and most recent years of all the songs in the
    /// catalog and then clears the catalog of all songs
    /// @return an array of the years of the earliest and most recent songs
    public int[] publish() {
        int[] years = new int[2];
        int oldYear = Integer.MAX_VALUE;
        int newYear = Integer.MIN_VALUE;
        years[0] = oldYear;
        years[1] = newYear;

        CatalogItem it = head;
        while(it != null) {
            int curYear = it.getSong().getYear();
            if (curYear < oldYear) {
                oldYear = curYear;
                years[0] = oldYear;
            }
            if (curYear > newYear) {
                newYear = curYear;
                years[1] = newYear;
            }
            it = it.next;
        }
        clear();
        return years;
    }

}
