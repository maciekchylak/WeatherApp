package Weather;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.nio.IntBuffer;

public class WeatherInfo
{

    //Weather
    public String icoId;
    public String main; //Group of weather parameters
    public int weatherId; //Weather condition id
    public String description; //Weather condition within the group


    public WeatherInfo()
    {
        icoId = "";
        main = "";
        weatherId = 0;
        description = "";
    }

    public void uppdate(JSONArray weather)
    {
        JSONObject weatherO = (JSONObject)weather.get(0);
        icoId = weatherO.get("icon").toString();
        main = weatherO.get("main").toString();
        weatherId = Integer.valueOf(weatherO.get("id").toString());
        description = weatherO.get("description").toString();
    }

    @Override
    public String toString() {
        return "WeatherInfo{" +
                "icoId='" + icoId + '\'' +
                ", main='" + main + '\'' +
                ", weatherId=" + weatherId +
                ", description='" + description + '\'' +
                '}';
    }
}
