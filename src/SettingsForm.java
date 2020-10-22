import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class SettingsForm extends CommonUIMethods{
    private JPanel settingsPanel;
    private JButton button1;



    public SettingsForm(JFrame mainFrame){

        setContentPane(settingsPanel);
        pack();
        setVisible(true);

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                onExit(mainFrame);
            }
        }); //unfreezes main gui when pop out is closed


    }



}
