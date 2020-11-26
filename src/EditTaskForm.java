import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class EditTaskForm extends CommonUIMethods{
    private JPanel editTaskForm;
    private JPanel actionPanel;
    private JPanel inputPanel;
    private JPanel selectionPanel;
    private JComboBox selectionCombo;
    private JButton updateButton;
    private JButton cancelButton;
    private JTextField nameTxt;
    private JComboBox teamCombo;
    private JTextField preReqTxt;
    private JTextField textField1;
    private JTextArea descriptionTxt;
    private JLabel daysLbl;

    private ArrayList<Task> tasksList;
    private int index;

    public EditTaskForm(MainGUI mainFrame){

        setContentPane(editTaskForm);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Add Task - Project Management System");
        populateComboBox();
        tasksList = Main.taskHandler.getTasks();
        setVisible(true);

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                onExit(mainFrame);
            }
        }); //unfreezes main gui when pop out is closed


        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelButtonPressed();
            }
        });
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateButtonPressed(mainFrame);
            }
        });
        selectionCombo.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                daysLbl.setForeground(Color.BLACK);
                if(e.getStateChange() == ItemEvent.SELECTED){
                    try {
                        selectionChange();
                    }catch(NumberFormatException f){
                        System.out.println("Edit Task form - wrong format");
                        daysLbl.setForeground(Color.RED);
                    }
                }
            }
        });
    }

    private void updateButtonPressed(MainGUI mainFrame) {
        //TODO implement
        String preReq = preReqTxt.getText();
        String teamName = teamCombo.getSelectedItem().toString();
        int estDays = Integer.parseInt(daysLbl.getText());
        String description = descriptionTxt.getText();
        Team team = Main.teamHandler.findTeam(teamName);

        Main.taskHandler.editTask(nameTxt.getText(), preReq,team,estDays,description);
        mainFrame.updateTaskPanels();
        onExit(mainFrame);

    }

    private void cancelButtonPressed() {
        //TODO implement
    }


    private void selectionChange() {
        for(int i = 0;i<tasksList.size();i++){
            if(selectionCombo.getSelectedItem().toString().equals(tasksList.get(i).getTaskName())){
                index = i;
            }
        }

    }

    private void populateComboBox() {

        System.out.println("TaskInfoForm.populateComboBox");
        for(int i=0;i<Main.taskHandler.getTasks().size();i++){
            selectionCombo.addItem(Main.taskHandler.getTasks().get(i).getTaskName());
        }
    }
}
