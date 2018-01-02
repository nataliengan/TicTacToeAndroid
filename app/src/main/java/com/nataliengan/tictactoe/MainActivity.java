package com.nataliengan.tictactoe;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.nataliengan.tictactoe.Model.AIPlayer;
import com.nataliengan.tictactoe.Model.Board;
import com.nataliengan.tictactoe.Model.Player;

public class MainActivity extends AppCompatActivity  {

    Button humanGame, aiGame, exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        humanGame = (Button) findViewById(R.id.humanGame);
        aiGame = (Button) findViewById(R.id.aiGame);
        exit = (Button) findViewById(R.id.exit);

        humanGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gameIntent = new Intent(MainActivity.this, AIActivity.class);
                Bundle informationForBoard = new Bundle();
                informationForBoard.putBoolean("isRealPlayer", true);
                gameIntent.putExtras(informationForBoard);
                startActivity(gameIntent);
                return;
            }
        });
        aiGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, DifficultyActivity.class));
                return;
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }


}
