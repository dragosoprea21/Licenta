package com.example.proiect.licenta.Utils;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proiect.licenta.MainActivity;
import com.example.proiect.licenta.R;
import com.example.proiect.licenta.TimetableActivity;

import org.w3c.dom.Text;

import java.sql.Time;
import java.util.List;


public class ClassAdapter extends RecyclerView.Adapter<ClassAdapter.ViewHolder>{
    private List<TimetableItem> timeTableItemList;


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView startTimeTextView;
        public TextView endTimeTextView;
        public TextView classNameTextView;
        public TextView professorTextView;
        public TextView placeTextView;
        public TextView typeTextView;
        public View lineView;
        public ViewHolder(View v) {
            super(v);
            this.startTimeTextView = (TextView) v.findViewById(R.id.startTime);
            this.endTimeTextView = (TextView) v.findViewById(R.id.endTime);
            this.classNameTextView = (TextView) v.findViewById(R.id.name);
            this.professorTextView = (TextView) v.findViewById(R.id.prof);
            this.placeTextView = (TextView) v.findViewById(R.id.place);
            this.lineView = (View) v.findViewById(R.id.lineView);
            this.typeTextView = (TextView) v.findViewById(R.id.type);
        }
    }

    public ClassAdapter(List<TimetableItem> list) {
        timeTableItemList = list;
    }


    @Override
    public ClassAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_class, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TimetableItem timetableItem = timeTableItemList.get(position);

        holder.startTimeTextView.setText(timetableItem.getStartTime());
        holder.endTimeTextView.setText(timetableItem.getEndTime());
        holder.classNameTextView.setText(timetableItem.getItem());
        holder.placeTextView.setText(timetableItem.getPlace());
        holder.professorTextView.setText(timetableItem.getProfessor());
        holder.typeTextView.setText(timetableItem.getType());



    }


    @Override
    public int getItemCount() {
        return timeTableItemList.size();
    }
}

