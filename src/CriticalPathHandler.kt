class CriticalPathHandler {

    var jobsList = ArrayList<Job>()
    var taskList = ArrayList<Task>()
    var criticalTasks = arrayListOf<ArrayList<String>>()
    var criticalInfo = ArrayList<String>()

    fun calcCriticalPath(isKotlin : Boolean) : ArrayList<ArrayList<String>>{

        //clear the lists from previous calculations
        jobsList.clear()
        taskList.clear()
        criticalInfo.clear()
        criticalTasks = arrayListOf()

        flipChildParentNodes(Main.taskHandler.tasks)
        val jobSet = listToSet()
        val returnJobs: Array<Job>
        if (jobsList.isEmpty()){
            return criticalTasks
        }

        if (isKotlin){
            //Kotlin
            println("CriticalPathHandler.CalcCriticalPath -> Kotlin Algorithm")
            returnJobs =  CriticalPathKotlin.calculateCriticalPath(jobSet)
        }else{
            //Scala
            println("CriticalPathHandler.CalcCriticalPath -> Scala Algorithm")
            returnJobs = Main.scalaHandler.calculateCriticalPath(jobSet)
        }

        toStringArray(returnJobs)
        criticalTasks.add(criticalInfo)
        return criticalTasks

    }

    private fun toStringArray(returnJobs : Array<Job>) {
        for (job in returnJobs){
            if(job.jobStatus != Status.COMPLETE){
                val jobInfo = ArrayList<String>()
                jobInfo.add(job.jobName)
                val slack = job.lateFinish-job.earlyFinish
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
        val beginningJobs = ArrayList<Job>()
        for((count, task) in taskList.withIndex()){
            if(task.preReqTasks.isEmpty()){
                beginningJobs.add(jobsList[count])

            }
        }
        jobsList.add(0, Job("START", 0,Status.COMPLETE, beginningJobs))
        jobsList.add(jobsList.size, Job( "END",0,Status.COMPLETE))
        for (job in jobsList) {
            if(job.listOfChildren.isEmpty() && job.jobName!="END"){
                job.listOfChildren.add(jobsList.last())
            }
        }

    }

    private fun createJobsList() {
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