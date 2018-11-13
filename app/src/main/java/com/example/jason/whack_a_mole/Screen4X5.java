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

public class Screen4X5 extends AppCompatActivity implements View.OnClickListener  {

    private ImageButton b1,b2,b3,b4,b5,b6,b7,b8,b9,b10,b11,b12,b13,b14,b15,b16,b17,b18,b19,b20;
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
        setContentView(R.layout.activity_screen4_x5);

        b1=(ImageButton)findViewById(R.id.b3_1);
        b2=(ImageButton)findViewById(R.id.b3_2);
        b3=(ImageButton)findViewById(R.id.b3_3);
        b4=(ImageButton)findViewById(R.id.b3_4);
        b5=(ImageButton)findViewById(R.id.b3_5);
        b6=(ImageButton)findViewById(R.id.b3_6);
        b7=(ImageButton)findViewById(R.id.b3_7);
        b8=(ImageButton)findViewById(R.id.b3_8);
        b9=(ImageButton)findViewById(R.id.b3_9);
        b10=(ImageButton)findViewById(R.id.b3_10);
        b11=(ImageButton)findViewById(R.id.b3_11);
        b12=(ImageButton)findViewById(R.id.b3_12);
        b13=(ImageButton)findViewById(R.id.b3_13);
        b14=(ImageButton)findViewById(R.id.b3_14);
        b15=(ImageButton)findViewById(R.id.b3_15);
        b16=(ImageButton)findViewById(R.id.b3_16);
        b17=(ImageButton)findViewById(R.id.b3_17);
        b18=(ImageButton)findViewById(R.id.b3_18);
        b19=(ImageButton)findViewById(R.id.b3_19);
        b20=(ImageButton)findViewById(R.id.b3_20);
        score=(TextView)findViewById(R.id.Score);
        rand=new Random();
        tapped=false;
        place=rand.nextInt(6);
        time=(TextView)findViewById(R.id.Time);
        buttons= new ArrayList<>(Arrays.asList(b1,b2,b3,b4,b5,b6,b7,b8,b9,b10,b11,b12,b13,b14,b15,b16,b17,b18,b19,b20));
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
                    place=rand.nextInt(20);
                    buttons.get(place).setVisibility(View.VISIBLE);
                    tapped=false;
                    lastClickTime=timeNow;
                }
            }

            @Override
            public void onFinish() {
                Intent intent = new Intent(Screen4X5.this, Final.class);
                intent.putExtra("SCORE",""+count);
                startActivity(intent);
            }
        }.start();


    }

    @Override
    public void onClick(View v) {
        MediaPlayer whack= MediaPlayer.create(Screen4X5.this,R.raw.whack);
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
