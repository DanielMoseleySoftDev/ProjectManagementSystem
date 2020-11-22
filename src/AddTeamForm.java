import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AddTeamForm extends CommonUIMethods{
    private JPanel addTeamPanel;
    private JButton addButton;
    private JButton cancelButton;
    private JTextField nameTxt;
    private JTextArea descriptionTxt;
    private JPanel actionPanel;
    private JPanel inputPanel;
    private JScrollPane descriptionPanel;
    private String teamName;
    private String teamDescription;
    public boolean stopFlag = false;


    public AddTeamForm(JFrame mainFrame){

        setContentPane(addTeamPanel);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Add Team - Project Management System");
        setVisible(true);

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                onExit(mainFrame);
            }
        }); //unfreezes main gui when pop out is closed


        //---------Action Listeners-------------------------------
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelButtonPressed(mainFrame);
            }
        });
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addButtonPressed(mainFrame);
            }
        });
    }

    //--------Methods---------------------------------------------
    private void addButtonPressed(JFrame mainFrame) {
        System.out.println("AddTeamForm.addButtonPressed");
        teamName = nameTxt.getText();
        teamDescription = descriptionTxt.getText();
        if(teamName.equals("N/A")){
            stopFlag  = true;
        }
        for (int i=0; i<Main.teamHandler.getTeams().size();i++) {
                if(teamName.equals(Main.teamHandler.getTeams().get(i).getTeamName())){
                    stopFlag = true;
                }
            }

        if(!stopFlag){
            Main.teamHandler.createTeam(teamName, teamDescription);
            System.out.println("Team created \n" + Main.teamHandler.getTeams());
            onExit(mainFrame);
        }else{
            JOptionPane.showMessageDialog(this, "Team name exists \n " +
                    "Please choose a different one","Warning" ,JOptionPane.WARNING_MESSAGE);
            stopFlag = false;
        }


    }

    private void cancelButtonPressed(JFrame mainFrame) {
        //todo implement cancelButtonPressed
        System.out.println("AddTeamForm.cancelButtonPressed");
        onExit(mainFrame);
    }
}
