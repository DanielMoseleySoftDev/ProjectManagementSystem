import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

public class AddTaskForm extends CommonUIMethods{
    private JPanel addTaskPanel;
    private JPanel inputPanel;
    private JPanel actionPanel;
    private JTextField taskNameTxt;
    private JComboBox teamCombo;
    private JTextArea descriptionTxt;
    private JButton addButton;
    private JButton cancelButton;
    private JScrollPane descriptionScrollPanel;
    private JTextField estDaysTxt;
    private JTextField preReqTxt;
    private JLabel nameLbl;
    private JLabel daysLbl;
    private String taskName;
    private Date estStartDate;
    private Date estFinishDate;
    private int estDays;
    private Team team;
    private String taskDescription;
    private String preReq;
    public boolean stopFlag = false;


    public AddTaskForm(MainGUI mainFrame){

        setContentPane(addTaskPanel);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Add Task - Project Management System");
        populateComboBox();
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
                nameLbl.setForeground(Color.BLACK);
                daysLbl.setForeground(Color.BLACK);
                try{
                    addButtonPressed(mainFrame);
                } catch (NumberFormatException f) {
                    System.out.println("Add Task form - wrong format");
                    daysLbl.setForeground(Color.RED);
                }

            }
        });
    }



    //-------------Methods-----------------------------------------
    private void addButtonPressed(MainGUI mainFrame) throws NumberFormatException {
        //todo implement addButtonPressed
        System.out.println("AddTaskForm.addButtonPressed");
        taskName = taskNameTxt.getText();
        if(taskName.equals("S-Startingpoint") || taskName.equals("E-Finishingpoint") || taskName.equals("Remaining critical duration:")){
            stopFlag = true;
        }
        for (int i=0; i<Main.taskHandler.getTasks().size();i++){
            if(taskName.equals(Main.taskHandler.getTasks().get(i).getTaskName())){
                stopFlag = true;
                break;
            }
        }

        if(!stopFlag){
            taskName = taskNameTxt.getText();
            estDays = Integer.parseInt(estDaysTxt.getText());
            taskDescription = descriptionTxt.getText();
            String teamName = teamCombo.getSelectedItem().toString();
            team = Main.teamHandler.findTeam(teamName);
            preReq = preReqTxt.getText();
            //ArrayList<Task> preReqList = getPreReq();
            Main.taskHandler.createTask(taskName,estDays,team,taskDescription,preReq,mainFrame);
            onExit(mainFrame);
        } else{
            nameLbl.setForeground(Color.RED);
            JOptionPane.showMessageDialog(this, "Illegal task name. \n " +
                    "Please choose a different one","Warning" ,JOptionPane.WARNING_MESSAGE);
            stopFlag = false;
        }
    }

    private void cancelButtonPressed(JFrame mainFrame) {
        //todo implement cancelButtonPressed
        System.out.println("AddTaskForm.cancelButtonPressed");
        onExit(mainFrame);
    }


    private void populateComboBox() {
        for (int i=0; i<Main.teamHandler.getTeams().size();i++) {
            teamCombo.addItem(Main.teamHandler.getTeams().get(i).getTeamName());
        }
    }



    private ArrayList<Task> getPreReq() {

        ArrayList<Task> returnList = new ArrayList<>();

        if(preReq.isEmpty()){
            return returnList;
        }else{
            //split the string
            String[] temp = preReq.split(", ");
            ArrayList<Task> thTask = Main.taskHandler.getTasks();

            //loops through list of user input and checks them against each task in task handler
            for(int i = 0; i< temp.length;i++){
                for(int j=0;j<thTask.size();j++){
                    if(temp[i] == thTask.get(j).getTaskName()) {
                        returnList.add(thTask.get(j));
                        System.out.println("Added Pre Req Task: " + thTask.get(j));
                    }
                }
            }
            return returnList;
        }


    }


}
