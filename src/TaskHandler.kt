import javax.swing.table.DefaultTableModel
import kotlin.collections.ArrayList


class TaskHandler() {

    var tasks = ArrayList<Task>()
    var critInfo = arrayListOf<ArrayList<String>>()

    init{

    }

    fun setCriticalInfo(critInfo:ArrayList<ArrayList<String>>){

        this.critInfo = critInfo
        println("TaskHandler.setCriticalInfo -> 2D array of info:\n ${this.critInfo}")
    }

    fun updateTaskTables(
        activeTable: DefaultTableModel,
        waitingTable: DefaultTableModel,
        completeTable: DefaultTableModel
    ){
        println("TaskHandler.updateTaskTables -> update started")
        println("TaskHandler.updateTaskTables -> clearing tables")
        activeTable.setNumRows(0)
        waitingTable.setNumRows(0)
        completeTable.setNumRows(0)

        println("TaskHandler.updateTaskTables -> adding tasks to rows")
        if(tasks.isNotEmpty()){
            for(task in tasks){

                val checkResult = checkTaskStatus(task)
                if(checkResult == "ACTIVE"){
                    activeTable.addRow(
                        arrayOf<Any>(
                            task.taskName, task.teamAssigned.teamName, task.estDays, getSlack(task.taskName), isCritical(task.taskName))
                    )
                }else if(checkResult == "WAITING"){
                    waitingTable.addRow(
                        arrayOf<Any>(
                            task.taskName, task.teamAssigned.teamName, task.estDays, getPreReqToString(task), getSlack(task.taskName), isCritical(task.taskName))
                    )

                }else if(checkResult == "COMPLETE"){
                    completeTable.addRow(arrayOf<Any>(task.taskName, task.teamAssigned.teamName, task.estDays))
                }

            }

        }

        println("TaskHandler.updateTaskTables -> tables updated")

    }

    private fun isCritical(name : String): String {
        //Checks to see if the task is critical
        var count = 0
        for (job in critInfo){
            if (name == job[0] && job[1] =="0"){
                //returns a tick
                return "\u2713"
            }
            count++
        }
        return ""
    }

    private fun getSlack(name: String): String {
        //returns how many days a job can be delayed before delaying
        // the whole project.
        println(critInfo)
        for (job in critInfo){
            if (name == job[0]){
                return job[1]
            }
        }
        return ""
    }

    fun getPreReqToString(task: Task) : String{
        //returns pre-requisites as a string
        println("TaskHandler.getPreReqToString")
        var taskStr =""
        for(i in task.preReqTasks){
            taskStr += i.taskName+", "
        }
        return if(taskStr.isNullOrBlank()){
            ""
        }else{
            taskStr
        }
    }

    private fun checkTaskStatus(i: Task): String {
        println("TaskHandler.checkTaskStatus")
        return when (i.status) {        //Uses Lambda
            Status.NO_STATUS -> {

                calculateTaskStatus(i)
                checkTaskStatus(i)      //uses recursion as the status should have been calculated
            }
            Status.ACTIVE -> {
                "ACTIVE"
            }
            Status.WAITING -> {
                "WAITING"
            }
            Status.COMPLETE -> {
                "COMPLETE"
            }
        }
    }

    fun createTask(
        taskName: String = "Task",
        estDays: Int,
        teamAssigned: Team,
        taskDescription: String,
        preReq: String,
        mainFrame: MainGUI
    ){

        println("TaskHandler.createTask -> started")

        val preReqList = getPreReq(preReq)      //gets the pre-req tasks as an ArrayList of Tasks
        tasks.add(Task(taskName, estDays, teamAssigned, taskDescription, preReqList))
        calculateTaskStatus(tasks.last())
        println("Task created -> \n$tasks")

        Main.projectHandler.saveProjects()
        mainFrame.updateTaskPanels()
    }


    fun findTask(taskName: String):Task{
        tasks.forEach { task ->
            if(taskName == task.taskName){
                return task
            }
        }
        return tasks[0]
    }

