package com.sjsu.lab2.campusmap.model;

import com.sjsu.lab2.campusmap.R;

import java.util.ArrayList;

public class CampusDetail {

    private static double topLeftLat;
    private static double topLeftLng;
    private static double bottomRightLat;
    private static double bottomRightLng;

    private static ArrayList<BuildingDetail> buildings;

    public static void initCampus(){
        //co-ordinates go HERE
        topLeftLat = 37.336535;
        topLeftLng = -121.882140;
        bottomRightLat = 37.336535;
        bottomRightLng = -121.882140;
    }

    public CampusDetail(){
        initCampus();
        initBuildings();
    }

    public static double getTopLeftLat() {
        return topLeftLat;
    }

    public static void setTopLeftLat(double topLeftLat) {
        CampusDetail.topLeftLat = topLeftLat;
    }

    public static double getTopLeftLng() {
        return topLeftLng;
    }

    public static void setTopLeftLng(double topLeftLng) {
        CampusDetail.topLeftLng = topLeftLng;
    }

    public static double getBottomRightLat() {
        return bottomRightLat;
    }

    public static void setBottomRightLat(double bottomRightLat) { CampusDetail.bottomRightLat = bottomRightLat; }

    public static double getBottomRightLng() {
        return bottomRightLng;
    }

    public static void setBottomRightLng(double bottomRightLng) { CampusDetail.bottomRightLng = bottomRightLng; }

    public static ArrayList<BuildingDetail> getBuildings() { return buildings; }

    public static void setBuildings(ArrayList<BuildingDetail> buildings) { CampusDetail.buildings = buildings; }

    public static void initBuildings(){

        buildings = new ArrayList<BuildingDetail>();
        BuildingDetail b1 = new BuildingDetail();
        b1.setName("King Library");
        b1.setAbbr("KING");
        b1.setAddress("Dr. Martin Luther King, Jr. Library, 150 East San Fernando Street, San Jose, CA 95112");
        b1.setLat(37.3355069);
        b1.setLng(-121.8867705);
        b1.setBuildinngImage(R.mipmap.mlk_library);

        BuildingDetail b2 = new BuildingDetail();
        b2.setName("Engineering Building");
        b2.setAbbr("ENG");
        b2.setAddress("San José State University Charles W. Davidson College of Engineering, 1 Washington Square, San Jose, CA 95112");
        b2.setLat(37.337111);
        b2.setLng(-121.881556);
        b2.setBuildinngImage(R.mipmap.engg);

        BuildingDetail b3 = new BuildingDetail();
        b3.setName("Yoshihiro Uchida Hall");
        b3.setAbbr("YUH");
        b3.setAddress("Yoshihiro Uchida Hall, San Jose, CA 95112");
        b3.setLat(37.333673);
        b3.setLng(-121.883799);
        b3.setBuildinngImage(R.mipmap.yuhall);

        BuildingDetail b4 = new BuildingDetail();
        b4.setName("Student Union");
        b4.setAbbr("SU");
        b4.setAddress("Student Union Building, San Jose, CA 95112");
        b4.setLat(37.336474);
        b4.setLng(-121.880817);
        b4.setBuildinngImage(R.mipmap.student_union);

        BuildingDetail b5 = new BuildingDetail();
        b5.setName("BBC");
        b5.setAbbr("BBC");
        b5.setAddress("Boccardo Business Complex, San Jose, CA 95112");
        b5.setLat(37.336565);
        b5.setLng(-121.878541);
        b5.setBuildinngImage(R.mipmap.bbc);

        BuildingDetail b6 = new BuildingDetail();
        b6.setName("South Parking Garage");
        b6.setAbbr("SPG");
        b6.setAddress("San Jose State University South Garage, 330 South 7th Street, San Jose, CA 95112");
        b6.setLat(37.3353619);
        b6.setLng(-121.8816743);
        b6.setBuildinngImage(R.mipmap.south_garage);

        buildings.add(b1);
        buildings.add(b2);
        buildings.add(b3);
        buildings.add(b4);
        buildings.add(b5);
        buildings.add(b6);
    }
}
