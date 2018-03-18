package com.aristotle.scouting2018;

import java.util.List;

public class MatchInfoAppToFirebase {

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

    public MatchInfoAppToFirebase(MatchInfo m){
        blue = m.getBlue();
        red = m.getRed();
        cubeTotals = m.getCubeTotalsList();
        date = m.getDate();
        time = m.getTime();
    }

}
