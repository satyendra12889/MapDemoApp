package com.example.satyendra.mapdemoapp;

import android.graphics.Color;
import android.graphics.Path;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener, View.OnClickListener{

    private GoogleMap mMap;// Might be null if Google Play services APK is not available.
    private Button toggleBtn;
    private Button applyPath;
    private Button startGeo;

    private boolean toggleState;

    private ArrayList<Path> paths;
    private ArrayList<LatLng> locations;
    private Path tempPath;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.home_activity);

        paths = new ArrayList<Path>();
        locations = new ArrayList<LatLng>();
        tempPath = new Path();

        toggleBtn  = (Button) findViewById(R.id.toggle);
        applyPath = (Button) findViewById(R.id.applyPath);
        startGeo  = (Button) findViewById(R.id.startGeo);

        applyPath.setOnClickListener(this);
        toggleBtn.setOnClickListener(this);
        startGeo.setOnClickListener(this);

        setUpMapIfNeeded();
    }


    @Override
    protected void onResume()
    {
        super.onResume();
        setUpMapIfNeeded();
    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded()
    {

        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null)
        {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map1))
                    .getMap();
    // Check if we were successful in obtaining the map.
//            ((SupportMapFragment)mMap).getMapAsync(this);
            if (mMap != null)
            {
                setUpMap();
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {

        mMap.setOnMapClickListener(this);

        mMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));
    }


    public void addMarkOnMap(LatLng loc)
    {

        LatLng location = loc;
        mMap.addMarker(new MarkerOptions().position(location).title("Marker"));

    }

    @Override
    public void onMapReady(GoogleMap googleMap)
    {
        LatLng sydney = new LatLng(-34, 151);
    }

    @Override
    public void onMapClick(LatLng latLng) {

        if(toggleState == true)
        {
            locations.add(latLng);;

            addMarkOnMap(latLng);
           ;
        }
    }

    @Override
    public void onClick(View v) {
        if(v == toggleBtn)
        {
            onToggleBtnClick();
        }
        else if( v == applyPath)
        {
            applyPath();
            locations = new ArrayList<LatLng>();
        }else if(v== startGeo)
        {
            startLocationTracker();
        }
    }

    private void startLocationTracker() {

        LocationManager locationManager = (LocationManager) this.getSystemService(this.LOCATION_SERVICE);

        LocationListener locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                // Called when a new location is found by the network location provider.
//                makeUseOfNewLocation(location);
                onMapClick(new LatLng(location.getLatitude(),location.getLongitude()));
                System.out.print("location "+ location.getLatitude());
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(),location.getLongitude()), 5));
            }

            public void onStatusChanged(String provider, int status, Bundle extras) {}

            public void onProviderEnabled(String provider) {}

            public void onProviderDisabled(String provider) {}
        };

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        locationManager.requestLocationUpdates(LocationManager.PASSIVE_PROVIDER, 0, 0, locationListener);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);


    }

    public void applyPath()
    {
        PolylineOptions rectOptions = new PolylineOptions();
        for (int i = 0; i < locations.size() ; i++) {
                rectOptions.add(locations.get(i));
        }
        Polyline polygon = mMap.addPolyline(rectOptions);
    }

    public void onToggleBtnClick(){
        if(toggleState == true)
        {
            toggleState = false;
            toggleBtn.setText("OFF");
        }
        else
        {
            toggleState  = true;
            toggleBtn.setText("ON");
        }
    }

}
