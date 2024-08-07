package dailymixes;

import queue.EmptyQueueException;
import student.TestCase;

/**
 * @author Racquell Grey racquellg
 * @version 2023.04.06
 * @param <T>
 *
 */
public class ArrayQueueTest<T> extends TestCase {

    // fields
    private ArrayQueue<Song> aq;

    /**
     * Sets up the test cases
     */
    public void setUp() {
        aq = new ArrayQueue<Song>();
    }


    /**
     * Test clear method
     */
    public void testClear() {
        ArrayQueue<Song> p = new ArrayQueue<Song>(5);
        Song s1 = new Song("100$ Bill", 80, 35, 0, "p30");
        for (int i = 0; i < 5; i++) {
            p.enqueue(s1);
        }
        assertEquals(5, p.getSize());

        p.clear();
        assertEquals(0, p.getSize());

    }


    /**
     * Test toString method
     */
    public void testToString() {
        assertEquals("[]", aq.toString());
        Song s1 = new Song("100$ Bill", 80, 35, 0, "p30");
        aq.enqueue(s1);
        assertEquals(1, aq.getSize());
        Song s2 = new Song("Fingertips", 50, 25, 5, "america");
        aq.enqueue(s2);
        assertEquals(2, aq.getSize());
        String result = "[100$ Bill Pop:80 Rock:35 Country:0 Suggested: p30, "
            + "Fingertips Pop:50 Rock:25 Country:5 Suggested: america]";
        assertEquals(result, aq.toString());

    }


    /**
     * Test getSize method
     */
    public void testGetSize() {
        assertEquals(0, aq.getSize());
    }


    /**
     * Test equals method
     */
    public void testEquals() {
        Song s1 = new Song("100$ Bill", 80, 35, 0, "p30");
        ArrayQueue<Song> empt = new ArrayQueue<Song>();
        aq.enqueue(s1);
        Object[] dupe = aq.toArray();
        Object obj = null;
        ArrayQueue<Song> dupl = new ArrayQueue<Song>();
        dupl.enqueue(s1);

        assertEquals(true, aq.equals(aq));
        assertEquals(false, aq.equals(dupe));
        assertEquals(false, aq.equals(obj));
        assertEquals(false, aq.equals(empt));
        assertEquals(true, aq.equals(dupl));

        Song s2 = new Song("Fingertips", 50, 25, 5, "america");
        dupl.dequeue();
        dupl.enqueue(s2);
        assertEquals(false, aq.equals(dupl));
    }


    /**
     * Test enqueue method
     */
    public void testEnqueue() {
        Song s1 = new Song("100$ Bill", 80, 35, 0, "p30");
        aq.enqueue(s1);
        assertEquals(1, aq.getSize());
        Song s2 = new Song("Fingertips", 50, 25, 0, "america");
        aq.enqueue(s2);
        assertEquals(2, aq.getSize());

        ArrayQueue<Song> p = new ArrayQueue<Song>(5);
        p.clear();

        assertEquals(6, p.getLengthOfUnderlyingArray());

        for (int i = 0; i < 5; i++) {
            p.enqueue(s1);
        }

        assertEquals(5, p.getSize());
        p.enqueue(s2);
        assertEquals(11, p.getLengthOfUnderlyingArray());
    }


    /**
     * Test dequeue method
     */
    public void testDequeue() {
        Exception thrown = null;
        try {
            aq.dequeue();
        }
        catch (Exception e) {
            thrown = e;
        }
        assertNotNull(thrown);
        assertTrue(thrown instanceof EmptyQueueException);

        Song s1 = new Song("100$ Bill", 80, 35, 0, "p30");
        aq.enqueue(s1);
        assertEquals(1, aq.getSize());
        Song s2 = new Song("Fingertips", 50, 25, 5, "america");
        aq.enqueue(s2);
        assertEquals(2, aq.getSize());

        assertEquals(s1, aq.dequeue());
    }


    /**
     * Test getLengthOfUnderlyingArray method
     */
    public void testGetLengthOfUnderlyingArray() {
        assertEquals(21, aq.getLengthOfUnderlyingArray());
    }


    /**
     * Test getFront method
     */
    public void testGetFront() {
        Exception thrown = null;
        try {
            aq.getFront();
        }
        catch (Exception e) {
            thrown = e;
        }
        assertNotNull(thrown);
        assertTrue(thrown instanceof EmptyQueueException);

        thrown = null;
        Song s1 = new Song("100$ Bill", 80, 35, 0, "p30");
        aq.enqueue(s1);

        try {
            aq.getFront();
        }
        catch (Exception e) {
            thrown = e;
        }
        assertNull(thrown);
        assertFalse(thrown instanceof EmptyQueueException);
    }


    /**
     * Test toArray method
     */
    public void testToArray() {
        Exception thrown = null;
        try {
            aq.toArray();
        }
        catch (Exception e) {
            thrown = e;
        }
        assertNotNull(thrown);
        assertTrue(thrown instanceof EmptyQueueException);

        Song s1 = new Song("100$ Bill", 80, 35, 0, "p30");
        Song s2 = new Song("Fingertips", 50, 25, 5, "america");
        aq.enqueue(s1);
        aq.enqueue(s2);
        Object[] arr = new Object[0];

        thrown = null;
        try {
            arr = aq.toArray();
        }
        catch (Exception e) {
            thrown = e;
        }
        assertNull(thrown);
        assertFalse(thrown instanceof EmptyQueueException);
        assertEquals(2, arr.length);
        assertEquals(s1, arr[0]);
    }

}
