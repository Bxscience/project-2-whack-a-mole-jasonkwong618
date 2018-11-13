package com.example.jason.whack_a_mole;

import android.content.Intent;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.media.MediaPlayer;
import java.util.Arrays;
import java.util.Random;
import java.util.ArrayList;

public class Screen3X4 extends AppCompatActivity implements View.OnClickListener {
    private ImageButton b1,b2,b3,b4,b5,b6,b7,b8,b9,b10,b11,b12;
    private TextView score, time;
    private int count, place;
    private Random rand;
    private CountDownTimer timer;
    private ArrayList<ImageButton> buttons;
    private boolean tapped;
    private long lastClickTime=0,timeNow,timeElapsed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen3_x4);

        b1=(ImageButton)findViewById(R.id.b2_1);
        b2=(ImageButton)findViewById(R.id.b2_2);
        b3=(ImageButton)findViewById(R.id.b2_3);
        b4=(ImageButton)findViewById(R.id.b2_4);
        b5=(ImageButton)findViewById(R.id.b2_5);
        b6=(ImageButton)findViewById(R.id.b2_6);
        b7=(ImageButton)findViewById(R.id.b2_7);
        b8=(ImageButton)findViewById(R.id.b2_8);
        b9=(ImageButton)findViewById(R.id.b2_9);
        b10=(ImageButton)findViewById(R.id.b2_10);
        b11=(ImageButton)findViewById(R.id.b2_11);
        b12=(ImageButton)findViewById(R.id.b2_12);
        score=(TextView)findViewById(R.id.Score);
        rand=new Random();
        tapped=false;
        place=rand.nextInt(6);
        time=(TextView)findViewById(R.id.Time);
        buttons= new ArrayList<>(Arrays.asList(b1,b2,b3,b4,b5,b6,b7,b8,b9,b10,b11,b12));
        MediaPlayer background = MediaPlayer.create(this,R.raw.background);
        background.setLooping(true);
        background.start();

        for (ImageButton button:buttons){
            button.setOnClickListener(this);
        }

        timer= new CountDownTimer(60000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                long s=millisUntilFinished/1000;
                time.setText("Time Remaining: "+s+" seconds");
                timeNow= SystemClock.elapsedRealtime();
                timeElapsed=timeNow-lastClickTime;
                if(tapped||timeElapsed>=2000){
                    buttons.get(place).setVisibility(View.INVISIBLE);
                    place=rand.nextInt(12);
                    buttons.get(place).setVisibility(View.VISIBLE);
                    tapped=false;
                    lastClickTime=timeNow;
                }
            }

            @Override
            public void onFinish() {
                Intent intent = new Intent(Screen3X4.this, Final.class);
                intent.putExtra("SCORE",""+count);
                startActivity(intent);
            }
        }.start();


    }

    @Override
    public void onClick(View v) {
        MediaPlayer whack= MediaPlayer.create(Screen3X4.this,R.raw.whack);
        whack.start();
        count+=1;
        score.setText("Score: "+String.valueOf(count));
        buttons.get(place).setVisibility(View.INVISIBLE);
        timeNow= SystemClock.elapsedRealtime();
        timeElapsed=(long)0;
        lastClickTime=timeNow;
        tapped=true;
    }
}
