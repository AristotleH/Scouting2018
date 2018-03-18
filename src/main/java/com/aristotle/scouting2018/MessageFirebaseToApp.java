package com.aristotle.scouting2018;

import java.util.List;

public class MessageFirebaseToApp {

    public String content;
    public String dateAndTimeCreated;
    public String deviceId;

    /*
        positions in cubeTotals:
        0 - blue's own switch
        1 - blue's scale
        2 - blue's faraway switch
        3 - red's own switch
        4 - red's scale
        5 - red's faraway switch
         */

    private MessageFirebaseToApp() {
    }

    public MessageFirebaseToApp(String c, String dT, String dI){
        this.content = c;
        this.dateAndTimeCreated = dT;
        this.deviceId = dI;
    }

}
