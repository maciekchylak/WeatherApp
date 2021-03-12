import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class DailyWeatherPanel extends JPanel{
    public  JLabel weather_desc;
    public  JLabel weather_icon;
    public  JLabel temp_night;
    public  JLabel temp_day;
    public JPanel mainPanel;
    public JLabel day;

    public DailyWeatherPanel() {
        float [] color = new float[] {0,0,0};
        Color.RGBtoHSB(178,229,237, color);
        mainPanel.setBackground(Color.getHSBColor(color[0], color[1], color[2]));
        mainPanel.setPreferredSize(new Dimension(610, 200));
    }
//    public void actionPerformed(ActionEvent e)
//    {
//        temp_day.setText(String.valueOf(GUI.weatherAnalizer.daysWeather[0].temperatureDay));
//    }
}
