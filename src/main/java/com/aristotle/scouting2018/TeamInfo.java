package com.aristotle.scouting2018;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

public class TeamInfo {

    private int teamNumber;
    private ArrayList<MatchInfo> involvedMatches;
    private String databaseKey;
    private List<String> firebaseMatchesKeys;

    public TeamInfo(int tn, List<String> listMatches, ArrayList<MatchInfo> arrayMatches, String key, boolean fromDatabase) {
        teamNumber = tn;
        databaseKey = key;
        if (fromDatabase) {
            firebaseMatchesKeys = listMatches;
            involvedMatches = new ArrayList<MatchInfo>();
            int incrementor = 0;
            for (String i : firebaseMatchesKeys) {
                involvedMatches.add(findMatchWithKey(i));
                incrementor++;
            }
        }
        else {
            involvedMatches = arrayMatches;
            firebaseMatchesKeys = new ArrayList<String>();
            for (MatchInfo i : involvedMatches)
            {
                firebaseMatchesKeys.add(i.getDatabaseKey());
                System.out.println(i.getDatabaseKey() + "LOOK LOOK I GOT IT LOOKIE");
            }
        }
    }

    public int getTeamNumber() {
        return teamNumber;
    }

    public void setTeamNumber(int teamNumber) {
        this.teamNumber = teamNumber;
    }

    public ArrayList<MatchInfo> getInvolvedMatches() {
        return involvedMatches;
    }

    public void setInvolvedMatches(ArrayList<MatchInfo> involvedMatches) {
        this.involvedMatches = involvedMatches;
    }

    public List<String> getFirebaseMatchesKeys() {
        return firebaseMatchesKeys;
    }

    public void setFirebaseMatchesKeys(List<String> firebaseMatchesKeys) {
        this.firebaseMatchesKeys = firebaseMatchesKeys;
    }

    public void setDatabaseKey(String databaseKey) {
        this.databaseKey = databaseKey;
    }

    public String getDatabaseKey() {
        return databaseKey;

    }

    private MatchInfo findMatchWithKey (String key) {
        for (MatchInfo i : MainActivity.matches) {
            if (i.getDatabaseKey() == key)
                return i;
        }
        return null;
    }

}
