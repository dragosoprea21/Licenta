package com.example.proiect.licenta;


import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private Button btnChangeActivity;
    private ArrayList<String> stringsSelectedGrupa;
    private ArrayList<String> stringsSelectedSemigrupa;
    private ArrayList<String> stringsSelectedAn;
    private ArrayList<String> stringsSelectedSerie;
    private EditText choiceTextGrupa, choiceTextSemigrupa, choiceTextAn,choiceTextSerie;
    static AlertDialog alertDialog1 = null;
    static AlertDialog alertDialog2 = null;
    static AlertDialog alertDialog3 = null;
    static AlertDialog alertDialog4 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnChangeActivity = (Button) findViewById(R.id.btnSelect);
        choiceTextGrupa = (EditText) findViewById(R.id.mychoiceGrupa);
        choiceTextSemigrupa = (EditText) findViewById(R.id.mychoiceSemigrupa);
        choiceTextAn = (EditText) findViewById(R.id.mychoiceAn);
        choiceTextSerie = (EditText) findViewById(R.id.mychoiceSerie);


        choiceTextAn.setShowSoftInputOnFocus(false);
        choiceTextAn.setCursorVisible(false);
        choiceTextAn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final CharSequence[] values = {"1","2","3","4"};
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this, R.style.MaterialThemeDialog);

                builder.setTitle("Selecteaza anul:");
                stringsSelectedAn = new ArrayList<String>();
                builder.setSingleChoiceItems(values, -1, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {

                        switch (item) {
                            case 0:
                                choiceTextAn.setText(values[0]);
                                Toast.makeText(getApplicationContext(), choiceTextAn.getText().toString(), Toast.LENGTH_SHORT).show();
                                stringsSelectedAn.add(choiceTextAn.getText().toString());
                                break;
                            case 1:
                                choiceTextAn.setText(values[1]);
                                stringsSelectedAn.add(choiceTextAn.getText().toString());
                                break;
                            case 2:
                                choiceTextAn.setText(values[2]);
                                stringsSelectedAn.add(choiceTextAn.getText().toString());
                                break;
                            case 3:
                                choiceTextAn.setText(values[3]);
                                stringsSelectedAn.add(choiceTextAn.getText().toString());
                                break;
                        }
                        alertDialog1.dismiss();
                    }
                });

                alertDialog1 = builder.create();
                alertDialog1.show();

            }
        });

/////////////////////////////////////////////////////////////////////////////////////
        choiceTextSerie.setShowSoftInputOnFocus(false);
        choiceTextSerie.setCursorVisible(false);
        choiceTextSerie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final CharSequence[] values = {"CA", "CB", "CC", "CD"};
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this, R.style.MaterialThemeDialog);

                builder.setTitle("Selecteaza o serie:");
                stringsSelectedSerie = new ArrayList<String>();
                builder.setSingleChoiceItems(values, -1, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {

                        switch (item) {
                            case 0:
                                choiceTextSerie.setText(values[0]);
                                stringsSelectedSerie.add(choiceTextSerie.getText().toString());
                                break;
                            case 1:
                                choiceTextSerie.setText(values[1]);
                                stringsSelectedSerie.add(choiceTextSerie.getText().toString());
                                break;
                            case 2:
                                choiceTextSerie.setText(values[2]);
                                stringsSelectedSerie.add(choiceTextSerie.getText().toString());
                                break;
                            case 3:
                                choiceTextSerie.setText(values[3]);
                                stringsSelectedSerie.add(choiceTextSerie.getText().toString());
                                break;
                        }
                        alertDialog2.dismiss();
                    }
                });

                alertDialog2 = builder.create();
                alertDialog2.show();

            }
        });
///////////////////////////////////////////////////////////////////////////////////////////

        choiceTextGrupa.setShowSoftInputOnFocus(false);
        choiceTextGrupa.setCursorVisible(false);
        choiceTextGrupa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final CharSequence[] altceva = new String[4];
                String anSelected = choiceTextAn.getText().toString();
                Toast.makeText(getApplicationContext(), anSelected, Toast.LENGTH_SHORT).show();
                String serieSelected = choiceTextSerie.getText().toString();
                Toast.makeText(getApplicationContext(), serieSelected, Toast.LENGTH_SHORT).show();
                for(int i = 0; i < 4; i++) {
                    altceva[i] = "3" + anSelected + (i+1) +serieSelected;
                    Toast.makeText(getApplicationContext(), altceva[i].toString(), Toast.LENGTH_SHORT).show();
                }

                final CharSequence[] values = altceva;
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this, R.style.MaterialThemeDialog);

                builder.setTitle("Selecteaza o grupa:");
                stringsSelectedGrupa = new ArrayList<String>();
                builder.setSingleChoiceItems(values, -1, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {

                        switch (item) {
                            case 0:
                                choiceTextGrupa.setText(values[0]);
                                stringsSelectedGrupa.add(choiceTextGrupa.getText().toString());
                                break;
                            case 1:
                                choiceTextGrupa.setText(values[1]);
                                stringsSelectedGrupa.add(choiceTextGrupa.getText().toString());
                                break;
                            case 2:
                                choiceTextGrupa.setText(values[2]);
                                stringsSelectedGrupa.add(choiceTextGrupa.getText().toString());
                                break;
                            case 3:
                                choiceTextGrupa.setText(values[3]);
                                stringsSelectedGrupa.add(choiceTextGrupa.getText().toString());
                                break;
                        }
                        alertDialog3.dismiss();
                    }
                });

                alertDialog3 = builder.create();
                alertDialog3.show();

            }
        });
/////////////////////////////////////////////////////////
        choiceTextSemigrupa.setShowSoftInputOnFocus(false);
        choiceTextSemigrupa.setCursorVisible(false);
        choiceTextSemigrupa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String grupaSelected = choiceTextGrupa.getText().toString();
                CharSequence[] altceva = new String[2];
                for(int i = 0; i < 2; i++) {
                    altceva[i] = grupaSelected + (i+1);
                }

                final CharSequence[] values = altceva;
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this, R.style.MaterialThemeDialog);

                builder.setTitle("Selecteaza o semigrupa:");
                stringsSelectedSemigrupa = new ArrayList<String>();
                builder.setSingleChoiceItems(values, -1, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {

                        switch (item) {
                            case 0:
                                choiceTextSemigrupa.setText(values[0]);
                                stringsSelectedSemigrupa.add(choiceTextSemigrupa.getText().toString());
                                break;
                            case 1:
                                choiceTextSemigrupa.setText(values[1]);
                                stringsSelectedSemigrupa.add(choiceTextSemigrupa.getText().toString());
                                break;
                        }
                        alertDialog4.dismiss();
                    }
                });

                alertDialog4 = builder.create();
                alertDialog4.show();

            }
        });


        btnChangeActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(TextUtils.isEmpty(choiceTextSemigrupa.getText().toString())) {
                    Toast.makeText(getApplicationContext(),"Pentru a vizualiza orarul trebuie selectata o grupa.",Toast.LENGTH_LONG).show();
                    return;
                }
                Intent intent = new Intent(MainActivity.this, TimetableActivity.class);
                intent.putStringArrayListExtra("myKeysGrupa", stringsSelectedGrupa);
                intent.putStringArrayListExtra("myKeysSemigrupa", stringsSelectedSemigrupa);
                startActivity(intent);
            }
        });

    }
}


