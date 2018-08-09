package com.example.proiect.licenta;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.proiect.licenta.MainActivity;
import com.example.proiect.licenta.R;

public class StartActivity extends AppCompatActivity {

    private ImageView imageLogo;
    private TextView textLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        imageLogo=(ImageView) findViewById(R.id.imageLogo);
        textLogo=(TextView) findViewById(R.id.textLogo);

        Animation myAnimation = AnimationUtils.loadAnimation(this, R.anim.my_animation);
        imageLogo.startAnimation(myAnimation);
        textLogo.startAnimation(myAnimation);
        final Intent intent = new Intent(this, MainActivity.class);
        Thread timer = new Thread() {
            public void run() {
                try {
                    sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    startActivity(intent);
                    finish();
                }
            }
        };
        timer.start();
    }
}
