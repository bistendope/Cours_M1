package com.example.matthiasfoltier.myapplication;

import android.content.Context;
import android.opengl.Matrix;

/**
 * Created by matthias.foltier on 18/05/18.
 */

public class Ennemies {
    private com.example.matthiasfoltier.myapplication.Model3D mEnnemi;
    private float[] mEnnemiMatrix=new float[16];

    private float randomPositionX;
    private float randomPositionY;

    private long currentTime;
    //Constructor
    Ennemies(Context context){

        Matrix.setIdentityM(mEnnemiMatrix,0);
        mEnnemi= com.example.matthiasfoltier.myapplication.ModelLoader.readOBJFile(context,"Tie-Fighter.obj");
        mEnnemi.init("vertexshader.vert","fragmentshader.frag","vPosition", "vNormal","vTexCoord", R.drawable.fighter_texture,1);
        setPosition();
    }

    /*
     *   setPosition : Permet de remettre l'objet au loin dans un champ visible de la caméra
     */
    public void setPosition(){

        Matrix.setIdentityM(mEnnemiMatrix,0);
        randomPositionX=(float)(Math.random()*40);
        //permet de mettre de -19 à 20 en X
        if(randomPositionX>20)randomPositionX=-randomPositionX;
        randomPositionY=(float)(Math.random()*40);
        // permet de mettre de -37 à 2 en Y évitant la sphère
        if(randomPositionY>3)randomPositionY=-randomPositionY;
        currentTime=0;

        Matrix.scaleM(mEnnemiMatrix,0,0.05f,0.05f,0.05f);
        Matrix.translateM(getmEnnemiMatrix(),0,randomPositionX,70f,randomPositionY);
    }


    //GETTER & SETTER

    public long getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(long currentTime) {
        this.currentTime = currentTime;
    }

    public Model3D getmEnnemi() {
        return mEnnemi;
    }

    public void setmEnnemi(Model3D mEnnemi) {
        this.mEnnemi = mEnnemi;
    }

    public float[] getmEnnemiMatrix() {
        return mEnnemiMatrix;
    }

    public void setmEnnemiMatrix(float[] mEnnemiMatrix) {
        this.mEnnemiMatrix = mEnnemiMatrix;
    }
}
