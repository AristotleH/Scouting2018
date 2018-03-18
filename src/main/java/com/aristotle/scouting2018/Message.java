package com.aristotle.scouting2018;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

public class Message {

    private String content;
    private String deviceId;
    private String dateAndTimeCreated;
    /*
        positions in cubeTotals:
        0 - blue's own switch
        1 - blue's scale
        2 - blue's faraway switch
        3 - red's own switch
        4 - red's scale
        5 - red's faraway switch
         */

    public Message(String c, String dT, String dI) {
        content = c;
        deviceId = dI;
        if (dT == null) {
            SimpleDateFormat sdf = new SimpleDateFormat("'D'yyyyMMdd'T'HHmmss");
            sdf.setTimeZone(TimeZone.getDefault());
            dateAndTimeCreated = sdf.format(Calendar.getInstance().getTime());
        }
        else {
            dateAndTimeCreated = dT;
        }
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDateAndTimeCreated() {
        return dateAndTimeCreated;
    }

    public void setDateAndTimeCreated(String dateAndTimeCreated) {
        this.dateAndTimeCreated = dateAndTimeCreated;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
}
