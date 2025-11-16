package Config_Specific.Basic_Calculator;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Config_Common.Calculator;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class BasicCalculator extends Calculator {
    double num1=0,num2=0,result=0;
    char operator;

    public BasicCalculator(){
        super("Basic Calculator");
        setSize(420,550);
        setLayout(null);
        setResizable(false);
        setLocationRelativeTo(null);
        addGuiComponents();
    }

    private void addGuiComponents(){
        Font myFont = new Font("Ink Free",Font.BOLD,30);

        JTextField textfield = new JTextField();
        textfield.setBounds(50,25,300,50);
        textfield.setFont(myFont);
        textfield.setEditable(false);
        add(textfield);

        JButton addButton = new JButton("+");
        JButton subButton = new JButton("-");
        JButton mulButton = new JButton("*");
        JButton divButton = new JButton("/");
        JButton decButton = new JButton(".");
        JButton equButton = new JButton("=");
        JButton delButton = new JButton("Del");
        JButton clrButton = new JButton("Clr");

        JButton[] funcButtons = new JButton[BasicCalculationFuncEnum.values().length];
        funcButtons[BasicCalculationFuncEnum.ADDITION.ordinal()] = addButton;
        funcButtons[BasicCalculationFuncEnum.SUBTRACTION.ordinal()] = subButton;
        funcButtons[BasicCalculationFuncEnum.MULTIPLICATION.ordinal()] =  mulButton;
        funcButtons[BasicCalculationFuncEnum.DIVISION.ordinal()] = divButton;
        funcButtons[BasicCalculationFuncEnum.DOT.ordinal()] = decButton;
        funcButtons[BasicCalculationFuncEnum.EQUAL.ordinal()] = equButton;
        funcButtons[BasicCalculationFuncEnum.DELETE.ordinal()] = delButton;
        funcButtons[BasicCalculationFuncEnum.CLEAR.ordinal()] = clrButton;

        for(int i=0;i<8;i++){
            final int index = i;
            funcButtons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    switch (BasicCalculationFuncEnum.values()[index]) {
                        case BasicCalculationFuncEnum.ADDITION:
                            num1 = Double.parseDouble(textfield.getText());
                            operator = '+';
                            textfield.setText("");
                            break;
                        case BasicCalculationFuncEnum.SUBTRACTION:
                            num1 = Double.parseDouble(textfield.getText());
                            operator = '-';
                            textfield.setText("");
                            break;
                        case BasicCalculationFuncEnum.MULTIPLICATION:
                            num1 = Double.parseDouble(textfield.getText());
                            operator = '*';
                            textfield.setText("");
                            break;
                        case BasicCalculationFuncEnum.DIVISION:
                            num1 = Double.parseDouble(textfield.getText());
                            operator = '/';
                            textfield.setText("");
                            break;
                        case BasicCalculationFuncEnum.DOT:
                            textfield.setText(textfield.getText().concat("."));
                            break;
                        case BasicCalculationFuncEnum.EQUAL:
                            num2 = Double.parseDouble(textfield.getText());
                            switch (operator) {
                                case '+':
                                    result= num1+num2;
                                    break;
                                case '-':
                                    result= num1-num2;
                                    break;
                                case '*':
                                    result= num1*num2;
                                    break;
                                case '/':
                                    result= num1/num2;
                                    break;

                                default:
                                    break;
                            }
                            textfield.setText(String.valueOf(result));
                            num1=result;
                            break;
                        case BasicCalculationFuncEnum.DELETE:
                            String string = textfield.getText();
                            textfield.setText("");
                            for(int j=0;j<string.length()-1;j++)
                                textfield.setText(textfield.getText()+string.charAt(j));
                            break;
                        case BasicCalculationFuncEnum.CLEAR:
                            textfield.setText("");
                            break;
                        default:
                            break;
                    }
                }});
            funcButtons[i].setFont(myFont);
            funcButtons[i].setFocusable(false);
        }

        JButton[] numberButtons = new JButton[10];

        for(int i=0;i<10;i++){
            final int index = i;
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    textfield.setText(textfield.getText().concat(String.valueOf(index)));
                }});
            numberButtons[i].setFont(myFont);
            numberButtons[i].setFocusable(false);
        }

        delButton.setBounds(50,430,145,50);
        add(delButton);
        clrButton.setBounds(205,430,145,50);
        add(clrButton);

        JPanel panel = new JPanel();
        panel.setBounds(50,100,300,300);
        panel.setLayout(new GridLayout(4,4,10,10));
        panel.add(numberButtons[1]);
        panel.add(numberButtons[2]);
        panel.add(numberButtons[3]);
        panel.add(addButton);
        panel.add(numberButtons[4]);
        panel.add(numberButtons[5]);
        panel.add(numberButtons[6]);
        panel.add(subButton);
        panel.add(numberButtons[7]);
        panel.add(numberButtons[8]);
        panel.add(numberButtons[9]);
        panel.add(mulButton);
        panel.add(decButton);
        panel.add(numberButtons[0]);
        panel.add(equButton);
        panel.add(divButton);
        add(panel);


    }

}
