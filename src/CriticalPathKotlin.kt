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

            // Backflow algorithm
            // while there are tasks whose critical cost isn't calculated.
            while (!remaining.isEmpty()) {

                var progress = false

                // find a new task to calculate
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

                        // add job to completed and remove from remainin
                        completed.add(job)
                        iterator.remove()

                        //if this is set, we know there is no cycle
                        progress = true
                    }
                }
                // If we haven't made any progress then a cycle must exist in
                // the graph and we wont be able to calculate the critical path
                if (!progress) throw RuntimeException("Cyclic dependency, algorithm stopped!")
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
            val remaining: HashSet<Job> = HashSet<Job>(jobs)
            for (job in jobs) {
                for (child in job.listOfChildren) {
                    remaining.remove(child)
                }
            }
            print("Starting jobs: ")
            for (job in remaining) print(job.jobName + " ")
            print("\n\n")
            return remaining
        }

        fun calcEarly(startingJobs: HashSet<Job>) {
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