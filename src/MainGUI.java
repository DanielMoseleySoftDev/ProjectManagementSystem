import javax.swing.*;
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
    private JButton editTaskButton;
    private JButton deleteProjectButton;
    private JButton settingsButton;


    public MainGUI(){

        setContentPane(mainPanel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        //--------------ACTION LISTENERS---------------------------------
        settingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                settingButtonPressed();
            }
        });
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveButtonPressed();
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
        //-----------------------------------------------------------------
    }


    //----------------BUTTON PRESSED METHODS--------------------------------
    private void deleteProjectButtonPressed() {
        //to do
        System.out.println("delete project button pressed");
    }

    private void editTaskButtonPressed() {
        //to do
        System.out.println("edit task button pressed");
    }

    private void completeTaskButtonPressed() {
        //to do
        System.out.println("complete task button pressed");
    }

    private void deleteTaskButtonPressed() {
        //to do CODE IT
        System.out.println("delete task button pressed");
    }

    private void addTaskButtonPressed() {
        //to do CODE IT
        System.out.println("add task button pressed");
    }

    private void addProjectButtonPressed() {
        //to do CODE IT
        System.out.println("add project button pressed");
    }

    private void openProjectButtonPressed() {
        //to do CODE IT
        System.out.println("openProjectButtonPressed");
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