    fun deleteTask(selectedTaskIndex: Int, selectedTaskName: String, mainFrame: MainGUI) {
        println("TaskHandler.deleteTask -> started")

        tasks.removeAt(selectedTaskIndex)
        removeTaskFromPreReq(selectedTaskName)
        mainFrame.updateTaskPanels()
        Main.projectHandler.saveProjects()

        println("TaskHAndler.deleteTask -> delete end")
    }

    fun completeTask(selectedTask: String, mainFrame: MainGUI){

        val foundTask = findTask(selectedTask)

        foundTask.status = Status.COMPLETE

        println("attempt at completing the task")
        println("found task Status -> ${foundTask.status}")
        println("current project tasks -> "+Main.projectHandler.currentProject.tasks)
        removeTaskFromPreReq(selectedTask)

        Main.projectHandler.saveProjects()

        mainFrame.updateTaskPanels()
    }

    fun taskToString(taskName: String): String{
        val selectedTask = findTask(taskName)
        var preReq =""
        for(t in selectedTask.preReqTasks){
            preReq += t.taskName + ", "
        }

        return "\nTask Name: ${selectedTask.taskName}\n" +
                "Team Assigned: ${selectedTask.teamAssigned.teamName}\n" +
                "Estimated Time: ${selectedTask.estDays} days\n" +
                "Pre-Requisites: ${preReq}\n" +
                "\nDescription: ${selectedTask.taskDescription}"
    }

    fun editTask(taskName: String, newTeam: Team, newDays: Int, newDesc:String){
        val taskToEdit = findTask(taskName)

        taskToEdit.teamAssigned = newTeam
        taskToEdit.estDays = newDays
        taskToEdit.taskDescription = newDesc

        Main.projectHandler.saveProjects()
    }

    private fun removeTaskFromPreReq(selectedTaskName: String) {

        println("TaskHandler.deleteTask.removeFromPreReq -> removing deleted task from pre-req's")
        for (i in tasks){
            for (j in i.preReqTasks){
                if (selectedTaskName == j.taskName){
                    i.preReqTasks.remove(j)
                    break
                }
            }
        }
        //Re-calculating the status of Tasks and updating
        for(i in tasks){
            calculateTaskStatus(i)
        }

        println("TaskHandler.deleteTask.removeFromPreReq -> deleted task from pre-req removed")
    }


    private fun calculateTaskStatus(task: Task) {
        println("TaskHandler.calculateTaskStatus -> Calculating...")

        if(task.preReqTasks.isNotEmpty()){
            for (i in task.preReqTasks){
                if (i.status == Status.WAITING || i.status == Status.ACTIVE){
                    task.status = Status.WAITING
                    println("TaskHandler.calculateTaskStatus -> Status=WAITING")
                    break
                }
                else if(i.status == Status.COMPLETE){
                    task.status = Status.ACTIVE

                    println("TaskHandler.calculateTaskStatus -> Current Status=ACTIVE")
                }
                else{
                    println("ERROR: TaskHandler.calculateTaskStatus -> Pre-Req doesn't have status")
                }
            }
        }
        else{
            if(task.status==Status.COMPLETE){
                println("TaskHandler.calculateTaskStatus -> Task is Complete")
            }else{
                task.status = Status.ACTIVE
                println("TaskHandler.calculateTaskStatus -> Status=ACTIVE")
            }
        }
    }

    private fun getPreReq(preReq: String): ArrayList<Task> {
        println("taskHandler.getPreReq -> getting pre-req tasks")
        val returnList = ArrayList<Task>()
        return if (preReq.isEmpty()) {
            returnList
        } else {
            //split the string
            val temp: Array<String> = preReq.split(", ".toRegex()).toTypedArray()
            val thTask = Main.taskHandler.tasks

            //loops through list of user input and checks them against each task in task handler
            for (i in temp.indices) {
                for (j in thTask.indices) {
                    if (temp[i] == thTask[j].taskName) {
                        returnList.add(thTask[j])
                        println("Added Pre Req Task: " + thTask[j])
                    }
                }
            }
            returnList
        }
    }
}