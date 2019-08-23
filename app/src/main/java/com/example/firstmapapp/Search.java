package com.example.firstmapapp;

public class Search {

    private double Lat;
    private double Long;
    private int Cat;
    private Location[] locations;

    public Search(double Lat, double Long, int Radius, int Cat, Location[] locations) {
        this.Lat = Lat;
        this.Long = Long;
        this.Cat = Cat;
        this.locations = locations;
    }

    public double getLat() {
        return Lat;
    }

    public double getLong() {
        return Long;
    }

    public int getCat() {
        return Cat;
    }

    public Location[] getlocations() {
        return locations;
    }
}
