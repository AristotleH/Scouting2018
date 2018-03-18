package com.aristotle.scouting2018;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

public class MatchInfo {

    private int[] cubeTotalsPrimitive;
    private Integer[] cubeTotalsObject;
    private List<Integer> cubeTotalsList;
    private String dateAndTimeCreated;
    private int blue;
    private int red;
    private List<Integer> cubeTotals;
    private int date;
    private int time;
    private String databaseKey;

    /*
        positions in cubeTotals:
        0 - blue's own switch
        1 - blue's scale
        2 - blue's faraway switch
        3 - red's own switch
        4 - red's scale
        5 - red's faraway switch
         */

    public MatchInfo(int bNumber, int rNumber, List<Integer> cT, int[] primCT, String timeAndDate, String key, boolean fromDatabase) {
        blue = bNumber;
        red = rNumber;
        databaseKey = key;
        if (fromDatabase) {
            cubeTotalsList = cT;
            cubeTotalsObject = cubeTotalsList.toArray(new Integer[6]);
            cubeTotalsPrimitive = new int[6];
            int incrementor = 0;
            for (Integer i : cubeTotalsObject) {
                cubeTotalsPrimitive[incrementor] = cubeTotalsObject[incrementor];
                incrementor++;
            }
        }
        else {

            cubeTotalsPrimitive = primCT;
            cubeTotalsList = new ArrayList<Integer>();
            for (int i : cubeTotalsPrimitive)
            {
                cubeTotalsList.add(i);
            }
            cubeTotalsObject = null;
        }
        if (timeAndDate == null) {
            SimpleDateFormat sdf = new SimpleDateFormat("'D'yyyyMMdd'T'HHmmss");
            sdf.setTimeZone(TimeZone.getDefault());
            dateAndTimeCreated = sdf.format(Calendar.getInstance().getTime());
        }
        else {
            dateAndTimeCreated = timeAndDate;
        }
        date = Integer.valueOf(getDateAndTimeCreated().substring(1, getDateAndTimeCreated().indexOf('T')));
        time = Integer.valueOf(getDateAndTimeCreated().substring(getDateAndTimeCreated().indexOf('T')+1));
    }

    public int[] getCubeTotalsPrimitive() {
        return cubeTotalsPrimitive;
    }

    public void setCubeTotalsPrimitive(int[] cubeTotalsPrimitive) {
        this.cubeTotalsPrimitive = cubeTotalsPrimitive;
    }

    public String getDateAndTimeCreated() {
        return dateAndTimeCreated;
    }

    public void setDateAndTimeCreated(String dateAndTimeCreated) {
        this.dateAndTimeCreated = dateAndTimeCreated;
    }

    public int getBlue() {
        return blue;
    }

    public void setBlue(int blue) {
        this.blue = blue;
    }

    public int getRed() {
        return red;
    }

    public void setRed(int red) {
        this.red = red;
    }

    public List<Integer> getCubeTotalsList() {
        return cubeTotalsList;
    }

    public void setCubeTotalsList(List<Integer> cubeTotals) {
        this.cubeTotalsList = cubeTotalsList;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void setDatabaseKey(String databaseKey) {
        this.databaseKey = databaseKey;
    }

    public String getDatabaseKey() {
        return databaseKey;

    }

}
