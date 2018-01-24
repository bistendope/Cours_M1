package edu.univorleans.simon;

import android.widget.Button;

public class TurnOff implements Runnable {

    Button b;

    public TurnOff(Button b) {
        this.b = b;
    }

    @Override
    public void run() {
        b.setBackgroundColor((Integer) b.getTag(R.string.color));
    }
}