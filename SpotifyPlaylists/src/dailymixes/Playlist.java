// Virginia Tech Honor Code Pledge:
// f
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Racquell Grey racquellg

package dailymixes;

/**
 * @author Racquell Grey racquellg
 * @version 2023.04.10
 *
 */
public class Playlist implements Comparable<Playlist> {

    // fields
    private GenreSet minGenreSet;
    private GenreSet maxGenreSet;
    private Song[] songs;
    private int capacity;
    private int numberOfSongs;
    private String name;

    /**
     * Constructor for Playlist
     * 
     * @param playlistName
     *            name of the playlist
     * @param minPop
     *            minimum percentage for pop
     * @param minRock
     *            minimum percentage for rock
     * @param minCountry
     *            minimum percentage for country
     * @param maxPop
     *            maximum percentage for pop
     * @param maxRock
     *            maximum percentage for rock
     * @param maxCountry
     *            maximum percentage for country
     * @param playlistCap
     *            maximum capacity for playlist
     */
    public Playlist(
        String playlistName,
        int minPop,
        int minRock,
        int minCountry,
        int maxPop,
        int maxRock,
        int maxCountry,
        int playlistCap) {
        name = playlistName;
        minGenreSet = new GenreSet(minPop, minRock, minCountry);
        maxGenreSet = new GenreSet(maxPop, maxRock, maxCountry);
        capacity = playlistCap;
        numberOfSongs = 0;
        songs = new Song[capacity];
    }


    /**
     * Returns the minGenreSet
     * 
     * @return minGenreSet
     *         the minimum genreset
     */
    public GenreSet getMinGenreSet() {
        return minGenreSet;
    }


    /**
     * Sets the name of the playlist
     * 
     * @param input
     *            the new name of the playlist
     */
    public void setName(String input) {
        name = input;
    }


    /**
     * Returns the number of available spaces left in the
     * playlist. Uses the capacity and current number of songs on the playlist
     * 
     * @return (capacity - numberOfSongs)
     *         the spaces left in the playlist.
     * 
     */
    public int getSpacesLeft() {
        return (capacity - numberOfSongs);
    }


    /**
     * Returns the maxGenreSet
     * 
     * @return maxGenreSet
     *         the maximum genreset
     */
    public GenreSet getMaxGenreSet() {
        return maxGenreSet;
    }


    /**
     * Order the playlist based on capacity.
     * 
     * A playlist with more capacity will be greater than one with less. In the
     * event that 2 playlists have equal capacities then they will be ordered
     * based on spaces left (greatest to least).
     * 
     * In the event that the 2 playlists also have an equal number of slots
     * available then they will be ordered based on
     * MinGenreSet(see compareTo for GenreSet).
     * 
     * If 2 playlists have all three of those attributes the same then they will
     * be ordered based on MaxGenreSet.
     * 
     * Playlists that have all of the aforementioned
     * attributes in common will be ordered based on name.
     * 
     * @param other
     *            other playlist to be compared to
     * @return int
     *         integer that should be returned based on rules above
     * 
     */
    @Override
    public int compareTo(Playlist other) {
        if (this.getCapacity() > other.getCapacity()) {
            return 1;
        }
        else if (this.getCapacity() == other.getCapacity()) {
            if (this.getSpacesLeft() > other.getSpacesLeft()) {
                return 1;
            }
            else if (this.getSpacesLeft() == other.getSpacesLeft()) {
                if (this.getMinGenreSet().compareTo(other
                    .getMinGenreSet()) == 1) {
                    return 1;
                }
                else if (this.getMinGenreSet().compareTo(other
                    .getMinGenreSet()) == 0) {
                    if (this.getMaxGenreSet().compareTo(other
                        .getMaxGenreSet()) == 1) {
                        return 1;
                    }
                    else if (this.getMaxGenreSet().compareTo(other
                        .getMaxGenreSet()) == 0) {
                        if (this.getName().compareTo(other.getName()) == 1) {
                            return 1;
                        }
                        else if (this.getName().compareTo(other
                            .getName()) == 0) {
                            return 0;
                        }
                        else {
                            return -1;
                        }
                    }
                    else {
                        return -1;
                    }
                }
                else {
                    return -1;
                }
            }
            else {
                return -1;
            }
        }
        return -1;
    }


    /**
     * Returns the numberOfSongs
     * 
     * @return numberOfSongs
     *         the number of songs on the playlist
     */
    public int getNumberOfSongs() {
        return numberOfSongs;
    }


    /**
     * Attempt to add a Song to the Playlist.
     * 
     * It will need to check two things.
     * First, that the playlist has room available for this song. Secondly, it
     * will need to determine whether the song is qualified to be placed on the
     * playlist.
     * 
     * @param newSong
     *            new song to be added
     * 
     * @return boolean
     *         whether or not a new song will be added
     */
    public boolean addSong(Song newSong) {
        if (numberOfSongs < capacity && this.isQualified(newSong)) {
            songs[numberOfSongs] = newSong;
            numberOfSongs++;
            return true;
        }
        return false;
    }


    /**
     * String representation of playlist
     * 
     * @return result
     *         the results in string form
     */
    public String toString() {
        final StringBuilder result = new StringBuilder();
        result.append("Playlist: " + this.getName() + ", ");
        result.append("# of songs: " + this.getNumberOfSongs() + " ");
        result.append("(cap: " + this.getCapacity() + "), ");
        result.append("Requires: Pop:" + this.getMinGenreSet().getPop() + "%-"
            + this.getMaxGenreSet().getPop() + "%, ");
        result.append("Rock:" + this.getMinGenreSet().getRock() + "%-" + this
            .getMaxGenreSet().getRock() + "%, ");
        result.append("Country:" + this.getMinGenreSet().getCountry() + "%-"
            + this.getMaxGenreSet().getCountry() + "%");

        return result.toString();
    }


    /**
     * Returns boolean that if the numberOfSongs reaches capacity
     * 
     * @return boolean
     *         whether the playlist is at capacity.
     */
    public boolean isFull() {
        return (numberOfSongs == capacity);
    }


    /**
     * Two playlists are equal if all 8 their input fields are equal and they
     * have the the same songs in the same order.
     * 
     * @param obj
     *            object to be compared to
     * 
     * @return boolean
     *         whether the playlist is equal to another object
     */
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass().equals(obj.getClass())) {
            Playlist plist = (Playlist)obj;

            if (this.toString().equals(plist.toString())) {
                for (int i = 0; i < this.getNumberOfSongs(); i++) {
                    if (!(this.getSongs()[i].equals(plist.getSongs()[i]))) {
                        return false;
                    }
                }
                return true;
            }
            return false;
        }
        return false;
    }


    /**
     * Gets the array of songs
     * 
     * @return songs
     *         array of songs
     */
    public Song[] getSongs() {
        return songs;
    }


    /**
     * Gets the capacity of the playlist
     * 
     * @return capacity
     *         the capacity of the playlist
     */
    public int getCapacity() {
        return capacity;
    }


    /**
     * Gets the name of the playlist
     * 
     * @return name
     *         the name of the playlist
     */
    public String getName() {
        return name;
    }


    /**
     * Whether or not a song is qualified
     * 
     * @param possibleSong
     *            the possible song to be qualified for
     * 
     * @return boolean
     *         whether or not a song is qualified
     */
    public boolean isQualified(Song possibleSong) {
        return possibleSong.getGenreSet().isWithinRange(minGenreSet,
            maxGenreSet);
    }
}
