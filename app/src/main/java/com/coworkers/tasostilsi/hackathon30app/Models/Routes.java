package com.coworkers.tasostilsi.hackathon30app.Models;

public class Routes {
    private String id;
    private Area startPoint;
    private Area endPoint;
    private double distance;

    public Routes() {
    }

    public Routes(String id, Area startPoint, Area endPoint, double distance) {
        this.id = id;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.distance = distance;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Area getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(Area startPoint) {
        this.startPoint = startPoint;
    }

    public Area getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(Area endPoint) {
        this.endPoint = endPoint;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
}
