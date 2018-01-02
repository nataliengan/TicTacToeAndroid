package com.nataliengan.tictactoe;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

/**
 * Created by Alaska on 2017-05-05.
 */

public class DifficultyActivity extends AppCompatActivity {

    Button easy, medium, hard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_difficulty);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        easy = (Button) findViewById(R.id.easy);
        medium = (Button) findViewById(R.id.medium);
        hard = (Button) findViewById(R.id.hard);
        final Bundle informationForGame = new Bundle();
        informationForGame.putBoolean("isRealPlayer", false);
        easy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DifficultyActivity.this, AIActivity.class);
                informationForGame.putInt("difficulty", 1);
                i.putExtras(informationForGame);
                startActivityForResult(i, 0);
                return;
            }
        });

        medium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DifficultyActivity.this, AIActivity.class);
                informationForGame.putInt("difficulty", 3);
                i.putExtras(informationForGame);
                startActivity(i);
                return;
            }
        });

        hard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DifficultyActivity.this, AIActivity.class);
                informationForGame.putInt("difficulty", 5);
                i.putExtras(informationForGame);
                startActivity(i);
                return;
            }
        });


    }


}
