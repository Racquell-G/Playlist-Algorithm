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
 * @version 2023.04.04
 *
 */
public class SongTest extends TestCase {

    // fields
    private Song fave;

    /**
     * Set up the test cases
     */
    public void setUp() {
        fave = new Song("100$ Bill", 80, 35, 0, "p30");
    }


    /**
     * Test toString method
     */
    public void testToString() {
        String result = "100$ Bill Pop:80 Rock:35 Country:0 Suggested: p30";
        assertEquals(result, fave.toString());
        Song fave2 = new Song("100$ Bill", 80, 35, 0, "");
        String result2 = "No-Playlist 100$ Bill Pop:80 Rock:35 Country:0";
        assertEquals(result2, fave2.toString());
    }


    /**
     * Test equals method
     */
    public void testEquals() {
        Song dup = new Song("100$ Bill", 80, 35, 0, "p30");
        Song s1 = new Song("100 Bill", 80, 35, 0, "p30");
        Song s2 = new Song("100$ Bill", 90, 35, 0, "p30");
        Song s3 = new Song("100$ Bill", 80, 40, 0, "p30");
        Song s4 = new Song("100$ Bill", 80, 35, 8, "p30");
        Song s5 = new Song("100$ Bill", 80, 35, 0, "greenbrier pkwy");

        String notSong = "";
        Song fakeSong = null;
        assertEquals(false, fave.equals(fakeSong));
        assertEquals(false, fave.equals(notSong));
        assertEquals(false, fave.equals(s1));
        assertEquals(false, fave.equals(s2));
        assertEquals(false, fave.equals(s3));
        assertEquals(false, fave.equals(s4));
        assertEquals(false, fave.equals(s5));
        assertEquals(true, fave.equals(dup));
        assertEquals(true, fave.equals(fave));

    }

}
