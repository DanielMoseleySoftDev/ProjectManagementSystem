class CriticalPathHandler {

    var jobsList = ArrayList<Job>()
    var taskList = ArrayList<Task>()
    //var criticalTasks = ArrayList<String>()
    var criticalTasks = arrayListOf<ArrayList<String>>()
    var criticalInfo = ArrayList<String>()

    fun calcCriticalPath(isKotlin : Boolean) : ArrayList<ArrayList<String>>{

        //clear the lists from previous calculations
        jobsList.clear()
        taskList.clear()
        criticalInfo.clear()
        criticalTasks = arrayListOf<ArrayList<String>>()


        flipChildParentNodes(Main.taskHandler.tasks)
        val jobSet = listToSet()
        var returnJobs: Array<Job>

        if (isKotlin){
            //Kotlin
            println("CriticalPathHandler.CalcCriticalPath -> Kotlin Algorithm")
            returnJobs =  CriticalPathKotlin.calculateCriticalPath(jobSet)


        }else{
            //Scala
            println("CriticalPathHandler.CalcCriticalPath -> Scala Algorithm")
            val tempJob = Job("ERROR 6543",0,Status.NO_STATUS)
            returnJobs = arrayOf(tempJob)
        }

        toStringArray(returnJobs)
        criticalTasks.add(criticalInfo)

        return criticalTasks

    }

    private fun toStringArray(returnJobs : Array<Job>) {
        println("Critical Tasks -> why we printing everything?")


        for (job in returnJobs){
            if(job.jobStatus != Status.COMPLETE){
                var jobInfo = ArrayList<String>()
                jobInfo.add(job.jobName)
                val slack = job.lateFinish-job.earlyFinish
                jobInfo.add(slack.toString())
                //criticalTasks.add(job.jobName)
                criticalTasks.add(jobInfo)

                println(job.jobName)
            }
        }



    }

    fun flipChildParentNodes(tasks: ArrayList<Task>){

        //Makes the taskList and jobsList the same size by removing completed tasks
        var incompleteTasks = ArrayList<Task>()
        for (task in tasks) {
            if(task.status != Status.COMPLETE) {
                incompleteTasks.add(task)
            }
        }
        taskList.addAll(incompleteTasks)
        //println(taskList)
        createJobsList()


        //println(jobsList)
        var count = 0
        for (i in taskList){
            println("Looking at ${i.taskName}")

            var countB = 0
            for (j in taskList){
                //var countB = 0
                println("Looking at ${i.taskName} -> ${j.taskName}")
                for (preReq in j.preReqTasks){

                    if (preReq.taskName == i.taskName){
                        //println(jobsList[count].listOfChildren.size)
                        println("found a child ${jobsList[countB].jobName}")

                        jobsList[count].listOfChildren.add(jobsList[countB])

                    }
                    //countB++
                }
                countB++

            }
            count++
            //println(jobsList[count].listOfParents)
        }
        println(jobsList[0].listOfChildren)
        addStartAndEnd()
    }

    private fun addStartAndEnd(){
        var beginningJobs = ArrayList<Job>()
       // var finishingJobs = ArrayList<Job>()
        for((count, task) in taskList.withIndex()){
            if(task.preReqTasks.isEmpty()){
                beginningJobs.add(jobsList[count])

            }
        }
        jobsList.add(0, Job("START", 0,Status.COMPLETE, beginningJobs))
        jobsList.add(jobsList.size, Job( "END",0,Status.COMPLETE))
        for ((count, job) in jobsList.withIndex()) {
            if(job.listOfChildren.isEmpty() && job.jobName!="END"){
                job.listOfChildren.add(jobsList.last())
            }
        }

        //jobsList.add(jobsList.size, Job( "END",0, finishingJobs))

    }

    private fun createJobsList() {
        //TODO("Not yet implemented")
        println("CriticalPathHandler.createJobList -> Adding Jobs")

        for (task in taskList){
            if(task.status != Status.COMPLETE){
                jobsList.add(Job(task.taskName,task.estDays, task.status))
            }else{
                println("Task is Complete: ${task.taskName} -> Not added to job list")
            }
        }
        println("riticalPathHandler.createJobList -> All jobs added: \n $jobsList")
    }

    private fun listToSet(): HashSet<Job> {
        return HashSet(jobsList)
    }


}