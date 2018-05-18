package com.example.matthiasfoltier.myapplication;


import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity {

    //creer la surface openGL
    private GLSurfaceView surfaceView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ShadderUtilities.init(this);
        //surface view
        surfaceView = new MyGLSurfaceView(this);
        this.setContentView(surfaceView);
        ShadderUtilities.init(this);
    }
}
