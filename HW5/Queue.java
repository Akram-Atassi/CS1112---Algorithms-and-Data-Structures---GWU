/*--------------------------------------------------------------------------
GWU CSCI 1112 Spring 2026
author: Akram Atassi, Charles Peeke 
--------------------------------------------------------------------------*/

// Defines the necessary elements for the definition of a Queue.
public class Queue {
    private QueueElement front;             // front of the queue.
    private QueueElement back;              // back of the queue.
    private int count;                      // Number of elements in the queue.

    // Creates the class that will be used in the following functions.
    public Queue() {
        this.front = null;
        this.back = null;
        this.count = 0;
    }

    // Appends the input t of type transaction to the end of the queue. Returns void.
    public void enqueue(Transaction t) {
        QueueElement temp = new QueueElement(t);
        if (this.front == null) {          // Edge case, if queue is empty, t becomes both the front and back until the next enqueue.
            this.front = temp;
            this.back = temp;
        }
        else{
            this.back.next = temp;         // Enqueues element to the back of the queue.
            this.back = temp;
        }
        this.count++;                      // Increment count.
    }

    // Removes the element present at the front of the queue. No input, returns void.
    public Transaction dequeue() {
        if(isEmpty()) return null;          // Edge case, if queue does not exist, return null.

        Transaction temp = this.front.value; // Temporary holder for value of the front; to be returned.
        this.front = this.front.next;       // Remove from the front.
        this.count--;                       // Decrement count.
        return temp;                        // Return temporary holder.
    }

    // Checks if queue is empty. No input, returns boolean.
    public boolean isEmpty() {
        return this.front == null;          // Returns conditional check of front. If front = null, return true. If not, false.
    }

    // Returns number of elements in the queue.
    public int size() { 
        return this.count;                  // Returns count.
    }

    // Combines all the elements to create the account.
    @Override
    public String toString() {
        String s = "";
        QueueElement current = this.front;
        while (current != null) {
            s += current.value.toString() + ";";
            current = current.next;
        }
        return s;
    }

}
