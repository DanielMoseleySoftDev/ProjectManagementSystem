class CriticalPathHandler {

    private var jobsList = ArrayList<Job>()
    private var taskList = ArrayList<Task>()
    private var criticalTasks = arrayListOf<ArrayList<String>>()
    var criticalInfo = ArrayList<String>()

    fun calcCriticalPath(isKotlin : Boolean) : ArrayList<ArrayList<String>>{

        //clear the lists from previous calculations
        jobsList.clear()
        taskList.clear()
        criticalInfo.clear()
        criticalTasks = arrayListOf()

        //Get descendants of tasks. Format for critical path algorithm
        flipChildParentNodes(Main.taskHandler.tasks)
        //Convert list of jobs to HashSet
        val jobSet = listToSet()
        val returnJobs: Array<Job>
        if (jobsList.isEmpty()){
            return criticalTasks
        }

        if (isKotlin){
            //Kotlin algorithm
            println("CriticalPathHandler.CalcCriticalPath -> Kotlin Algorithm")
            returnJobs =  CriticalPathKotlin.calculateCriticalPath(jobSet)
        }else{
            //Scala algorithm
            println("CriticalPathHandler.CalcCriticalPath -> Scala Algorithm")
            returnJobs = Main.scalaHandler.calculateCriticalPath(jobSet)
        }

        //Convert the returned jobs into format that can be used for displaying
        toStringArray(returnJobs)
        criticalTasks.add(criticalInfo)
        return criticalTasks

    }

    private fun toStringArray(returnJobs : Array<Job>) {
        for (job in returnJobs){
            if(job.jobStatus != Status.COMPLETE){   //Don't want to include completed Tasks in critical path
                val jobInfo = ArrayList<String>()
                jobInfo.add(job.jobName)
                val slack = job.lateFinish-job.earlyFinish //Task with 0 slack is considered critical
                jobInfo.add(slack.toString())
                criticalTasks.add(jobInfo)
            }
        }
    }

    fun flipChildParentNodes(tasks: ArrayList<Task>){
        //Makes the taskList and jobsList the same size by removing completed tasks
        val incompleteTasks = ArrayList<Task>()
        for (task in tasks) {
            if(task.status != Status.COMPLETE) {
                incompleteTasks.add(task)
            }
        }
        taskList.addAll(incompleteTasks)
        createJobsList()
        if(jobsList.isEmpty()){
            return
        }
        //Finding nodes descendants based on list of ancestors
        for ((count, i) in taskList.withIndex()){
            for ((countB, j) in taskList.withIndex()){
                for (preReq in j.preReqTasks){
                    if (preReq.taskName == i.taskName){
                        jobsList[count].listOfChildren.add(jobsList[countB])
                    }
                }
            }
        }
        addStartAndEnd()
    }

    private fun addStartAndEnd(){
        /*
        * There can be multiple tasks happening concurrently. To ensure the DAG has a set Start
        * and End node, they are artificially added here with a duration of 0.
        */
        val beginningJobs = ArrayList<Job>()
        for((count, task) in taskList.withIndex()){
            if(task.preReqTasks.isEmpty()){
                beginningJobs.add(jobsList[count])

            }
        }

        jobsList.add(0, Job("S-Startingpoint", 0,Status.COMPLETE, beginningJobs))
        jobsList.add(jobsList.size, Job( "E-Finishingpoint",0,Status.COMPLETE))
        for (job in jobsList) {
            if(job.listOfChildren.isEmpty() && job.jobName!="E-Finishingpoint"){
                job.listOfChildren.add(jobsList.last())
            }
        }
    }

    private fun createJobsList() {
        /*creating a new object based on Task object with added variables relevant to critical
        * path algorithm. */

        println("CriticalPathHandler.createJobList -> Adding Jobs")
        for (task in taskList){
            if(task.status != Status.COMPLETE){
                jobsList.add(Job(task.taskName,task.estDays, task.status))
            }else{
                println("Task is Complete: ${task.taskName} -> Not added to job list")
            }
        }
        println("CriticalPathHandler.createJobList -> All jobs added: \n $jobsList")
    }

    private fun listToSet(): HashSet<Job> {
        return HashSet(jobsList)
    }
}