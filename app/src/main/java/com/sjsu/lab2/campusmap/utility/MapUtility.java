package com.sjsu.lab2.campusmap.utility;

import com.sjsu.lab2.campusmap.model.BuildingDetail;
import com.sjsu.lab2.campusmap.model.CampusDetail;

import java.util.ArrayList;

public class MapUtility {

    /**
     * Method searchFor
     * Looks for corresponding building on campus by comparing given input to building names and abbrevations
     *
     * @param buildingName  input provided by user
     * @return BuildingDetail corresponding building detail
     */
    public static BuildingDetail seacrhFor(String buildingName){

        ArrayList<BuildingDetail> buildings = CampusDetail.getBuildings();

        for(int i=0; i<buildings.size(); i++){
            if(buildings.get(i).getName().toUpperCase().equals(buildingName.toUpperCase())
                    || buildings.get(i).getAbbr().toUpperCase().equals(buildingName.toUpperCase()))
                return buildings.get(i);
        }
        return null;
    }

    public static BuildingDetail seacrhForAddress(String address){

        ArrayList<BuildingDetail> buildings = CampusDetail.getBuildings();

        for(int i=0; i<buildings.size(); i++){
            if(buildings.get(i).getAddress().toUpperCase().equals(address.toUpperCase()))
                return buildings.get(i);
        }
        return null;
    }
}