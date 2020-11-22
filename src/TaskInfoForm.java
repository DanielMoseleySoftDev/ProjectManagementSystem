import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TaskInfoForm extends CommonUIMethods{
    private JPanel taskInfoFormpanel;
    private JPanel northPanel;
    private JPanel centerPanel;
    private JComboBox taskCombo;
    private JTextArea descriptionTxt;


    public TaskInfoForm(MainGUI mainFrame){

        setContentPane(taskInfoFormpanel);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Task Info - Project Management System");
        populateComboBox();
        selectionChange();
        setVisible(true);

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                onExit(mainFrame);
            }
        }); //unfreezes main gui when pop out is closed

        taskCombo.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED){
                    selectionChange();
                }
            }
        });
    }

    private void selectionChange() {
        descriptionTxt.setText(Main.taskHandler.taskToString(taskCombo.getSelectedItem().toString()));
    }

    private void populateComboBox() {

        System.out.println("TaskInfoForm.populateComboBox");
        for(int i=0;i<Main.taskHandler.getTasks().size();i++){
            taskCombo.addItem(Main.taskHandler.getTasks().get(i).getTaskName());
        }
    }
}
