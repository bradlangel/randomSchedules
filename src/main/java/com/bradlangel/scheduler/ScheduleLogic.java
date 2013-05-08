package com.bradlangel.scheduler;

import java.io.IOException;
import java.util.Random;

/**
 * User: bradlangel
 * Date: 4/14/13
 * Time: 4:25 AM
 */
public class ScheduleLogic {
    private static final String exercise ="Exercise";
    private static final String breakfast ="Breakfast";
    private static final String hygiene = "hygiene and stuff";
    private static final String setGoals = "setGoals";
    private static final String readBook1 = "read Book one";
    private static final String journal ="Journal";
    private static final String gbmApp = "gbmApp";
    private static final String japanese = "Japanese";
    private static final String lunch = "Lunch";
    private static final String job = "Job";
    private static final String freeBlock = "Free Block";
    private static final String creativeWriting = "Creative Writing";
    private static final String walk = "Go for a walk";
    private static final String readBook2 = "read Book niban";
    private static final String math = "do some math";
    private static final String music = "listen to some music";
    private static final String techStars = "Visit Brian at techStars";
    private static final String library = "go to library";
    private static final String hangout = "go hangout somewhere";
    private static final String internet = "surf the web";
    private static final String tv = "watch the blobtube";
    private static final String dinner = "Dinner";
    private static final String travel = "Travel";
    private static final String sleep = "Sleep";
    private static String lunchTime;
    private static String dinnerTime;
    private static String workStartTime;
    private static String workEndTime;
    private static String workTravelTime;
    //TODO make multiple plans available
    private static String planStartTime;
    private static String planEndTime;
    private static String planTravelTime;
    private static String planName;


    public static String[][] scheduleLogic(String workIndicator, String plansIndicator) throws IOException {
        //RandomOrg generator = new RandomOrg();
        Random generator = new Random();
        String[][] schedule = setTimes();
        String[][] tbdList = setTBD();

        for(int i = 0; i < 90; i++) {
            if(i == 0) {
                //int k = generator.getSingleRandomInt(0, 1);
                int k = generator.nextInt(2);
                if (k == 0) {
                    schedule[i][1] = exercise;
                    schedule[++i][1] = exercise;
                } else {
                    schedule[i][1] = freeBlock;
                    schedule[++i][1] = freeBlock;
                }
                tbdList[0][1] ="0"; //set remaining blocks to 0
            } else if(i == 2){
                schedule[i][1] = breakfast;
                schedule[++i][1] = breakfast;
                schedule[++i][1] = hygiene;
                schedule[++i][1] = hygiene;
            } else if(schedule[i][0].equals(lunchTime)) {
                schedule[i][1] = lunch;
                schedule[++i][1] = lunch;
            } else if(schedule[i][0].equals(dinnerTime)) {
                schedule[i][1] = dinner;
                schedule[++i][1] = dinner;
            } else if(workIndicator.equals("y") && schedule[i][0].equals(workStartTime)){
                int diffMinutes = diffTime(workStartTime, workEndTime);
                int travelTimePer15 = calculatedTravelTime(workTravelTime);
                i -= travelTimePer15;
                for (int j = 0; j<travelTimePer15; j++, i++) {
                    schedule[i][1] = travel;
                }
                for (; diffMinutes >=0; diffMinutes-=15, i++) {
                    schedule[i][1] = job;
                }
                for (int j = 0; j<travelTimePer15; j++, i++) {
                    schedule[i][1] = travel;
                }
                i--; //it is offset by 1 block, so need to subtract when it goes back through loop

            } else if(plansIndicator.equals("y") && schedule[i][0].equals(planStartTime)){
                int diffMinutes = diffTime(planStartTime, planEndTime);
                int travelTimePer15 = calculatedTravelTime(planTravelTime);
                i -= travelTimePer15;
                for (int j = 0; j<travelTimePer15; j++, i++) {
                    schedule[i][1] = travel;
                }
                for (; diffMinutes >=0; diffMinutes-=15, i++) {
                    schedule[i][1] = planName;
                }
                for (int j = 0; j<travelTimePer15; j++, i++) {
                    schedule[i][1] = travel;
                }
                i--; //it is offset by 1 block, so need to subtract when it goes back through loop

            } else if(i > 50 && i < 65) {
                //int activity = generator.getSingleRandomInt(1, 14);
                int activity = generator.nextInt(15);
                while(Integer.parseInt(tbdList[activity][1]) == 0) {
                    //activity = generator.getSingleRandomInt(1, 14);
                    activity = generator.nextInt(15);
                }
                schedule[i][1] = tbdList[activity][0];
                tbdList[activity][1] = String.valueOf(Integer.parseInt(tbdList[activity][1]) - 1);

            } else if (i >= 65) {
                schedule[i][1] = sleep;
            } else {
                //int activity = generator.getSingleRandomInt(1, 13);
                int activity = generator.nextInt(14);
                while(Integer.parseInt(tbdList[activity][1]) == 0) {
                    //activity = generator.getSingleRandomInt(1, 13);
                    activity = generator.nextInt(14);
                }
                schedule[i][1] = tbdList[activity][0];
                tbdList[activity][1] = String.valueOf(Integer.parseInt(tbdList[activity][1]) - 1);
            }


        }
        return schedule;

    }


