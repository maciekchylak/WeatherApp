import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CurrentWeather implements ActionListener {
    public  JPanel mainPanel;
    public  JLabel weather_desc;
    public  JLabel weather_icon;
    public  JLabel temp;
    public  JLabel feels_like;
    public  JLabel pressure;
    public  JLabel humidity;
    public  JLabel dew_point;
    public  JLabel clouds;
    public  JLabel visibility;
    public  JLabel wind_speed;
    public  JLabel wind_deg;
    public  JLabel rain;
    public  JLabel snow;
    public  JLabel sunrise;
    public  JLabel sunset;
    private JPanel upperPanel;
    private JPanel lowerPanel;

    public CurrentWeather(){
        float [] color = new float[] {0,0,0};
        Color.RGBtoHSB(178,229,237, color);
        mainPanel.setBackground(Color.getHSBColor(color[0], color[1], color[2]));
        upperPanel.setBackground(Color.getHSBColor(color[0], color[1], color[2]));
        lowerPanel.setBackground(Color.getHSBColor(color[0], color[1], color[2]));
    }

//    public static void main (String[] args) {
//        GUI.frame.setContentPane(new CurrentWeather().mainPanel);
//        GUI.frame.setVisible(true);
//        //spawnsize
//        GUI.frame.setPreferredSize(new Dimension(500, 500));
//        GUI.frame.setMinimumSize(new Dimension(500, 500));
//        //spawnpoint
//        GUI.frame.setLocationRelativeTo(null);
//    }

    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource().equals(""))
        //weather_icon.setText(GUI.weatherAnalizer.currentWeather.weatherInfo.icoId);
        weather_desc.setText(GUI.weatherAnalizer.currentWeather.weatherInfo.description);
        temp.setText(String.valueOf(GUI.weatherAnalizer.currentWeather.temperature));
        feels_like.setText(String.valueOf(GUI.weatherAnalizer.currentWeather.perceptibleTemperature));
        pressure.setText(String.valueOf(GUI.weatherAnalizer.currentWeather.pressure));
        humidity.setText(String.valueOf(GUI.weatherAnalizer.currentWeather.humidity));
        dew_point.setText(String.valueOf(GUI.weatherAnalizer.currentWeather.dewTemperature));
        clouds.setText(String.valueOf(GUI.weatherAnalizer.currentWeather.cloudiness));
        visibility.setText(String.valueOf(GUI.weatherAnalizer.currentWeather.visibility));
        wind_speed.setText(String.valueOf(GUI.weatherAnalizer.currentWeather.windSpeed));
        wind_deg.setText(String.valueOf(GUI.weatherAnalizer.currentWeather.windDeg));
        if (GUI.weatherAnalizer.currentWeather.isRaining) {
           rain.setText(String.valueOf(GUI.weatherAnalizer.currentWeather.rain));
        }
        else{
            rain.setText(String.valueOf("-"));
        }
        if (GUI.weatherAnalizer.currentWeather.isSnowing) {
            snow.setText(String.valueOf(GUI.weatherAnalizer.currentWeather.snow));
        }
        else{
            snow.setText(String.valueOf("-"));
        }
        sunrise.setText(String.valueOf(GUI.weatherAnalizer.currentWeather.sunriseTime));
        sunset.setText(String.valueOf(GUI.weatherAnalizer.currentWeather.sunsetTime));
    }
}
