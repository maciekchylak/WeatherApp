import javax.swing.*;

public class DailyWeather {
    public JPanel mainPanel;
    public JPanel contentPanel;
    public DailyWeatherPanel[] panels = new DailyWeatherPanel[]{new DailyWeatherPanel(), new DailyWeatherPanel(), new DailyWeatherPanel(),
            new DailyWeatherPanel(), new DailyWeatherPanel(), new DailyWeatherPanel(), new DailyWeatherPanel()};


    public DailyWeather() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setAutoscrolls(true);
        JScrollPane scroll = new JScrollPane(mainPanel);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        scroll.setBounds(0,0,630,470);
        contentPanel = new JPanel(null);
//        contentPanel.setPreferredSize(new Dimension(300, 300));
        contentPanel.add(scroll);

        for (int i = 0; i < 7; i ++) {
            mainPanel.add(panels[i].mainPanel);
        }
    }
}