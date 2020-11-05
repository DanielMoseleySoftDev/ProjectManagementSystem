import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.util.Date;

public class AddTaskForm extends CommonUIMethods{
    private JPanel addTaskPanel;
    private JPanel inputPanel;
    private JPanel actionPanel;
    private JTextField taskNameTxt;
    private JComboBox preReqCombo;
    private JComboBox teamCombo;
    private JTextArea descriptionTxt;
    private JButton addButton;
    private JButton cancelButton;
    private JScrollPane descriptionScrollPanel;
    private JTextField estDaysTxt;

    private String taskName;
    private Date estStartDate;
    private Date estFinishDate;
    private int estDays;
    private String team;
    private String taskDescription;



    public AddTaskForm(JFrame mainFrame){

        setContentPane(addTaskPanel);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Add Task - Project Management System");
        setVisible(true);

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                onExit(mainFrame);
            }
        }); //unfreezes main gui when pop out is closed


        //---------Action Listeners---------------------------------
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelButtonPressed(mainFrame);

            }
        });
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    addButtonPressed();
                } catch (NumberFormatException f) {
                    System.out.println("Add Task form - wrong format");
                }

            }
        });
    }

    //-------------Methods-----------------------------------------
    private void addButtonPressed() throws NumberFormatException {
        //todo implement addButtonPressed
        System.out.println("AddTaskForm.addButtonPressed");
        taskName = taskNameTxt.getText();
        estDays = Integer.parseInt(estDaysTxt.getText());
        taskDescription = descriptionTxt.getText();


        //Main.taskHandler.createTask(taskName, estDays, taskDescription);
        //TODO Team variable for task information?
    }


    private void cancelButtonPressed(JFrame mainFrame) {
        //todo implement cancelButtonPressed
        System.out.println("AddTaskForm.cancelButtonPressed");
        onExit(mainFrame);
    }
}
