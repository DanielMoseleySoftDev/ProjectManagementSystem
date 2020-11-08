import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.spi.CalendarDataProvider;

public class Main {
    //Handlers
    public static ProjectHandler projectHandler;
    public static TaskHandler taskHandler;
    public static TeamHandler teamHandler;

    public static void main(String[] args) {
        projectHandler = new ProjectHandler();
        taskHandler = new TaskHandler();
        teamHandler = new TeamHandler();
        //Open GUI
        new MainGUI();


        Persistence p = new Persistence();
        System.out.println("Persistence object made");

        System.out.println("Creating test projects");
        projectHandler.getProjects().add(new Project("Project1", new Date(), new Date(), "String test project 1"));
        projectHandler.getProjects().add(new Project("Project2", new Date(), new Date(), "String test project 2"));
        projectHandler.getProjects().add(new Project("Project3", new Date(), new Date(), "String test project 3"));
        projectHandler.getProjects().add(new Project("Project3", new Date(), new Date(), "String test project 3"));

        Calendar c = Calendar.getInstance();
        ArrayList<Task> tks = new ArrayList<Task>();
        tks.add(new Task("task1",5,c,new Team("team a","blahh"),"task description"));
        projectHandler.getProjects().get(0).setTasks(tks);


        System.out.println("\n 1. Saving to file \n");
        p.saveToFile(projectHandler.getProjects());

        System.out.println("\n 2. Clearing the projectHandler list\n");
        projectHandler.getProjects().clear();

        System.out.println("\n 3. Loading the projects back\n");
        projectHandler.setProjects(p.loadFromFile());

        System.out.println("\n 4. Displaying the loaded projects list");
        for(int i = 0; i<projectHandler.getProjects().size();i++){
            System.out.println(projectHandler.getProjects().get(i));
        }
        System.out.println("\nProject1 task: "+ projectHandler.getProjects().get(0).getTasks());
    }

}