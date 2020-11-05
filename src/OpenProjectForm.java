import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class OpenProjectForm extends CommonUIMethods{
    private JPanel openProjectPanel;
    private JPanel projectOptionPanel;
    private JPanel actionPanel;
    private JComboBox projectSelectCombo;
    private JButton openButton;
    private JButton cancelButton;

    public OpenProjectForm(JFrame mainFrame) {

        setContentPane(openProjectPanel);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Open Project - Project Management System");
        setVisible(true);

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                onExit(mainFrame);
            }
        }); //unfreezes main gui when pop out is closed
        

        //-----------Action Listeners---------------------------------------
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelButtonPressed(mainFrame);
            }
        });
        openButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openButtonPressed();
            }
        });
    }

    //----------------Methods-----------------------------------------------
    private void openButtonPressed() {
        //todo implement openButtonPressed

        System.out.println("OpenProjectForm.openButtonPressed");
    }

    private void cancelButtonPressed(JFrame mainFrame) {
        //todo implement cancelButtonPressed
        System.out.println("OpenProjectForm.cancelButtonPressed");
        onExit(mainFrame);
    }
}
