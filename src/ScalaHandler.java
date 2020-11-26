import java.util.HashSet;

public class ScalaHandler {
    CriticalPathScala criticalPathScala = new CriticalPathScala();

    public Job[] calculateCriticalPath(HashSet<Job> jobs) {
        //Acts as a middle man between Kotlin and Scala as there is issues calling
        // Scala functions from Kotlin.
        Job[] output = criticalPathScala.calculateCriticalPath(jobs);
        return output;
    }
}
