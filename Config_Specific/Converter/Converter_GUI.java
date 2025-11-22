package Config_Specific.Converter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Config_Common.Calculator;
import Config_Common.ErrorEvent;
import Utilities.PopUp;

public class Converter_GUI extends Calculator {
    private static final int HEIGHT = 400;
    private static final int WIDTH = 1250;

    private JComboBox<UnitConverter_Enum> ConvertOptions;
    private JTextField InputUnit;
    private JTextField ConvertUnitField;

    public Converter_GUI(){
        super("Converter");
        //isMaintained = true;
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(false);
        addGuiComponents();
        updateUnitsConvertion();
    }

    private void addGuiComponents() {

        JLabel InputLabel = new JLabel("Input Value: ");
        InputLabel.setFont(myFont);
        InputLabel.setBounds(20, 50, 200, 35);
        add(InputLabel);

        JTextField InputValue = new JTextField();
        InputValue.setBounds(250, 50, 750, 35);
        add(InputValue);

        InputUnit = new JTextField("");
        InputUnit.setEditable(false);
        InputUnit.setFont(myFont);
        InputUnit.setBounds(1020, 50, 200, 35);
        add(InputUnit);


        JLabel ConvertValueLabel = new JLabel("Converted: ");
        ConvertValueLabel.setFont(myFont);
        ConvertValueLabel.setBounds(20, 100, 200, 35);
        add(ConvertValueLabel);

        JTextField ConvertValueField = new JTextField("");
        ConvertValueField.setEditable(false);
        ConvertValueField.setBounds(250, 100, 750, 35);
        add(ConvertValueField);

        ConvertUnitField = new JTextField("");
        ConvertUnitField.setEditable(false);
        ConvertUnitField.setFont(myFont);
        ConvertUnitField.setBounds(1020, 100, 200, 35);
        add(ConvertUnitField);

        JLabel ChooseConvert = new JLabel("Convertion: ");
        ChooseConvert.setFont(myFont);
        ChooseConvert.setBounds(20, 150, 200, 35);
        add(ChooseConvert);

        ConvertOptions = new JComboBox<>(UnitConverter_Enum.values());
        ConvertOptions.setBounds(250, 150, 400, 35);
        add(ConvertOptions);

        JButton ConvertButton = new JButton("Convert");
        ConvertButton.setFont(myFont);
        ConvertButton.setBounds(550, 225, 150, 35);
        add(ConvertButton);

        ConvertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                try{
                    ErrorEvent validation = new FunctionConvert().validate(InputValue.getText());
                    if(validation != ErrorEvent.PreConditionPassed){
                        new PopUp(validation);
                        return;
                    }
                    Double input = Double.parseDouble(InputValue.getText());
                    UnitConverter_Enum selectedConversion = (UnitConverter_Enum) ConvertOptions.getSelectedItem();
                    if(selectedConversion != null){
                        double result = selectedConversion.executeConversion(input);
                        ConvertValueField.setText(String.valueOf((result == -1) ? "" : result));
                    }

                }catch(Exception e2){

                }
            }
        });

        ConvertOptions.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                updateUnitsConvertion();
            }
        });

    }

    private void updateUnitsConvertion(){
                UnitConverter_Enum selectedConversion = (UnitConverter_Enum) ConvertOptions.getSelectedItem();
                if (selectedConversion != null) {
                    InputUnit.setText(selectedConversion.InputUnit);
                    ConvertUnitField.setText(selectedConversion.ConvertedUnit);
                }
    }

}
