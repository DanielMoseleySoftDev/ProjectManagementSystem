import java.util.*


class TaskHandler() {

    var tasks = mutableListOf<Task>()

    fun createTask(taskName: String = "Task",  estDays: Int,  teamAssigned: Team,  taskDescription: String){

        println("TaskHandler.createTask started")

        var estStartDate = calculateEstStartDate()
        //var estStartDate : Calendar = Calenda
        tasks.add(Task(taskName, estDays, estStartDate ,teamAssigned, taskDescription))
        println("Task created -> \n"+tasks)
    }

    private fun calculateEstStartDate() : Calendar{
        TODO("Not yet implemented")
        var calendar = Calendar.getInstance()
        calendar.set(2020,11,5)
        return calendar
    }

    init{

    }
}