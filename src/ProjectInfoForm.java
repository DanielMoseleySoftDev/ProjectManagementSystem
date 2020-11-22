import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ProjectInfoForm extends CommonUIMethods{
    private JPanel projectInfoPanel;
    private JComboBox projectCombo;
    private JTextArea projectInfoTxt;

    public ProjectInfoForm(MainGUI mainFrame){

        setContentPane(projectInfoPanel);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Project Info - Project Management System");
        populateComboBox();
        setVisible(true);

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                onExit(mainFrame);
            }
        }); //unfreezes main gui when pop out is closed
        projectCombo.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED){
                    selectionChange();
                }

            }
        });
    }

    private void selectionChange() {
        projectInfoTxt.setText(Main.projectHandler.projectToString(projectCombo.getSelectedItem().toString()));

    }

    private void populateComboBox() {

        System.out.println("OpenProjectForm.populateComboBox");
        for(int i=0;i<Main.projectHandler.getProjects().size();i++){

            projectCombo.addItem(Main.projectHandler.getProjects().get(i).getProjectName());
        }
    }
}
