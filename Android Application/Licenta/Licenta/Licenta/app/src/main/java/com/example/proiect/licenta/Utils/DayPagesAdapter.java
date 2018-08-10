package com.example.proiect.licenta.Utils;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

/**
 * Created by Dona on 25/07/2018.
 */

public class DayPagesAdapter extends FragmentStatePagerAdapter {

    public ArrayList<TimetableItem> timetableItems;

    public DayPagesAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                Fragment fragmentMonday = Monday.newInstance(timetableItems);
                return fragmentMonday;
            case 1:
                Fragment fragmentTuesday = Tuesday.newInstance(timetableItems);
                return fragmentTuesday;
            case 2:
                Fragment fragmentWednesday = Wednesday.newInstance(timetableItems);
                return fragmentWednesday;
            case 3:
                Fragment fragmentThursday = Thursday.newInstance(timetableItems);
                return fragmentThursday;
            case 4:
                Fragment fragmentFriday = Friday.newInstance(timetableItems);
                return fragmentFriday;
        }

        return null;

    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "LUNI";
            case 1:
                return "MARTI";
            case 2:
                return "MIERCURI";
            case 3:
                return "JOI";
            case 4:
                return "VINERI";

        }
        return null;
    }
}
