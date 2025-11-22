package Config;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import Config_Common.Calculator;
import Config_Common.ErrorEvent;
import Config_Common.SupportCalculatorEnum;
import Config_Common.SupportCalculatorROM;
import Utilities.PopUp;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AppGui extends JFrame {

    private static final int HEIGHT = 250;
    private static final int WIDTH = 750;

    private SupportCalculatorROM supportCalculatorROM;
    private JComboBox<SupportCalculatorEnum> CalculatorsChoice;

    public AppGui(){
        super("All Calculators");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(false);
        addGuiComponents();
        InitCalculators();
    }

    private void addGuiComponents(){
        Font myFont = new Font("Dialog",Font.BOLD,30);
        JLabel labelInputCal = new JLabel("Choose calculator:");
        labelInputCal.setFont(myFont);
        labelInputCal.setBounds(10,50 , 325,40 );
        add(labelInputCal);

        CalculatorsChoice = new JComboBox<>(SupportCalculatorEnum.values());
        CalculatorsChoice.setBounds(340, 50, 325, 40);
        add(CalculatorsChoice);

        JButton btnOpenCalculator = new JButton("Run");
        btnOpenCalculator.setFont(myFont);
        btnOpenCalculator.setBounds(50, 125, 600, 60);
        add(btnOpenCalculator);

        btnOpenCalculator.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                try{
                    Calculator calc = supportCalculatorROM.GetCalculator((SupportCalculatorEnum) CalculatorsChoice.getSelectedItem());
                    if(!calc.isMaintained){
                        calc.setVisible(true);
                    }else{
                        new PopUp(ErrorEvent.UnderMaintainance);
                    }
                }catch(Exception e2){
                    new PopUp(ErrorEvent.UnsupportedCalculator);
                }
            }
        });

    }

    private void InitCalculators(){
        supportCalculatorROM = new SupportCalculatorROM();
        supportCalculatorROM.InitCalculators();
        if(SystemMode.DEBUG)
            CalculatorsChoice.setSelectedItem(SupportCalculatorEnum.Converter_Calculator);
    }

}
