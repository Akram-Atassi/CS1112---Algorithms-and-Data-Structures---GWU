/*--------------------------------------------------------------------------
GWU CSCI 1112 Spring 2026
author: Akram Atassi, Charles Peeke 
--------------------------------------------------------------------------*/

public class QueueElement {
    public Transaction value;               // Defines the type of the elements in the queue, type = Transaction.
    public QueueElement next;               // Defines the next pointer of the queue as defined by its interface.

    public QueueElement(Transaction value) {
        this.value = value;                 // Defines the input as equal to itself.
        this.next = null;                   // Currently no next as its just been defined.
    }

    /// Returns current state of the queue.
    @Override
    public String toString() {
        return "QueueElement [value=" + value + ", next=" + next + "]";
    }
}