    public static String[][] setTBD() {

        String[][] TBD = new String[18][2];
        TBD[0][0] = exercise;
        TBD[0][1] = "2";
        TBD[1][0] = setGoals;
        TBD[1][1] = "1";
        TBD[2][0] =readBook1;
        TBD[2][1] = "8";
        TBD[3][0] = journal;
        TBD[3][1] = "4";
        TBD[4][0] = gbmApp;
        TBD[4][1] = "10";
        TBD[5][0] = japanese;
        TBD[5][1] = "4";
        TBD[6][0] = freeBlock;
        TBD[6][1] = "6";
        TBD[7][0] = creativeWriting;
        TBD[7][1] = "4";
        TBD[8][0] = walk;
        TBD[8][1] = "2";
        TBD[9][0] = readBook2;
        TBD[9][1] = "4";
        TBD[10][0] = math;
        TBD[10][1] = "4";
        TBD[11][0] = music;
        TBD[11][1] = "2";
        TBD[12][0] = internet;
        TBD[12][1] = "4";
        TBD[13][0] = tv;
        TBD[13][1] = "4";
        TBD[14][0] = hangout;
        TBD[14][1] = "8";
        TBD[15][0] = techStars;
        TBD[15][1] = "3";
        TBD[16][0] = library;
        TBD[16][1] = "2";

        return TBD;
    }

    public static int diffTime(String beginTime, String finishTime) {

        String[] beginTimeSplit = beginTime.split(":");
        String[] finishTimeSplit = finishTime.split(":");
        int hourFinish = Integer.parseInt(finishTimeSplit[0]);
        int hourBegin = Integer.parseInt(beginTimeSplit[0]);
        if (hourFinish < 7) {
            hourFinish += 24;
        } else if(hourBegin < 7) {
            hourBegin += 24;
        } else {
            //do nothing
        }
        int hourValue = ( hourFinish - hourBegin ) * 60;
        int minValue = (Integer.parseInt(finishTimeSplit[1]) - Integer.parseInt(beginTimeSplit[1])) % 60;
        int diffMinutes = hourValue+minValue;
        return diffMinutes;

    }
    public static String[][] setTimes() {

        String[][] times = new String[92][2];
        int j = 0;
        for(int i = 7; i < 30; i++) {

            String hour = String.valueOf((i%24));
            times[j][0] = hour+":00";
            times[++j][0] = hour+":15";
            times[++j][0] = hour+":30";
            times[++j][0] = hour+":45";
            j++;

        }
        return times;
    }

    public static int calculatedTravelTime(String travelType) {
        return Integer.parseInt(travelType)/15;
    }



}
