package edu.univorleans.winteriscoming.openWeatherMap;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class City {

    private static String api = "http://api.openweathermap.org/data/2.5/weather";
    private static String appid = "7de56b18f9e770cbe1a3ecbec44feaab";
    private static String iconURL = "http://openweathermap.org/img/w/";

    private String name;
    private String description;
    private double temperature;
    private double wind_speed;
    private double humidity;
    private double pressure;
    private Bitmap bitmap;

    public String getName(){return name;}
    public String getDescription(){return description;}
    public double getTemperature(){return temperature;}
    public double getWind_speed(){return wind_speed;}
    public double getHumidity(){return humidity;}
    public double getPressure(){return pressure;}
    public Bitmap getBitmap(){return bitmap;}

    public static City newInstance(String name)
    {
        City city = null;
        try {
            city = new City();
            city.name = name;

            URL url = new URL(api + "?q=" + name + "&units=metric&lang=fr" + "&appid=" + appid);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            InputStream inputStream = connection.getInputStream();
            String result = InputStreamToString(inputStream, 1024);

            JSONObject jsonObject = new JSONObject(result);
            JSONArray array = new JSONArray(jsonObject.getString("weather"));
            JSONObject jsonObject1 = array.getJSONObject(0);
             city.description = jsonObject1.getString("description");
            JSONObject main = jsonObject.getJSONObject("main");
            city.temperature = main.getDouble("temp");
            city.wind_speed = jsonObject.getJSONObject("wind").getDouble("speed") * 3.6;
            city.humidity = main.getDouble("humidity");
            city.pressure = main.getDouble("pressure");

            InputStream in = new URL( iconURL + jsonObject1.getString("icon")+".png").openStream();
            city.bitmap = BitmapFactory.decodeStream(in);


        } catch (JSONException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return city;
    }

    private City() {
    }

    private static String InputStreamToString (InputStream in, int bufSize) {
        final StringBuilder out = new StringBuilder();
        final byte[] buffer = new byte[bufSize];
        try {
            for (int ctr; (ctr = in.read(buffer)) != -1;) {
                out.append(new String(buffer, 0, ctr));
            }
        } catch (IOException e) {
            throw new RuntimeException("Cannot convert stream to string", e);
        }
        return out.toString();
    }

}
