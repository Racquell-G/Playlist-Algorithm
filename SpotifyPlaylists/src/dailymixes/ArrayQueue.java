package dailymixes;

import queue.EmptyQueueException;
import queue.QueueInterface;

/**
 * @author Racquell Grey racquellg
 * @version 2023.04.05
 * @param <T>
 *
 */
public class ArrayQueue<T> implements QueueInterface<T> {

    /**
     * Default capacity
     */
    public static final int DEFAULT_CAPACITY = 20;
    private T[] queue;
    private int dequeueIndex; // front index
    private int size;
    private int enqueueIndex; // back index

    /**
     * Constructor for ArrayQueue w/parameter
     * 
     * @param capacity
     *            the capacity to set the queue at
     */
    public ArrayQueue(int capacity) {
        queue = (T[])new Object[capacity + 1];
        dequeueIndex = 0;
        size = 0;
        enqueueIndex = queue.length - 1;
    }


    /**
     * Default constructor for ArrayQueue w/ no parameter
     */
    public ArrayQueue() {
        this(DEFAULT_CAPACITY);
    }


    /**
     * Resets the object to its default state
     */
    @Override
    public void clear() {
        while (!isEmpty()) {
            dequeue();
        }
        dequeueIndex = 0;
        size = 0;
        enqueueIndex = queue.length - 1;

    }


    /**
     * Increments the index
     * 
     * @param index
     *            index to increment
     * @return int
     *         index to increment by
     */
    private int incrementIndex(int index) {
        return ((index + 1) % queue.length);
    }


    /**
     * String representation of array queue
     * 
     * @return result
     */
    public String toString() {
        final StringBuilder result = new StringBuilder();
        if (getSize() == 0) {
            return "[]";
        }
        result.append("[");
        for (int i = dequeueIndex; i <= enqueueIndex; i++) {
            result.append(queue[i].toString());
            result.append(", ");
        }
        result.setLength(result.length() - 2);

        result.append("]");
        return result.toString();
    }


    /**
     * Determines whether or not the array queues are equal
     * 
     * @param obj
     *            the object to determined if equal to
     * @return boolean
     *         whether or not the object equals whats calling this method
     */
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass().equals(obj.getClass())) {
            ArrayQueue aq = (ArrayQueue)obj;
            if (this.getSize() != aq.getSize()) {
                return false;
            }

            for (int i = 0; i < this.getSize(); i++) {
                T curr = queue[(dequeueIndex + i) % queue.length];
                T other =
                    ((ArrayQueue<T>)obj).queue[(((ArrayQueue<T>)obj)
                        .dequeueIndex + i) % ((ArrayQueue<T>)obj)
                                               .queue.length];
                if (!curr.equals(other)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }


    /**
     * Removes first item from the queue array
     */
    @Override
    public T dequeue() {
        if (size == 0) {
            throw new EmptyQueueException();
        }
        T front = getFront();
        queue[dequeueIndex] = null;
        dequeueIndex = incrementIndex(dequeueIndex);
        size--;
        return front;
    }


    /**
     * Helper method to upgrade the length of the underlying array when the
     * queue is full.
     * 
     * The capacity of the expanded queue should be twice as long as the
     * capacity of the old queue
     * 
     */
    private void ensureCapacity() {

        if (isFull()) {
            T[] oldArray = queue;
            int oldSize = queue.length;
            int newSize = 2 * oldSize - 1;
            queue = (T[])new Object[newSize];

            int j = dequeueIndex;
            for (int i = 0; i < oldSize - 1; i++) {
                queue[i] = oldArray[j];
                j = (j + 1) % oldSize;
            }

            dequeueIndex = 0;
            enqueueIndex = oldSize - 2;
        }
    }


    /**
     * Adds something into the array queue
     * 
     * @param anEntry
     *            entry that is to be added to the queue
     */
    @Override
    public void enqueue(T anEntry) {
        ensureCapacity();
        enqueueIndex = incrementIndex(enqueueIndex);
        // queue[incrementIndex(enqueueIndex)] = anEntry;
        queue[enqueueIndex] = anEntry;
        size++;
    }


    /**
     * Should return the length of the underlying array
     * 
     * @return queue.length
     *         the length of the array of the queue
     * 
     */
    public int getLengthOfUnderlyingArray() {
        return queue.length;
    }


    /**
     * Returns item at the front index aka dequeue index
     * 
     * @return T
     *         whatever item is at the front of the queue array
     */
    @Override
    public T getFront() {
        if (size == 0) {
            throw new EmptyQueueException();
        }
        return queue[dequeueIndex];
    }


    /**
     * Returns the number of items in the queue
     * 
     * @return size
     *         the number of items
     */
    public int getSize() {
        return this.size;
    }


    /**
     * Whether or not the end index reached the size
     * 
     * @return boolean
     *         whether it is full or not
     */
    private boolean isFull() {
        return ((enqueueIndex + 2) % queue.length == dequeueIndex);
    }


    /**
     * Copies array contents
     * 
     * @return arr
     *         copy of underlying array
     */
    public Object[] toArray() {
        if (isEmpty()) {
            throw new EmptyQueueException("Empty Queue");
        }
        T[] arr = (T[])new Object[size];

        int arrDeq = dequeueIndex;
        for (int i = 0; i < this.getSize(); i++) {
            arr[i] = queue[arrDeq];
            arrDeq = incrementIndex(arrDeq);
        }
        return arr;
    }


    /**
     * Returns boolean that says whether the array queue is empty
     * 
     * @return boolean
     *         whether or not the array queue is empty
     */
    @Override
    public boolean isEmpty() {
        return (this.getSize() == 0);
    }

}
