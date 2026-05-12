/*--------------------------------------------------------------------------
GWU CSCI 1112 Spring 2026
author: Akram Atassi, Charles Peeke 

--------------------------------------------------------------------------*/

public class StackElement {
    public String value;                            // Creates the first value of the stack.
    public StackElement next;                       // Creates the next pointer as defined by the stack interface.

    // Defines the stack class to be used in the future. 
    public StackElement(String value) {
        this.value = value;                         // Defines the first value as equal to itself.
        this.next = null;                           // Defines the value after the first as null.
    }

    // Returns the current value of the stack.
    @Override
    public String toString() {
        return "StackElement [value=" + value + ", next=" + next + "]";
    }
}
