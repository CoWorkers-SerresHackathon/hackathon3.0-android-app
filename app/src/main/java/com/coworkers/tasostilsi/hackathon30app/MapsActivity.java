package com.coworkers.tasostilsi.hackathon30app;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.FrameLayout;

import com.androidnetworking.AndroidNetworking;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import libs.mjn.prettydialog.PrettyDialog;
import libs.mjn.prettydialog.PrettyDialogCallback;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private NetworkAvailability network;
    private FrameLayout progressBar;
    private PrettyDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        AndroidNetworking.initialize(getApplicationContext());

        progressBar = findViewById(R.id.progressBar);

        network = new NetworkAvailability(this);
        if (network.isNetworkWorks()) {
            progressBar.setVisibility(View.GONE);
            // Obtain the SupportMapFragment and get notified when the map is ready to be used.
            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map);

            mapFragment.getMapAsync(this);
        } else {
            dialog = new PrettyDialog(this);
            dialog.setTitle(getString(R.string.app_name))
                    .setMessage(getString(R.string.checkForNetworkMessage))
                    .addButton(getString(R.string.retry), android.R.color.white, android.R.color.holo_green_light, new PrettyDialogCallback() {
                        @Override
                        public void onClick() {
                            recreate();
                            dialog.dismiss();
                        }
                    })
                    .setAnimationEnabled(true)
                    .show();
        }
    }

    @Override
    public void onBackPressed() {

        dialog = new PrettyDialog(this);
        dialog.setTitle(getString(R.string.app_name))
                .setMessage(getString(R.string.exitTheAppMessage))
                .addButton(getString(android.R.string.ok), android.R.color.white, android.R.color.holo_red_light, new PrettyDialogCallback() {
                    @Override
                    public void onClick() {
                        MapsActivity.super.onBackPressed();
                        dialog.dismiss();
                    }
                })
                .addButton(getString(android.R.string.cancel), android.R.color.white, android.R.color.holo_blue_light, new PrettyDialogCallback() {
                    @Override
                    public void onClick() {
                        dialog.dismiss();
                    }
                })
                .setAnimationEnabled(true)
                .show();
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

    private void getTheLocationsAndAddMarkersOnMap(GoogleMap mMap) {
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

        addPolyline(mMap, serres, provatas);
        addPolyline(mMap, serres, kmitrousi);
        addPolyline(mMap, serres, skoutari);
        addPolyline(mMap, provatas, akamila);
        addPolyline(mMap, kmitrousi, akamila);
        addPolyline(mMap, kmitrousi, kkamila);
        addPolyline(mMap, kmitrousi, koumaria);
        addPolyline(mMap, skoutari, kkamila);
        addPolyline(mMap, skoutari, ageleni);
        addPolyline(mMap, skoutari, peponia);
        addPolyline(mMap, akamila, koumaria);
        addPolyline(mMap, kkamila, koumaria);
        addPolyline(mMap, koumaria, adelfiko);
        addPolyline(mMap, ageleni, peponia);
        addPolyline(mMap, peponia, adelfiko);
    }

    private void addPolyline(GoogleMap googleMap, LatLng start, LatLng end) {
        Polyline polyline = googleMap.addPolyline(new PolylineOptions()
                .clickable(true)
                .add(start, end));


    }


}
