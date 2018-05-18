package com.example.matthiasfoltier.myapplication;

import android.content.Context;
import android.opengl.Matrix;

/**
 * Created by matthias.foltier on 25/04/18.
 */

public class Ground {
    private com.example.matthiasfoltier.myapplication.Model3D mGround;
    private float[] mGroundMatrix=new float[16];


    //Constructor
    Ground(Context context){

        Matrix.setIdentityM(mGroundMatrix,0);
        mGround= com.example.matthiasfoltier.myapplication.ModelLoader.readOBJFile(context,"3d-model.obj");
        mGround.init("vertexshader.vert","fragmentshader.frag","vPosition", "vNormal","vTexCoord", R.drawable.planet2,3);

        Matrix.scaleM(mGroundMatrix,0,300f,60f,40f);
        Matrix.translateM(mGroundMatrix,0,0f,1f,2f);

    }


    //GETTER & SETTER
    public Model3D getmGround() {
        return mGround;
    }

    public void setmGround(Model3D mGround) {
        this.mGround = mGround;
    }

    public float[] getmGroundMatrix() {
        return mGroundMatrix;
    }

    public void setmGroundMatrix(float[] mGroundMatrix) {
        this.mGroundMatrix = mGroundMatrix;
    }
}
