
package dailymixes;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;

/**
 * @author Racquell Grey racquellg
 * @version 2023.04.14
 *
 */
public class ProjectRunner {
    /**
     * Main method that runs the project
     * 
     * @param args
     * @throws FileNotFoundException
     * @throws DailyMixDataException
     * @throws ParseException
     */
    public static void main(String[] args)
        throws FileNotFoundException,
        DailyMixDataException,
        ParseException {
        PlaylistReader read = null;
        if (args.length == 2) {
            read = new PlaylistReader(args[0], args[1]);
        }
        else {
            read = new PlaylistReader("input.txt", "playlists.txt");
        }
    }
}
