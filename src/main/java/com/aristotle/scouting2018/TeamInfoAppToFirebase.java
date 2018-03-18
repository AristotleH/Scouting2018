package com.aristotle.scouting2018;

import java.util.List;

public class TeamInfoAppToFirebase {

    public int number;
    public List<String> matchKeys;

    public TeamInfoAppToFirebase(TeamInfo m){
        number = m.getTeamNumber();
        matchKeys = m.getFirebaseMatchesKeys();
    }

}
