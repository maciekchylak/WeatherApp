package Weather;

import org.json.simple.JSONObject;

import java.util.Date;

public class Hour extends Weather
{
    //Temperature in selected Units(K, C, F)
    public double temperature;
    public double perceptibleTemperature;

    //Average visibility in metres (distance)
    public int visibility;

    //Probability of precipitation
    public double probabilityPercipitation;

    //Rain
    public boolean isRaining;
    public double rain; //Rain volume for last hour, mm

    //Snow
    public boolean isSnowing;
    public double snow; //Rain volume for last hour, mm

    public Hour()
    {
        super();

        temperature = 0.0;
        perceptibleTemperature = 0.0;

        visibility = 0;

        probabilityPercipitation = 0.0;

        isRaining = false;
        rain = 0;

        isSnowing = false;
        snow = 0;
    }

    @Override
    public void uppdate(JSONObject current)
    {
        super.uppdate(current);


        temperature = Double.valueOf(current.get("temp").toString());
        perceptibleTemperature = Double.valueOf(current.get("feels_like").toString());

        visibility = Integer.valueOf(current.get("visibility").toString());

        probabilityPercipitation = Double.valueOf(current.get("pop").toString());

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
        return "Hour{" +
                super.toString() +
                "temperature=" + temperature +
                ", perceptibleTemperature=" + perceptibleTemperature +
                ", probabilityPercipitation=" + probabilityPercipitation +
                ", isRaining=" + isRaining +
                ", rain=" + rain +
                ", isSnowing=" + isSnowing +
                ", snow=" + snow +
                ", visibility" + visibility +
                '}';
    }
}
