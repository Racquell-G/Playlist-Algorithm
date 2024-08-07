package dailymixes;

import student.TestCase;

/**
 * @author Racquell Grey racquellg
 * @version 2023.04.14
 *
 */
public class PlaylistCalculatorTest extends TestCase {

    // fields
    private ArrayQueue<Song> songQ;
    private Playlist[] playlists;
    private Song popSong;
    private Song rockSong;
    private PlaylistCalculator pc;

    /**
     * Sets up the method
     */
    public void setUp() {
        songQ = new ArrayQueue();
        playlists = new Playlist[3];
        Playlist pop = new Playlist("pop", 40, 10, 10, 100, 100, 100, 5);
        Playlist rock = new Playlist("rock", 10, 40, 10, 100, 100, 100, 5);
        Playlist country = new Playlist("country", 40, 10, 10, 100, 100, 100,
            5);
        popSong = new Song("Blue Skies", 50, 20, 11, "pop");
        rockSong = new Song("Pattern", 50, 50, 50, "rock");
        Song countrySong = new Song("Old Town Road", 50, 50, 90, "country");
        playlists[0] = pop;
        playlists[1] = rock;
        playlists[2] = country;
        songQ.enqueue(popSong);
        songQ.enqueue(rockSong);
        songQ.enqueue(countrySong);
        pc = new PlaylistCalculator(songQ, playlists);
    }


    /**
     * Test getQueue method
     */
    public void testGetQueue() {
        assertEquals(songQ, pc.getQueue());
    }


    /**
     * Test getPlaylists method
     */
    public void testGetPlaylists() {
        assertEquals(playlists, pc.getPlaylists());
    }


    /**
     * Test reject method
     */
    public void testReject() {
        pc.reject();
        assertEquals(rockSong, songQ.getFront());
    }


    /**
     * Test getPlaylistIndex method
     */
    public void testGetPlaylistIndex() {
        assertEquals(0, pc.getPlaylistIndex("pop"));
        assertEquals(1, pc.getPlaylistIndex("rock"));
        assertEquals(2, pc.getPlaylistIndex("country"));
        assertEquals(-1, pc.getPlaylistIndex("i lied"));
    }


    /**
     * Test get playlist for song
     */
    public void testGetPlaylistForSong() {
        Song fakeSong = null;
        Song superPop = new Song("Super Pop", 90, 90, 90, "pop");
        Song notPop = new Song("Not Pop", 90, 90, 90, "NOT ON POP PLAYLIST");
        Song failPop = new Song("Fail Pop", 0, 0, 0, "pop");
        Song failPop2 = new Song(null, 0, 0, 0, null);
        assertEquals(null, pc.getPlaylistForSong(fakeSong));
        assertEquals(playlists[0], pc.getPlaylistForSong(superPop));
        assertEquals(playlists[2], pc.getPlaylistForSong(notPop));
        assertEquals(null, pc.getPlaylistForSong(null));
        assertEquals(null, pc.getPlaylistForSong(failPop));
        assertEquals(null, pc.getPlaylistForSong(failPop2));
    }


    /**
     * Exception for playlist test
     */
    public void testExceptionPlaylistCalculatorTest() {
        Exception thrown = null;

        try {
            PlaylistCalculator pcWrong = new PlaylistCalculator(null,
                playlists);
        }
        catch (Exception e) {
            thrown = e;
        }
        assertNotNull(thrown);
        assertTrue(thrown instanceof IllegalArgumentException);
    }


    /**
     * Test addSongToPlaylist method
     */
    public void testAddSongToPlaylist() {
        ArrayQueue<Song> fakeQ = new ArrayQueue();
        PlaylistCalculator emptyCalc = new PlaylistCalculator(fakeQ, playlists);
        assertEquals(false, emptyCalc.addSongToPlaylist());

        assertEquals(true, pc.addSongToPlaylist());
        assertEquals(true, pc.addSongToPlaylist());
        assertEquals(true, pc.addSongToPlaylist());
        songQ.enqueue(null);
        assertEquals(false, pc.addSongToPlaylist());
        songQ.enqueue(popSong);
        songQ.enqueue(popSong);
        songQ.enqueue(popSong);
        songQ.enqueue(popSong);
        songQ.enqueue(popSong);
        assertEquals(true, pc.addSongToPlaylist());
        assertEquals(true, pc.addSongToPlaylist());
        assertEquals(true, pc.addSongToPlaylist());
        assertEquals(true, pc.addSongToPlaylist());
        assertEquals(null, pc.getPlaylistForSong(songQ.getFront()));
    }
}
