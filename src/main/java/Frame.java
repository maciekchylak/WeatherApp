import org.json.simple.JSONObject;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Frame {
    public JPanel mainPanel;
    public JTextField city;
    public  JComboBox unit;
    public JPanel rightPanel;
    private JPanel leftPanel;
    private JButton backButton;
    public  JButton applyButton;
    public JSplitPane splitPanel;
    private JLabel city_name;
    private JLabel units_name;
    private JLabel errorText;
    public static String cityName;
    public static String unitSelected = "metric";


    public Frame() {

        errorText.setVisible(false);

        // przycisk Back służący do wracania do menu głównego
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GUI.frame.setContentPane(GUI.mainMenu.mainPanel);
                GUI.frame.setVisible(true);
            }
        });

        // przycisk Apply służący do zatwierdzenia wprowadzonych zmian i odświeżenia pogody
        applyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    GUI.weatherAnalizer.getCurrentWeather(Frame.cityName, Frame.unitSelected, Main_menu.language);
                    errorText.setVisible(false);
                    updateCurrentWeather();
                    updateDailyWeather();
                    updateHourlyWeather();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                    errorText.setVisible(true);
                }

            }
        });

        // pole tekstowe służące do przyjmowania nazwy miejscowości

        city.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cityName = city.getText();
            }
        });

        city.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
            };
            public void focusLost(FocusEvent e) {
                if (!e.isTemporary()) {
                    cityName = city.getText();
                }
            }
        });

        // combo box służący do przyjmowania rodzaju jednostek w których ma zostać wyświetlona pogoda
        unit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                unitSelected = (String) unit.getSelectedItem();
                if(Main_menu.language.equals("pl")){
                    if (unitSelected.equals("metryczne")){
                        unitSelected = "metric";
                    }
                    else if(unitSelected.equals("imperialne")){
                        unitSelected = "imperial";
                    }
                }
            }
        });
        if (Main_menu.language.equals("pl")) {
            city_name.setText("Wybierz miasto");
            errorText.setText("Zła nazwa miasta");
            units_name.setText("Wybierz jednostki");
            applyButton.setText("Zatwierdź");
            backButton.setText("Cofnij");
            String[] units = { "metryczne", "imperialne" };
            DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(units);
            unit.setModel( model );

        } else {
            city_name.setText("Choose city");
            errorText.setText("Wrong city name");
            units_name.setText("Choose units");
            applyButton.setText("Apply");
            backButton.setText("Back");
            String[] units = { "metric", "imperial" };
            DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(units);
            unit.setModel( model );
        }
    }
    public class FocusGrabber implements Runnable {
        private JComponent component;

        public FocusGrabber(JComponent component) {
            this.component = component;
        }

        public void run() {
            component.grabFocus();
        }
    }

    private void updateCurrentWeather() {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        URL url = null;
        try {
            url = new URL("http://openweathermap.org/img/wn/" + GUI.weatherAnalizer.currentWeather.weatherInfo.icoId.replace("n", "d") + "@2x.png");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            GUI.currentWeather.weather_icon.setIcon(new ImageIcon(ImageIO.read(url)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] symbols = new String[]{" °C", " HPa", " %", " °", " mm", " m", " m/s"};
        if(unitSelected.equals("metric")){
            symbols = new String[]{" °C", " HPa", " %", " °", " mm", " m", " m/s"};
        }
        else if(unitSelected.equals("imperial")){
            symbols = new String[]{" °F", " HPa", " %", " °", " mm", " m", " mph"};

        }
        if (Main_menu.language.equals("pl")) {

            GUI.currentWeather.weather_desc.setText(GUI.weatherAnalizer.currentWeather.weatherInfo.description.toUpperCase());
            //GUI.currentWeather.weather_icon.setIcon();
            //public  JLabel weather_icon;
            GUI.currentWeather.temp.setText("Temperatura:  " + GUI.weatherAnalizer.currentWeather.temperature + symbols[0]);
            GUI.currentWeather.feels_like.setText("T. odczuwalna:  " + GUI.weatherAnalizer.currentWeather.perceptibleTemperature+ symbols[0]);
            GUI.currentWeather.pressure.setText("Ciśnienie:  " +Integer.toString(GUI.weatherAnalizer.currentWeather.pressure)+ symbols[1]);
            GUI.currentWeather.humidity.setText("Wilgotność:  " +Integer.toString(GUI.weatherAnalizer.currentWeather.humidity)+ symbols[2]);
            GUI.currentWeather.dew_point.setText("Punkt rosy:  " +Double.toString(GUI.weatherAnalizer.currentWeather.dewTemperature)+ symbols[0]);
            GUI.currentWeather.clouds.setText("Zachmurzenie:  " +Integer.toString(GUI.weatherAnalizer.currentWeather.cloudiness)+ symbols[2]);
            GUI.currentWeather.visibility.setText("Widoczność:  " +Integer.toString(GUI.weatherAnalizer.currentWeather.visibility)+ symbols[5]);
            GUI.currentWeather.wind_speed.setText("Prędkość wiatru:  " +Double.toString(GUI.weatherAnalizer.currentWeather.windSpeed) + symbols[6]);
            GUI.currentWeather.wind_deg.setText("Kierunek wiatru:  " +Integer.toString(GUI.weatherAnalizer.currentWeather.windDeg) + symbols[3]);
            GUI.currentWeather.rain.setText("Opady:  " + (GUI.weatherAnalizer.currentWeather.rain + GUI.weatherAnalizer.currentWeather.snow) + symbols[4]);
            GUI.currentWeather.sunrise.setText("Wschód słońca:  " +format.format(GUI.weatherAnalizer.currentWeather.sunriseTime));
            GUI.currentWeather.sunset.setText("Zachód słońca:  " +format.format(GUI.weatherAnalizer.currentWeather.sunsetTime));
        }
        else if (Main_menu.language.equals("en")) {


            GUI.currentWeather.weather_desc.setText(GUI.weatherAnalizer.currentWeather.weatherInfo.description.toUpperCase());
            //GUI.currentWeather.weather_icon.setIcon();
            //public  JLabel weather_icon;
            GUI.currentWeather.temp.setText("Temperature:  " + GUI.weatherAnalizer.currentWeather.temperature+ symbols[0]);
            GUI.currentWeather.feels_like.setText("T. perceived:  " + GUI.weatherAnalizer.currentWeather.perceptibleTemperature+ symbols[0]);
            GUI.currentWeather.pressure.setText("Pressure:  " + Integer.toString(GUI.weatherAnalizer.currentWeather.pressure)+ symbols[1]);
            GUI.currentWeather.humidity.setText("Humidity:  " + Integer.toString(GUI.weatherAnalizer.currentWeather.humidity)+ symbols[2]);
            GUI.currentWeather.dew_point.setText("Dew point:  " + Double.toString(GUI.weatherAnalizer.currentWeather.dewTemperature)+ symbols[0]);
            GUI.currentWeather.clouds.setText("Cloudiness:  " + Integer.toString(GUI.weatherAnalizer.currentWeather.cloudiness)+ symbols[2]);
            GUI.currentWeather.visibility.setText("Visibility:  " + Integer.toString(GUI.weatherAnalizer.currentWeather.visibility)+ symbols[5]);
            GUI.currentWeather.wind_speed.setText("Wind speed:  " + Double.toString(GUI.weatherAnalizer.currentWeather.windSpeed) + symbols[6]);
            GUI.currentWeather.wind_deg.setText("Wind degree:  " + Integer.toString(GUI.weatherAnalizer.currentWeather.windDeg)+ symbols[3]);
            GUI.currentWeather.rain.setText("Precipitation:  " + Double.toString(GUI.weatherAnalizer.currentWeather.rain + GUI.weatherAnalizer.currentWeather.snow) + symbols[4]);
            GUI.currentWeather.sunrise.setText("Sunrise:  " + format.format(GUI.weatherAnalizer.currentWeather.sunriseTime));
            GUI.currentWeather.sunset.setText("Sunset:  " + format.format(GUI.weatherAnalizer.currentWeather.sunsetTime));
        }
    }

    public void updateDailyWeather(){
        String[] symbols = new String[]{" °C", " HPa", " %", " °", " mm", " m", " m/s"};
        if(unitSelected.equals("metric")){
            symbols = new String[]{" °C", " HPa", " %", " °", " mm", " m", " m/s"};
        }
        else if(unitSelected.equals("imperial")){
            symbols = new String[]{" °F", " HPa", " %", " °", " mm", " m", " mph"};

        }
        SimpleDateFormat format = new SimpleDateFormat("dd.MM");
        for (int i = 0; i < 7; i++) {
            if (Main_menu.language.equals("pl")) {
                GUI.dailyWeather.panels[i].day.setText(format.format(GUI.weatherAnalizer.daysWeather[i].currentTime));
                GUI.dailyWeather.panels[i].temp_day.setText("Temperatura w dzień:  " + Double.toString(GUI.weatherAnalizer.daysWeather[i].temperatureDay) + symbols[0]);
                GUI.dailyWeather.panels[i].weather_desc.setText(GUI.weatherAnalizer.daysWeather[i].weatherInfo.description.toUpperCase());
                GUI.dailyWeather.panels[i].temp_night.setText("Temperatura w nocy:  " + Double.toString(GUI.weatherAnalizer.daysWeather[i].perceptibleTemperatureNight) + symbols[0]);
            }
            else if (Main_menu.language.equals("en")) {
                GUI.dailyWeather.panels[i].day.setText(format.format(GUI.weatherAnalizer.daysWeather[i].currentTime));
                GUI.dailyWeather.panels[i].temp_day.setText("Days temperature:  " + Double.toString(GUI.weatherAnalizer.daysWeather[i].temperatureDay) + symbols[0]);
                GUI.dailyWeather.panels[i].weather_desc.setText(GUI.weatherAnalizer.daysWeather[i].weatherInfo.description.toUpperCase());
                GUI.dailyWeather.panels[i].temp_night.setText("Night temperature:  " + Double.toString(GUI.weatherAnalizer.daysWeather[i].perceptibleTemperatureNight) + symbols[0]);
            }

                URL url = null;
                try {
                    url = new URL("http://openweathermap.org/img/wn/" + GUI.weatherAnalizer.daysWeather[i].weatherInfo.icoId.replace("n", "d") + "@2x.png");
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                try {
                    GUI.dailyWeather.panels[i].weather_icon.setIcon(new ImageIcon(ImageIO.read(url)));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }


    public void updateHourlyWeather(){
        String[] symbols = new String[]{" °C", " HPa", " %", " °", " mm", " m", " m/s"};
        if(unitSelected.equals("metric")){
            symbols = new String[]{" °C", " HPa", " %", " °", " mm", " m", " m/s"};
        }
        else if(unitSelected.equals("imperial")){
            symbols = new String[]{" °F", " HPa", " %", " °", " mm", " m", " mph"};

        }
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        for (int i = 0; i <= 7; i++) {
            if (Main_menu.language.equals("pl")) {
                GUI.hourlyWeather.panels[i].temp.setText("Temperatura:  "+Double.toString(GUI.weatherAnalizer.hoursWeather[i].temperature)+ symbols[0]);
                GUI.hourlyWeather.panels[i].humidity.setText("Wilgotność:  "+Double.toString(GUI.weatherAnalizer.hoursWeather[i].humidity)+symbols[2]);
                GUI.hourlyWeather.panels[i].pressure.setText("Ciśnienie:  "+Double.toString(GUI.weatherAnalizer.hoursWeather[i].pressure)+symbols[1]);
//            GUI.hourlyWeather.panels[i].rain.setText(Double.toString(GUI.weatherAnalizer.hoursWeather[i].rain));
                GUI.hourlyWeather.panels[i].rain.setText("Opady:  "+ Double.toString(GUI.weatherAnalizer.hoursWeather[i].snow + GUI.weatherAnalizer.hoursWeather[i].rain) + symbols[4]);
                GUI.hourlyWeather.panels[i].time.setText(format.format(GUI.weatherAnalizer.hoursWeather[i].currentTime));
                GUI.hourlyWeather.panels[i].weather_desc.setText(GUI.weatherAnalizer.hoursWeather[i].weatherInfo.description.toUpperCase());
            }
            else if (Main_menu.language.equals("en")) {
                GUI.hourlyWeather.panels[i].temp.setText("Temperature:  "+Double.toString(GUI.weatherAnalizer.hoursWeather[i].temperature)+ symbols[0]);
                GUI.hourlyWeather.panels[i].humidity.setText("Humidity:  "+Double.toString(GUI.weatherAnalizer.hoursWeather[i].humidity)+symbols[2]);
                GUI.hourlyWeather.panels[i].pressure.setText("Pressure:  "+Double.toString(GUI.weatherAnalizer.hoursWeather[i].pressure)+symbols[1]);
//            GUI.hourlyWeather.panels[i].rain.setText(Double.toString(GUI.weatherAnalizer.hoursWeather[i].rain));
                GUI.hourlyWeather.panels[i].rain.setText("Precipitation:  "+ Double.toString(GUI.weatherAnalizer.hoursWeather[i].snow + GUI.weatherAnalizer.hoursWeather[i].rain) + symbols[4]);
                GUI.hourlyWeather.panels[i].time.setText(format.format(GUI.weatherAnalizer.hoursWeather[i].currentTime));
                GUI.hourlyWeather.panels[i].weather_desc.setText(GUI.weatherAnalizer.hoursWeather[i].weatherInfo.description.toUpperCase());

            }
                URL url = null;
            try {
                url = new URL("http://openweathermap.org/img/wn/" + GUI.weatherAnalizer.hoursWeather[i].weatherInfo.icoId.replace("n", "d") + "@2x.png");
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            try {
                GUI.hourlyWeather.panels[i].weather_icon.setIcon(new ImageIcon(ImageIO.read(url)));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
//        BufferedImage titlePicture = null;
//        URL url = null;
//        try {
//            url = new URL("http://openweathermap.org/img/wn/10d@2x.png");
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//        try {
//            titlePicture = ImageIO.read(url);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        assert titlePicture != null;
//        GUI.hourlyWeather.panels[0].weather_icon.setIcon(new ImageIcon(titlePicture));
    }
}