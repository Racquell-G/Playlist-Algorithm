
package dailymixes;

import java.util.Arrays;
import java.util.Comparator;
import list.AList;

/**
 * @author Racquell Grey racquellg
 * @version 2023.04.13
 *
 */
public class PlaylistCalculator {

    /**
     * num playlists the amount
     * min percent the minimum percentage
     * max percent the maximum percentage
     */
    public static final int NUM_PLAYLISTS = 3;
    /**
     * num playlists the amount
     * min percent the minimum percentage
     * max percent the maximum percentage
     */
    public static final int MIN_PERCENT = 0;
    /**
     * num playlists the amount
     * min percent the minimum percentage
     * max percent the maximum percentage
     */
    public static final int MAX_PERCENT = 100;

    // more fields
    private Playlist[] playlists;
    private AList<Song> rejectedTracks;
    private ArrayQueue<Song> songQueue;

    /**
     * Constructor for playlist calculator
     * 
     * @param songQ
     *            an array queue
     * @param p
     *            playlist array
     */
    public PlaylistCalculator(ArrayQueue<Song> songQ, Playlist[] p) {
        if (songQ == null) {
            throw new IllegalArgumentException();
        }
        songQueue = songQ;
        playlists = p;
        rejectedTracks = new AList<Song>();
    }


    /**
     * Next song in line should be put on the list of
     * rejected tracks. Remove the Song from the queue and add it to an
     * AList.
     */
    public void reject() {
        Song reject = songQueue.dequeue();
        rejectedTracks.add(reject);
    }


    /**
     * Returns the playlist with the most room
     * 
     * @param aSong
     *            for the playlist
     */
    private Playlist getPlaylistWithMostRoom(Song aSong) {
        Playlist[] copyArr = Arrays.copyOf(playlists, NUM_PLAYLISTS);
        Arrays.sort(copyArr, Comparator.reverseOrder());

        for (int i = 0; i < copyArr.length; i++) {
            if (canAccept(copyArr[i], aSong)) {
                return copyArr[i];
            }
        }
        return null;
    }


    /**
     * Adds the song to the playlist
     * 
     * @return boolean
     *         whether or not the song was added to the playlist
     */
    public boolean addSongToPlaylist() {
        if (songQueue.isEmpty()) {
            return false;
        }
        Song aSong = songQueue.getFront();
        Playlist rec = this.getPlaylistForSong(aSong);
        if (rec != null) {
            rec.addSong(aSong);
            songQueue.dequeue();
            return true;
        }
        else {
            reject();
            return false;
        }
    }


    /**
     * This method will determine if the next song can be added to a playlist
     * 
     * @param nextSong
     *            the song to get a playlist for
     * @return playlist
     *         playlist for the song
     */
    public Playlist getPlaylistForSong(Song nextSong) {
        if (nextSong == null) {
            return null;
        }
        String pName = nextSong.getPlaylistName();
        int index = this.getPlaylistIndex(pName);
        if (pName != null && index > -1) {
            if (canAccept(playlists[index], nextSong)) {
                return playlists[index];
            }
            return null;
        }
        else {
            return this.getPlaylistWithMostRoom(nextSong);
        }
    }


    /**
     * Return the ArrayQueue of songs
     * 
     * @return songQueue
     *         the song queue
     */
    public ArrayQueue<Song> getQueue() {
        return songQueue;
    }


    /**
     * Determines if a song can be accepted on a given playlist
     * 
     * @param playlist
     *            the playlist the song will be added
     * @param song
     *            the song to be added toe the playlist
     */
    private boolean canAccept(Playlist playlist, Song song) {
        return ((!playlist.isFull()) && song.getGenreSet().isWithinRange(
            playlist.getMinGenreSet(), playlist.getMaxGenreSet()));

    }


    /**
     * Return the int representation for the playlist name
     * 
     * @param playlist
     *            the playlist name
     * @return int
     *         index in the playlists array
     */
    public int getPlaylistIndex(String playlist) {
        for (int i = 0; i < playlists.length; i++) {
            if (playlists[i].getName().equals(playlist)) {
                return i;
            }
        }
        return -1;
    }


    /**
     * Return the array of playlists
     * 
     * @return playlists
     *         the array of playlists
     */
    public Playlist[] getPlaylists() {
        return playlists;
    }
}
