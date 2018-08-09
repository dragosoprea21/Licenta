package com.example.proiect.licenta.Utils;

import android.annotation.TargetApi;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
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

import com.example.proiect.licenta.Messaging.MyNotificationManager;
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

public class Monday extends android.support.v4.app.Fragment implements OreoApiHandler {

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
        final String grupa = keysSelectedGrupa.get(0);
        String semigrupa = keysSelectedSemigrupa.get(0);
        String concatenare = an.concat(serie);

        reference = FirebaseDatabase.getInstance().getReference(concatenare).child(grupa).child(semigrupa).child("Luni");
        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                timetableItems.add(dataSnapshot.getValue(TimetableItem.class));
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                TimetableItem item = dataSnapshot.getValue(TimetableItem.class);
                MyNotificationManager.getInstance(getContext()).displayNotification("Actualizare noua!" ,"Orarul pentru grupa " + grupa + " a fost modificat." );
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

    @Override
    @TargetApi(Build.VERSION_CODES.O)
    public void oreoApi() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationManager mNotificationManager =
                    (NotificationManager) getContext().getSystemService(Context.NOTIFICATION_SERVICE);
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel mChannel = new NotificationChannel(Constants.CHANNEL_ID, Constants.CHANNEL_NAME, importance);
            mChannel.setDescription(Constants.CHANNEL_DESCRIPTION);
            mChannel.enableLights(true);
            mChannel.setLightColor(Color.RED);
            mChannel.enableVibration(true);
            mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            mNotificationManager.createNotificationChannel(mChannel);
        }
    }

}

