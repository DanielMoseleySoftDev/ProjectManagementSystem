class CriticalPathHandler {

    var jobsList = ArrayList<Job>()
    var taskList = ArrayList<Task>()

    fun flipChildParentNodes(tasks: ArrayList<Task>){
        println("hello")

        taskList.addAll(tasks)
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

    }

    private fun createJobsList() {
        //TODO("Not yet implemented")

        for (task in taskList){
           jobsList.add(Job(task.taskName,task.estDays))


        }
    }

    fun addJobs(tasks: ArrayList<Task>) {
        for (task in tasks) {

        }
    }
}