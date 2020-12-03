import java.io.Serializable
import kotlin.collections.ArrayList

data class Task(val taskName: String = "Task", var estDays: Int,  var teamAssigned: Team,var taskDescription: String, var preReqTasks: ArrayList<Task>) : Serializable {

    var status = Status.NO_STATUS
}
