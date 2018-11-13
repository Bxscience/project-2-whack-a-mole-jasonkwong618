package com.example.jason.whack_a_mole;

import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import android.media.MediaPlayer;

public class Final extends AppCompatActivity {
    private Button exit,restart;
    private TextView scorebox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);

        MediaPlayer background = MediaPlayer.create(this,R.raw.background);
        background.setLooping(true);
        background.start();
        exit=(Button)findViewById(R.id.exit);
        restart=(Button)findViewById(R.id.restart);
        scorebox = (TextView)findViewById(R.id.scorebox);

        String message = getIntent().getStringExtra("SCORE");
        scorebox.setText("Score:"+message);


        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.finishAffinity(Final.this);
                System.exit(0);
            }
        });
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Final.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
