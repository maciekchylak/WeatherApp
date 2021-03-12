package Weather;

import org.json.simple.JSONObject;

import java.util.Date;

public class Day extends Weather
{
    //Time
    public Date sunriseTime;
    public Date sunsetTime;

    //Temperature in selected Units(K, C, F)
    public double temperatureMorn;
    public double temperatureDay;
    public double temperatureEve;
    public double temperatureNight;
    public double temperatureMin;
    public double temperatureMax;

    public double perceptibleTemperatureMorn;
    public double perceptibleTemperatureDay;
    public double perceptibleTemperatureEve;
    public double perceptibleTemperatureNight;

    //Probability of precipitation
    public double probabilityPercipitation;

    //Rain
    public boolean isRaining;
    public double rain; //Rain volume for day, mm

    //Snow
    public boolean isSnowing;
    public double snow; //Rain volume for day, mm

    public Day()
    {
        super();

        sunriseTime = new Date();
        sunsetTime = new Date();

        temperatureMorn = 0.0;
        temperatureDay = 0.0;
        temperatureEve = 0.0;
        temperatureNight = 0.0;
        temperatureMin = 0.0;
        temperatureMax = 0.0;

        perceptibleTemperatureMorn = 0.0;
        perceptibleTemperatureDay = 0.0;
        perceptibleTemperatureEve = 0.0;
        perceptibleTemperatureNight = 0.0;

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

        sunriseTime = new Date(Long.valueOf(current.get("sunrise").toString()) * 1000);
        sunsetTime = new Date(Long.valueOf(current.get("sunset").toString()) * 1000);

        probabilityPercipitation = Double.valueOf(current.get("pop").toString());

        JSONObject temp = (JSONObject)current.get("temp");
        temperatureMorn = Double.valueOf(temp.get("morn").toString());
        temperatureDay = Double.valueOf(temp.get("day").toString());
        temperatureEve = Double.valueOf(temp.get("eve").toString());
        temperatureNight = Double.valueOf(temp.get("night").toString());
        temperatureMin = Double.valueOf(temp.get("min").toString());
        temperatureMax = Double.valueOf(temp.get("max").toString());

        JSONObject feel = (JSONObject)current.get("feel_like");
        perceptibleTemperatureMorn = Double.valueOf(temp.get("morn").toString());
        perceptibleTemperatureDay = Double.valueOf(temp.get("day").toString());
        perceptibleTemperatureEve = Double.valueOf(temp.get("eve").toString());
        perceptibleTemperatureNight = Double.valueOf(temp.get("night").toString());

        if(null != current.get("rain"))
        {
            isRaining = true;
            rain = Double.valueOf(current.get("rain").toString());
        }

        if(null != current.get("snow"))
        {
            isSnowing = true;
            snow = Double.valueOf(current.get("snow").toString());
        }
    }

    @Override
    public String toString() {
        return "Day{" +
                super.toString() +
                "sunriseTime=" + sunriseTime +
                ", sunsetTime=" + sunsetTime +
                ", temperatureMorn=" + temperatureMorn +
                ", temperatureDay=" + temperatureDay +
                ", temperatureEve=" + temperatureEve +
                ", temperatureNight=" + temperatureNight +
                ", temperatureMin=" + temperatureMin +
                ", temperatureMax=" + temperatureMax +
                ", perceptibleTemperatureMorn=" + perceptibleTemperatureMorn +
                ", perceptibleTemperatureDay=" + perceptibleTemperatureDay +
                ", perceptibleTemperatureEve=" + perceptibleTemperatureEve +
                ", perceptibleTemperatureNight=" + perceptibleTemperatureNight +
                ", probabilityPercipitation=" + probabilityPercipitation +
                ", isRaining=" + isRaining +
                ", rain=" + rain +
                ", isSnowing=" + isSnowing +
                ", snow=" + snow +
                '}';
    }
}
