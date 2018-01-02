package com.nataliengan.tictactoe.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alaska on 2017-05-07.
 */

public class MiniMax {

    public static int minimax(char[][] board, int depth, boolean isMax) {
        List<int[]> nextMoves = generateMoves(board);
        int currentScore = 0;
        int bestScore = isMax? Integer.MIN_VALUE : Integer.MAX_VALUE;

        if(nextMoves.isEmpty()|| depth == 0) {
            bestScore = evaluate(board);
        }
        else {
            for (int[] move : nextMoves) {
                if (isMax) {
                    board[move[0]][move[1]] = 'X'; // add MIN (Player) move to board
                    currentScore = minimax(board, depth - 1, isMax); // do minimax on the new board (node)
                    if (currentScore > bestScore) {
                        bestScore = currentScore;
                    }
                }
                if (!isMax) {
                    board[move[0]][move[1]] = 'O'; // add MAX (AI) move to board
                    currentScore = minimax(board, depth - 1, !isMax); // do minimax on the new board (node)
                    if (currentScore < bestScore) {
                        bestScore = currentScore;
                    }
                }
                board[move[0]][move[1]] = '_'; // erase move from board
            }
        }
        return bestScore;
    }

    // return the best move {row, column}
    // the caller is always MAX (AI)
    public static int[] getBestMove(Board board, int depth) {
        char[][] b = board.getBoard();
        int bestRow = -1;
        int bestCol = -1;
        int bestScore = Integer.MIN_VALUE;
        int currentScore = 0;

        List<int[]> nextMoves = generateMoves(b);

        for (int[] move: nextMoves) {
            b[move[0]][move[1]] = 'O'; // add AI's piece to the next move
            currentScore = minimax(b, depth, true);
            if (currentScore > bestScore) {
                bestScore = currentScore;
                bestRow = move[0];
                bestCol = move[1];
            }
            b[move[0]][move[1]] = '_'; // remove AI's piece
        }
        int[] bestMove = {bestRow, bestCol};

        // Add best move to the board --> test if the next move is a winning move
        b[bestMove[0]][bestMove[1]] = 'O';
        Board nextBoard = new Board(b);
        if (nextBoard.checkGameOver() == 2) { // if next move is NOT a winning move

            // check for block
            if (depth >= 3) {
                b[bestMove[0]][bestMove[1]] = '_'; // return to currentBoard
                int[] blockMove = checkBlock(board);
                if (blockMove[0] != -1) {
                    return blockMove; // return a blocked move
                }
            }
        }
        b[bestMove[0]][bestMove[1]] = '_'; // return to currentBoard
        return bestMove; // if next move is a winning move --> return that move
    }

    // Check board for opponent's imminent win
    // Effect: returns the blocking move if detects opponent's imminent win. otherwise, return null move {-1, -1}.
    public static int[] checkBlock(Board board) {
        char[][] b = board.getBoard();

        int[] horizontal = blockHorizontal(b);
        if (horizontal[0] != -1) return horizontal;

        int[] vertical = blockVertical(b);
        if (vertical[0] != -1) return vertical;

        int[] diagonal = blockDiagonal(b);
        if (diagonal[0] != -1) return diagonal;

        int[] antiDiagonal = blockAntiDiagonal(b);
        if (antiDiagonal[0] != -1) return antiDiagonal;

        int[] noBlock = {-1, -1};
        return noBlock;
    }

