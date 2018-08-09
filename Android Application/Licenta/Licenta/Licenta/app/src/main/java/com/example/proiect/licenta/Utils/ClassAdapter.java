package com.example.proiect.licenta.Utils;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proiect.licenta.MainActivity;
import com.example.proiect.licenta.MapsActivity;
import com.example.proiect.licenta.R;
import com.example.proiect.licenta.TimetableActivity;

import org.w3c.dom.Text;

import java.sql.Time;
import java.util.List;
import java.util.Map;


public class ClassAdapter extends RecyclerView.Adapter<ClassAdapter.ViewHolder>{
    private List<TimetableItem> timeTableItemList;
    private Context context;
    private Dialog myDialog;


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout linearLayout;
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
            this.linearLayout = (LinearLayout) v.findViewById(R.id.item_class_layout);
        }
    }

    public ClassAdapter(Context context,List<TimetableItem> list) {
        this.context = context;
        this.timeTableItemList = list;
    }


    @Override
    public ClassAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.item_class, parent, false);
        final ViewHolder viewHolder = new ViewHolder(v);

        myDialog = new Dialog(context);
        myDialog.setContentView(R.layout.custom_dialog);

        viewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView customViewCurs = (TextView) myDialog.findViewById(R.id.customViewCurs);
                TextView customViewProf = (TextView) myDialog.findViewById(R.id.customViewProffesor);
                TextView customViewCladire = (TextView) myDialog.findViewById(R.id.customViewCorpCladire);
                TextView customViewEtaj = (TextView) myDialog.findViewById(R.id.customViewEtaj);
                Button btnDirectii = (Button) myDialog.findViewById(R.id.customViewDirectii);
                ImageButton closeDialog = (ImageButton) myDialog.findViewById(R.id.imageButtonClose);

                closeDialog.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        myDialog.dismiss();
                    }
                });

                btnDirectii.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(context, MapsActivity.class);
                        context.startActivity(i);
                    }
                });

                customViewCurs.setText(timeTableItemList.get(viewHolder.getAdapterPosition()).getItem());
                customViewProf.setText(timeTableItemList.get(viewHolder.getAdapterPosition()).getProfessor());
                customViewCladire.setText(timeTableItemList.get(viewHolder.getAdapterPosition()).getCorp());
                customViewEtaj.setText(timeTableItemList.get(viewHolder.getAdapterPosition()).getEtaj());
                myDialog.show();
            }
        });
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

