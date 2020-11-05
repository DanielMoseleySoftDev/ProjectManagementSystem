import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class EditTeamForm extends CommonUIMethods{
    private JPanel editTeamPanel;
    private JButton updateButton;
    private JButton cancelButton;
    private JTextField teamSelected;
    private JTextArea descriptionTxt;
    private JPanel inputPanel;
    private JScrollPane descriptionPanel;

    public EditTeamForm(JFrame mainFrame){

        setContentPane(editTeamPanel);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Edit Team - Project Management System");
        setVisible(true);

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                onExit(mainFrame);
            }
        }); //unfreezes main gui when pop out is closed

        //------Action Listeners----------------------------------------
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelButtonPressed(mainFrame);
            }
        });
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateButtonPressed();
            }
        });
    }


    //---------Methods--------------------------------------------------
    private void updateButtonPressed() {
        //todo implement updateButtonPressed
        System.out.println("EditTeamForm.updateButtonPressed");
    }

    private void cancelButtonPressed(JFrame mainFrame) {
        //todo implement cancelButtonPressed
        System.out.println("EditTeamForm.cancelButtonPressed");
        onExit(mainFrame);

    }
}
