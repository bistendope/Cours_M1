package com.example.o2122130.testdemo;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLES32;
import android.opengl.Matrix;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by o2122130 on 06/04/18.
 */

class MyRenderer implements android.opengl.GLSurfaceView.Renderer {
    private Context context;

    public MyRenderer(Context context){
        super();
        this.context = context;
    }

    private Model3D model;
    private float[] mProjectionMatrix = new float[16];
    private float[] mViewMatrix = new float[16];
    private float[] mModelMatrix = new float[16];

    @Override
    public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {
        GLES32.glEnable(GLES32.GL_DEPTH_TEST);
        GLES32.glClearColor(0.0f, 0.0f, 0.5f, 1.0f);
        GLES32.glClearDepthf(1.0f);
        GLES32.glDepthFunc(GLES20.GL_LEQUAL);

        Matrix.setLookAtM(mViewMatrix, 0, 0, 1, 2, 0f, 0f, 0f, 0f, 1.0f, 0.0f);
        Matrix.perspectiveM(mProjectionMatrix, 0, 70.0f, 9.0f / 16.0f, 0.1f, 100.0f);
        Matrix.setIdentityM(mModelMatrix,0);

        //mcube = new Cube();

        model = ModelLoader.readOBJFile(context, "spaceship.obj");
        model.init("vertexshader.vert", "fragmentshader.frag", "vPosition", "vNormal", "vTexCoord", R.drawable.no_texture);
    }

    @Override
    public void onSurfaceChanged(GL10 gl10, int width, int height) {
        GLES32.glViewport(0, 0, width, height);
        Matrix.perspectiveM(mProjectionMatrix, 0, 70.0f, (float) width / (float) height, 0.1f, 100.0f);

    }

    @Override
    public void onDrawFrame(GL10 gl10) {
        GLES32.glClear(GLES32.GL_COLOR_BUFFER_BIT | GLES32.GL_DEPTH_BUFFER_BIT);
        model.draw(mProjectionMatrix, mViewMatrix, mModelMatrix);
    }
}
