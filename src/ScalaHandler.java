import java.lang.reflect.Array;
import java.util.HashSet;

public class ScalaHandler {
    CriticalPathScala criticalPathScala = new CriticalPathScala();

    public Job[] jobSetPasser(HashSet<Job> jobs) {
        criticalPathScala.helloWorld(jobs);


        Job[] output = new Job[2];
        return output;
    }
}
