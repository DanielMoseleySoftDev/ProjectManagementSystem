import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class EditTaskForm extends CommonUIMethods{

    private JPanel editTaskPanel;
    private JPanel optionsPanel;
    private JPanel actionPanel;
    private JButton updateButton;
    private JButton cancelButton;
    private JTextField taskSelectedTxt;
    private JComboBox preReqCombo;
    private JComboBox teamCombo;
    private JTextArea descriptionTxt;
    private JScrollPane descriptionScrollPanel;


    public EditTaskForm(JFrame mainFrame){

        setContentPane(editTaskPanel);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Edit Task - Project Management System");
        setVisible(true);

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                onExit(mainFrame);
            }
        }); //unfreezes main gui when pop out is closed


        //--------Action Listeners------------------------------------
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


    //-----------Methods----------------------------------------------
    private void updateButtonPressed() {
        //todo implement updateButtonPressed
        System.out.println("EditTaskForm.updateButtonPressed");
    }

    private void cancelButtonPressed(JFrame mainFrame) {
        //todo implement cancelButtonPressed
        System.out.println("EditTaskForm.cancelButtonPressed");
        onExit(mainFrame);
    }

}
