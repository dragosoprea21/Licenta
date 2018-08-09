package com.example.proiect.licenta;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;
import android.widget.Toast;

import com.example.proiect.licenta.Utils.DayPagesAdapter;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class TimetableActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private DayPagesAdapter dayPagesAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private ArrayList<String> stringsSelectedFromMainActivityGrupa;
    private ArrayList<String> stringsSelectedFromMainActivitySemigrupa;
    private ArrayList<String> stringsSelectedFromMainActivitySerie;
    private ArrayList<String> stringsSelectedFromMainActivityAn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        stringsSelectedFromMainActivityAn = getIntent().getExtras().getStringArrayList("myKeysAn");
        stringsSelectedFromMainActivitySerie = getIntent().getExtras().getStringArrayList("myKeysSerie");
        stringsSelectedFromMainActivityGrupa=getIntent().getExtras().getStringArrayList("myKeysGrupa");
        stringsSelectedFromMainActivitySemigrupa = getIntent().getExtras().getStringArrayList("myKeysSemigrupa");

        dayPagesAdapter = new DayPagesAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(dayPagesAdapter);


        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_timetable, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        /*if (id == R.id.action_filters) {
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }

    public  ArrayList<String> getChoiceGrupa() {
        return stringsSelectedFromMainActivityGrupa;
    }

    public  ArrayList<String> getChoiceSemigrupa() {
        return stringsSelectedFromMainActivitySemigrupa;
    }

    public  ArrayList<String> getChoiceAn() {
        return stringsSelectedFromMainActivityAn;
    }

    public  ArrayList<String> getChoiceSerie() {
        return stringsSelectedFromMainActivitySerie;
    }
}
