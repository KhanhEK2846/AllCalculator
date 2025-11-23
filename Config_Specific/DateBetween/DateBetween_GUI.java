package Config_Specific.DateBetween;

import javax.swing.JLabel;

import Config_Common.Calculator;

public class DateBetween_GUI extends Calculator{
    private static final int HEIGHT = 400;
    private static final int WIDTH = 1250;

    public DateBetween_GUI(){
        super("Date Between Calculator");
        isMaintained = true;

        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(false);
        addGuiComponents();
    }

    private void addGuiComponents() {
        JLabel startDateLabel = new JLabel("Start Date:");
        startDateLabel.setBounds(50, 50, 175, 30);
        startDateLabel.setFont(myFont);
        add(startDateLabel);


        JLabel endDateLabel = new JLabel("End Date:");
        endDateLabel.setBounds(50, 100, 175, 30);
        endDateLabel.setFont(myFont);
        add(endDateLabel);
    }



}
