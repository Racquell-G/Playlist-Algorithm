
package dailymixes;

import student.TestCase;

/**
 * @author Racquell Grey racquellg
 * @version 2023.04.11
 *
 */
public class PlaylistTest extends TestCase {

    // fields
    private Playlist play;

    /**
     * Sets up the test methods
     */
    public void setUp() {
        play = new Playlist("p30", 40, 35, 0, 99, 99, 80, 5);
    }


    /**
     * Test getMinGenreSet method
     */
    public void testGetMinGenreSet() {
        GenreSet min = new GenreSet(40, 35, 0);
        assertEquals(min, play.getMinGenreSet());
    }


    /**
     * Test setName method
     */
    public void testSetName() {
        assertEquals("p30", play.getName());
        play.setName("bos");
        assertEquals("bos", play.getName());
    }


    /**
     * Test getSpacesLeft method
     */
    public void testGetSpacesLeft() {
        assertEquals(5, play.getSpacesLeft());
    }


    /**
     * Test getMaxGenreSet method
     */
    public void testGetMaxGenreSet() {
        GenreSet max = new GenreSet(99, 99, 80);
        assertEquals(max, play.getMaxGenreSet());
    }


    /**
     * Test compareTo method
     */
    public void testCompareTo() {
        Playlist smallCap = new Playlist("p30", 40, 35, 0, 99, 99, 80, 4);
        Playlist bigCap = new Playlist("p30", 40, 35, 0, 99, 99, 80, 6);

        assertEquals(1, play.compareTo(smallCap));
        assertEquals(-1, play.compareTo(bigCap));

        Playlist sameCap = new Playlist("p30", 40, 35, 0, 99, 99, 80, 5);
        Song s1 = new Song("100$ Bill", 80, 35, 0, "p30");
        sameCap.addSong(s1);
        assertEquals(1, play.compareTo(sameCap));
        play.addSong(s1);
        play.addSong(s1);
        assertEquals(-1, play.compareTo(sameCap));
        sameCap.addSong(s1);

        // min test
        Playlist smallMin = new Playlist("p30", 0, 0, 0, 100, 100, 100, 5);
        Playlist bigMin = new Playlist("p30", 100, 100, 100, 100, 100, 100, 5);
        smallMin.addSong(s1);
        smallMin.addSong(s1);
        bigMin.addSong(s1);
        bigMin.addSong(s1);
        assertEquals(1, play.compareTo(smallMin));
        assertEquals(-1, smallMin.compareTo(play));

        Playlist smallMax = new Playlist("p30", 40, 35, 0, 10, 10, 0, 5);
        Playlist bigMax = new Playlist("p30", 40, 35, 0, 100, 100, 100, 5);
        smallMax.addSong(s1);
        smallMax.addSong(s1);
        bigMax.addSong(s1);
        bigMax.addSong(s1);
        assertEquals(-1, play.compareTo(smallMax));
        assertEquals(1, bigMax.compareTo(play));
        assertEquals(-1, play.compareTo(bigMax));

        Playlist smallName = new Playlist("p3", 40, 35, 0, 99, 99, 80, 5);
        Playlist bigName = new Playlist("p300", 40, 35, 0, 99, 99, 80, 5);
        Playlist theSame = new Playlist("p30", 40, 35, 0, 99, 99, 80, 5);
        smallName.addSong(s1);
        smallName.addSong(s1);
        bigName.addSong(s1);
        bigName.addSong(s1);
        theSame.addSong(s1);
        theSame.addSong(s1);
        assertEquals(1, play.compareTo(smallName));
        assertEquals(-1, smallName.compareTo(play));
        assertEquals(-1, play.compareTo(bigName));
        assertEquals(-1, play.compareTo(bigName));
        assertEquals(0, play.compareTo(theSame));
    }


    /**
     * Test getNumberOfSongs method
     */
    public void testGetNumberOfSongs() {
        assertEquals(0, play.getNumberOfSongs());
    }


    /**
     * Test addSong method
     */
    public void testAddSong() {
        Song s1 = new Song("100$ Bill", 80, 35, 0, "p30");
        assertEquals(0, play.getNumberOfSongs());
        assertEquals(true, play.addSong(s1));
        assertEquals(1, play.getNumberOfSongs());

        Song wrong = new Song("100$ Bill", 0, 0, 0, "p30");
        assertEquals(false, play.isQualified(wrong));
        assertEquals(false, play.addSong(wrong));
        assertEquals(1, play.getNumberOfSongs());

        for (int i = 0; i < 5; i++) {
            play.addSong(s1);
        }
        assertEquals(5, play.getNumberOfSongs());
        assertEquals(false, play.addSong(s1));
        assertEquals(5, play.getNumberOfSongs());
        assertEquals(true, play.isFull());

    }


    /**
     * Test toString method
     */
    public void testToString() {
        String result =
            "Playlist: p30, # of songs: 0 (cap: 5), Requires: Pop:40%-99%, "
                + "Rock:35%-99%, Country:0%-80%";
        assertEquals(result, play.toString());
    }


    /**
     * Test isFull method
     */
    public void testIsFull() {
        assertEquals(false, play.isFull());
        Song s1 = new Song("100$ Bill", 80, 35, 0, "p30");
        for (int i = 0; i < 6; i++) {
            play.addSong(s1);
        }
        assertEquals(5, play.getNumberOfSongs());
        assertEquals(false, play.addSong(s1));
        assertEquals(5, play.getNumberOfSongs());
        assertEquals(true, play.isFull());

    }


    /**
     * Test isEquals method
     */
    public void testIsEquals() {
        Playlist p2 = new Playlist("p30", 40, 35, 0, 99, 99, 80, 5);
        Playlist p3 = new Playlist("p30", 40, 35, 0, 99, 99, 80, 5);

        Song s1 = new Song("100$ Bill", 80, 35, 0, "p30");
        Song wrong = new Song("100$ Bill", 80, 30, 0, "p30");
        Song s2 = new Song("Fingertips", 50, 35, 5, "p30");
        String notSong = "";
        Playlist fakeSong = null;

        play.addSong(s1);
        p2.addSong(wrong);

        assertEquals(true, play.equals(play));
        assertEquals(false, play.equals(fakeSong));
        assertEquals(false, play.equals(notSong));
        assertEquals(false, play.equals(p2));

        p2.addSong(s2);
        assertEquals(false, play.equals(p2));

        p3.addSong(s1);
        assertEquals(true, play.equals(p3));
    }


    /**
     * Test getSongs method
     */
    public void testGetSongs() {
        Song s1 = new Song("100$ Bill", 80, 35, 0, "p30");
        assertEquals(0, play.getNumberOfSongs());
        assertEquals(true, play.addSong(s1));
        assertEquals(1, play.getNumberOfSongs());
        assertEquals(s1, play.getSongs()[0]);
    }


    /**
     * Test getCapacity method
     */
    public void testGetCapacity() {
        assertEquals(5, play.getCapacity());
    }


    /**
     * Test getName method
     */
    public void testGetName() {
        assertEquals("p30", play.getName());
    }


    /**
     * Test isQualified method
     */
    public void testIsQualified() {
        Song s1 = new Song("100$ Bill", 80, 35, 0, "p30");
        Song wrong = new Song("100$ Bill", 80, 30, 0, "p30");
        assertEquals(true, play.isQualified(s1));
        assertEquals(false, play.isQualified(wrong));
    }
}
