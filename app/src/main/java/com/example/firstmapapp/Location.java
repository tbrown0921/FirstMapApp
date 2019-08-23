package com.example.firstmapapp;

public class Location {

    private double longitude;
    private double latitude;
    private int catid;
    private double distance;
    private Provider[] providers;

    public Location(double longitude, double latitude, int catid, double distance, Provider[] provider) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.catid = catid;
        this.distance = distance;
        this.providers = provider;
    }

    public double getlongitude() {
        return longitude;
    }

    public double getlatitude() {
        return latitude;
    }

    public int getcatid() {
        return catid;
    }

    public double getdistance() {
        return distance;
    }

    public Provider[] getproviders() {
        return providers;
    }

    @Override
    public String toString() {
        return String.valueOf(longitude) + ' ' + String.valueOf(latitude);
    }
}
