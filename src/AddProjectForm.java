import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;

public class AddProjectForm extends CommonUIMethods{

    private JPanel addProjectPanel;
    private JPanel actionPanel;
    private JPanel contentPanel;
    private JButton cancelButton;
    private JTextField projectNameTxt;
    private JTextField dateTxt;
    private JButton addButton;
    private JTextArea descriptionTxt;
    private JScrollPane descriptionPanel;
    private DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    private String projectName;
    private Date startDate;
    private String Description;

    public AddProjectForm(JFrame mainFrame){

        setContentPane(addProjectPanel);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Add Project - Project Management System");
        setVisible(true);

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                onExit(mainFrame);
            }
        }); //unfreezes main gui when pop out is closed


        //----------------Action Listeners------------------------------------------------------
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    addButtonPressed(mainFrame);
                } catch (ParseException parseException) {
                    System.out.println("Add Project Form - Wrong Date Format");
                }
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelButtonPressed(mainFrame);
            }
        });
    }

    //-------------------Methods----------------------------------------------------------------
    private void cancelButtonPressed(JFrame mainFrame) {
        //todo implement cancelButtonPressed
        System.out.println("AddProjectForm.cancelButtonPressed");
        onExit(mainFrame);

    }

    private void addButtonPressed(JFrame mainFrame) throws ParseException {
        //todo implement addButtonPressed
        System.out.println("AddProjectForm.addButtonPressed");

        String projectName = projectNameTxt.getText();
        String description = descriptionTxt.getText();
        Date startDate = dateFormat.parse(dateTxt.getText());
        Main.projectHandler.createProject(projectName, startDate, description);
        System.out.println("Added project to list of projects:\n"+Main.projectHandler.getProjects());


        //todo load the created

    }
}
