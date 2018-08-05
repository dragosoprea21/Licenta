package com.example.proiect.licenta.Utils;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.proiect.licenta.R;
import com.example.proiect.licenta.TimetableActivity;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dona on 23/07/2018.
 */

public class Monday extends android.support.v4.app.Fragment {

    private static final String ARG_PARAM = "timetable";
    private RecyclerView recyclerView;
    public FirebaseDatabase database;
    public DatabaseReference reference;
    public ClassAdapter adapter;
    private ArrayList<TimetableItem> timetableItems = new ArrayList<TimetableItem>();

    public Monday() {
    }

    public static Monday newInstance(ArrayList<TimetableItem> timetableItems) {
        Monday fragment = new Monday();
        Bundle args = new Bundle();
        args.putParcelableArrayList(ARG_PARAM, timetableItems);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.monday_frag, container, false);
        database = FirebaseDatabase.getInstance();

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewMonday);
        LinearLayoutManager lin = new LinearLayoutManager(getContext());
        adapter = new ClassAdapter(timetableItems);
        lin.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(lin);
        recyclerView.setAdapter(adapter);

        populateView();

        return view;

    }

    public void populateView() {
        TimetableActivity activity = (TimetableActivity) getActivity();
        ArrayList<String> keysSelectedGrupa = activity.getChoiceGrupa();
        ArrayList<String> keysSelectedSemigrupa = activity.getChoiceSemigrupa();
        String an = keysSelectedGrupa.get(0).substring(1,2);
        String serie = keysSelectedGrupa.get(0).substring(3,5);
        String concatenare = an.concat(serie);

        reference = FirebaseDatabase.getInstance().getReference(concatenare).child(keysSelectedGrupa.get(0)).child(keysSelectedSemigrupa.get(0)).child("Luni");
        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                timetableItems.add(dataSnapshot.getValue(TimetableItem.class));
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                TimetableItem item = dataSnapshot.getValue(TimetableItem.class);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}

