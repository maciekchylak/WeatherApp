import Weather.Current;
import Weather.Day;
import Weather.Hour;
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
import java.util.Arrays;

public class WeatherAnalizer
{

    private JSONObject jsonWeather;

    private Api api;

    public Current currentWeather;
    public Hour[] hoursWeather;
    public int nbOfHour;
    public Day[] daysWeather;
    public int nbOfDay;

    public WeatherAnalizer()
    {
        api = new Api("0e082e7abe9c29697bec4d3e968c1e96");
        jsonWeather = null;

        currentWeather = new Current();

        nbOfHour = 48;
        hoursWeather = new Hour[nbOfHour];
        for (int i = 0; i<nbOfHour; i++)
        {
            hoursWeather[i] = new Hour();
        }

        nbOfDay = 8;
        daysWeather = new Day[nbOfDay];
        for (int i = 0; i<nbOfDay; i++)
        {
            daysWeather[i] = new Day();
        }
    }

    public void getCurrentWeather(String city, String units, String lang) throws IOException
    {
        jsonWeather = api.getWeatherByCity(city, units, lang);

        JSONObject jsonCurrent = (JSONObject)jsonWeather.get("current");
        currentWeather.uppdate(jsonCurrent);

        JSONArray jsonArrayHours = (JSONArray)jsonWeather.get("hourly");
        for (int i=0; i<nbOfHour; i++)
        {
            hoursWeather[i].uppdate((JSONObject)jsonArrayHours.get(i));
        }

        JSONArray jsonArrayDay = (JSONArray)jsonWeather.get("daily");
        for (int i=0; i<nbOfDay; i++)
        {
            daysWeather[i].uppdate((JSONObject)jsonArrayDay.get(i));
        }
    }

    @Override
    public String toString() {
        return "WeatherAnalizer{" +
                ", currentWeather=" + currentWeather + '\n' + '\n' + '\n' +
                ", hoursWeather=" + Arrays.toString(hoursWeather) +
                '\n' + '\n' + '\n' +
                ", daysWeather=" + Arrays.toString(daysWeather) +
                '}';
    }
}
