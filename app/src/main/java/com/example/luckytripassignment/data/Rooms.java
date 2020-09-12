package com.example.luckytripassignment.data;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//Response architecture for the rooms array
public class Rooms implements Serializable {

    public static class Price {

        @SerializedName("currency")
        public String currency;

        @SerializedName("price_value")
        public double price_value;
    }

    public static class BedConfiguration {

        @SerializedName("count")
        public int count;

        @SerializedName("name")
        public String name;

    }

    public static class Room implements Comparable<Room>{

        @SerializedName("room_id")
        public int room_id;

        @SerializedName("name")
        public String name;

        @SerializedName("room_description")
        public String room_description;

        @SerializedName("number_of_rooms_left")
        public int number_of_rooms_left;

        @SerializedName("max_occupancy")
        public int max_occupancy;

        @SerializedName("price")
        public Price price;

        @SerializedName("bed_configurations")
        public ArrayList<BedConfiguration> bed_configurations;

        @SerializedName("photos")
        public List<String> photos;

        @Override
        public int compareTo(Room o) {
            return o.max_occupancy;
        }
    }

    public static class Root {

        @SerializedName("rooms")
        public ArrayList<Room> rooms;
    }


}
