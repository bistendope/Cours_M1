package com.example.matthiasfoltier.myapplication;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.Log;
import android.view.MotionEvent;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by matthias.foltier on 06/04/18.
 */

class MyGLSurfaceView extends android.opengl.GLSurfaceView {
    private final MyRenderer myRenderer;
    private final float TOUCH_SCALE_FACTOR = 0.00001f;
    private float mPreviousX;
    private float mPreviousY;

    public boolean onTouchEvent(MotionEvent e) {
        float x = e.getX();
        float y = e.getY();

        switch (e.getAction()) {
            case MotionEvent.ACTION_MOVE:

                float dx = x - mPreviousX;
                float dy = y - mPreviousY;
                // reverse direction of rotation above the mid-line
                if (y > getHeight() ) {
                    dx = dx  -10;
                }

                // reverse direction of rotation to left of the mid-line
                if (x < getWidth()) {
                    dy = dy * 10;
                }

                myRenderer.translate(dx / 50.0f, 0, -dy / 500.0f);
                requestRender();
        }

        mPreviousX = x;
        mPreviousY = y;

        return true;
    }

    public MyGLSurfaceView(MainActivity mainActivity) {
        super(mainActivity);

        Log.d("Debug : ", "MyGLSurfaceView");
        setEGLContextClientVersion(2);
        Log.d("Debug : ", "Setting OpenGLES version");
        myRenderer = new MyRenderer(mainActivity);
        Log.d("Debug : ", "Initialize renderer");
        setRenderer(myRenderer);
        Log.d("Debug : ", "Set renderer");
        //setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
        Log.d("Debug : ", "Set render mode");
        setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
    }
}