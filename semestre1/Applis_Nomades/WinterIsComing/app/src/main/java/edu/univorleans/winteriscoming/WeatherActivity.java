package edu.univorleans.winteriscoming;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.google.android.gms.maps.model.LatLng;

public class WeatherActivity extends FragmentActivity {

    public final static String cityName = "Orléans";
    public final static LatLng cityCoord = new LatLng(47.9025, 1.9043);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
    }

    public void onClick(View v) {
        startActivity(new Intent(this, MapActivity.class));
    }
}
