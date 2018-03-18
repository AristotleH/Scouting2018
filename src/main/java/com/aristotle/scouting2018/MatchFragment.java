package com.aristotle.scouting2018;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MatchFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MatchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MatchFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public static ArrayList<String> matchViewList;
    private static ArrayAdapter<String> matchListAdapter;
    private ListView matchesView;
    public static ArrayList<MatchInfo> matches = MainActivity.matches;;

    public MatchFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MatchFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MatchFragment newInstance(String param1, String param2) {
        MatchFragment fragment = new MatchFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_match, container, false);

        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        matchesView = view.findViewById(R.id.listMatches);
        matchViewList = new ArrayList<String>();
        matchListAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, matchViewList);
        matchesView.setAdapter(matchListAdapter);

        DatabaseReference update = MainActivity.database.getReference("matches");

        update.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                matches.clear();
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    MatchInfoFirebaseToApp m = postSnapshot.getValue(MatchInfoFirebaseToApp.class);
                    System.out.println(postSnapshot.getKey());
                    MainActivity.matches.add(new MatchInfo(m.blue, m.red, m.cubeTotals,null, "D" + m.date + "T" + m.time, postSnapshot.getKey() , true));
                }
                updateMatchView(false);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });

        //updateMatchView(false);

        FloatingActionButton fab = view.findViewById(R.id.newMatch);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean newMatch = true;
                //int[] teamNumbers = {0};
                Intent intent = new Intent(getActivity().getApplicationContext(), MatchEditActivity.class);
                intent.putExtra("NEW_MATCH", newMatch);
                //intent.putExtra("TEAM_NUMBERS", teamNumbers);
                startActivity(intent);
            }
        });

        matchesView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> m, View v, int pos, long i) {
                boolean newMatch = false;
                int[] teamNumbers = {matches.get(pos).getBlue(), matches.get(pos).getBlue()};
                String timeAndDate = matches.get(pos).getDateAndTimeCreated();
                Intent intent = new Intent(getActivity().getApplicationContext(), MatchEditActivity.class);
                int matchClicked = pos;
                intent.putExtra("NEW_MATCH", newMatch);
                intent.putExtra("TEAM_NUMBERS", teamNumbers);
                intent.putExtra("TIME_DATE", timeAndDate);
                intent.putExtra("MATCH_POS", matchClicked);
                startActivity(intent);
            }
        });

        matchesView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> m, View v, int pos, long i) {
                MainActivity.dataRef.child("matches").child(matches.get(pos).getDatabaseKey()).setValue(null);
                matches.remove(pos);
                Snackbar.make(v, "Match deleted", Snackbar.LENGTH_SHORT).show();
                updateMatchView(true);
                return true;
            }
        });

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public static void updateMatchView (boolean saveAndUpload) {
        matchListAdapter.clear();
        for (int i = 0; i < MainActivity.matches.size(); i++) {
            matchListAdapter.add(matches.get(i).getBlue() + " vs. " +  matches.get(i).getRed() + " at " + matches.get(i).getDateAndTimeCreated());
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
