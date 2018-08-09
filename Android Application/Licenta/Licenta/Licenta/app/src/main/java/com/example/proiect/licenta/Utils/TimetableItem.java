package com.example.proiect.licenta.Utils;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Dona on 25/07/2018.
 */

public class TimetableItem implements Parcelable {

    private String item;
    private String professor;
    private String place;
    private String startTime;
    private String endTime;
    private String type;

    public TimetableItem() {

    }

    public TimetableItem(String item, String professor, String place, String startTime, String endTime, String type) {
        this.item = item;
        this.professor = professor;
        this.place = place;
        this.startTime = startTime;
        this.endTime = endTime;
        this.type = type;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    /* make seter & geter */
    public String getCorp() {
        return getPlace().substring(0,2);
    }

    public String getEtaj() {
        return getPlace().substring(2,3);
    }


    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeString(item);
        out.writeString(professor);
        out.writeString(place);
        out.writeString(startTime);
        out.writeString(endTime);
        out.writeString(type);
    }

    public static final Parcelable.Creator<TimetableItem> CREATOR
            = new Parcelable.Creator<TimetableItem>() {
        public TimetableItem createFromParcel(Parcel in) {
            return new TimetableItem(in);
        }

        public TimetableItem[] newArray(int size) {
            return new TimetableItem[size];
        }
    };

    private TimetableItem(Parcel in) {
        item = in.readString();
        professor = in.readString();
        place = in.readString();
        startTime = in.readString();
        endTime = in.readString();
        type = in.readString();
    }
}


