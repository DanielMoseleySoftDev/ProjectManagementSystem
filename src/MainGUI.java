import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainGUI extends JFrame{
    private JPanel mainPanel;
    private JPanel northPanel;
    private JPanel westPanel;
    private JTextField projectLoadedTxt;
    private JTextField pEstFinTxt;
    private JPanel centerPanel;
    private JButton saveButton;
    private JButton openProjectButton;
    private JButton addProjectButton;
    private JButton addTaskButton;
    private JButton deleteTaskButton;
    private JButton completeTaskButton;
    private JButton deleteProjectButton;
    private JButton CriticalButton;
    private JButton addTeamButton;
    private JButton editTeamButton;
    private JButton deleteTeamButton;
    private JScrollPane activeTaskScrollPanel;
    private JScrollPane waitingTaskScrollPanel;
    private JTable activeTaskTable;
    private JTable waitingTaskTable;
    private JTable completedTaskTable;
    private JComboBox scalaorkotlin;
    private JProgressBar progressBar1;
    private DefaultTableModel activeModel;
    private DefaultTableModel waitingModel;
    private DefaultTableModel completeModel;

    private boolean loadedFlag = false;         //Flag for if a project is loaded. Activates/deactivates task options


    public MainGUI(){
//        projectHandler = new ProjectHandler();

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
        System.out.println("--------------------------\n");
        scalaorkotlin.addItem("Kotlin");
        scalaorkotlin.addItem("Scala");



        //--------------ACTION LISTENERS---------------------------------
        CriticalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { criticalButtonPressed(); }
        });


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
        editTeamButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editTeamButtonPressed();
            }
        });
        deleteTeamButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteTeamButtonPressed();
            }
        });
    }


    public void updateLoadedProject(){
        //todo this method needs to be updated so that all project loaded stuff goes through here
        //update the loaded project of the system
        projectLoadedTxt.setText(Main.projectHandler.getCurrentProject().getProjectName());
        System.out.println("MainGUI.updateLoadedProject -> Updated loadedProjectTxt");
        updateTaskPanels();
    }

    public void updateTaskPanels() {
        if(loadedFlag){
            Main.projectHandler.calculateCriticalPath(true);
        }
        Main.taskHandler.updateTaskTables(activeModel,waitingModel,completeModel);
        System.out.println("Task Panels Updated");
        toggleTaskOptionsEnabled();

    }

    public void setLoadedFlag(Boolean flag){
        loadedFlag = flag;
    }

    public void toggleTaskOptionsEnabled(){

        addTaskButton.setEnabled(loadedFlag);
        completeTaskButton.setEnabled(loadedFlag);
        deleteTaskButton.setEnabled(loadedFlag);
        System.out.println("MainGui.toggleTaskOptionsEnabled -> Task Options Enabled = "+loadedFlag);

    }



    private void createTaskTable(String type) {
        System.out.println("Creating Table...");

        if(type == "active") {                                                          //creating active task table
            String[] columnNames = {"Task Name", "Team", "Due date", "Time Left", "Delay"};
            activeTaskTable.setModel(new DefaultTableModel(null, columnNames));

            activeModel = (DefaultTableModel) activeTaskTable.getModel();

            System.out.println("...Active Table Created");


        }else if(type == "waiting"){                                                    //Creating waiting task table


            String[] columnNames = {"Task Name", "Team", "Est Time", "Est. Start", "Prerequisites"};
            waitingTaskTable.setModel(new DefaultTableModel(null,columnNames));
            waitingModel = (DefaultTableModel) waitingTaskTable.getModel();
            System.out.println("...Waiting Table Created");


        }else if(type == "completed"){

            String[] columnNames = {"Task Name", "Team", "Due date", "Time spent"," Delay"};
            completedTaskTable.setModel(new DefaultTableModel(null, columnNames));
            completeModel = (DefaultTableModel) completedTaskTable.getModel();
            System.out.println("...Completed table created");
        }
        else{
            System.out.println("MainGUI Table could not be created. No type stated");
        }

    }


    //----------------BUTTON PRESSED METHODS--------------------------------

    private void deleteTeamButtonPressed() {
        //todo implement delete
        System.out.println("delete team button pressed");
        DeleteTeamForm popout = new DeleteTeamForm(this);
        this.setEnabled(false);
    }

    private void editTeamButtonPressed() {
        System.out.println("edit team button pressed");
        EditTeamForm popout = new EditTeamForm(this);
        this.setEnabled(false);
    }

    private void addTeamButtonPressed() {
        System.out.println("add team button pressed");
        AddTeamForm popout = new AddTeamForm(this);
        this.setEnabled(false);
    }
    
    private void deleteProjectButtonPressed() {
        System.out.println("delete project button pressed");
        DeleteProjectForm popout = new DeleteProjectForm(this);
        this.setEnabled(false);
        System.out.println("delete project button pressed");
    }

    private void completeTaskButtonPressed() {
        //todo : Need to add validation that a task has been selected.
        //  checkIfTaskSelected()
        // if selected != null then ... else...
        System.out.println("complete task button pressed");
        CompleteTaskForm popout = new CompleteTaskForm(this);

    }

    private void deleteTaskButtonPressed() {
        System.out.println("delete task button pressed");
        DeleteTaskForm popout = new DeleteTaskForm(this);
        this.setEnabled(false);

    }

    private void addTaskButtonPressed() {
        //to do CODE IT
        System.out.println("add task button pressed");
        AddTaskForm popout = new AddTaskForm(this);
        this.setEnabled(false);
    }

    private void addProjectButtonPressed() {
        //to do CODE IT

        AddProjectForm popout = new AddProjectForm(this);
        this.setEnabled(false);
        System.out.println("add project button pressed");

    }

    private void openProjectButtonPressed() {
        //to do CODE IT
        System.out.println("openProjectButtonPressed");
        OpenProjectForm popout = new OpenProjectForm(this);
        this.setEnabled(false);

    }

    private void criticalButtonPressed() {
        System.out.println("CriticalButtonPressed");
        System.out.println(Main.criticalPathHandler.calcCriticalPath(true));


    }

    //-----------------------------------------------------------------------

}
