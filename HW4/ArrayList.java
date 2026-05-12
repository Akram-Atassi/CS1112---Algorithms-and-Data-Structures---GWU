/*--------------------------------------------------------------------------
GWU - CS1112 Data Structures and Algorithms - Spring 2026

TODO : Document this file

authors: Akram Atassi, Charles Peeke
--------------------------------------------------------------------------*/
public class ArrayList implements MusicCatalog {
    // For an array-based list, the array itself
    private CatalogItem[] data;
    // The counter to track the number of elements in the list
    private int count;

    // Parameterless Constructor
    public ArrayList() {
        count = 0;                              // Number of non-null elements
        data = new CatalogItem[2];              // Array containing the CatalogItems
    }
   
    // Appends song to the end of the sequence of non-null elemtns present in the array. Return value is void.
    public void add(Song song) {
        if(count == data.length) {              // Conditional if statement, if length of array = number of elements,
            CatalogItem[] result = new CatalogItem[data.length * 2];    // create deep copy with length  = number of elements times 2
            for(int i = 0; i < data.length; i++) {
                result[i] = data[i];            // Iterates for creation of deep copy.
            }
            data = result;
        }

        data[count] = new CatalogItem(song);    // Adds the input song to the end of the sequence of non-null elements
        count++;                                // Increase number of elements in the non-null sequence of elements counter by 1
    }

    // Removes element from the end of the sequece of non-null elements present in the array. Returns the removed song.
    public Song remove() {
        if(get(0) != null){                  // Edge case, if data[i] = null, then array doesnt exist; fail case
            Song result = get(0);
            for(int i = 0; i < count - 1; i++){
                data[i] = data[i+1];            // Iterates while copying value of data[i] to data[i+1]; data[count] = data[count - 1]
            }
            count--;                            // reduce count of non-null elements in the array
            data[data.length-1] = null;         // Set the value of the final song (data[count]) in the sequence of non-null elements to null.
            return result;                      // Returns removed value.
        } 

        return null;                            // Fail case, if array doesn't exist (data[i] = null); returns null.
    }

    // Removes first appearance of the input value "song" from the array. Returns removed song.
    
    public Song remove(Song song) {
        if (!contains(song)) return null;       // Edge case, if does not contain the song; return null.

        for(int i = 0; i < count - 1; i++){
            Song candidate = get(i);
            if (candidate == song){
                data[i] = null;                 // If found, sets the value of its position to null.
                i--;
            }
            if (get(i) == null){                // Now that the value is null, copy the next value in the array to replace the null value.
                data[i] = data[i+1];
            }
            count--;                            // Decrease number of elements in the sequence of non-null elements.
            data[data.length-1] = null;         // Sets the final value in the sequence of non-null elements to null
            return song;                        // returns the removed song
        }
        
        return null;                            // Fail case, if does not qualify for for loop requirement, returns null.
    }
    
    // Clears the array, and resets it to initial value. Return value is void.
    public void clear() {
        count = 0;                              // Sets number of elements in the sequence of non-null elements to 0.
        data = new CatalogItem[2];              // Erases all data in the previous array.
    }
    
    // Checks if array exists. If it does, return true. If not, return false.
    public boolean isEmpty() {
        return count == 0;                      // Returns boolean of if count = 0 or != 0. If count = 0, array does not exist, and vice versa.              
    }
    
    /// Returns number of non-null elements in the array
    public int count() {
        return count;
    }
    
    // Searches for existence of song depending on the index of the song.Index is the input. Returns the song if found.
    public Song get(int i) {
        if(i < 0 || i >= count) {               // Double edge case check. If the index input is invalid, returns null.
            return null;
        }
        return data[i].getSong();               // Returns the song.
    }

    // Searches for existence of the song depending on the value of the song. Song is the input. Returns boolean.
    public boolean contains(Song song) {
        for(int i = 0; i < count; i++){
            if (data[i].getSong() == song){
                return true;                    // If song exists; return true.
            }
        }
        return false;                           // Fail case, if song does not exist; return false.
    }

    //-----------------------------------------------------------------
    // Utilities
    //-----------------------------------------------------------------

    /// Returns a truth value indicating whether the catalog's structural
    /// integrity remains valid.  If the integrity is no longer valid,
    /// then the catalog should be invalidated and usage should not be 
    /// trusted
    /// @return true if the catalog integrity is valid; otherwise, false
    public boolean isIntegrityValid() {
        if(count < 0) {
            return false;
        }
        if(data == null) {
            return false;
        }
        if(count > data.length) {
            return false;
        }
        for(int i = 0; i < count; i++) {
            if(data[i] == null) {
                return false;
            }
        }

        return true;
    }

    /// Returns a string that contains information about the list and the 
    /// contents of the list.  This is mostly useful for visual debugging 
    /// @return a string containing information about the contents of the 
    ///         catalog
    public String toString() {
        String s = "";
        s = "ArrayList::allocated=" + data.length;
        s += ", count=" + count(); 
        s += ", isEmpty=" + isEmpty(); 
        s += ", ["; 
        for(int i = 0; i < count; i++) {
            if(i > 0) {
                s += ", ";
            }
            s += data[i].getSong().getTitle();
            s += " | ";
            s += data[i].getSong().getYear();
        }
        s += "]";
        return s;
    }

    /// Returns the earliest and most recent years of all the songs in the
    /// catalog and then clears the catalog of all songs
    /// @return an array of the years of the earliest and most recent songs
    public int[] publish() {
        int oldYear = Integer.MAX_VALUE;
        int newYear = Integer.MIN_VALUE;

        for(int i = 0; i < count; i++) {
            int curYear = data[i].getSong().getYear();
            if (curYear < oldYear) oldYear = curYear;
            if (curYear > newYear) newYear = curYear;
        }
        clear();
        return new int[] { oldYear, newYear };
    }

}
