package com.aristotle.scouting2018;

import java.util.List;

public class TeamInfoFirebaseToApp {

    public int number;
    public List<String> matchKeys;

    private TeamInfoFirebaseToApp() {
    }

    public TeamInfoFirebaseToApp(int number, List<String> matchKeys){
        this.number = number;
        this.matchKeys = matchKeys;
    }

}
