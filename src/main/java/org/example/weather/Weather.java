package org.example.weather;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Weather
    {
    private URL url;
    private Double temp;
    private Double wind;
    private Double windDir;
    
    public void weatherFromGeo(double latitude, double longitude) throws IOException, ParseException
        {
        url = new URL("https://api.open-meteo.com/v1/forecast?latitude=" + latitude + "&longitude=" + longitude
                + "&hourly=temperature_2m,rain&current_weather=true&forecast_days=1&timezone=Europe%2FBerlin");
        
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        
        int responseCode = connection.getResponseCode();
        
        if (HttpURLConnection.HTTP_OK == responseCode)
            {
            StringBuilder response = new StringBuilder();
            
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            
            Scanner scanner = new Scanner(url.openStream());
            
            String input;
            while ((input = in.readLine()) != null)
                {
                response.append(input);
                }
            scanner.close();
            
            JSONParser parser = new JSONParser();
            JSONObject weatherObject = (JSONObject) parser.parse(response.toString());
            JSONObject currentWeatherObject = (JSONObject) weatherObject.get("current_weather");
            
            temp = (Double) currentWeatherObject.get("temperature");
            wind = (Double) currentWeatherObject.get("windspeed");
            windDir = (Double) currentWeatherObject.get("winddirection");
            
            System.out.println(temp + "°C");
            System.out.print(wind + "km/h");
            
            if (windDir >= 90.0)
                System.out.print(" From North East");
            else if (windDir < 90 && windDir > 180)
                System.out.print(" From South East");
            else if (windDir < 180 && windDir > 270)
                System.out.print(" From South West");
            else if (windDir < 270 && windDir > 359)
                System.out.print(" From North West");
            else if (windDir == 0)
                System.out.print(" From North");
            else if (windDir == 90)
                System.out.print(" From East");
            else if (windDir == 180)
                System.out.print(" From South");
            else if (windDir == 270)
                System.out.print(" From West");
            }
        }
    }
