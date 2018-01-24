package edu.univorleans.simon;


public class Player implements Runnable {

    private final static int DELAY = 600;

    Board board;
    int [] sequence;
    int rank;
    int delay;

    public Player(Board board, int rank, int[] sequence, int delay){
        this.board = board;
        this.sequence = sequence;
        this.rank = rank;
        this.delay = delay;
    }

    @Override
    public void run() {
        try {
            if (delay > 0) Thread.sleep(delay);
            for (int i = 0; i < rank; i++) {
                board.light(sequence[i]);
                Thread.sleep(DELAY);
            }
        }catch (InterruptedException e) {
           e.printStackTrace();
        }
    }
}
