import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class DeleteTeamForm extends CommonUIMethods{
    private JPanel deleteTeamPanel;
    private JPanel actionPanel;
    private JPanel inputPanel;
    private JButton deleteButton;
    private JButton cancelButton;
    private JComboBox teamCombo;

    public DeleteTeamForm(JFrame mainFrame){

        setContentPane(deleteTeamPanel);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Delete Team - Project Management System");
        setVisible(true);

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                onExit(mainFrame);
            }
        }); //unfreezes main gui when pop out is closed


        //-------Action Listeners-------------------------------------
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelButtonPressed(mainFrame);
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteButtonPressed();
            }
        });
    }

    //------------Methods--------------------------------------------------
    private void deleteButtonPressed() {
        //todo implement delete team
        System.out.println("DeleteTeamForm.deleteButtonPressed");
    }

    private void cancelButtonPressed(JFrame mainFrame) {
        //todo implement cancelButtonPressed
        System.out.println("DeleteTeamForm.cancelButtonPressed");
        onExit(mainFrame);
    }
}
