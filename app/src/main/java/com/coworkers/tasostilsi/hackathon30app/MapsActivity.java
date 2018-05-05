package com.coworkers.tasostilsi.hackathon30app;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.androidnetworking.AndroidNetworking;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        AndroidNetworking.initialize(getApplicationContext());
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
       /* LatLng serres = new LatLng(41.092083, 23.541016);
        mMap.addMarker(new MarkerOptions().position(serres).title("Serres"));

        LatLng provatas = new LatLng(41.068238, 23.390686);
        mMap.addMarker(new MarkerOptions().position(provatas).title("provatas"));

        LatLng akamila = new LatLng(41.058320, 23.424134);
        mMap.addMarker(new MarkerOptions().position(akamila).title("akamila"));

        Polyline polyline1 = googleMap.addPolyline(new PolylineOptions()
                .clickable(true)
                .add(serres, provatas, akamila));

        LatLng kkamila = new LatLng(41.020431, 23.483293);
        mMap.addMarker(new MarkerOptions().position(kkamila).title("kkamila"));

        LatLng kmitrousi = new LatLng(41.058680, 23.457547);
        mMap.addMarker(new MarkerOptions().position(kmitrousi).title("kmitrousi"));

        LatLng koumaria = new LatLng(41.016434, 23.434656);
        mMap.addMarker(new MarkerOptions().position(koumaria).title("koumaria"));

        LatLng skoutari = new LatLng(41.016434, 23.434656);
        mMap.addMarker(new MarkerOptions().position(skoutari).title("skoutari"));*/

       getTheLocationsAndAddMarkersOnMap(googleMap);


        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(41.092083, 23.541016), 7.0f));
    }

    private void getTheLocationsAndAddMarkersOnMap(GoogleMap mMap){
        LatLng serres = new LatLng(41.092083, 23.541016);
        mMap.addMarker(new MarkerOptions().position(serres).title("Serres"));

        LatLng provatas = new LatLng(41.068238, 23.390686);
        mMap.addMarker(new MarkerOptions().position(provatas).title("provatas"));

        LatLng akamila = new LatLng(41.058320, 23.424134);
        mMap.addMarker(new MarkerOptions().position(akamila).title("akamila"));

        LatLng kkamila = new LatLng(41.020431, 23.483293);
        mMap.addMarker(new MarkerOptions().position(kkamila).title("kkamila"));

        LatLng kmitrousi = new LatLng(41.058680, 23.457547);
        mMap.addMarker(new MarkerOptions().position(kmitrousi).title("kmitrousi"));

        LatLng koumaria = new LatLng(41.016434, 23.434656);
        mMap.addMarker(new MarkerOptions().position(koumaria).title("koumaria"));

        LatLng skoutari = new LatLng(41.020032, 23.520701);
        mMap.addMarker(new MarkerOptions().position(skoutari).title("skoutari"));

        LatLng adelfiko = new LatLng(41.014645, 23.457354);
        mMap.addMarker(new MarkerOptions().position(adelfiko).title("adelfiko"));

        LatLng ageleni = new LatLng(41.003545, 23.559196);
        mMap.addMarker(new MarkerOptions().position(ageleni).title("ageleni"));

        LatLng peponia = new LatLng(40.988154, 23.516756);
        mMap.addMarker(new MarkerOptions().position(peponia).title("peponia"));

        addPolyline(mMap,serres,provatas);
        addPolyline(mMap,serres,kmitrousi);
        addPolyline(mMap,serres,skoutari);
        addPolyline(mMap,provatas,akamila);
        addPolyline(mMap,kmitrousi,akamila);
        addPolyline(mMap,kmitrousi,kkamila);
        addPolyline(mMap,kmitrousi,koumaria);
        addPolyline(mMap,skoutari,kkamila);
        addPolyline(mMap,skoutari,ageleni);
        addPolyline(mMap,skoutari,peponia);
        addPolyline(mMap,akamila,koumaria);
        addPolyline(mMap,kkamila,koumaria);
        addPolyline(mMap,koumaria,adelfiko);
        addPolyline(mMap,ageleni,peponia);
        addPolyline(mMap,peponia,adelfiko);
    }

    private void addPolyline(GoogleMap googleMap, LatLng start, LatLng end){
        Polyline polyline = googleMap.addPolyline(new PolylineOptions()
                .clickable(true)
                .add(start,end));


    }
}
