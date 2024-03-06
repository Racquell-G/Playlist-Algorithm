// Virginia Tech Honor Code Pledge:
// f
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Racquell Grey racquellg

package dailymixes;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.Scanner;

/**
 * @author Racquell Grey racquellg
 * @version 2023.04.14
 *
 */
public class PlaylistReader {

    private ArrayQueue<Song> queue;
    private Playlist[] playlists;

    /**
     * Constructor for the playlist
     * 
     * @param songsFileName
     * @param playlistsFileName
     * @throws FileNotFoundException
     * @throws DailyMixDataException
     * @throws ParseException
     */
    public PlaylistReader(String songsFileName, String playlistsFileName)
        throws FileNotFoundException,
        DailyMixDataException,
        ParseException {
        PlaylistCalculator pCalc = new PlaylistCalculator(readQueueFile(
            songsFileName), readPlaylistFile(playlistsFileName));
        PlaylistWindow pWindow = new PlaylistWindow(pCalc);
    }


    /**
     * Checks if numbers are within percent range
     * 
     * @param num1
     * @param num2
     * @param num3
     * @return boolean
     *         whether or not the numbers are within percent range
     */
    private boolean isInValidPercentRange(int num1, int num2, int num3) {
        return num1 >= PlaylistCalculator.MIN_PERCENT
            && num2 >= PlaylistCalculator.MIN_PERCENT
            && num3 >= PlaylistCalculator.MIN_PERCENT
            && num1 <= PlaylistCalculator.MAX_PERCENT
            && num2 <= PlaylistCalculator.MAX_PERCENT
            && num3 <= PlaylistCalculator.MAX_PERCENT;
    }


    /**
     * Reads a playlist file
     * 
     * @param playlistFileName
     * @return
     * @throws FileNotFoundException
     * @throws DailyMixDataException
     * @throws ParseException
     * @return Playlist[]
     *         playlist array
     */
    private Playlist[] readPlaylistFile(String playlistFileName)
        throws FileNotFoundException,
        DailyMixDataException,
        ParseException {
        Playlist[] pArr = new Playlist[PlaylistCalculator.NUM_PLAYLISTS];
        Scanner aFile = new Scanner(new File(playlistFileName));
        int value = 0;
        int index = 0;
        while (index < PlaylistCalculator.NUM_PLAYLISTS && aFile
            .hasNextLine()) {
            Scanner aLine = new Scanner(aFile.nextLine()).useDelimiter(",\\s*");
            String[] ite = new String[8];
            int iteCounter = 0;
            while (aLine.hasNext()) {
                if (iteCounter < 8) {
                    ite[iteCounter++] = aLine.next();
                }
                else {
                    iteCounter++;
                    aLine.next();
                }
            }
            aLine.close();
            if (iteCounter == 8) {
                String pName = ite[0];
                int minPop = Integer.valueOf(ite[1]);
                int minRock = Integer.valueOf(ite[2]);
                int minCountry = Integer.valueOf(ite[3]);
                int maxPop = Integer.valueOf(ite[4]);
                int maxRock = Integer.valueOf(ite[5]);
                int maxCountry = Integer.valueOf(ite[6]);
                int cap = Integer.valueOf(ite[7]);
                if (!(this.isInValidPercentRange(minPop, minRock, minCountry)
                    || this.isInValidPercentRange(maxPop, maxRock,
                        maxCountry))) {
                    throw new DailyMixDataException("Daily Mix Data Exception");
                }
                Playlist playlist = new Playlist(pName, minPop, minRock,
                    minCountry, maxPop, maxRock, maxCountry, cap);
                pArr[value++] = playlist;
            }
            else {
                throw new java.text.ParseException(playlistFileName, iteCounter);
            }
            index++;
        }
        if (index < PlaylistCalculator.NUM_PLAYLISTS) {
            throw new DailyMixDataException("Daily Mix Data Exception");
        }
        aFile.close();
        return pArr;
    }


    /**
     * Reads a song file
     * 
     * @param songFileName
     * @return
     * @throws FileNotFoundException
     * @throws DailyMixDataException
     * @throws ParseException
     * @return ArrayQueue<Song>
     *         an array queue of songs
     */
    private ArrayQueue<Song> readQueueFile(String songFileName)
        throws FileNotFoundException,
        DailyMixDataException,
        ParseException {
        ArrayQueue<Song> arrQ = new ArrayQueue<Song>();
        Scanner aFile = new Scanner(new File(songFileName));
        int aLine = 0;
        int counter = 0;
        while (aFile.hasNext()) {
            Scanner scan = new Scanner(aFile.nextLine()).useDelimiter(",\\s*");
            String[] ite = new String[5];
            int iteCounter = 0;
            while (scan.hasNext() && iteCounter < 5) {
                ite[iteCounter++] = scan.next();
            }
            scan.close();
            if (iteCounter >= 4) {
                String sName = ite[0];
                int popS = Integer.valueOf(ite[1]);
                int rockS = Integer.valueOf(ite[2]);
                int countryS = Integer.valueOf(ite[3]);
                String play;
                if (iteCounter == 0) {
                    play = ite[4];
                }
                else {
                    play = "";
                }
                if (!(isInValidPercentRange(popS, rockS, countryS))) {
                    throw new DailyMixDataException("Daily Mix Data Exception");
                }
                Song newSong = new Song(sName, popS, rockS, countryS, play);
                arrQ.enqueue(newSong);
            }
            else {
                throw new ParseException(songFileName, iteCounter);
            }
            aLine++;
        }
        aFile.close();
        return arrQ;
    }

}
