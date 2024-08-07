package dailymixes;

/**
 * @author Racquell Grey racquellg
 * @version 2023.04.01
 *
 */
public class GenreSet implements Comparable<GenreSet> {

    // fields
    private int rock;
    private int pop;
    private int country;

    /**
     * Constructor for the GenreSet method
     * 
     * @param pop
     *            pop percentage
     * @param rock
     *            rock percentage
     * @param country
     *            country percentage
     */
    public GenreSet(int pop, int rock, int country) {
        this.rock = rock;
        this.pop = pop;
        this.country = country;
    }


    /**
     * Gets rock variable
     * 
     * @return rock
     *         rock integer
     */
    public int getRock() {
        return rock;
    }


    /**
     * Gets pop variable
     * 
     * @return pop
     *         pop integer
     */
    public int getPop() {
        return pop;
    }


    /**
     * Gets country variable
     * 
     * @return country
     *         country integer
     */
    public int getCountry() {
        return country;
    }


    /**
     * String representation of genreSet
     * 
     * @return result
     *         the three attributes of the genre set merged together
     */
    public String toString() {
        String result = "Pop:" + getPop() + " Rock:" + getRock() + " Country:"
            + getCountry();

        return result;
    }


    /**
     * Two GenreSet objects are equal if all three fields, pop, rock, and
     * country, are equal.
     * 
     * @param obj
     *            an object to be determined equal to
     * 
     * @return boolean
     *         the boolean to to determine whether two objects are equal
     */
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass().equals(obj.getClass())) {
            GenreSet gs = (GenreSet)obj;
            return (this.getPop() == (gs.getPop()) && this.getRock() == (gs
                .getRock()) && this.getCountry() == (gs.getCountry()));
        }
        return false;
    }


    /**
     * A GenreSet object is within range of the minimum genre set and the
     * maximum genre set if all attributes in the genre set are greater than or
     * equal to the attributes in the minimum genre set and less than or equal
     * to the attributes in the maximum genre set.
     * 
     * @param minGenreSet
     *            the lower bound of a genre set
     * @param maxGenreSet
     *            the upper bounds of a genre set
     * 
     * @return boolean
     *         whether or not the two genreSets are within range
     */
    public boolean isWithinRange(GenreSet minGenreSet, GenreSet maxGenreSet) {

        return ((minGenreSet.getPop() <= this.getPop() && this
            .getPop() <= maxGenreSet.getPop()) && (minGenreSet.getRock() <= this
                .getRock() && this.getRock() <= maxGenreSet.getRock())
            && (minGenreSet.getCountry() <= this.getCountry() && this
                .getCountry() <= maxGenreSet.getCountry()));
    }


    /**
     * Compare "this" GenreSet object to a given GenreSet object "other".
     * Returns true only if "this.pop" is less or equal to "other.pop" AND
     * "this.rock" is less or equal to "other.rock" AND "this.country" is less
     * or equal to "other.country".
     * 
     * @param other
     *            other genre set to be compared to
     * 
     * @return boolean
     *         whether or not a genreSet is less than or equal to another
     */
    private boolean isLessThanOrEqualTo(GenreSet other) {
        return (this.getPop() <= other.getPop() && this.getRock() <= other
            .getRock() && this.country <= other.getCountry());
    }


    /**
     * Implements Comparable so that a negative integer, positive integer, or
     * zero are returned based on whether the sum of the genre percent
     * composition of the current object are less than, greater than, or equal
     * to the sum of the genre percent composition of the parameter object.
     * 
     * @param other
     *            the other genre set to be compared to
     * 
     * @return int
     *         integer that indicates the results above
     */
    public int compareTo(GenreSet other) {
        int thisSum = this.getPop() + this.getRock() + this.getCountry();
        int otherSum = other.getPop() + other.getRock() + other.getCountry();

        if (thisSum == otherSum) {
            return 0;
        }
        else if (this.isLessThanOrEqualTo(other)) {
            return -1;
        }
        else {
            return 1;
        }
    }

}
