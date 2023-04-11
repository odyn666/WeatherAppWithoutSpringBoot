package org.example.cities;

import org.example.weather.Weather;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class CityGeolocation
    {
    private URL url;
    private String geolocation = "";
    private String cityName;
    private Double latitude;
    private Double longitude;
    private Weather weather = new Weather();
    
    public Double getLatitude()
        {
        return latitude;
        }
    
    public Double getLongitude()
        {
        return longitude;
        }
    
    public void geoLocation(Enum cityEnum) throws Exception
        {
        url = new URL("https://geocoding-api.open-meteo.com/v1/search?name=" + cityEnum + "&count=1" + "&format=json");
        
        cityName = cityEnum.toString().toLowerCase();
        String city = cityName.substring(0, 1).toUpperCase() + cityName.substring(1);
        
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        
        int responseCode = connection.getResponseCode();
        System.out.println("Response code: " + responseCode);
        
        System.out.println("Geolocation of: " + city);
        
        if (responseCode == HttpURLConnection.HTTP_OK)
            {
            StringBuilder response = new StringBuilder();
            try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream())))
                {
                String input;
                while ((input = in.readLine()) != null)
                    {
                    response.append(input);
                    }
                }
            
            JSONParser parser = new JSONParser();
            JSONObject dataObject = (JSONObject) parser.parse(response.toString());
            JSONArray resultArray = (JSONArray) dataObject.get("results");
            JSONObject resultObject = (JSONObject) resultArray.get(0);
            
            latitude = (Double) resultObject.get("latitude");
            longitude = (Double) resultObject.get("longitude");
            
            System.out.println("Latitude: " + latitude);
            System.out.println("Longitude: " + longitude);
            }
        }
    }
