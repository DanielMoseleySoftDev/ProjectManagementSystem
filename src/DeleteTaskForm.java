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
    private JList list1;


    public DeleteTaskForm(MainGUI mainFrame) {
        setContentPane(deleteTaskPanel);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Delete Task - Project Management System");
        populateComboBox();
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
                deleteButtonPressed(mainFrame);
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelButtonPressed(mainFrame);
            }
        });
    }
    //-------Methods-----------------------------------------------
    private void cancelButtonPressed(JFrame mainFrame) {
        System.out.println("DeleteTaskForm.cancelButtonPressed");
        onExit(mainFrame);
    }

    private void deleteButtonPressed(MainGUI mainFrame) {
        String selectedTask = taskCombo.getSelectedItem().toString();
        Task foundTask = Main.taskHandler.findTask(selectedTask);
        int taskIndex = Main.taskHandler.getTasks().indexOf(foundTask);
        Main.taskHandler.deleteTask(taskIndex, selectedTask, mainFrame);

        System.out.println("DeleteTeamForm.deleteButtonPressed");
        onExit(mainFrame);
    }

    private void populateComboBox() {
        for (int i=0; i<Main.taskHandler.getTasks().size();i++) {
            taskCombo.addItem(Main.taskHandler.getTasks().get(i).getTaskName());
        }
    }

}