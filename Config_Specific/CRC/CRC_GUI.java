package Config_Specific.CRC;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Config_Common.Calculator;
import Config_Common.ErrorEvent;
import Utilities.JTextFieldLimit;
import Utilities.PopUp;

public class CRC_GUI extends Calculator {
    private static final int HEIGHT = 375;
    private static final int WIDTH = 1250;

    public CRC_GUI() {
        super("CRC Calculator");
        isMaintained = true;
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(false);
        addGuiComponents();
    }

    void addGuiComponents() {
        Font myFont = new Font("Dialog",Font.BOLD,30);
        JLabel InputDataLabel = new JLabel("Data (Hex)");
        InputDataLabel.setFont(myFont);
        InputDataLabel.setBounds(5,25,200,50);
        add(InputDataLabel);

        JTextFieldLimit InputDataField = new JTextFieldLimit(32,"[0-9A-Fa-f]+");
        InputDataField.setFont(myFont);
        InputDataField.setBounds(200,25,1000,50);
        add(InputDataField);


        JComboBox<CRCTypeEnum> CRCTypeComboBox = new JComboBox<>(CRCTypeEnum.values());
        CRCTypeComboBox.setBounds(200, 100, 1000, 50);
        add(CRCTypeComboBox);

        JLabel CRCTypeLabel = new JLabel("CRC Type");
        CRCTypeLabel.setFont(myFont);
        CRCTypeLabel.setBounds(5,100,200,50);
        add(CRCTypeLabel);

        JLabel ResultLabel = new JLabel("Checksum");
        ResultLabel.setFont(myFont);
        ResultLabel.setBounds(5,175,200,50);
        add(ResultLabel);

        JTextField ResultField = new JTextField();
        ResultField.setFont(myFont);
        ResultField.setBounds(200,175,1000,50);
        ResultField.setEditable(false);
        add(ResultField);

        JButton CalCRCButton = new JButton("Result");
        CalCRCButton.setFont(myFont);
        CalCRCButton.setBounds((WIDTH - 500)/2, 250, 500, 50);
        add(CalCRCButton);

        CalCRCButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                String Checksum = "";
                CRC crc = new CRC();

                try{
                    Checksum = crc.CalculateChecksum(InputDataField.getText(), (CRCTypeEnum) CRCTypeComboBox.getSelectedItem());

                }catch(Exception ex){
                    new PopUp(ErrorEvent.GeneralFailure);
                }
                ResultField.setText(Checksum);
            }
        });
    }

}
