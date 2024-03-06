// Virginia Tech Honor Code Pledge:
// f
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Racquell Grey racquellg

package dailymixes;

/**
 * @author Racquell Grey racquellg
 * @version 2023.04.04
 * 
 *
 */
public class Song {

    // fields
    private String name;
    private String suggestedPlaylist;
    private GenreSet genreSet;

    /**
     * Constructor for the song class
     * 
     * @param name
     *            the song's name
     * @param pop
     *            the pop percentage
     * @param rock
     *            the rock percentage
     * @param country
     *            the country percentage
     * @param suggested
     *            the suggested playlist
     */
    public Song(String name, int pop, int rock, int country, String suggested) {
        this.name = name;
        this.genreSet = new GenreSet(pop, rock, country);
        this.suggestedPlaylist = suggested;
    }


    /**
     * Returns the String representation of a song object
     * 
     * @return result
     *         the string result
     */
    public String toString() {
        final StringBuilder result = new StringBuilder();
        if (getPlaylistName().equals("")) {
            result.append("No-Playlist ");
            result.append(this.getName());
            result.append(" ");
            result.append(this.getGenreSet().toString());
        }
        else {

            result.append(this.getName());
            result.append(" ");
            result.append(this.getGenreSet().toString());
            result.append(" Suggested: ");
            result.append(this.getPlaylistName());
        }

        return result.toString();
    }


    /**
     * Returns the playlist name
     * Note: Use string builder
     * 
     * @return suggestedPlaylist
     */
    public String getPlaylistName() {
        return this.suggestedPlaylist;
    }


    /**
     * Compares two song objects to see if their name, genreSet, and suggested
     * playlist values are the same
     * 
     * @param obj
     *            object to be compared to
     * @return boolean
     *         whether or not the object equals this
     */
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass().equals(obj.getClass())) {
            Song s = (Song)obj;
            return (this.getName().equals(s.getName()) && this.genreSet.equals(s
                .getGenreSet()) && this.suggestedPlaylist.equals(s
                    .getPlaylistName()));
        }
        return false;
    }


    /**
     * Returns the song name
     * 
     * @return name
     *         the name of the song
     */
    public String getName() {
        return this.name;
    }


    /**
     * Returns the genreSet
     * 
     * @return genreSet
     *         the genreSet of the song
     */
    public GenreSet getGenreSet() {
        return this.genreSet;
    }

}
