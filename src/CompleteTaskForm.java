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
            public void actionPerformed(ActionEvent e) { cancelButtonPressed(mainFrame);

            }
        });
        completeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { completeButtonPressed(mainFrame);

            }
        });
    }

    private void cancelButtonPressed(JFrame mainFrame) {
        System.out.println("CompleteTaskForm.cancelButtonPressed");
        onExit(mainFrame);
    }

    private void completeButtonPressed(MainGUI mainFrame) {
        System.out.println("CompleteTaskForm.completeButtonPressed");

        if(taskCombo.getItemCount() != 0){
            String selectedTask = taskCombo.getSelectedItem().toString();
            Main.taskHandler.completeTask(selectedTask, mainFrame);
        }else{
            System.out.println("CompleteTaskForm.completeButtonPressed -> No Task Selected");
            JOptionPane.showMessageDialog(this,"No Tasks.\nPlease create task first","Error: No Tasks",JOptionPane.ERROR_MESSAGE);
        }

        onExit(mainFrame);
    }

    private void populateComboBox() {
        System.out.println("CompleteTaskForm.populateComboBox -> populating");
        for (int i=0; i<Main.taskHandler.getTasks().size();i++) {
            if(Main.taskHandler.getTasks().get(i).getStatus()==Status.ACTIVE) {
                taskCombo.addItem(Main.taskHandler.getTasks().get(i).getTaskName());
            }
        }
    }
}
