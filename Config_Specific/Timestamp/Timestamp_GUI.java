package Config_Specific.Timestamp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;

import Config_Common.Calculator;
import Config_Common.ErrorEvent;
import Utilities.JTextFieldLimit;
import Utilities.PopUp;

public class Timestamp_GUI extends Calculator{
    private static final int HEIGHT = 400;
    private static final int WIDTH = 1250;

    private boolean is2TimeStamp = true;
    private JCheckBox toTimestampBox;


    public Timestamp_GUI(){
        super("Timestamp Calculator (GMT)");

        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(false);
        addGuiComponents();
        InitData();
    }

    private void addGuiComponents() {

        JLabel DayLabel = new JLabel("Day:");
        DayLabel.setBounds(50, 50, 150, 30);
        DayLabel.setFont(myFont);
        add(DayLabel);

        JTextFieldLimit DayInput = new JTextFieldLimit(2,"[0-9]+");
        DayInput.setBounds(200, 50, 400, 30);
        DayInput.setFont(myFont);
        add(DayInput);

        JLabel MonthLabel = new JLabel("Month:");
        MonthLabel.setBounds(50, 100, 150, 30);
        MonthLabel.setFont(myFont);
        add(MonthLabel);

        JTextFieldLimit MonthInput = new JTextFieldLimit(2,"[0-9]+");
        MonthInput.setBounds(200, 100, 400, 30);
        MonthInput.setFont(myFont);
        add(MonthInput);

        JLabel YearLabel = new JLabel("Year:");
        YearLabel.setBounds(50, 150, 150, 30);
        YearLabel.setFont(myFont);
        add(YearLabel);

        JTextFieldLimit YearInput = new JTextFieldLimit(4,"[0-9]+");
        YearInput.setBounds(200, 150, 400, 30);
        YearInput.setFont(myFont);
        add(YearInput);

        JLabel HourLabel = new JLabel("Hour:");
        HourLabel.setBounds(WIDTH/2+10, 50, 150, 30);
        HourLabel.setFont(myFont);
        add(HourLabel);

        JTextFieldLimit HourInput = new JTextFieldLimit(2,"[0-9]+");
        HourInput.setBounds(WIDTH/2+150, 50, 400, 30);
        HourInput.setFont(myFont);
        add(HourInput);

        JLabel MinuteLabel = new JLabel("Minute:");
        MinuteLabel.setBounds(WIDTH/2+10, 100, 150, 30);
        MinuteLabel.setFont(myFont);
        add(MinuteLabel);

        JTextFieldLimit MinuteInput = new JTextFieldLimit(2,"[0-9]+");
        MinuteInput.setBounds(WIDTH/2+150, 100, 400, 30);
        MinuteInput.setFont(myFont);
        add(MinuteInput);

        JLabel SecondLabel = new JLabel("Second:");
        SecondLabel.setBounds(WIDTH/2+10, 150, 150, 30);
        SecondLabel.setFont(myFont);
        add(SecondLabel);

        JTextFieldLimit SecondInput = new JTextFieldLimit(2,"[0-9]+");
        SecondInput.setBounds(WIDTH/2+150, 150, 400, 30);
        SecondInput.setFont(myFont);
        add(SecondInput);

        JLabel TimestampLabel = new JLabel("Timestamp:");
        TimestampLabel.setBounds(50, 220, 200, 30);
        TimestampLabel.setFont(myFont);
        add(TimestampLabel);

        JTextFieldLimit TimestampInput = new JTextFieldLimit("[0-9]+");
        TimestampInput.setBounds(275, 220, 800, 30);
        TimestampInput.setFont(myFont);
        add(TimestampInput);

        toTimestampBox = new JCheckBox("Date to Timestamp");
        toTimestampBox.setBounds(50, 280, 300, 30);
        add(toTimestampBox);

        JButton ConvertButton = new JButton("Convert");
        ConvertButton.setBounds(WIDTH/2-75, 320, 150, 40);
        ConvertButton.setFont(myFont);
        add(ConvertButton);

        ConvertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                is2TimeStamp = toTimestampBox.isSelected();
                ErrorEvent resultSts = ErrorEvent.InitialState;

                if(is2TimeStamp){
                    //Convert date to timestamp
                    resultSts = Timestamp.InputTimestamp(DayInput.getText(), MonthInput.getText(), YearInput.getText(), HourInput.getText(), MinuteInput.getText(), SecondInput.getText());
                    if(resultSts == ErrorEvent.TaskSuccess){
                        Timestamp.toTimestamp();
                        TimestampInput.setText(String.valueOf(Timestamp.TimestampValue));
                    }
                }else{
                    //Convert timestamp to date
                    resultSts = Timestamp.InputTimestamp(TimestampInput.getText());
                    if(resultSts == ErrorEvent.TaskSuccess){
                        Timestamp.toDate();
                        DayInput.setText(String.valueOf(Timestamp.Day));
                        MonthInput.setText(String.valueOf(Timestamp.Month));
                        YearInput.setText(String.valueOf(Timestamp.Year));
                        HourInput.setText(String.valueOf(Timestamp.Hour));
                        MinuteInput.setText(String.valueOf(Timestamp.Minute));
                        SecondInput.setText(String.valueOf(Timestamp.Second));
                    }

                }

                new PopUp((resultSts == ErrorEvent.TaskSuccess)? ErrorEvent.BuildSuccess:resultSts);

            }
        });
    }

    private void InitData(){
        //Initialize default data if needed
        toTimestampBox.setSelected(is2TimeStamp);
    }
}
