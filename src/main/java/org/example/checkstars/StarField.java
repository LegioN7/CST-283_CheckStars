package org.example.checkstars;

import java.io.*;
import java.util.Scanner;

// CST-283
// Aaron Pelto
// Winter 2024

// The astronomer who hired you for this solution has changed the algorithm to be used for star detection.
// Instead of just the star, and its four "neighbors", change to include the start and all eight neighbors.

// The algorithm does not require averaging all the positions plus eight neighboring points.
// If the average exceeds the value of 5.3, you will now declare a star at that location.

public class StarField
{
    private final int MAXROWS = 6;
    private final int MAXCOLS = 8;

    private int starData[][];
    private String starMarkers[][];

    // This function builds an array of digitized star data
    // from a data file
    public StarField(String starFileName)
    {
        starData = new int[MAXROWS][MAXCOLS];
        try
        {
            File inFileRef = new File(starFileName);
            Scanner inputFile = new Scanner(inFileRef);

            // Read-process-write text messages one at a time
            while (inputFile.hasNext())
            {
                for (int i = 0; i < MAXROWS; i++)
                    for (int j = 0; j < MAXCOLS; j++)
                        starData[i][j] = inputFile.nextInt();
            }
        }
        catch (IOException e)
        {
            System.out.println("Problem with file - Shutting down.");
            System.exit(0);
        }
    }

    // Scan for stars and write a text-based image marking stars with an asterisk
    // Note:  Stars on outside boundary are ignored in analysis
    // Output is stored in starMarkers array.
    public void ScanForStars()
    {
        starMarkers = new String[MAXROWS][MAXCOLS];

        for (int i=1; i<MAXROWS-1; i++)
        {
            for (int j=1; j<MAXCOLS-1; j++)
            {
                // If average brightness of current star and surrounding 8 is over
                // 5.3, then define this position as a likely star
                if ((double)(starData[i][j] + starData[i][j+1] + starData[i-1][j]
                        + starData[i][j-1] + starData[i+1][j] + starData[i-1][j-1]
                        + starData[i-1][j+1] + starData[i+1][j-1] + starData[i+1][j+1]
                ) / 9.0 > 5.3)
                    starMarkers[i][j] = "*";    // Show a star
                else
                    starMarkers[i][j] = " ";    // Show open space (blank)
            }

        }
    }

    // Write a start scan array to one string and return.
    // Pre:  starMarkers 2-D array has been built.
    public String toString()
    {
        String outStarString = "";
        outStarString += "---------------" + "\n";  // Top line
        for (int i = 1; i < MAXROWS-1; i++)
        {
            outStarString +=  "| ";   // Left boundary

            for (int j=1; j<MAXCOLS-1; j++)
            {
                // If average brightness of current star and surrounding 4 is over
                // 5, the define this position as likely star
                outStarString += starMarkers[i][j] + " ";
            }
            outStarString +=  "|" + "\n";   // Right boundary
        }
        outStarString +=  "---------------";  // Bottom line

        return outStarString;
    }
}