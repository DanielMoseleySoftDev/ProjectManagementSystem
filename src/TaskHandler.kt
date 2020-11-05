import java.util.Date
class TaskHandler() {

    var tasks = mutableListOf<Task>()

    fun createTask(taskName: String = "Task", estStartDate: Date,  estDays: Int,  teamAssigned: Team,  taskDescription: String){
        tasks.add(Task(taskName, estStartDate, estDays, teamAssigned, taskDescription))
        println("testing123")
    }
    init{

    }
}