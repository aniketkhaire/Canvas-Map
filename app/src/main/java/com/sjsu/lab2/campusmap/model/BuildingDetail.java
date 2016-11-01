package com.sjsu.lab2.campusmap.model;

public class BuildingDetail {
    private String name;
    private String abbr;
    private String address;
    private double lat;
    private double lng;
    private int buildinngImage;

    public BuildingDetail(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbbr() {
        return abbr;
    }

    public void setAbbr(String abbr) {
        this.abbr = abbr;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() { return lng; }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public int getBuildinngImage() {
        return buildinngImage;
    }

    public void setBuildinngImage(int buildinngImage) {
        this.buildinngImage = buildinngImage;
    }
}
