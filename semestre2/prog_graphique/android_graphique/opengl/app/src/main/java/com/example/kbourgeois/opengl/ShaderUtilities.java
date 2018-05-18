package com.example.kbourgeois.opengl;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLES32;
import android.opengl.GLUtils;
import android.util.Log;

import java.io.InputStream;

public class ShaderUtilities {

    private static Context context;

    public static void init(Context c){
        context = c;
    }

    public static int loadShader(int type, String filename){
        try {
            InputStream is = context.getAssets().open(filename);

            java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
            String sourceCode = s.hasNext() ? s.next() : "";

            s.close();
            is.close();

            int shader = GLES32.glCreateShader(type);
            GLES32.glShaderSource(shader, sourceCode);
            GLES32.glCompileShader(shader);
            int[] compiled = new int[1];
            GLES32.glGetShaderiv(shader, GLES32.GL_COMPILE_STATUS, compiled, 0);
            if (compiled[0] == 0) {
                String info = GLES32.glGetShaderInfoLog(shader);
                GLES32.glDeleteShader(shader);
                shader = 0;
                throw new RuntimeException("Could not compile shader " +
                        type + ":" + info);
            }
            Log.d("Debug : ", "Shader created");
            return shader;
        } catch (Exception e){
            Log.e(e.toString(), e.getMessage());
            return -1;
        }
    }

    public static int loadTexture(final int resourceId, int[] textureHandle , int index)
    {

        if (textureHandle[index] != 0)
        {
            final BitmapFactory.Options options = new BitmapFactory.Options();
            options.inScaled = false;   // No pre-scaling

            // Read in the resource
            final Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), resourceId, options);

            // Bind to the texture in OpenGL
            GLES32.glBindTexture(GLES32.GL_TEXTURE_2D, textureHandle[index]);

            // Set filtering
            GLES32.glTexParameteri(GLES32.GL_TEXTURE_2D, GLES32.GL_TEXTURE_MIN_FILTER, GLES32.GL_NEAREST);
            GLES32.glTexParameteri(GLES32.GL_TEXTURE_2D, GLES32.GL_TEXTURE_MAG_FILTER, GLES32.GL_NEAREST);

            // Load the bitmap into the bound texture.
            GLUtils.texImage2D(GLES32.GL_TEXTURE_2D, 0, bitmap, 0);

            // Recycle the bitmap, since its data has been loaded into OpenGL.
            bitmap.recycle();
        }

        if (textureHandle[index] == 0)
        {
            throw new RuntimeException("Error loading texture.");
        }

        return textureHandle[index];
    }
}
