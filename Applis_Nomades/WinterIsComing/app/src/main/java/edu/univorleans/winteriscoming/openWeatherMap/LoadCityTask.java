package edu.univorleans.winteriscoming.openWeatherMap;

import android.os.AsyncTask;

import edu.univorleans.winteriscoming.openWeatherMap.City;

public class LoadCityTask extends AsyncTask<String, Void, City> {

    AsyncResponse asyncResponse;

    public LoadCityTask(AsyncResponse asyncResponse){
        this.asyncResponse = asyncResponse;
    }

    @Override
    protected City doInBackground(String... strings) {

        return City.newInstance(strings[0]);
    }

    @Override
    protected void onPostExecute(City city) {

        super.onPostExecute(city);
        asyncResponse.publishResults(city);
    }

    public interface AsyncResponse{

        public void publishResults(City city);
    }
}
