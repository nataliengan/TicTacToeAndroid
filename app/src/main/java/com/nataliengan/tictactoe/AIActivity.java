package com.nataliengan.tictactoe;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.nataliengan.tictactoe.Model.AIPlayer;
import com.nataliengan.tictactoe.Model.Board;
import com.nataliengan.tictactoe.Model.MiniMax;
import com.nataliengan.tictactoe.Model.Player;

/**
 * Created by Alaska on 2017-05-05.
 */

public class AIActivity extends AppCompatActivity implements View.OnClickListener {

    Button a1,a2,a3,b1,b2,b3,c1,c2,c3;
    Button[] bArray;
    TextView playerXScore, playerOScore;
    Board board;
    Player p1;
    Player p2;
    int depth = 1;
    int turnCount = 0;


    int XScore = 0;
    int OScore = 0;

    boolean turn = true;
    boolean isRealPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle informationForBoard = getIntent().getExtras();
        isRealPlayer = informationForBoard.getBoolean("isRealPlayer", false);

        if (isRealPlayer) {
            setContentView(R.layout.activity_p2p);
        } else {
            setContentView(R.layout.activity_ai);
        }

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        a1 = (Button) findViewById(R.id.a1);
        a2 = (Button) findViewById(R.id.a2);
        a3 = (Button) findViewById(R.id.a3);
        b1 = (Button) findViewById(R.id.b1);
        b2 = (Button) findViewById(R.id.b2);
        b3 = (Button) findViewById(R.id.b3);
        c1 = (Button) findViewById(R.id.c1);
        c2 = (Button) findViewById(R.id.c2);
        c3 = (Button) findViewById(R.id.c3);

        bArray = new Button[] {a1,a2,a3,b1,b2,b3,c1,c2,c3};

        for (Button b: bArray) {
            b.setOnClickListener(this);
        }

        playerXScore = (TextView) findViewById(R.id.playerXScore);
        playerOScore = (TextView) findViewById(R.id.playerOScore);

        playerXScore.setText(""+ XScore);
        playerOScore.setText(""+ OScore);

        p1 = new Player('X');
        if(isRealPlayer){
            p2 = new Player('O');
        }else {
            p2 = new AIPlayer();
        }

        board = new Board();

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            depth = bundle.getInt("difficulty");
        }
    }

    @Override
    public void onClick(View v) {
        Button b = (Button) v;
        if(isRealPlayer){
            buttonClickedForPlayer(b);
        }else {
            buttonClickedForAI(b);
        }
    }

    private void buttonClickedForPlayer(Button b) {
        String move = moveToString(b);
        if (turn) {
            b.setText("X");
            b.setBackgroundColor(Color.parseColor("#ff00ddff"));
            board.addMove(move, p1);
        }
        else {
            b.setText("O");
            b.setBackgroundColor(Color.parseColor("#ff99cc00"));
            board.addMove(move, p2);
        }

        b.setClickable(false);
        turnCount++;
        turn = !turn;

        if ((board.checkGameOver() == 2) && turnCount >= 9) {
            Dialog dialog = DialogHelper.showWhoWonDialog(this, "It's a tie!");

            dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    resetGame();
                }
            });
            return;
        }
        else if (board.checkGameOver() == 0) {
            Dialog dialog = DialogHelper.showWhoWonDialog(this, "Player X Wins!");

            dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    resetGame();
                }
            });
            XScore++;
            playerXScore.setText("" + XScore);
            return;
        }
        else if (board.checkGameOver() == 1) {
            Dialog dialog = DialogHelper.showWhoWonDialog(this, "Player O Wins!");

            dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    resetGame();
                }
            });
            OScore++;
            playerOScore.setText("" + OScore);
            return;
        }
    }

    private void buttonClickedForAI(Button b) {
        b.setText("X");
        String move = moveToString(b);

        board.addMove(move, p1);
        b.setBackgroundColor(Color.parseColor("#ff00ddff"));
        b.setClickable(false);


        turnCount++;

        if ((board.checkGameOver() == 2) && turnCount >= 9) {
            Dialog dialog = DialogHelper.showWhoWonDialog(this, "It's a tie!");

            dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    resetGame();
                }
            });
            return;
        }

        if (board.checkGameOver() == 0) {
            Dialog dialog = DialogHelper.showWhoWonDialog(this, "Player X Wins!");

            dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    resetGame();
                }
            });
            XScore++;
            playerXScore.setText("" + XScore);
            return;
        }

        //AI's move
        int[] nextMove = MiniMax.getBestMove(board, depth);
        String row = "";
        if (nextMove[0] == 0) row = "a";
        if (nextMove[0] == 1) row = "b";
        if (nextMove[0] == 2) row = "c";
        String aiMove = row + Integer.toString(nextMove[1] + 1);
        board.addMove(aiMove, p2);
        Button aiButton = stringToButton(aiMove);
        aiButton.setText("O");
        aiButton.setBackgroundColor(Color.parseColor("#ff99cc00"));
        aiButton.setClickable(false);
        turnCount++;

        if (board.checkGameOver() == 1) {
            Dialog dialog = DialogHelper.showWhoWonDialog(this, "Computer Wins!");

            dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    resetGame();
                }
            });

            OScore++;
            playerOScore.setText("" + OScore);
            return;
        }

    }

    private Button stringToButton(String aiMove) {
        Button b = null;
        switch(aiMove) {
            case "a1":
                b = (Button) findViewById(R.id.a1);
                break;
            case "a2":
                b = (Button) findViewById(R.id.a2);
                break;
            case "a3":
                b = (Button) findViewById(R.id.a3);
                break;
            case "b1":
                b = (Button) findViewById(R.id.b1);
                break;
            case "b2":
                b = (Button) findViewById(R.id.b2);
                break;
            case "b3":
                b = (Button) findViewById(R.id.b3);
                break;
            case "c1":
                b = (Button) findViewById(R.id.c1);
                break;
            case "c2":
                b = (Button) findViewById(R.id.c2);
                break;
            case "c3":
                b = (Button) findViewById(R.id.c3);
                break;
        }
        return b;
    }


    private String moveToString(Button b) {
        String move = "";
        switch(b.getId()) {
            case R.id.a1:
                move = "a1";
                break;
            case R.id.a2:
                move = "a2";
                break;
            case R.id.a3:
                move = "a3";
                break;
            case R.id.b1:
                move = "b1";
                break;
            case R.id.b2:
                move = "b2";
                break;
            case R.id.b3:
                move = "b3";
                break;
            case R.id.c1:
                move = "c1";
                break;
            case R.id.c2:
                move = "c2";
                break;
            case R.id.c3:
                move = "c3";
                break;
        }
        return move;
    }

    private void resetGame() {
        board = new Board();
        for (Button b: bArray) {
            b.setText("");
            b.setClickable(true);
            b.setBackgroundColor(Color.parseColor("#9D9D9D"));
        }
        turnCount = 0;
        turn = true;
        board.setWinner('_');
    }
}
