package com.example.matthiasfoltier.myapplication;

import android.content.Context;
import android.opengl.GLES32;
import android.opengl.Matrix;
import android.os.SystemClock;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by matthias.foltier on 06/04/18.
 */

class MyRenderer implements android.opengl.GLSurfaceView.Renderer {
    private com.example.matthiasfoltier.myapplication.Model3D mModel;
    private Ground Ground;
    private Ground Ground2;

    private Sky Sky;


    public float[] mViewMatrix=new float[16];
    private float[] mProjectionMatrix=new float[16];
    private float[] mModelMatrix=new float[16];
    private Context context;

    public MyRenderer(Context context){
        super();
        this.context=context;
    }


    private Ennemies spawnHandler;
    private Ennemies spawnHandler2;

    @Override
    public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {
        // Set the background color to black ( rgba ).
        GLES32.glClearColor(0.529f, 0.700f, 0.999f, 0.5f);
        GLES32.glClearDepthf(1.0f);
        GLES32.glEnable(GLES32.GL_DEPTH_TEST);
        GLES32.glDepthFunc(GLES32.GL_LEQUAL);

        Matrix.setLookAtM(mViewMatrix, 0, 0, -5, -1, 0f, 0f ,0f, 0f, 1.0f, 0.0f);
        Matrix.perspectiveM(mProjectionMatrix, 0, 70.0f, 9.0f / 16.0f, 0.1f, 100.0f);
        Matrix.setIdentityM(mModelMatrix,0);


        Ground = new Ground(context);
        Ground2 = new Ground(context);

        Sky = new Sky(context);

        spawnHandler = new Ennemies(context);
        spawnHandler2 = new Ennemies(context);

        mModel = com.example.matthiasfoltier.myapplication.ModelLoader.readOBJFile(context,"fighter.obj");
        mModel.init("vertexshader.vert","fragmentshader.frag","vPosition", "vNormal","vTexCoord", R.drawable.fighter_texture,0);

        Matrix.scaleM(mModelMatrix,0,-0.5f,-0.5f,-0.5f);
        Matrix.rotateM(Ground2.getmGroundMatrix(),0,180f,0.5f,0f,0f);

    }

    @Override
    public void onSurfaceChanged(GL10 gl10, int width, int height) {
        GLES32.glViewport(0,0,width,height);
        Matrix.perspectiveM(mProjectionMatrix, 0, 70.0f, (float)width/height, 0.1f, 100.0f);
    }


    @Override
    public void onDrawFrame(GL10 gl10) {
        GLES32.glClear(GLES32.GL_COLOR_BUFFER_BIT | GLES32.GL_DEPTH_BUFFER_BIT);

        mModel.draw(mProjectionMatrix, mViewMatrix, mModelMatrix,0);

        if(spawnHandler.getCurrentTime()==250)
            spawn(1);
        spawnHandler.setCurrentTime(spawnHandler.getCurrentTime()+1);
        if(spawnHandler2.getCurrentTime()==250)
            spawn(2);
        spawnHandler2.setCurrentTime(spawnHandler2.getCurrentTime()+1);

        spawnHandler.getmEnnemi().draw(mProjectionMatrix,mViewMatrix,spawnHandler.getmEnnemiMatrix(),1);
        spawnHandler2.getmEnnemi().draw(mProjectionMatrix,mViewMatrix,spawnHandler2.getmEnnemiMatrix(),1);
        Matrix.translateM(spawnHandler.getmEnnemiMatrix(),0,0f,-1f,0f);
        Matrix.translateM(spawnHandler2.getmEnnemiMatrix(),0,0f,-1f,0f);

        Sky.getmSky().draw(mProjectionMatrix, mViewMatrix,Sky.getmSkyMatrix(),2);
        Matrix.rotateM(Sky.getmSkyMatrix(),0,0.2f,0f,0.5f,0f);

        Ground.getmGround().draw(mProjectionMatrix, mViewMatrix,Ground.getmGroundMatrix(),3);
        Ground2.getmGround().draw(mProjectionMatrix, mViewMatrix,Ground2.getmGroundMatrix(),3);
        Matrix.rotateM(Ground.getmGroundMatrix(),0,0.7f,-0.5f,0f,0f);
        Matrix.rotateM(Ground2.getmGroundMatrix(),0,0.7f,-0.5f,0f,0f);

    }
    public void translate(float dx, float dy, float dz){
        Matrix.translateM(mModelMatrix, 0, dx, dy, dz);
    }

    public void spawn(int i)  {
        boolean pos=true;
        if (i==1)
        spawnHandler.setPosition();
        else {

            spawnHandler2.setPosition();

        }

    }
}
