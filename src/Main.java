public class Main {
    //Handlers
    public static ProjectHandler projectHandler;
    public static TaskHandler taskHandler;
    public static TeamHandler teamHandler;
    public static CriticalPathHandler criticalPathHandler;
    public static ScalaHandler scalaHandler;

    public static void main(String[] args) {

        taskHandler = new TaskHandler();
        teamHandler = new TeamHandler();
        projectHandler = new ProjectHandler();
        criticalPathHandler = new CriticalPathHandler();
        scalaHandler = new ScalaHandler();
        //Open GUI
        new MainGUI();
    }

}