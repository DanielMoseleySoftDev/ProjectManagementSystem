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
        MainGUI mainGUI = new MainGUI();
    }

}