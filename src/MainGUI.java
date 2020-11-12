import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

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
    private JButton editTaskButton;
    private JButton deleteProjectButton;
    private JButton settingsButton;
    private JButton addTeamButton;
    private JButton editTeamButton;
    private JButton deleteTeamButton;
    private JScrollPane activeTaskScrollPanel;
    private JScrollPane waitingTaskScrollPanel;
    private JTable activeTaskTable;
    private JTable waitingTaskTable;
    private JTable completedTaskTable;
    private DefaultTableModel activeModel;
    private DefaultTableModel waitingModel;
    private DefaultTableModel completeModel;


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



        //--------------ACTION LISTENERS---------------------------------
        settingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                settingButtonPressed();
            }
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
        editTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editTaskButtonPressed();
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
        Main.taskHandler.updateTaskTables(activeModel,waitingModel,completeModel);
        System.out.println("Task Panels Updated");
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
        //to do
        System.out.println("delete project button pressed");
    }

    private void editTaskButtonPressed() {
        //to do
        System.out.println("edit task button pressed");
        EditTaskForm popout = new EditTaskForm(this);
        this.setEnabled(false);
    }

    private void completeTaskButtonPressed() {
        //todo : Need to add validation that a task has been selected.
        //  checkIfTaskSelected()
        // if selected != null then ... else...

        System.out.println("delete task button pressed");
        CompleteTaskForm popout = new CompleteTaskForm(this);
        System.out.println("complete task button pressed");


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

    private void saveButtonPressed() {
        //to do CODE IT
        System.out.println("Save button pressed");
    }

    private void settingButtonPressed() {

        SettingsForm popout = new SettingsForm(this);
        this.setEnabled(false);
        System.out.println("SettingsButtonPressed");

    }

    //-----------------------------------------------------------------------

}
