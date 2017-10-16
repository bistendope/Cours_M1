package edu.univorleans.simon;

import android.media.SoundPool;
import android.widget.Button;

public class TurnOn implements Runnable {

    GameActivity activity;
    Button b;
    SoundPool soundPool;
    float volume;

    public TurnOn(GameActivity activity, Button b, SoundPool soundPool, float volume) {
        this.activity = activity;
        this.volume = volume;
        this.soundPool = soundPool;
        this.b = b;
    }

    @Override
    public void run() {
        b.setBackgroundColor((Integer) b.getTag(R.string.lightColor));
        activity.runOnUiThread(new TurnOff(b), 500);
        soundPool.play((Integer) b.getTag(R.string.sound),  volume, volume, 1, 0, 1f);
    }
}
