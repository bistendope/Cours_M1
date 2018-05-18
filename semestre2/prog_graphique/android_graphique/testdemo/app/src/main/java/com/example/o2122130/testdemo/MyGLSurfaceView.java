package com.example.o2122130.testdemo;

import android.content.Context;
import android.opengl.GLSurfaceView;

/**
 * Created by o2122130 on 06/04/18.
 */

class MyGLSurfaceView extends android.opengl.GLSurfaceView {

    GLSurfaceView.Renderer renderer;

    public MyGLSurfaceView(Context context) {
        super(context);
        this.setEGLContextClientVersion(2);

        renderer = new MyRenderer(context);
        this.setRenderer(renderer);
        this.setRenderMode(this.RENDERMODE_WHEN_DIRTY);

    }
}
