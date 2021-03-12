package Weather;

import com.sun.org.apache.xpath.internal.objects.XString;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Current extends Weather
{
    //Time
    public Date sunriseTime;
    public Date sunsetTime;

    //Temperature in selected Units(K, C, F)
    public double temperature;
    public double perceptibleTemperature;

    //Average visibility in metres (distance)
    public int visibility;

    //Rain
    public boolean isRaining;
    public double rain; //Rain volume for last hour, mm

    //Snow
    public boolean isSnowing;
    public double snow; //Rain volume for last hour, mm

    public Current()
    {
        super();

        sunriseTime = new Date();
        sunsetTime = new Date();

        visibility = 0;

        temperature = 0.0;
        perceptibleTemperature = 0.0;

        isRaining = false;
        rain = 0;

        isSnowing = false;
        snow = 0;
    }

    @Override
    public void uppdate(JSONObject current)
    {
        super.uppdate(current);

        sunriseTime = new Date(Long.valueOf(current.get("sunrise").toString()) * 1000);
        sunsetTime = new Date(Long.valueOf(current.get("sunset").toString()) * 1000);


        temperature = Double.valueOf(current.get("temp").toString());
        perceptibleTemperature = Double.valueOf(current.get("feels_like").toString());

        visibility = Integer.valueOf(current.get("visibility").toString());

        if(null != current.get("rain"))
        {
            isRaining = true;
            rain = Double.valueOf(((JSONObject)current.get("rain")).get("1h").toString());
        }

        if(null != current.get("snow"))
        {
            isSnowing = true;
            snow = Double.valueOf(((JSONObject)current.get("snow")).get("1h").toString());
        }
    }

    @Override
    public String toString() {
        return "Current{" +
                super.toString() +
                ", sunriseTime=" + sunriseTime +
                ", sunsetTime=" + sunsetTime +
                ", temperature=" + temperature +
                ", perceptibleTemperature=" + perceptibleTemperature +
                ", isRaining=" + isRaining +
                ", rain=" + rain +
                ", isSnowing=" + isSnowing +
                ", snow=" + snow +
                ", visibility" + visibility +
                '}';
    }

}
