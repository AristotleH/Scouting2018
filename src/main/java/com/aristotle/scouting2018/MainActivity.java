package com.aristotle.scouting2018;

import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MatchFragment.OnFragmentInteractionListener,
        MessageFragment.OnFragmentInteractionListener, TeamFragment.OnFragmentInteractionListener {

    public static ArrayList<MatchInfo> matches;
    public static ArrayList<TeamInfo> teams;
    public static ArrayList<Message> messages;
    public static FirebaseDatabase database = databaseSetup();
    public static DatabaseReference dataRef = database.getReference();

    private static FirebaseDatabase databaseSetup () {
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        return FirebaseDatabase.getInstance();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        matches = new ArrayList<>();
        messages = new ArrayList<>();
        teams = new ArrayList<>();

        setTitle("Match List");
        Fragment frg = new MatchFragment();
        loadFragment(frg);

        /*DatabaseReference update = MainActivity.database.getReference("matches");

        update.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                matches.clear();
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    MatchInfoFirebaseToApp m = postSnapshot.getValue(MatchInfoFirebaseToApp.class);
                    System.out.println(postSnapshot.getKey());
                    MainActivity.matches.add(new MatchInfo(m.blue, m.red, m.cubeTotals,null, "D" + m.date + "T" + m.time, postSnapshot.getKey() , true));
                }
                MatchFragment.updateMatchView(false);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });*/

        BottomNavigationView nav = findViewById(R.id.bottom_nav);
        nav.setOnNavigationItemSelectedListener (new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem option) {
                Fragment frg;
                switch (option.getItemId()) {
                    case R.id.open_matchview:
                        setTitle("Match List");
                        frg = new MatchFragment();
                        loadFragment(frg);
                        break;
                    case R.id.open_teamview:
                        setTitle("Team List");
                        frg = new TeamFragment();
                        loadFragment(frg);
                        break;
                    case R.id.open_messages:
                        //setTitle("2035 Messaging");
                        //frg = new MessageFragment();
                        //loadFragment(frg);
                        break;
                }
                return true;
            }
        });

    }

    public static String firebaseNewMatch(MatchInfoAppToFirebase m) {
        DatabaseReference pushed = dataRef.child("matches").push();
        pushed.setValue(m);
        return pushed.getKey();
    }

    public static void firebaseUpdateMatch(MatchInfoAppToFirebase m, String key) {
        dataRef.child("matches").child(key).setValue(m);
    }

    public static String firebaseNewTeam(TeamInfoAppToFirebase t) {
        DatabaseReference pushed = dataRef.child("teams").push();
        pushed.setValue(t);
        return pushed.getKey();
    }

    public static void firebaseUpdateTeam(TeamInfoAppToFirebase t, String key) {
        dataRef.child("teams").child(key).setValue(t);
    }

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        //transaction.addToBackStack(null);
        transaction.commit();
    }

    public void onFragmentInteraction(Uri uri){
    }

}
