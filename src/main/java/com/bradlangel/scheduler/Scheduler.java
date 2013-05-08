package com.bradlangel.scheduler;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * User: bradlangel
 * Date: 4/12/13
 * Time: 4:26 AM
 */
public class Scheduler {

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
    private static final int scheduleBlockLength = 96;
    private static final int minBlock = 15;
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

    public static void main(String[] args) throws IOException {

        scheduleQuestions();

    }

    public static void scheduleQuestions() throws IOException {

            //ask questions related to work, your plans, lunch time, and dinner time.
            String work = work();
            lunchTime();
            String plans = yourPlans();
            dinnerTime();
            if(work.equals("y")){
                boolean timeComparison = timeComparison(workStartTime, planStartTime, workEndTime, planEndTime);
                if(timeComparison) {

                    setSchedule(work, plans);

                } else {
                    scheduleQuestions();
                }
            } else {
                setSchedule(work, plans);
            }



    }
    //TODO write directly to output file
    public static void setSchedule(String workIndicator, String planIndicator) throws IOException {

        String[][] schedule = null;
        schedule = scheduleLogic(workIndicator, planIndicator);

        //Send schedule to output file
        Output output = new Output();
        output.toFile(schedule, scheduleBlockLength);


    }

    public static String[][] scheduleLogic(String workIndicator, String plansIndicator) throws IOException {
        //RandomOrg generator = new RandomOrg();
        Random generator = new Random();
        String[][] schedule = setTimes();
        String[][] tbdList = setTBD();

        for(int i = 0; i < scheduleBlockLength; i++) {
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
                for (; diffMinutes >=0; diffMinutes-=minBlock, i++) {
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

            } else if(i > 50 && i < 60) {
                //int activity = generator.getSingleRandomInt(1, 14);
                int activity = generator.nextInt(minBlock);
                while(Integer.parseInt(tbdList[activity][1]) == 0) {
                    //activity = generator.getSingleRandomInt(1, 14);
                    activity = generator.nextInt(minBlock);
                }
                schedule[i][1] = tbdList[activity][0];
                tbdList[activity][1] = String.valueOf(Integer.parseInt(tbdList[activity][1]) - 1);

            } else if (i >= 60) {
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

        String[][] times = new String[scheduleBlockLength][2];
        int j = 0;
        for(int i = 7; i < 31; i++) {

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
        return Integer.parseInt(travelType)/minBlock;
    }

    public static void lunchTime() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Want to set a time for lunch?(y|n)");
        String lunchPlans = scanner.nextLine();
        if(lunchPlans.equals("y")) {
            System.out.println("What time will you be eating Lunch?(24hrTime, i.e. 14:00 = 2:00pm)");
            lunchTime = scanner.nextLine();
        } else {
            System.out.println("Be advised that no lunch will be put into the schedule. Want to change your decision?(y|n)");
            lunchPlans = scanner.nextLine();
            if(lunchPlans.equals("y")) {
                lunchTime();
            }
        }

    }

    public static void dinnerTime() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Want to set a time for Dinner?(y|n)");
        String dinnerPlans = scanner.nextLine();

        if(dinnerPlans.equals("y")) {
            System.out.println("What time will you be eating Dinner?(24hrTime, i.e. 14:00 = 2:00pm)");
            dinnerTime = scanner.nextLine();
        } else {
            System.out.println("Be advised that no dinner will be put into the schedule. Want to change your decision?(y|n)");
            dinnerPlans = scanner.nextLine();
            if(dinnerPlans.equals("y")) {
                dinnerTime();
            } else {
                //exit dinnerTime
            }
        }

    }

    public static String yourPlans() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Anything else planned for today?(y|n)");
        String plans = scanner.nextLine();

        if(plans.equals("y")) {
            System.out.println("When do your plans start?(24hrTime, i.e. 14:00 = 2:00pm)");
            planStartTime = scanner.nextLine();
            System.out.println("When do your plans finish?(24hrTime, i.e. 14:00 = 2:00pm)");
            planEndTime = scanner.nextLine();
            System.out.println("What is your estimated travel time? (15min intervals)");
            planTravelTime = scanner.nextLine();
            System.out.println("What is the event/plan called?");
            planName = scanner.nextLine();
        } else if(plans.equals("n")) {
            System.out.println("Who needs plans?! You are a beautiful son of a bitch!");
        } else {
            System.out.println("Error: Input invalid. Please retry.");
            yourPlans();
        }
        return plans;

    }

    public static String work() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Do you work today?(y|n)");
        String work = scanner.nextLine();
        if(work.equals("y")) {
            System.out.println("When do you start work?(24hrTime, i.e. 14:00 = 2:00pm");
            workStartTime = scanner.nextLine();
            System.out.println("When do you finish work?(24hrTime, i.e. 14:00 = 2:00pm");
            workEndTime = scanner.nextLine();
            System.out.println("What is your estimated travel time to work? (15min intervals)");
            workTravelTime = scanner.nextLine();
        } else if(work.equals("n")) {
            System.out.println("More cool components to come");
        } else {
            System.out.println("Error: Input invalid. Please retry.");
            work();
        }
        return work;
    }

    public static boolean timeComparison(String time1Begin, String time2Begin, String time1End, String time2End){

        String[] time1BeginTimeSplit = time1Begin.split(":");
        String[] time1EndTimeSplit = time1End.split(":");
        String[] time2BeginTimeSplit = time2Begin.split(":");
        String[] time2EndTimeSplit = time2End.split(":");
        int time1BeginTime = Integer.parseInt(time1BeginTimeSplit[0]);
        int time1EndTime = Integer.parseInt(time1EndTimeSplit[0]);
        int time2BeginTime = Integer.parseInt(time2BeginTimeSplit[0]);
        int time2EndTime = Integer.parseInt(time2EndTimeSplit[0]);
        if((time1BeginTime <= time2EndTime && time2BeginTime <= time1EndTime) ||
                (time2BeginTime <= time1EndTime && time1BeginTime <= time2EndTime)) {
            System.out.println("Your times overlap. ReEnter correct values.");
            return false;
        } else {
            return true;
        }

    }

    public static String[] timeRange(String beginTime, String finishTime) {
        String[] timeRange = null;
        int diffMinutes = diffTime(beginTime, finishTime);
        String[] beginTimeSplit = beginTime.split(":");
        int j = 0;
        for (int i = 0; i< diffMinutes; i+=minBlock, j++){
            int minutes = (i+ Integer.parseInt(beginTimeSplit[1]))%60;
            int hour = Integer.parseInt(beginTimeSplit[0]);
            if (minutes == 0) {
                hour++;
            }
            timeRange[j] = String.valueOf(hour) + ":" + String.valueOf(minutes);
        }
        return timeRange;
    }


}
