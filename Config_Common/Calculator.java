package Config_Common;

import javax.swing.JFrame;

import java.awt.Font;

public class Calculator extends JFrame{

    public boolean isMaintained = false;
    protected Font myFont = new Font("Dialog",Font.BOLD,30);

    public Calculator(String title){
        super(title);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    public Calculator(){
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
}
