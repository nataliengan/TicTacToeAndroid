package com.nataliengan.tictactoe;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Alaska on 2017-05-07.
 */

public class DialogHelper {

    public static Dialog showWhoWonDialog(final Context ctx, String winner){


        final Dialog winnerDialog = new Dialog(ctx);
        winnerDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        winnerDialog.setContentView(R.layout.winwindow);
        winnerDialog.show();

        TextView winnerTextview = (TextView)winnerDialog.findViewById(R.id.winner_textview);
        winnerTextview.setText(String.format(winner));

        Button playAgain = (Button) winnerDialog.findViewById(R.id.playAgain);
        Button endGame = (Button) winnerDialog.findViewById(R.id.endGame);

        playAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                winnerDialog.dismiss();
            }
        });

        endGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                winnerDialog.dismiss();
                Intent intent = new Intent(ctx, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                ctx.startActivity(intent);
            }
        });
        return winnerDialog;

    }

}