    // Helper to check horizontal rows for opponent's imminent win
    // Effect: returns the blocking move if detects opponent's imminent win. otherwise, return null move {-1, -1}.
    public static int[] blockHorizontal(char[][] board) {
        int numX = 0;
        int numO = 0;
        int[] blockMove = {-1, -1};

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == 'X') numX++;
                if (board[i][j] == 'O') numO++;
            }
            if (numX == 2 && numO == 0) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == '_') {
                        blockMove[0] = i;
                        blockMove[1] = j;
                    }
                }
            }
            numX = 0; // reset
            numO = 0; // reset
        }
        return blockMove;
    }

    // Helper to check vertical column for opponent's imminent win
    // Effect: returns the blocking move if detects opponent's imminent win. otherwise, return null move {-1, -1}.
    public static int[] blockVertical(char[][] board) {
        int numX = 0;
        int numO = 0;
        int[] blockMove = {-1, -1};

        for (int j = 0; j < 3; j++) { // for each column
            for (int i = 0; i < 3; i++) { // for each cell in the column
                if (board[i][j] == 'X') numX++;
                if (board[i][j] == 'O') numO++;
            }
            if (numX == 2 && numO == 0) {
                for (int i = 0; i < 3; i++) {
                    if (board[i][j] == '_') {
                        blockMove[0] = i;
                        blockMove[1] = j;
                    }
                }
            }
            numX = 0; // reset
            numO = 0; // reset
        }
        return blockMove;
    }

    // Helper to check diagonal cells for opponent's imminent win
    // Effect: returns the blocking move if detects opponent's imminent win. otherwise, return null move {-1, -1}.
    public static int[] blockDiagonal(char[][] board) {
        int numX = 0;
        int numO = 0;
        int[] blockMove = {-1, -1};

        if (board[0][0] == 'X') numX++;
        if (board[0][0] == 'O') numO++;

        if (board[1][1] == 'X') numX++;
        if (board[1][1] == 'O') numO++;

        if (board[2][2] == 'X') numX++;
        if (board[2][2] == 'O') numO++;

        if (numX == 2 && numO == 0) {
            if (board[0][0] == '_') {
                blockMove[0] = 0;
                blockMove[1] = 0;
            }
            if (board[1][1] == '_') {
                blockMove[0] = 1;
                blockMove[1] = 1;
            }
            if (board[2][2] == '_') {
                blockMove[0] = 2;
                blockMove[1] = 2;
            }
        }
        return blockMove;
    }

    // Helper to check AntiDiagonal cells for opponent's imminent win
    // Effect: returns the blocking move if detects opponent's imminent win. otherwise, return null move {-1, -1}.
    public static int[] blockAntiDiagonal(char[][] board) {
        int numX = 0;
        int numO = 0;
        int[] blockMove = {-1, -1};

        if (board[0][2] == 'X') numX++;
        if (board[0][2] == 'O') numO++;

        if (board[1][1] == 'X') numX++;
        if (board[1][1] == 'O') numO++;

        if (board[2][0] == 'X') numX++;
        if (board[2][0] == 'O') numO++;

        if (numX == 2 && numO == 0) {
            if (board[0][2] == '_') {
                blockMove[0] = 0;
                blockMove[1] = 2;
            }
            if (board[1][1] == '_') {
                blockMove[0] = 1;
                blockMove[1] = 1;
            }
            if (board[2][0] == '_') {
                blockMove[0] = 2;
                blockMove[1] = 0;
            }
        }
        return blockMove;
    }

    // return list of possible moves with the given game board
    public static List<int[]> generateMoves(char[][] board) {
        List<int[]> moves = new ArrayList<>();
        Board b = new Board(board);

        if ((b.checkGameOver() == 0) || b.checkGameOver() == 1) {
            return moves; // return an empty list of moves
        }
        else {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == '_') {
                        int[] move = {i, j}; // {row, column}
                        moves.add(move);
                    }
                }
            }
            return moves;
        }
    }

    // return integer score of game board. Greater integer indicates winning board for A.I.
    public static int evaluate(char[][] board) {
        int numX = 0;
        int numO = 0;
        int totalScore = 0;


        //horizontal
        for (int i = 0; i < 3; i++) { // for each row
            for (int j = 0; j < 3; j++) { // go through each cell in the row
                if (board[i][j] == 'X') numX++;
                if (board[i][j] == 'O') numO++;
            }
            totalScore = totalScore + getScore(numX, numO);

            numX = 0;  // reset
            numO = 0;  // reset
        }
        //vertical
        for (int j = 0; j < 3; j++) { // for each column
            for (int i = 0; i < 3; i++) { // for each cell in the column
                if (board[i][j] == 'X') numX++;
                if (board[i][j] == 'O') numO++;
            }
            totalScore = totalScore + getScore(numX, numO);

            numX = 0;  // reset
            numO = 0;  // reset
        }

        //diagonal
        if (board[0][0] == 'X') numX++;
        if (board[0][0] == 'O') numO++;

        if (board[1][1] == 'X') numX++;
        if (board[1][1] == 'O') numO++;

        if (board[2][2] == 'X') numX++;
        if (board[2][2] == 'O') numO++;

        totalScore = totalScore + getScore(numX, numO);

        numX = 0;  // reset
        numO = 0;  // reset

        //anti-diagonal
        if (board[0][2] == 'X') numX++;
        if (board[0][2] == 'O') numO++;

        if (board[1][1] == 'X') numX++;
        if (board[1][1] == 'O') numO++;

        if (board[2][0] == 'X') numX++;
        if (board[2][0] == 'O') numO++;

        totalScore = totalScore + getScore(numX, numO);

        numX = 0;  // reset
        numO = 0;  // reset

        return totalScore;
    }


    // Helper Function to determine score
    public static int getScore(int numX, int numO) {

        int subScore = 0;
        if ((numX == 0) && (numO == 1)) {
            subScore = subScore + 1;
        }
        if ((numX == 0) && (numO == 2)) {
            subScore = subScore + 10;
        }
        if ((numX == 0) && (numO == 3)) {
            subScore = subScore + 100;
        }
        if ((numX == 1) && (numO == 0)) {
            subScore = subScore - 1;
        }
        if ((numX == 2) && (numO == 0)) {
            subScore = subScore - 10;
        }
        if ((numX == 3) && (numO == 0)) {
            subScore = subScore - 100;
        }

        return subScore;
    }


}
