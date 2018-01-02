package com.nataliengan.tictactoe.Model;

import android.widget.Button;

import com.nataliengan.tictactoe.MainActivity;
import com.nataliengan.tictactoe.R;

/**
 * Created by Alaska on 2017-05-04.
 */

public class Board {
    private char winner = '_';

    private char[][] board;

    public Board() {
        board = new char[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '_';
            }
        }
    }

    public Board(char[][] board) {
        this.board = board;
    }

    public char[][] getBoard() {
        return board;
    }

    public char getWinner() { return winner; }

    public void setWinner(char winner) { this.winner = winner;}

    public void addMove(String move, Player p) {
        char letter = move.charAt(0);
        int i = 0;
        switch (letter) {
            case 'a':
                i = 0;
                break;
            case 'b':
                i = 1;
                break;
            case 'c':
                i = 2;
                break;
        }

        int j = Integer.parseInt(move.substring(1, 2));
        j = j - 1;

        board[i][j] = p.getMarker();
    }

    public boolean isMoveValid(String move) {
        if (move.length() > 2) {
            return false;
        }
        String i = move.substring(0,1);
        String j = move.substring(1,2);

        return (i.matches("[A-C]") && j.matches("[1-3]"));
    }

    // return 0 if X wins, 1 if O wins,
    // otherwise return 2
    public int checkGameOver() {
        char h = checkHorizontal();
        char v = checkVertical();
        char d = checkDiagonal();

        if (h == 'X' || v == 'X' || d == 'X') {
            return 0;
        }
        else if (h == 'O' || v == 'O' || d == 'O') {
            return 1;
        }
        else {
            return 2;
        }
    }

    public char checkHorizontal(){
        for (int i = 0; i < 3; i++) {
            char piece = board[i][0];
            if (piece != '_' && board[i][1] == piece && board[i][2] == piece)
                return piece;
        }
        return 0;
    }

    public char checkVertical(){
        for (int j = 0; j < 3; j++) {
            char piece = board[0][j];
            if (piece != '_' && board[1][j] == piece && board [2][j] == piece) {
                return piece;
            }
        }
        return 0;
    }

    public char checkDiagonal() {
        char centerPiece = board[1][1];

        if (centerPiece != '_' && board[0][0] == centerPiece && board[2][2] == centerPiece) return centerPiece;
        else if (centerPiece != '_' && board[2][0] == centerPiece && board[0][2] == centerPiece) return centerPiece;
        else return 0;
    }


    public void printBoard() {
        System.out.println("  1 2 3");

        for (int i = 0; i < board.length; i++) {
            String r = "";
            if (i == 0) {
                r = "A ";
            }
            if (i == 1) {
                r = "B ";
            }
            if (i == 2) {
                r = "C ";
            }
            System.out.println(r + board[i][0] + " " + board[i][1] + " " + board[i][2]);
        }
        System.out.println("");
    }

}
