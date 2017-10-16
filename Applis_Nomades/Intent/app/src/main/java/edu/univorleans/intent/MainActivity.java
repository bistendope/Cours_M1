package edu.univorleans.intent;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    public static String uri = "geo:47.8446264,1.93974819999994";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Intent intent = new Intent();
        //intent.setAction(Intent.ACTION_VIEW);
        //intent.setData(Uri.parse(uri));
        startActivity(intent);
    }

//content://downloads/all_downloads/4"
    //Intent intent = new Intent();
    //intent.setAction(Intent.ACTION_VIEW);
    //intent.setDataAndType(Uri.parse(uri), "image/*");
    //startActivity(intent);

}
