/*--------------------------------------------------------------------------
GWU CSCI 1112 Spring 2026
author: Akram Atassi, Charles Peeke 
--------------------------------------------------------------------------*/

import java.util.LinkedList;

public class Stack {
    private StackElement top;               // Defines top as its described in the stack interface.
    private int count;                      // Number of elements in the stack.
    
    // Defines the stack class that will be used in the following functions.
    public Stack() {
        this.top = null;
        this.count = 0;

    }
    
    // Pushes the input element s to the top of the stack. Returns void.
    public void push(String s) {
        StackElement temp = new StackElement(s);            // Temporary holder for the value to be set as the top.
        temp.next = this.top;
        this.top = temp;                                    // Set top's value to the temporary holder's.
        this.count++;                                       // Increment count.
    }
    
    // Pops the top of the stack. Returns the popped element.
    public String pop() {
        if (isEmpty()) return null;                         // Edge case, if stack does not exist, returns null.
        String temp = this.top.value;                       // Temporary holder for the value to be popped.
        this.top = this.top.next;                           // Replace top with top.next
        this.count--;                                       // Decrement count.
        return temp;                                        // Returns temporary holder.
    }
    
    // Checks if stack is empty. No input, returns boolean. 
    public boolean isEmpty() {
        return top == null;              // Returns conditional statement of top = null. if top = null, return true. If not, return false.
    }

    // Returns the size of the stack. No input.
    public int size() {
        return this.count;              // Returns count.
    }

    // Returns current value of the stack
    @Override
    public String toString() {
        String s = "";
        StackElement current = this.top;
        while (current != null) {
            s += current + " ";
            current = current.next;
        }
        return s;
    }
}


