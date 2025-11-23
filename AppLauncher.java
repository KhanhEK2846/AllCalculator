import javax.swing.SwingUtilities;

import Config.AppGui;
import Config_Common.ErrorEvent;
import Utilities.PopUp;

public class AppLauncher {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run(){
                try{
                    AppGui app = new AppGui();
                    app.setVisible(true);
                }catch(Exception eS){
                    // Lenght of Calculator & Length of Support is mismatch
                    new PopUp(ErrorEvent.ConfigureMismatch);
                }
            }
        });
    }
}
