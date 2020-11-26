import javax.swing.*;
import java.awt.*;
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
    private JLabel dateLbl;
    private JLabel nameLbl;
    private DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    private String projectName;
    private Date startDate;
    private String Description;
    public boolean stopFlag = false;

    public AddProjectForm(MainGUI mainFrame){

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
                dateLbl.setForeground(Color.BLACK);
                nameLbl.setForeground(Color.BLACK);
                try {
                    addButtonPressed(mainFrame);
                } catch (ParseException parseException) {
                    System.out.println("Add Project Form - Wrong Date Format");
                    dateLbl.setForeground(Color.RED);
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
        System.out.println("AddProjectForm.cancelButtonPressed");
        onExit(mainFrame);

    }

    private void addButtonPressed(MainGUI mainFrame) throws ParseException {
        System.out.println("AddProjectForm.addButtonPressed");
        String projectName = projectNameTxt.getText();
        String description = descriptionTxt.getText();
        Date startDate = dateFormat.parse(dateTxt.getText());
        if(projectName.isEmpty()){ //Checks if the project name is empty and changes the flag for the exception
            nameLbl.setForeground(Color.RED);
            JOptionPane.showMessageDialog(this, "Project name cannot be empty","Warning" ,JOptionPane.WARNING_MESSAGE);
            stopFlag=true;
        }

        for (int i=0; i<Main.projectHandler.getProjects().size();i++){
            if(projectName.equals(Main.projectHandler.getProjects().get(i).getProjectName())){
                stopFlag = true;
                break;
            }
        }


        if(!stopFlag){
            Main.projectHandler.createProject(projectName, startDate, description);
            mainFrame.setLoadedFlag(true);
            mainFrame.updateLoadedProject();
            onExit(mainFrame);
            mainFrame.setLoadedFlag(true);
            mainFrame.updateLoadedProject();
            onExit(mainFrame);
        }else{
            if(!projectName.isEmpty()) {//If the Project name error has been found then this is not the error
                nameLbl.setForeground(Color.RED);
                JOptionPane.showMessageDialog(this, "Project already exists \n " +
                        "Please choose a different name","Warning" ,JOptionPane.WARNING_MESSAGE);
                stopFlag = false;
            }
        }
        stopFlag = false;
    }
}
