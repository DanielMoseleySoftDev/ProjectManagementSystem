import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class DeleteProjectForm extends CommonUIMethods{
    private JPanel deleteProjectPanel;
    private JPanel actionPanel;
    private JPanel inputPanel;
    private JButton deleteButton;
    private JButton cancelButton;
    private JComboBox projectCombo;

    public DeleteProjectForm(MainGUI mainFrame) {
        setContentPane(deleteProjectPanel);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Delete Project - Project Management System");
        populateComboBox();
        setVisible(true);

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                onExit(mainFrame);
            }
        }); //unfreezes main gui when pop out is closed

        //-----------Action Listeners------------------------------
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { deleteButtonPressed(mainFrame);}


        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { cancelButtonPressed(mainFrame);}

            });
        }

    private void cancelButtonPressed(JFrame mainFrame) {
        System.out.println("DeleteProjectForm.cancelButtonPressed");
        onExit(mainFrame);
    }

    private void deleteButtonPressed(MainGUI mainFrame) {

        System.out.println("DeleteProjectForm.deleteButtonPressed");
        String selectedProject = projectCombo.getSelectedItem().toString();
        Project foundProject = Main.projectHandler.findProject(selectedProject);
        int projectIndex = Main.projectHandler.getProjects().indexOf(foundProject);
        Main.projectHandler.deleteProject(projectIndex);

        mainFrame.updateLoadedProject();
        onExit(mainFrame);
    }
    private void populateComboBox() {
        System.out.println("DeleteProjectForm.populateComboBox -> populating");
        for (int i=0; i<Main.projectHandler.getProjects().size();i++) {
            projectCombo.addItem(Main.projectHandler.getProjects().get(i).getProjectName());
        }
    }

}
