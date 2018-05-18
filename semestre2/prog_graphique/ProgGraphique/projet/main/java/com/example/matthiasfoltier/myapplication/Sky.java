package com.example.matthiasfoltier.myapplication;

import android.content.Context;
import android.opengl.Matrix;

/**
 * Created by matthias.foltier on 17/05/18.
 */

public class Sky {
    private com.example.matthiasfoltier.myapplication.Model3D mSky;
    private float[] mSkyMatrix=new float[16];


    //Constructor
    Sky(Context context){

        Matrix.setIdentityM(mSkyMatrix,0);
        mSky= com.example.matthiasfoltier.myapplication.ModelLoader.readOBJFile(context,"cylinder.obj");
        mSky.init("vertexshader.vert","fragmentshader.frag","vPosition", "vNormal","vTexCoord", R.drawable.planet2,2);

        Matrix.scaleM(mSkyMatrix,0,100f,80f,80f);
        Matrix.rotateM(getmSkyMatrix(),0,90f,0f,0f,0.5f);
        Matrix.translateM(getmSkyMatrix(),0,0f,-1f,0f);
    }


    //GETTER & SETTER
    public Model3D getmSky() {
        return mSky;
    }

    public void setmSky(Model3D mSky) {
        this.mSky = mSky;
    }

    public float[] getmSkyMatrix() {
        return mSkyMatrix;
    }

    public void setmSkyMatrix(float[] mSkyMatrix) {
        this.mSkyMatrix = mSkyMatrix;
    }
}


