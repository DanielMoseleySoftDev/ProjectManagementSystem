data class Job(val jobName: String, val duration: Int, var listOfChildren : ArrayList<Job> = ArrayList()) {
        //var depe = HashSet<Job>()
    init {


    }
   /* fun dodepe() {
        for (task in allTasks) {
            for (prereq in task.preReqTasks) {
                for (task2 in allTasks) {
                    if (task2 == prereq) {
                        depe
                    }
                }
            }
        }
    }*/
}