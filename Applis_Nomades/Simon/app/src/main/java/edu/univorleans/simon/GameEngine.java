package edu.univorleans.simon;

import java.util.Random;


public class GameEngine {

    private static final int NEXT_DELAY = 1000;

    Board board;
    int[] sequence;
    int rank;
    int played;


    public GameEngine(Board board){
        this. board = board;
    }

    public void init(){
        sequence = new int[board.getSequenceSize()];
        played = 0;
        rank = 1;
        Random random = new Random();
        for (int i = 0; i< sequence.length;i++)
            sequence[i] = random.nextInt(board.getSize());
        board.setRank(rank);

    }

    public void execute(int delay){
       new Thread(new Player(board, rank, sequence, delay)).start();
    }

    public void execute(){execute(0);}

    public void play(int i){
        board.light(i);
        if (sequence[played] != i) {
            played = 0;
            board.playLooseSound();
        } else {
            played++;
            if (played == rank) {
                played = 0;
                rank++;
                execute(NEXT_DELAY);
                board.setRank(rank);
            }
        }
    }
}