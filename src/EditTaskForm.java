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
    private JTextField daysTxt;
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
        populateTeamComboBox();
        tasksList = Main.taskHandler.getTasks();
        selectionChange();
        setVisible(true);

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                onExit(mainFrame);
            }
        }); //unfreezes main gui when pop out is closed


        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelButtonPressed(mainFrame);
            }
        });
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                daysLbl.setForeground(Color.BLACK);
                try {
                    updateButtonPressed(mainFrame);
                }catch(NumberFormatException f){
                    System.out.println("Edit Task form - wrong format");
                    daysLbl.setForeground(Color.RED);
                }
            }
        });
        selectionCombo.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED){
                    selectionChange();
                }
            }
        });
    }

    private void updateButtonPressed(MainGUI mainFrame) {

        String teamName = teamCombo.getSelectedItem().toString();
        int estDays = Integer.parseInt(daysTxt.getText());
        String description = descriptionTxt.getText();
        Team team = Main.teamHandler.findTeam(teamName);

        Main.taskHandler.editTask(nameTxt.getText(), team,estDays,description);
        mainFrame.updateTaskPanels();
        onExit(mainFrame);

    }

    private void cancelButtonPressed(MainGUI mainFrame) {
        System.out.println("EditTaskForm.cancelButtonPressed");
        onExit(mainFrame);
    }


    private void selectionChange() {
        for(int i = 0;i<tasksList.size();i++){
            Task task = tasksList.get(i);
            if(selectionCombo.getSelectedItem().toString().equals(task.getTaskName())){
                nameTxt.setText(task.getTaskName());
                preReqTxt.setText(Main.taskHandler.getPreReqToString(task));
                teamCombo.setSelectedItem(task.getTeamAssigned().getTeamName());
                daysTxt.setText(Integer.toString(task.getEstDays()));
                descriptionTxt.setText(task.getTaskDescription());
            }
        }


    }

    private void populateComboBox() {

        System.out.println("TaskInfoForm.populateComboBox");
        for(int i=0;i<Main.taskHandler.getTasks().size();i++){
            selectionCombo.addItem(Main.taskHandler.getTasks().get(i).getTaskName());
        }
    }

    private void populateTeamComboBox() {
        for (int i=0; i<Main.teamHandler.getTeams().size();i++) {
            teamCombo.addItem(Main.teamHandler.getTeams().get(i).getTeamName());
        }
    }
}
