package com.aristotle.scouting2018;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

public class MatchInfoFirebaseToApp {

    public int blue;
    public int red;
    public List<Integer> cubeTotals;
    public int date;
    public int time;

    /*
        positions in cubeTotals:
        0 - blue's own switch
        1 - blue's scale
        2 - blue's faraway switch
        3 - red's own switch
        4 - red's scale
        5 - red's faraway switch
         */

    private MatchInfoFirebaseToApp() {
    }

    public MatchInfoFirebaseToApp(int blue, int red, List<Integer> cubeTotals, int date, int time){
        this.blue = blue;
        this.red = red;
        this.cubeTotals = cubeTotals;
        this.date = date;
        this.time = time;
    }

}
