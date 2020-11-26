import kotlin.collections.HashSet

class CriticalPathKotlin {

    companion object{

        var maxDuration=0

        fun calculateCriticalPath(jobs: HashSet<Job>) : Array<Job>{
            println("CriticalPathKotlin.calculateCriticalPath -> Calculations Started")
            maxDuration = 0

            // jobs whose critical cost has been calculated
            var completed: HashSet<Job> = HashSet()

            // jobs whose critical cost needs to be calculated
            var remaining = HashSet(jobs)


            while (remaining.isNotEmpty()) {

                var progress = false

                // Iterate over each job
                val iterator: MutableIterator<Job> = remaining.iterator()
                while (iterator.hasNext()) {
                    val job: Job = iterator.next()
                    if (completed.containsAll(job.listOfChildren)) {

                        // critical duration plus child critical duration
                        var criticalDuration = 0
                        for (child in job.listOfChildren) {
                            if (child.criticalDuration > criticalDuration) {
                                criticalDuration = child.criticalDuration
                            }
                        }
                        job.criticalDuration = criticalDuration + job.duration

                        // add job to completed and remove from remaining
                        completed.add(job)
                        iterator.remove()

                        //if this is set, we know there is no cycle
                        progress = true
                    }
                }
                // If progress isn't made then the algorithm is stuck in a loop because there
                //are cycles in the graph. it is not a DAG
                if (!progress) throw RuntimeException("CriticalPathKotlin.calculateCriticalPath -> Error: Loop in graph")
            }

            // calculate the critical duration (total critical path value)
            calcMaxDuration(jobs)

            //get the starting jobs and set their early start and finish
            val startingJobs: HashSet<Job> = findStartingJobs(jobs)
            calcEarly(startingJobs)

            // Convert completed to an array
            val completedArray: Array<Job> = completed.toTypedArray()

            // Append the critical duration to the return array
            println("CriticalPathKotlin.calculateCriticalPath.maxDuration -> $maxDuration")
            Main.criticalPathHandler.criticalInfo.add("Remaining critical duration:")
            Main.criticalPathHandler.criticalInfo.add(maxDuration.toString())

            return completedArray
        }

        private fun calcMaxDuration(jobs: Set<Job>) {
            // Set max to -1 so that the critical duration of a job is always bigger
            var max = -1
            for (job in jobs) {
                if (job.criticalDuration > max) max = job.criticalDuration
            }
            maxDuration = max
            println("Critical path length (Duration): $maxDuration")
            for (job in jobs) {
                job.setLatest(maxDuration)
            }
        }

        private fun findStartingJobs(jobs: Set<Job>): HashSet<Job> {
            /* All jobs that don't have a predecessor get returned.
            * Multiple Tasks can be active at the same time, therefore
            * the start of the graph is not a single point. Theoretically,
            * there could be hundreds of independent graphs (Task chains)
            * in a project. This returned HashSet allows the program to
            * artificially put a single starting node.*/

            val remaining: HashSet<Job> = HashSet<Job>(jobs)
            for (job in jobs) {
                for (child in job.listOfChildren) {
                    remaining.remove(child)
                }
            }
            return remaining
        }

        private fun calcEarly(startingJobs: HashSet<Job>) {
            for (job in startingJobs) {
                job.earlyStart = 0
                job.earlyFinish = job.duration
                setEarly(job)
            }
        }

        private fun setEarly(startingJob: Job) {
            val completionTime = startingJob.earlyFinish
            for (child in startingJob.listOfChildren) {
                if (completionTime >= child.earlyStart) {
                    child.earlyStart = completionTime
                    child.earlyFinish = completionTime + child.duration
                }
                setEarly(child)
            }
        }
    }
}