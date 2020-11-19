import java.util.HashSet;

public class ScalaHandler {
    CriticalPathScala criticalPathScala = new CriticalPathScala();

    public Job[] calculateCriticalPath(HashSet<Job> jobs) {

        Job[] output = criticalPathScala.calculateCriticalPath(jobs);
        return output;
    }
}
