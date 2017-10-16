package edu.univorleans.winteriscoming;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;

import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.TileOverlayOptions;

import edu.univorleans.winteriscoming.openWeatherMap.OpenWeatherMap;

/**
 * Created by fredericdabrowski on 09/09/2017.
 */

public class MapFragment extends SupportMapFragment implements OnMapReadyCallback {

    MapView mapView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_map, container, false);
        mapView = v.findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);
        return v;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        googleMap.addMarker(new MarkerOptions().position(WeatherActivity.cityCoord).title("Marker"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(WeatherActivity.cityCoord));
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(3.0f));
        OpenWeatherMap openWeatherMap = new OpenWeatherMap();
        googleMap.addTileOverlay(new TileOverlayOptions().tileProvider(openWeatherMap));
        mapView.onResume();

    }

}
