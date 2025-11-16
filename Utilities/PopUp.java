package Utilities;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;

import Config_Common.ErrorEvent;

public class PopUp extends JDialog {

    public PopUp(ErrorEvent event)
    {
        setTitle("Pop-Up Message");
        setSize(300, 150);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(null);
        setModal(true);

        JButton okButton = new JButton("OK");
        okButton.setBounds(100, 75, 100, 30);
        okButton.addActionListener(e -> dispose());
        add(okButton);

        JLabel messageLabel = new JLabel(event.getDescription());
        messageLabel.setBounds(20, 25, 250, 50);
        add(messageLabel);
        setVisible(true);
    }
}
