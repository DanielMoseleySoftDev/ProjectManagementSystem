import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class CompleteTaskForm extends CommonUIMethods{
    private JPanel completeTaskPanel;
    private JButton completeButton;
    private JButton cancelButton;
    private JPanel inputPanel;
    private JPanel actionPanel;
    private JComboBox taskCombo;

    public CompleteTaskForm(MainGUI mainFrame) {
        setContentPane(completeTaskPanel);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Complete task - Project Management System");
        populateComboBox();
        setVisible(true);

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                onExit(mainFrame);
            }
        }); //unfreezes main gui when pop out is closed

        //---------------Action Listeners----------------------------
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { completeButtonPressed(mainFrame);

            }
        });
        completeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { cancelButtonPressed(mainFrame);

            }
        });
    }

    private void cancelButtonPressed(JFrame mainFrame) {
        System.out.println("CancelTaskForm.cancelButtonPressed");
        onExit(mainFrame);
    }

    private void completeButtonPressed(MainGUI mainFrame) {
        String selectedTask = taskCombo.getSelectedItem().toString();
        Task foundTask = Main.taskHandler.findTask(selectedTask);
        int taskIndex = Main.taskHandler.getTasks().indexOf(foundTask);
        Main.taskHandler.completeTask(taskIndex, mainFrame);
    }

    private void populateComboBox() {
        for (int i=0; i<Main.taskHandler.getTasks().size();i++) {
            taskCombo.addItem(Main.taskHandler.getTasks().get(i).getTaskName());
        }
    }
}
