import java.util.Date;
import java.util.List;

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
        System.out.println("Persistence made");

        Project testProject = new Project("test", new Date(), new Date(), "String test");
        p.saveToFile(testProject);
        p.loadFromFile();

//        projectHandler.createProject("Test", new Date(), "Testing");
//        System.out.println("Project Added");
//        System.out.println(projectHandler.getProjects());
//        p.saveToFile(projectHandler.getProjects());
//        System.out.println("Saved to File");
//        p.loadFromFile();
//        System.out.println("Loads from file");
    }

}