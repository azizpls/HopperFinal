package com.example.hopper.fragments;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.location.Location;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.LongDef;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import com.example.hopper.LoginActivity;
import com.example.hopper.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.parse.Parse;
import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;
import com.parse.ParseUser;

@ParseClassName("Post")
class Post extends ParseObject {


    // creating keys for different desired parse attributes
    public static final String KEY_location = "location";
    public static final String KEY_BUSINESS_NAME = "BusinessName";




    // set and get methods for desired attribute
    public ParseGeoPoint getlocation() {
        return getParseGeoPoint(KEY_location);
    }
    public String getBusinessName(){
        return getString(KEY_BUSINESS_NAME);
    }
}


public class MapFragment extends Fragment {
    private final String TAG = "MapFragment";
    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COURSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1234;
    private static final float DEFAULT_ZOOM = 15f;
    private GoogleMap mMap;
    private FusedLocationProviderClient mFusedLocationProviderClient;
    //vars
    private Boolean mLocationPermissionsGranted = false;


    // The onCreateView method is called when Fragment should create its View object hierarchy,
    // either dynamically or via XML layout inflation.

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_map, container, false);
    }

    // This event is triggered soon after onCreateView().
    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getLocationPermission();


    }


    private void getDeviceLocation() {
        Log.d(TAG, "getDeviceLocation: getting devices current location");
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getContext());
        try {
            if (mLocationPermissionsGranted) {
                final Task location = mFusedLocationProviderClient.getLastLocation();
                location.addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "onComplete: found location!");
                            Location currentLocation = (Location) task.getResult();

                            moveCamera(new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude()), DEFAULT_ZOOM);

                        } else {
                            Log.d(TAG, "onComplete: current location is null");
                            Toast.makeText(getContext(), "unablet to get current location", Toast.LENGTH_SHORT).show();

                        }

                    }
                });

            }

        } catch (SecurityException e) {
            Log.e(TAG, "getDeviceLocation: SecurityException: " + e.getMessage());


        }

    }

    private void moveCamera(LatLng latLng, float zoom) {
        Log.d(TAG, "moveCamera: moving the camera to: lat: " + latLng.latitude + ", lng: " + latLng.longitude);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));
    }

    private void initMap() {

        Log.d(TAG, "initMap: initializing map");
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);

        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                Toast.makeText(getActivity(), "Map is Ready", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onMapReady: map is ready");
                mMap = googleMap;
                if (mLocationPermissionsGranted) {

                    LatLng Tallahassee = new LatLng(30.4383, -84.2807);
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(Tallahassee));

                    LatLng Wilbury = new LatLng(30.435281, -84.288922);
                    mMap.addMarker(new MarkerOptions().position(Wilbury).title("The Wilbury"));

                    LatLng WarHorse = new LatLng(30.435282, -84.29061);
                    mMap.addMarker(new MarkerOptions().position(WarHorse).title("War Horse Whiskey Bar"));

                    LatLng GrasslandsBrewing = new LatLng(30.435191, -84.290737);
                    mMap.addMarker(new MarkerOptions().position(GrasslandsBrewing).title("Grasslands Brewing Company"));

                    LatLng ProofBrewing = new LatLng(30.430958, -84.281308);
                    mMap.addMarker(new MarkerOptions().position(ProofBrewing).title("Proof Brewing Company"));

                    LatLng BrassTap = new LatLng(30.435408, -84.292916);
                    mMap.addMarker(new MarkerOptions().position(BrassTap).title("The Brass Tap"));

                    LatLng MadisonSocial = new LatLng(30.436434, -84.29782);
                    mMap.addMarker(new MarkerOptions().position(MadisonSocial).title("Madison Social"));

                    LatLng Township = new LatLng(30.437005, -84.297991);
                    mMap.addMarker(new MarkerOptions().position(Township).title("Township"));

                    LatLng Liberty = new LatLng(30.456865, -84.280462);
                    mMap.addMarker(new MarkerOptions().position(Liberty).title("Liberty Bar"));

                    LatLng Palace = new LatLng(30.434069, -84.303992);
                    mMap.addMarker(new MarkerOptions().position(Palace).title("The Palace"));

                    LatLng PoorPaul = new LatLng(30.446927, -84.326123);
                    mMap.addMarker(new MarkerOptions().position(PoorPaul).title("Poor Paul's"));

                    getDeviceLocation();

                    updateLocationUI();


                    if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                            != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(),
                            Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                    mMap.setMyLocationEnabled(true);
                    //Add map UI settings using mMap.getUiSettings().

                }
            }
        });

    }

    private void updateLocationUI() {
        if (mMap == null) {
            return;
        }
        try {
            if (mLocationPermissionsGranted) {
                mMap.setMyLocationEnabled(true);
                mMap.getUiSettings().setMyLocationButtonEnabled(true);
            } else {
                mMap.setMyLocationEnabled(false);
                mMap.getUiSettings().setMyLocationButtonEnabled(false);
                //mLastKnownLocation = null;
                getLocationPermission();
            }
        } catch (SecurityException e)  {
            Log.e("Exception: %s", e.getMessage());
        }
    }

    private void getLocationPermission(){
        Log.d(TAG, "getLocationPermission: getting location permissions");
        String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
        if(ContextCompat.checkSelfPermission(getContext().getApplicationContext(),FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            if(ContextCompat.checkSelfPermission(getContext().getApplicationContext(),COURSE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                mLocationPermissionsGranted = true;
                initMap();
            }
            else{
                ActivityCompat.requestPermissions((Activity) getContext(), permissions, LOCATION_PERMISSION_REQUEST_CODE);
            }
        }else{
            ActivityCompat.requestPermissions((Activity) getContext(), permissions, LOCATION_PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.d(TAG, "onRequestPermissionResult: called.");
        mLocationPermissionsGranted = false;

        switch (requestCode){
            case LOCATION_PERMISSION_REQUEST_CODE:{
                if(grantResults.length > 0){
                    for(int i = 0; i<grantResults.length; i++){
                        if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                            mLocationPermissionsGranted = false;
                            Log.d(TAG, "onRequestPermissionResult: permission failed");
                        }
                    }
                    Log.d(TAG, "onRequestPermissionResult: permission granted");

                    mLocationPermissionsGranted = true;
                    //Initialize our map
                    initMap();

                }
            }

        }

    }

}
