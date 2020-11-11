import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class DeleteTeamForm extends CommonUIMethods{
    private JPanel deleteTeamPanel;
    private JPanel actionPanel;
    private JPanel inputPanel;
    private JButton deleteButton;
    private JButton cancelButton;
    private JComboBox teamCombo;

    public DeleteTeamForm(JFrame mainFrame){

        setContentPane(deleteTeamPanel);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Delete Team - Project Management System");
        populateComboBox();
        setVisible(true);

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                onExit(mainFrame);
            }
        }); //unfreezes main gui when pop out is closed


        //-------Action Listeners-------------------------------------
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelButtonPressed(mainFrame);
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteButtonPressed(mainFrame);
            }
        });
    }

    //------------Methods--------------------------------------------------
    private void deleteButtonPressed(JFrame mainFrame) {
        //todo implement delete team
        String selectedTeam = teamCombo.getSelectedItem().toString();
        Team foundTeam = Main.teamHandler.findTeam(selectedTeam);
        int teamIndex = Main.teamHandler.getTeams().indexOf(foundTeam);
        Main.teamHandler.deleteTeam(teamIndex);

        System.out.println("DeleteTeamForm.deleteButtonPressed");
        onExit(mainFrame);
    }

    private void cancelButtonPressed(JFrame mainFrame) {
        //todo implement cancelButtonPressed
        System.out.println("DeleteTeamForm.cancelButtonPressed");
        onExit(mainFrame);
    }
    private void populateComboBox() {
        for (int i=0; i<Main.teamHandler.getTeams().size();i++) {
            teamCombo.addItem(Main.teamHandler.getTeams().get(i).getTeamName());
        }
    }
}
