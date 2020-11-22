import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ProjectInfoForm extends CommonUIMethods{
    private JPanel projectInfoPanel;
    private JPanel centerPanel;
    private JComboBox projectCombo;
    private JPanel northPanel;
    private JTextArea projectInfoTxt;

    public ProjectInfoForm(MainGUI mainFrame){

        setContentPane(projectInfoPanel);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Project Info - Project Management System");
        populateComboBox(mainFrame);
        selectionChange();
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
        if(Main.projectHandler.getProjects().size() != 0) {
            projectInfoTxt.setText(Main.projectHandler.projectToString(projectCombo.getSelectedItem().toString()));
        }
    }

    private void populateComboBox(MainGUI mainFrame) {

        System.out.println("ProjectInfoForm.populateComboBox");
        if(Main.projectHandler.getProjects().size() != 0){
            for(int i=0;i<Main.projectHandler.getProjects().size();i++){
                projectCombo.addItem(Main.projectHandler.getProjects().get(i).getProjectName());
            }
        }else{
            JOptionPane.showMessageDialog(this, "No projects to show",
                    "Warning" ,JOptionPane.WARNING_MESSAGE);

        }
    }
}
