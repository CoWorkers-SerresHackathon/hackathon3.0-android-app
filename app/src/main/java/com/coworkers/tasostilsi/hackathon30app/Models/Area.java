package com.coworkers.tasostilsi.hackathon30app.Models;

import com.google.android.gms.maps.model.LatLng;

public class Area {
    private String id;
    private String name;
    private LatLng coordinates;

    public Area() {
    }

    public Area(String id, String name, LatLng coordinates) {

        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
    }

    public String getId() {

        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LatLng getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(LatLng coordinates) {
        this.coordinates = coordinates;
    }
}
