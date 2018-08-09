package com.example.proiect.licenta.Utils;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
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

/**
 * Created by Dona on 23/07/2018.
 */

public class Wendsady extends android.support.v4.app.Fragment {

    private static final String ARG_PARAM = "timetable";
    private RecyclerView recyclerView;
    public FirebaseDatabase database;
    public DatabaseReference reference;
    public ClassAdapter adapter;
    private ArrayList<TimetableItem> timetableItems = new ArrayList<TimetableItem>();

    public Wendsady() {

    }

    public static Wendsady newInstance(ArrayList<TimetableItem> timetableItems) {
        Wendsady fragment = new Wendsady();
        Bundle args = new Bundle();
        args.putParcelableArrayList(ARG_PARAM, timetableItems);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.wendsay_frag, container, false);
        database = FirebaseDatabase.getInstance();

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewWendsday);
        LinearLayoutManager lin = new LinearLayoutManager(getContext());
        adapter = new ClassAdapter(getContext(),timetableItems);
        lin.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(lin);
        recyclerView.setAdapter(adapter);

        populateView();

        return view;


    }


    public void populateView() {
        TimetableActivity activity = (TimetableActivity) getActivity();

        ArrayList<String> keysSelectedAn = activity.getChoiceAn();
        ArrayList<String> keysSelectedSerie = activity.getChoiceSerie();
        ArrayList<String> keysSelectedGrupa = activity.getChoiceGrupa();
        ArrayList<String> keysSelectedSemigrupa = activity.getChoiceSemigrupa();

        String an = keysSelectedAn.get(0);
        String serie = keysSelectedSerie.get(0);
        String concatenare = an.concat(serie);

        reference = FirebaseDatabase.getInstance().getReference(concatenare).child(keysSelectedGrupa.get(0)).child(keysSelectedSemigrupa.get(0)).child("Miercuri");
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


