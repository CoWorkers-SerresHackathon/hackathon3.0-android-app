package com.coworkers.tasostilsi.hackathon30app.Activities;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.FrameLayout;

import com.androidnetworking.AndroidNetworking;
import com.coworkers.tasostilsi.hackathon30app.Helpers.NetworkAvailability;
import com.coworkers.tasostilsi.hackathon30app.Models.Area;
import com.coworkers.tasostilsi.hackathon30app.Models.Routes;
import com.coworkers.tasostilsi.hackathon30app.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;

import libs.mjn.prettydialog.PrettyDialog;
import libs.mjn.prettydialog.PrettyDialogCallback;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private NetworkAvailability network;
    private FrameLayout progressBar;
    private PrettyDialog dialog;
    public final static double earthRadius = 6371e3;
    private ArrayList<Area> areas = new ArrayList<>();
    private ArrayList<Routes> routes = new ArrayList<>();

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

        getTheLocationsAndAddMarkersOnMap(googleMap);


        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(41.092083, 23.541016), 11.0f));
    }

    private void getTheLocationsAndAddMarkersOnMap(GoogleMap mMap) {
        setAreas();

        for (Area area : areas) {
            mMap.addMarker(new MarkerOptions().position(area.getCoordinates()).title(area.getName()));
        }

        for (Routes route : routes) {
            addPolyline(mMap, route);
        }

        /*HipsterDirectedGraph<String, Double> graph = GraphBuilder.<String, Double>create()
                .connect("serres").to("provatas").withEdge(getTheDistance(serres, provatas))
                .connect("serres").to("kmitrousi").withEdge(getTheDistance(serres, kmitrousi))
                .connect("serres").to("skoutari").withEdge(getTheDistance(serres, skoutari))
                .connect("provatas").to("akamila").withEdge(getTheDistance(provatas, akamila))
                .connect("kmitrousi").to("akamila").withEdge(getTheDistance(kmitrousi, akamila))
                .connect("kmitrousi").to("kkamila").withEdge(getTheDistance(kmitrousi, kkamila))
                .connect("kmitrousi").to("koumaria").withEdge(getTheDistance(kmitrousi, koumaria))
                .connect("skoutari").to("kkamila").withEdge(getTheDistance(skoutari, kkamila))
                .connect("skoutari").to("ageleni").withEdge(getTheDistance(skoutari, ageleni))
                .connect("skoutari").to("peponia").withEdge(getTheDistance(skoutari, peponia))
                .connect("akamila").to("koumaria").withEdge(getTheDistance(akamila, koumaria))
                .connect("kkamila").to("koumaria").withEdge(getTheDistance(kkamila, koumaria))
                .connect("koumaria").to("adelfiko").withEdge(getTheDistance(koumaria, adelfiko))
                .connect("ageleni").to("peponia").withEdge(getTheDistance(ageleni, peponia))
                .connect("peponia").to("adelfiko").withEdge(getTheDistance(peponia, adelfiko))
                .createDirectedGraph();

        SearchProblem problem = GraphSearchProblem.startingFrom("serres").in(graph).takeCostsFromEdges().build();
        System.out.println("Check graph Here " + Hipster.createDepthFirstSearch(problem).search("adelfiko"));*/
    }

    private void addPolyline(GoogleMap googleMap, Routes route) {
        Polyline polyline = googleMap.addPolyline(new PolylineOptions()
                .clickable(true)
                .add(route.getStartPoint().getCoordinates(), route.getEndPoint().getCoordinates()));
    }

    private double getTheDistance(LatLng s, LatLng e) {
        double distance = 0.0;
        double lat1 = Math.toRadians(s.latitude);
        double lon1 = Math.toRadians(s.longitude);
        double lat2 = Math.toRadians(e.latitude);
        double lon2 = Math.toRadians(e.longitude);
        double dLat = Math.toRadians(e.latitude - s.latitude);
        double dLon = Math.toRadians(e.longitude - s.longitude);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.cos(lat1) * Math.cos(lat2) *
                Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        distance = earthRadius * c;
        return distance;
    }

    private void makeTheArrayWithDistances() {

    }

    private void setAreas() {
        Area serres = new Area("1", "serres", new LatLng(41.092083, 23.541016));
        Area provatas = new Area("2", "provatas", new LatLng(41.068238, 23.390686));
        Area akamila = new Area("3", "akamila", new LatLng(41.058320, 23.424134));
        Area kkamila = new Area("4", "kkamila", new LatLng(41.020431, 23.483293));
        Area kmitrousi = new Area("5", "kmitrousi", new LatLng(41.058680, 23.457547));
        Area koumaria = new Area("6", "koumaria", new LatLng(41.016434, 23.434656));
        Area skoutari = new Area("7", "skoutari", new LatLng(41.020032, 23.520701));
        Area adelfiko = new Area("8", "adelfiko", new LatLng(41.014645, 23.457354));
        Area ageleni = new Area("9", "ageleni", new LatLng(41.003545, 23.559196));
        Area peponia = new Area("10", "peponia", new LatLng(40.988154, 23.516756));
        areas.add(serres);
        areas.add(provatas);
        areas.add(akamila);
        areas.add(kkamila);
        areas.add(kmitrousi);
        areas.add(koumaria);
        areas.add(skoutari);
        areas.add(adelfiko);
        areas.add(ageleni);
        areas.add(peponia);
        setRoutes();
    }

    private void setRoutes() {
        Routes route = new Routes("1", areas.get(0), areas.get(1), getTheDistance(areas.get(0).getCoordinates(), areas.get(1).getCoordinates()));
        routes.add(route);
        route = new Routes("2", areas.get(0), areas.get(4), getTheDistance(areas.get(0).getCoordinates(), areas.get(4).getCoordinates()));
        routes.add(route);
        route = new Routes("3", areas.get(0), areas.get(6), getTheDistance(areas.get(0).getCoordinates(), areas.get(6).getCoordinates()));
        routes.add(route);
        route = new Routes("4", areas.get(1), areas.get(2), getTheDistance(areas.get(1).getCoordinates(), areas.get(2).getCoordinates()));
        routes.add(route);
        route = new Routes("5", areas.get(4), areas.get(2), getTheDistance(areas.get(4).getCoordinates(), areas.get(2).getCoordinates()));
        routes.add(route);
        route = new Routes("6", areas.get(4), areas.get(3), getTheDistance(areas.get(4).getCoordinates(), areas.get(3).getCoordinates()));
        routes.add(route);
        route = new Routes("7", areas.get(4), areas.get(5), getTheDistance(areas.get(4).getCoordinates(), areas.get(5).getCoordinates()));
        routes.add(route);
        route = new Routes("8", areas.get(6), areas.get(3), getTheDistance(areas.get(6).getCoordinates(), areas.get(3).getCoordinates()));
        routes.add(route);
        route = new Routes("9", areas.get(6), areas.get(8), getTheDistance(areas.get(6).getCoordinates(), areas.get(8).getCoordinates()));
        routes.add(route);
        route = new Routes("10", areas.get(6), areas.get(9), getTheDistance(areas.get(6).getCoordinates(), areas.get(9).getCoordinates()));
        routes.add(route);
        route = new Routes("11", areas.get(2), areas.get(5), getTheDistance(areas.get(2).getCoordinates(), areas.get(5).getCoordinates()));
        routes.add(route);
        route = new Routes("12", areas.get(3), areas.get(5), getTheDistance(areas.get(3).getCoordinates(), areas.get(5).getCoordinates()));
        routes.add(route);
        route = new Routes("13", areas.get(5), areas.get(7), getTheDistance(areas.get(5).getCoordinates(), areas.get(7).getCoordinates()));
        routes.add(route);
        route = new Routes("14", areas.get(9), areas.get(8), getTheDistance(areas.get(9).getCoordinates(), areas.get(8).getCoordinates()));
        routes.add(route);
        route = new Routes("15", areas.get(9), areas.get(7), getTheDistance(areas.get(9).getCoordinates(), areas.get(7).getCoordinates()));
        routes.add(route);

    }
}
