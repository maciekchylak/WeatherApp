
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class Api {
    private String apiKey;

    public Api(String apiKey) {
        this.apiKey = apiKey;
    }

    private JSONObject makeApiCall(URL url) throws IOException {
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        int status = con.getResponseCode();

        con.connect();

        JSONParser jp = new JSONParser();
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null){
            response.append(inputLine);
        }

        in.close();
        con.disconnect();
        JSONObject result = null;
        try {
            result = (JSONObject) jp.parse(response.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }


    public JSONObject getWeatherByCity(String city, String units, String lang) throws IOException {
        URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=" + city +
                "&appid=" + apiKey);
        JSONObject location = this.makeApiCall(url);
        location = (JSONObject) location.get("coord");
        String lat = location.get("lat").toString();
        String lon = location.get("lon").toString();

        URL url1 = new URL("https://api.openweathermap.org/data/2.5/onecall?" +
                "lat=" + lat + "&lon=" + lon +
                "&units=" + units +
                "&lang=" + lang +
                "&appid=" + apiKey);

        return this.makeApiCall(url1);
    }


}