import sun.audio.AudioPlayer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main_menu{
    public JPanel mainPanel;
    private JLabel titleImg;
    private JButton mainCurrentWeatherButton;
    private JButton mainDailyWeatherButton;
    private JButton mainHourlyWeatherButton;
    public JButton mainLanguageButton;
    private JPanel upperMainPanel;
    private JPanel lowerMainPanel;
    private JButton mainMusicButton;
    public static String language = "en";
    public static boolean isMusic = true;

    public Main_menu(){

        //Main current weather button action listener
        mainCurrentWeatherButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Frame frameCurrentWeather = new Frame();
                frameCurrentWeather.splitPanel.setRightComponent(GUI.currentWeather.mainPanel);
                GUI.frame.setContentPane(frameCurrentWeather.mainPanel);
                GUI.frame.setVisible(true);
            }
        });

        //Main hourly weather button action listener
        mainHourlyWeatherButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Frame frameCurrentWeather = new Frame();
                frameCurrentWeather.splitPanel.setRightComponent(GUI.hourlyWeather.contentPanel);
                GUI.frame.setContentPane(frameCurrentWeather.mainPanel);
                GUI.frame.setVisible(true);
            }
        });

        //Main daily weather button action listener
        mainDailyWeatherButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Frame frameCurrentWeather = new Frame();
                frameCurrentWeather.splitPanel.setRightComponent(GUI.dailyWeather.contentPanel);
                GUI.frame.setContentPane(frameCurrentWeather.mainPanel);
                GUI.frame.setVisible(true);
            }
        });
        mainMusicButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               if (isMusic){
                   AudioPlayer.player.stop(GUI.BGM);
                   isMusic = false;
                   if(Main_menu.language.equals("en"))
                   {
                       mainMusicButton.setText("Music on");

                   }
                    else if (Main_menu.language.equals("pl")){
                       mainMusicButton.setText("Włącz muzykę");
                   }
                }
               else{
                   AudioPlayer.player.start(GUI.BGM);
                   isMusic = true;
                   if(Main_menu.language.equals("en"))
                   {
                       mainMusicButton.setText("Music off");

                   }
                   else if (Main_menu.language.equals("pl")){
                       mainMusicButton.setText("Wyłącz muzykę");
                   }
                }
            }
        });
        mainLanguageButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GUI.currentWeather = new CurrentWeather();
                GUI.dailyWeather = new DailyWeather();
                GUI.hourlyWeather = new HourlyWeather();

                if(language.equals("en")){
                    language = "pl";
                    mainLanguageButton.setText("English");
                    mainDailyWeatherButton.setText("Pogoda dzienna");
                    mainCurrentWeatherButton.setText("Pogoda na teraz");
                    mainHourlyWeatherButton.setText("Pogoda godzinowa");
                    if(isMusic) {
                        mainMusicButton.setText("Wyłącz muzykę");
                    }
                    else{
                        mainMusicButton.setText("Włącz muzykę");
                    }
                    }
                else if (language.equals("pl")){
                    language = "en";
                    mainLanguageButton.setText("Polski");
                    mainDailyWeatherButton.setText("Daily weather");
                    mainCurrentWeatherButton.setText("Current weather");
                    mainHourlyWeatherButton.setText("Hourly weather");
                    if(isMusic) {
                        mainMusicButton.setText("Music off");
                    }
                    else{
                        mainMusicButton.setText("Music on");
                    }
                }
            }
        });
    }

    //Customize action listeners of buttons
    private void createUIComponents() {

    }


//    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
//
//            //Declaring window
//
//            System.setProperty(
//                "Quaqua.design","lion"
//            );
//
//            UIManager.setLookAndFeel("ch.randelshofer.quaqua.QuaquaLookAndFeel");
//            //setting size of window
//            frame.setPreferredSize(new Dimension(500, 500));
//            frame.setMinimumSize(new Dimension(500, 500));
//
//            //setting spawn point of window
//            frame.setLocationRelativeTo(null);
//
//            // Adding main panel to window
//            frame.setContentPane(new Main_menu().mainPanel);
//            frame.setVisible(true);
//    }

}
