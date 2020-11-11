import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class DeleteTaskForm extends CommonUIMethods{
    private JPanel deleteTaskPanel;
    private JPanel inputPanel;
    private JPanel actionPanel;
    private JComboBox taskCombo;
    private JButton deleteButton;
    private JButton cancelButton;


    public DeleteTaskForm(JFrame mainFrame) {
        setContentPane(deleteTaskPanel);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Delete Task - Project Management System");
        //populateComboBox();
        setVisible(true);

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                onExit(mainFrame);
            }
        }); //unfreezes main gui when pop out is closed

        //-------Action Listeners-------------------------
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

}