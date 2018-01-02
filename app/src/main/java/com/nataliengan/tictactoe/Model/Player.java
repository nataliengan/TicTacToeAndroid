package com.nataliengan.tictactoe.Model;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.nataliengan.tictactoe.MainActivity;
import com.nataliengan.tictactoe.R;

import java.util.Scanner;

/**
 * Created by Alaska on 2017-05-04.
 */

public class Player extends AppCompatActivity{

    char marker;

    Button a1,a2,a3,b1,b2,b3,c1,c2,c3;

    String currMove = "";

    public Player(char marker) {
        this.marker = marker;

    }

    public char getMarker() {
        return marker;
    }

    public String promptUserInput(MainActivity activity) {
        a1 = (Button) activity.findViewById(R.id.a1);
        a2 = (Button) activity.findViewById(R.id.a2);
        a3 = (Button) activity.findViewById(R.id.a3);
        b1 = (Button) activity.findViewById(R.id.b1);
        b2 = (Button) activity.findViewById(R.id.b2);
        b3 = (Button) activity.findViewById(R.id.b3);
        c1 = (Button) activity.findViewById(R.id.c1);
        c2 = (Button) activity.findViewById(R.id.c2);
        c3 = (Button) activity.findViewById(R.id.c3);

        a1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (a1.getText().toString().equals("")) {
                    a1.setText(marker);
                    currMove = "a1";
                }
            }
        });
        a2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (a2.getText().toString().equals("")) {
                    a2.setText(marker);
                    currMove = "a2";
                }
            }
        });
        a3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (a3.getText().toString().equals("")) {
                    a3.setText(marker);
                    currMove = "a3";
                }
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (b1.getText().toString().equals("")) {
                    b1.setText(marker);
                    currMove = "b1";
                }
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (b2.getText().toString().equals("")) {
                    b2.setText(marker);
                    currMove = "b2";
                }
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (b3.getText().toString().equals("")) {
                    b3.setText(marker);
                    currMove = "b3";
                }
            }
        });
        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (c1.getText().toString().equals("")) {
                    c1.setText(marker);
                    currMove = "c1";
                }
            }
        });
        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (c2.getText().toString().equals("")) {
                    c2.setText(marker);
                    currMove = "c2";
                }
            }
        });
        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (c3.getText().toString().equals("")) {
                    c3.setText(marker);
                    currMove = "c3";
                }
            }
        });
        return currMove;
    }


}
