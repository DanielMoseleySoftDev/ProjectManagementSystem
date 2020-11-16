import java.util.*
import kotlin.collections.HashSet

class CriticalPathKotlin {

    companion object{

        var maxDuration=0

        fun calculateCriticalPath(jobs: HashSet<Job>) : Array<Job>{
            println("CriticalPathKotlin.calculateCriticalPath -> Calculations Started")

            // tasks whose critical cost has been calculated

            // tasks whose critical cost has been calculated
            var completed: HashSet<Job> = HashSet()
            // tasks whose critical cost needs to be calculated
            // tasks whose critical cost needs to be calculated
            var remaining = jobs

            // Backflow algorithm
            // while there are tasks whose critical cost isn't calculated.

            // Backflow algorithm
            // while there are tasks whose critical cost isn't calculated.
            while (!remaining.isEmpty()) {
                var progress = false

                // find a new task to calculate
                val it: MutableIterator<Job> = remaining.iterator()
                while (it.hasNext()) {
                    val job: Job = it.next()
                    if (completed.containsAll(job.listOfChildren)) {
                        // all dependencies calculated, critical cost is max
                        // dependency
                        // critical cost, plus our cost
                        var critical = 0
                        for (j in job.listOfChildren) {
                            if (j.criticalDuration > critical) {
                                critical = j.criticalDuration
                            }
                        }
                        job.criticalDuration = critical + job.duration
                        // set task as calculated an remove
                        completed.add(job)
                        it.remove()
                        // note we are making progress
                        progress = true
                    }
                }
                // If we haven't made any progress then a cycle must exist in
                // the graph and we wont be able to calculate the critical path
                if (!progress) throw RuntimeException("Cyclic dependency, algorithm stopped!")
            }

            // get the cost

            // get the cost
            calcMaxDuration(jobs)
            val initialNodes: HashSet<Job> = initials(jobs)
            calculateEarly(initialNodes)

            // get the tasks

            // get the tasks
            val ret: Array<Job> = completed.toTypedArray()
            // create a priority list
            // create a priority list
            Arrays.sort(ret) { job1, job2 -> job1.jobName.compareTo(job2.jobName) }

            return ret

        }

        private fun calcMaxDuration(jobs: Set<Job>) {
            var max = -1
            for (j in jobs) {
                if (j.criticalDuration > max) max = j.criticalDuration
            }
            maxDuration = max
            println("Critical path length (Duration): $maxDuration")
            for (j in jobs) {
                j.setLatest(maxDuration)
            }
        }

        private fun initials(jobs: Set<Job>): HashSet<Job> {
            val remaining: HashSet<Job> = HashSet<Job>(jobs)
            for (j in jobs) {
                for (tc in j.listOfChildren) {
                    remaining.remove(tc)
                }
            }
            print("Initial nodes: ")
            for (j in remaining) print(j.jobName + " ")
            print("\n\n")
            return remaining
        }

        fun calculateEarly(initials: HashSet<Job>) {
            for (initial in initials) {
                initial.earlyStart = 0
                initial.earlyFinish = initial.duration
                setEarly(initial)
            }
        }

        private fun setEarly(initial: Job) {
            val completionTime = initial.earlyFinish
            for (j in initial.listOfChildren) {
                if (completionTime >= j.earlyStart) {
                    j.earlyStart = completionTime
                    j.earlyFinish = completionTime + j.duration
                }
                setEarly(j)
            }

        }
    }




}