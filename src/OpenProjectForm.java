import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class OpenProjectForm extends CommonUIMethods{
    private JPanel openProjectPanel;
    private JPanel projectOptionPanel;
    private JPanel actionPanel;
    private JComboBox projectSelectCombo;
    private JButton openButton;
    private JButton cancelButton;

    public OpenProjectForm(MainGUI mainFrame) {

        setContentPane(openProjectPanel);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Open Project - Project Management System");
        populateComboBox();
        setVisible(true);

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                onExit(mainFrame);
            }
        }); //unfreezes main gui when pop out is closed
        

        //-----------Action Listeners---------------------------------------
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelButtonPressed(mainFrame);
            }
        });
        openButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openButtonPressed(mainFrame);
            }
        });
    }



    //----------------Methods-----------------------------------------------

    private void populateComboBox() {
        System.out.println("OpenProjectForm.populateComboBox");
        for(int i=0;i<Main.projectHandler.getProjects().size();i++){

            projectSelectCombo.addItem(Main.projectHandler.getProjects().get(i).getProjectName());
        }
    }

    private void openButtonPressed(MainGUI mainFrame) {
        //Button implemented    - Gets the selected name of project from combo-box
        //                      - Sets the current project in ProjectHandler
        //                      - Calls function in the main gui to update its loaded project

        System.out.println("OpenProjectForm.openButtonPressed");
        String selected = projectSelectCombo.getSelectedItem().toString();
        Main.projectHandler.selectProject(selected);
        mainFrame.updateLoadedProject();
        onExit(mainFrame);
    }

    private void cancelButtonPressed(JFrame mainFrame) {

        System.out.println("OpenProjectForm.cancelButtonPressed");
        onExit(mainFrame);
    }
}
