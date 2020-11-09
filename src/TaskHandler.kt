import java.util.*
import javax.swing.table.DefaultTableModel


class TaskHandler() {

    var tasks = mutableListOf<Task>()

    init{

    }

    fun updateTaskTables(
        activeTable: DefaultTableModel,
        waitingTable: DefaultTableModel,
        completeTable: DefaultTableModel
    ){

        activeTable.setNumRows(0)
        waitingTable.setNumRows(0)
        completeTable.setNumRows(0)

        if(tasks.isNotEmpty()){
            for(i in tasks){

                val checkResult = checkTaskStatus(i)
                if(checkResult == "ACTIVE"){
                    activeTable.addRow(arrayOf<Any>(i.taskName, i.teamAssigned.teamName,"NA","NA","NA"))
                }else if(checkResult == "WAITING"){
                    waitingTable.addRow(arrayOf<Any>(i.taskName, i.teamAssigned.teamName,i.estDays,"NA","?"))

                }else if(checkResult == "COMPLETE"){
                    completeTable.addRow(arrayOf<Any>(i.taskName, i.teamAssigned.teamName,"NA","NA","NA"))
                }

            }

        }



    }

    private fun checkTaskStatus(i: Task): String {
        return when (i.status) {        //Uses Lambda
            TaskStatus.NO_STATUS -> {

                calculateTaskStatus(i)
                checkTaskStatus(i)      //uses recursion as the status should have been calculated
            }
            TaskStatus.ACTIVE -> {
                "ACTIVE"
            }
            TaskStatus.WAITING -> {
                "WAITING"
            }
            TaskStatus.COMPLETE -> {
                "COMPLETE"
            }
            else -> {
                "ERROR"                 //the status could not be checked
            }
        }
    }


    fun createTask(taskName: String = "Task", estDays: Int, teamAssigned: Team, taskDescription: String){
        //TODO::: MAKE SURE TASK STATUS IS CALCULATED HERE IMMEDIATELY AFTER OBJECT CREATION
        println("TaskHandler.createTask started")

        var estStartDate = calculateEstStartDate()
        //var estStartDate : Calendar = Calenda
        tasks.add(Task(taskName, estDays, estStartDate, teamAssigned, taskDescription))
        println("Task created -> \n" + tasks)
    }

    private fun calculateEstStartDate() : Calendar{
        TODO("Not yet implemented")
        var calendar = Calendar.getInstance()
        calendar.set(2020, 11, 5)
        return calendar
    }

    private fun calculateTaskStatus(task: Task) {
        println("TaskHandler.calculateTaskStatus -> Calculating...")

        if(task.preReqTasks.isNotEmpty()){
            for (i in task.preReqTasks){
                if (i.status == TaskStatus.WAITING || i.status == TaskStatus.ACTIVE){
                    task.status = TaskStatus.WAITING
                    println("TaskHandler.calculateTaskStatus -> Status=WAITING")
                    break
                }
                else if(i.status == TaskStatus.COMPLETE){
                    task.status = TaskStatus.ACTIVE
                    println("TaskHandler.calculateTaskStatus -> Current Status=ACTIVE")
                }
                else{
                    println("ERROR: TaskHandler.calculateTaskStatus -> Pre-Req doesn't have status")
                }
            }

        }
        else{
            task.status = TaskStatus.ACTIVE
            println("TaskHandler.calculateTaskStatus -> Status=ACTIVE")
        }


    }
}