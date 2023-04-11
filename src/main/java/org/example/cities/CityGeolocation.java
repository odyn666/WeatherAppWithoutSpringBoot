package org.example.cities;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class CityGeolocation {
    URL url;
    String geolocation = "";
    String cityName;

    double[] location=new double[2];
    Double latitude;
    Double longitude;

    public void geoLocation() throws Exception {
        url = new URL("https://geocoding-api.open-meteo.com/v1/search?name=" + Cities.WARSAW + "&count=1" + "&format=json");
        // url = new URL("https://www.metaweather.com/api/location/search/?query=London");

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        System.out.println("Responde code: " + responseCode);


        if (responseCode == HttpURLConnection.HTTP_OK) {

            StringBuilder response = new StringBuilder();

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            Scanner scanner = new Scanner(url.openStream());

            String input;
            while ((input = in.readLine()) != null) {

                response.append(input);
            }
            scanner.close();

            System.out.println(response);
            //parsing


            JSONParser parser = new JSONParser();
            JSONObject dataObject = (JSONObject) parser.parse(response.toString());
            JSONArray resultArray = (JSONArray) dataObject.get("results");
            JSONObject resultObject = (JSONObject) resultArray.get(0);

            latitude = (Double) resultObject.get("latitude");
            longitude = (Double) resultObject.get("longitude");

            System.out.println("Latitude: " + latitude);
            System.out.println("Longitude: " + longitude);

            // JSONObject countryData = (JSONObject) dataArray.get(0);
            // System.out.println(countryData.get("woeid"));
        }
        location[0]=latitude;
        location[1]=longitude;

    }

    public double[] getLocation() {
        return location;
    }
}
    
    
    

    