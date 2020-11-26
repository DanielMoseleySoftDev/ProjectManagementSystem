import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class MainGUI extends JFrame{
    private JPanel mainPanel;
    private JPanel northPanel;
    private JPanel westPanel;
    private JTextField projectLoadedTxt;
    private JTextField expFinTxt;
    private JPanel centerPanel;
    private JButton openProjectButton;
    private JButton addProjectButton;
    private JButton addTaskButton;
    private JButton deleteTaskButton;
    private JButton completeTaskButton;
    private JButton deleteProjectButton;
    private JButton addTeamButton;
    private JButton deleteTeamButton;
    private JScrollPane activeTaskScrollPanel;
    private JScrollPane waitingTaskScrollPanel;
    private JTable activeTaskTable;
    private JTable waitingTaskTable;
    private JTable completedTaskTable;
    private JComboBox critPathSelectionCombo;
    private JTextField daysLeftTxt;
    private JButton projectInfoButton;
    private JButton taskInfoButton;
    private JButton editTaskButton;
    private DefaultTableModel activeModel;
    private DefaultTableModel waitingModel;
    private DefaultTableModel completeModel;

    private boolean loadedFlag = false;         //Flag for if a project is loaded. Activates/deactivates task options
    private boolean isKotlin = true;            //Flag for which algorithm has been chosen


    public MainGUI(){

        System.out.println("--------------------------\nSetting up GUI:");
        setContentPane(mainPanel);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Project Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setVisible(true);

        //Setting up the tables
        createTaskTable("active");
        createTaskTable("waiting");
        createTaskTable("completed");

        //Populating the algorithm selection combo box
        critPathSelectionCombo.addItem("Kotlin");
        critPathSelectionCombo.addItem("Scala");
        System.out.println("--------------------------\n");



        //--------------ACTION LISTENERS---------------------------------

        openProjectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openProjectButtonPressed();
            }
        });
        addProjectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addProjectButtonPressed();
            }
        });
        addTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addTaskButtonPressed();
            }
        });
        deleteTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteTaskButtonPressed();
            }
        });
        completeTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                completeTaskButtonPressed();

            }
        });
        deleteProjectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteProjectButtonPressed();
            }
        });
        addTeamButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addTeamButtonPressed();
            }
        });
        deleteTeamButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteTeamButtonPressed();
            }
        });
        critPathSelectionCombo.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED){
                    critPathAlgorithmChange();
                }

            }
        });
        projectInfoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                projectInfoButtonPressed();
            }
        });
        taskInfoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                taskInfoButtonPressed();
            }
        });
        editTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editTaskButtonPressed();
            }
        });
    }

    //-----------------GUI METHODS----------------------------------------
    private void critPathAlgorithmChange() {
        //Changes the flag for which algorithm is used. Algorithm is run in updateTaskPanels()
        if(critPathSelectionCombo.getSelectedItem().toString() == "Kotlin"){
            isKotlin = true;
        }else{
            isKotlin = false;
        }
        System.out.println("Critical Path Algorithm changed. (True = Kotlin False = Scala) -> "+ isKotlin);
        updateTaskPanels();
    }

    public void updateLoadedProject(){
        //update the loaded project of the system
        projectLoadedTxt.setText(Main.projectHandler.getCurrentProject().getProjectName());
        System.out.println("MainGUI.updateLoadedProject -> Updated loadedProjectTxt");
        updateTaskPanels();
    }

    public void updateTaskPanels() {
        if(loadedFlag){
            //Checks a project is loaded before running the algorithm
            Main.projectHandler.calculateCriticalPath(isKotlin);
        }

        //displays projects finish date and days left
        expFinTxt.setText(Main.projectHandler.calculateEndDate());
        daysLeftTxt.setText((Main.projectHandler.calculateDaysLeft()));

        Main.taskHandler.updateTaskTables(activeModel,waitingModel,completeModel);
        System.out.println("updateTaskPanels -> Task Panels Updated");
        //locks or enables the task options
        toggleTaskOptionsEnabled();
    }

    public void setLoadedFlag(Boolean flag){
        loadedFlag = flag;
    }

    public void toggleTaskOptionsEnabled(){
        /*
        Locks/enables the task options depending on whether a project is loaded.
        A user should not be able to perform functions on a task if a project is
        not loaded.
        */
        addTaskButton.setEnabled(loadedFlag);
        completeTaskButton.setEnabled(loadedFlag);
        deleteTaskButton.setEnabled(loadedFlag);
        taskInfoButton.setEnabled(loadedFlag);
        editTaskButton.setEnabled(loadedFlag);
        System.out.println("MainGui.toggleTaskOptionsEnabled -> Task Options Enabled = "+loadedFlag);
    }

    private void createTaskTable(String type) {
        System.out.println("Creating Table...");
        //Tables have different columns. Type is passed as String

        if(type == "active") {  //creating active task table

            String[] columnNames = {"Name", "Team", "Duration", "Slack", "Critical?"};
            activeTaskTable.setModel(new DefaultTableModel(null, columnNames));
            activeModel = (DefaultTableModel) activeTaskTable.getModel();
            System.out.println("Active Table Created");

        }else if(type == "waiting"){ //Creating waiting task table

            String[] columnNames = {"Name", "Team", "Est Time","Prerequisites","Slack","Critical?"};
            waitingTaskTable.setModel(new DefaultTableModel(null,columnNames));
            waitingModel = (DefaultTableModel) waitingTaskTable.getModel();
            System.out.println("Waiting Table Created");

        }else if(type == "completed"){ //Creating completed task table

            String[] columnNames = {"Name", "Team", "Duration"};
            completedTaskTable.setModel(new DefaultTableModel(null, columnNames));
            completeModel = (DefaultTableModel) completedTaskTable.getModel();
            System.out.println("Completed table created");
        }
        else{
            System.out.println("MainGUI Table could not be created. No type stated");
        }
    }

    //----------------BUTTON PRESSED METHODS--------------------------------

    private void deleteTeamButtonPressed() {
        //open the Delete Team pop-out form when button pressed
        System.out.println("delete team button pressed");
        DeleteTeamForm popout = new DeleteTeamForm(this);
        this.setEnabled(false);
    }

    private void addTeamButtonPressed() {
        //open the Add Team pop-out form when button pressed
        System.out.println("add team button pressed");
        AddTeamForm popout = new AddTeamForm(this);
        this.setEnabled(false);
    }
    
    private void deleteProjectButtonPressed() {
        //open the Delete Project pop-out form when button pressed
        System.out.println("delete project button pressed");
        DeleteProjectForm popout = new DeleteProjectForm(this);
        this.setEnabled(false);
    }

    private void completeTaskButtonPressed() {
        //open the Complete Task pop-out form when button pressed
        System.out.println("complete task button pressed");
        CompleteTaskForm popout = new CompleteTaskForm(this);
        this.setEnabled(false);
    }

    private void deleteTaskButtonPressed() {
        //open the Delete Task pop-out form when button pressed
        System.out.println("delete task button pressed");
        DeleteTaskForm popout = new DeleteTaskForm(this);
        this.setEnabled(false);

    }

    private void addTaskButtonPressed() {
        //open the Add Task pop-out form when button pressed
        System.out.println("add task button pressed");
        AddTaskForm popout = new AddTaskForm(this);
        this.setEnabled(false);
    }

    private void addProjectButtonPressed() {
        //open the Add Project pop-out form when button pressed
        AddProjectForm popout = new AddProjectForm(this);
        this.setEnabled(false);
        System.out.println("add project button pressed");

    }

    private void openProjectButtonPressed() {
        //open the Open Project pop-out form when button pressed
        System.out.println("openProjectButtonPressed");
        OpenProjectForm popout = new OpenProjectForm(this);
        this.setEnabled(false);

    }

    private void projectInfoButtonPressed() {
        //open the Project Info pop-out form when button pressed
        System.out.println("projectInfoButtonPressed");
        ProjectInfoForm popout = new ProjectInfoForm(this);
        this.setEnabled(false);
    }

    private void taskInfoButtonPressed() {
        //open the Task Info pop-out form when button pressed
        System.out.println("taskInfoButtonPressed");
        TaskInfoForm popout = new TaskInfoForm(this);
        this.setEnabled(false);
    }

    private void editTaskButtonPressed() {
        System.out.println("editInfoButtonPressed");
        EditTaskForm popout = new EditTaskForm(this);
        this.setEnabled(false);
    }

    //-----------------------------------------------------------------------

}
