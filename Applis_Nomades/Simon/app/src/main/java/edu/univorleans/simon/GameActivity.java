package edu.univorleans.simon;

import android.app.Activity;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameActivity extends Activity implements Board{

    private int size = 100;
    private final int buttons_size = 4;
    private TextView rankTextView;
    private Button[] buttons = new Button[buttons_size];
    private SoundPool soundPool;
    private int looseSound;
    private GameEngine engine;
    private boolean started = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AudioAttributes attributes = new AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .setFlags(AudioAttributes.FLAG_AUDIBILITY_ENFORCED)
                .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
                .build();
        soundPool = new SoundPool.Builder().setAudioAttributes(attributes).setMaxStreams(2).build();
        looseSound = soundPool.load(this, R.raw.aww, 1);

        // Buttons

        buttons[0] = findViewById(R.id.green);
        buttons[0].setTag(R.string.id, new Integer(0));
        buttons[0].setTag(R.string.sound, soundPool.load(this, R.raw.a_sharp, 1));
        buttons[0].setTag(R.string.color, ContextCompat.getColor(this, R.color.green));
        buttons[0].setTag(R.string.lightColor, ContextCompat.getColor(this, R.color.lightGreen));

        buttons[1]= findViewById(R.id.red);
        buttons[1].setTag(R.string.id, new Integer(1));
        buttons[1].setTag(R.string.sound, soundPool.load(this, R.raw.c_sharp, 1));
        buttons[1].setTag(R.string.color, ContextCompat.getColor(this, R.color.red));
        buttons[1].setTag(R.string.lightColor, ContextCompat.getColor(this, R.color.lightRed));

        buttons[2] = findViewById(R.id.yellow);
        buttons[2].setTag(R.string.id, new Integer(2));
        buttons[2].setTag(R.string.sound, soundPool.load(this, R.raw.d_sharp, 1));
        buttons[2].setTag(R.string.color, ContextCompat.getColor(this, R.color.yellow));
        buttons[2].setTag(R.string.lightColor, ContextCompat.getColor(this, R.color.lightYellow));

        buttons[3] = findViewById(R.id.blue);
        buttons[3].setTag(R.string.id, new Integer(3));
        buttons[3].setTag(R.string.sound, soundPool.load(this, R.raw.f_sharp, 1));
        buttons[3].setTag(R.string.color, ContextCompat.getColor(this, R.color.blue));
        buttons[3].setTag(R.string.lightColor, ContextCompat.getColor(this, R.color.lightBlue));

        rankTextView = findViewById(R.id.rankTextView);

        engine = new GameEngine(this);
    }

    public int getSize(){
        return buttons_size;
    }

    public int getSequenceSize(){
        return size;
    }

    public void setRank(final int i){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                rankTextView.setText(String.valueOf(i));
            }
        });
    }

    private void light(Button b){
        AudioManager audioManager= (AudioManager) getSystemService(AUDIO_SERVICE);
        float volume = (float) audioManager.getStreamVolume(AudioManager.STREAM_MUSIC) /
                (float) audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        runOnUiThread(new TurnOn(this, b, soundPool, volume));
    }

    public void light(int i){
        light(buttons[i]);
    }

    public void playLooseSound(){
        AudioManager audioManager= (AudioManager) getSystemService(AUDIO_SERVICE);
        float volume = (float) audioManager.getStreamVolume(AudioManager.STREAM_MUSIC) /
                (float) audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        soundPool.play(looseSound, volume, volume, 1, 0, 1f);
    }

    public void start(View v) {
        started = true;
        engine.init();
        engine.execute();
    }

    public void replay(View v){
        if (!started) return;
        engine.execute();
    }

    public void onClick(View v){
        if (!started) return;
        engine.play((Integer) v.getTag(R.string.id));
    }

    public void runOnUiThread(final Runnable action, final int delay){

        new Thread(){
            @Override
            public void run() {
                try {
                    Thread.sleep(delay);
                    runOnUiThread(action);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
