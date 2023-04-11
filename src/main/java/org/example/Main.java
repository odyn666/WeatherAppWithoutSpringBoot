package org.example;

import org.example.cities.Cities;
import org.example.cities.CityGeolocation;
import org.example.weather.Weather;

public class Main
    {
    public static void main(String[] args)
        {
        CityGeolocation cityGeolocation = new CityGeolocation();
        Weather weather = new Weather();
        try
            {
// Fetch geolocation for a specific city (e.g. Warsaw)
            cityGeolocation.geoLocation(Cities.WARSAW);
// Fetch weather information based on the geolocation
            weather.weatherFromGeo(cityGeolocation.getLatitude(), cityGeolocation.getLongitude());
            } catch (Exception e)
            {
// Handle exceptions properly instead of throwing a generic RuntimeException
            System.err.println("Error occurred: " + e.getMessage());
            e.printStackTrace();
            }
        }
    }
