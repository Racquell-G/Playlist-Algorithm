// Virginia Tech Honor Code Pledge:
// f
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Racquell Grey racquellg

package dailymixes;

import student.TestCase;

/**
 * @author Racquell Grey racquellg
 * @version 2023.01.04
 *
 */
public class GenreSetTest extends TestCase {

    // fields
    private GenreSet gs;

    /**
     * Sets up the test cases
     */
    public void setUp() {
        gs = new GenreSet(50, 30, 20);
    }


    /**
     * Tests the toString method
     */
    public void testToString() {
        assertEquals("Pop:50 Rock:30 Country:20", gs.toString());
    }


    /**
     * Tests the equals method
     */
    public void testEquals() {
        GenreSet gs2 = null;
        String notGS = "";
        GenreSet gs3 = new GenreSet(50, 30, 20); // base (same pop rock and
                                                 // country)

        GenreSet gs4 = new GenreSet(50, 20, 30); // same pop
        GenreSet gs5 = new GenreSet(30, 50, 20); // diff pop

        // GenreSet gs6 = new GenreSet(50, 30, 20); //base
        GenreSet gs6 = new GenreSet(40, 30, 30); // same rock
        GenreSet gs7 = new GenreSet(30, 50, 20); // diff rock

        // GenreSet gs9 = new GenreSet(50, 30, 20); //base
        GenreSet gs8 = new GenreSet(50, 30, 5); // diff country

        assertEquals(false, gs.equals(gs2));
        assertEquals(false, gs.equals(notGS));
        assertEquals(true, gs.equals(gs3));
        assertEquals(true, gs.equals(gs));
        assertEquals(false, gs.equals(gs4));
        assertEquals(false, gs.equals(gs5));
        assertEquals(false, gs.equals(gs6));
        assertEquals(false, gs.equals(gs8));
        assertEquals(false, gs.equals(gs7));
        assertEquals(false, gs.equals(gs8));
    }


    /**
     * Test isWithinRange method
     */
    public void testIsWithinRange() {
        // base case : 50 30 20
        GenreSet out = new GenreSet(1, 1, 1);
        GenreSet lowerTest = new GenreSet(5, 5, 5);
        GenreSet upperTest = new GenreSet(90, 90, 90);
        // easy
        GenreSet gs2 = new GenreSet(5, 5, 5);
        GenreSet gs3 = new GenreSet(90, 90, 90);
        // pop
        GenreSet gs4 = new GenreSet(5, 5, 5);
        GenreSet gs5 = new GenreSet(50, 90, 90);
        // rock - pass
        GenreSet gs6 = new GenreSet(5, 5, 5);
        GenreSet gs7 = new GenreSet(90, 40, 90);
        // rock - fail/pass
        GenreSet gs8 = new GenreSet(50, 40, 20);
        GenreSet gs9 = new GenreSet(90, 90, 90);
        GenreSet gs10 = new GenreSet(90, 10, 20);
        // country
        GenreSet gs12 = new GenreSet(50, 30, 30);
        GenreSet gs13 = new GenreSet(50, 30, 10);

        // easy
        assertEquals(true, gs.isWithinRange(gs2, gs3));
        assertEquals(true, lowerTest.isWithinRange(gs2, gs3));
        assertEquals(true, upperTest.isWithinRange(gs2, gs3));
        assertEquals(false, out.isWithinRange(gs2, gs3));
        // pop
        assertEquals(true, gs.isWithinRange(gs4, gs5));
        assertEquals(true, lowerTest.isWithinRange(gs4, gs5));
        assertEquals(false, upperTest.isWithinRange(gs4, gs5));
        assertEquals(false, out.isWithinRange(gs4, gs5));
        // rock
        assertEquals(true, gs.isWithinRange(gs6, gs7));
        assertEquals(true, lowerTest.isWithinRange(gs6, gs7));
        assertEquals(false, upperTest.isWithinRange(gs6, gs7));
        assertEquals(false, out.isWithinRange(gs6, gs7));
        assertEquals(false, lowerTest.isWithinRange(gs8, gs7));
        assertEquals(false, upperTest.isWithinRange(gs8, gs7));
        assertEquals(false, gs.isWithinRange(gs8, gs9));
        assertEquals(false, gs.isWithinRange(lowerTest, gs10));
        // country
        assertEquals(true, gs.isWithinRange(lowerTest, upperTest));
        assertEquals(false, out.isWithinRange(lowerTest, upperTest));
        assertEquals(false, gs.isWithinRange(gs12, upperTest));
        assertEquals(false, gs.isWithinRange(lowerTest, gs13));
    }


    /**
     * Test compareTo method
     */
    public void testCompareTo() {
        GenreSet gs2 = new GenreSet(50, 30, 20);
        GenreSet lowerTest = new GenreSet(5, 5, 5);
        GenreSet upperTest = new GenreSet(90, 90, 90);

        assertEquals(0, gs.compareTo(gs2));
        assertEquals(1, gs.compareTo(lowerTest));
        assertEquals(-1, gs.compareTo(upperTest));
    }

}
