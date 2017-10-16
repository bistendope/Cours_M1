package edu.univorleans.winteriscoming;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import edu.univorleans.winteriscoming.openWeatherMap.City;
import edu.univorleans.winteriscoming.openWeatherMap.LoadCityTask;


public class WeatherFragment extends Fragment implements LoadCityTask.AsyncResponse {


    ImageView iconImageView;
    TextView textView, descTextView, tempTextView, windTextView, humidityTextView, pressureTextView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_weather, container, false);
        iconImageView = v.findViewById(R.id.imageView);
        tempTextView = v.findViewById(R.id.tempTextView);
        windTextView = v.findViewById(R.id.windTextView);
        humidityTextView = v.findViewById(R.id.humidityTextView);
        pressureTextView =  v.findViewById(R.id.pressureTextView);
        descTextView = v.findViewById(R.id.descTextView);
        textView = v.findViewById(R.id.textView);
        return v;
    }

    @Override
    public void onActivityCreated(Bundle bundle){
        super.onActivityCreated(bundle);
        textView.setText(WeatherActivity.cityName);
        new LoadCityTask(this).execute(WeatherActivity.cityName);
    }

    @Override
    public void publishResults(City city) {

        if (city != null) {
            descTextView.setText(city.getDescription());
            tempTextView.setText(String.valueOf((int) city.getTemperature())+"°C");
            windTextView.setText(String.valueOf((int) (city.getWind_speed()))+"km/h");
            humidityTextView.setText(String.valueOf((int) city.getHumidity())+"%");
            pressureTextView.setText(String.valueOf((int) city.getPressure())+"hPa");
            iconImageView.setImageBitmap(city.getBitmap());
        }
    }
}
