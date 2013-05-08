package com.bradlangel.scheduler;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

/**
 * User: bradlangel
 * Date: 4/14/13
 * Time: 4:57 AM
 */
public class Output {

    private static final String fileLocation = "/Users/bradlangel/WorkImprovement/Calendar/MySchedule/";

    public void toFile(String[][] schedule, int scheduleLength){

        Date today = new Date();
        FileWriter writer = null;
        try {
            writer = new FileWriter(fileLocation+ today.toString()+".txt");
            for(int i = 0; i < scheduleLength; i++) {
                writer.write(schedule[i][0] + "  |  "+ schedule[i][1] + "\n");

            }
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }

    public void toGoogleVoice(String[][] schedule, int scheduleLength) {

    }

}
