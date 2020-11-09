import java.io.Serializable
import java.util.*
import kotlin.collections.ArrayList

data class Task(val taskName: String = "Task", val estDays: Int, val estStartDate:  Calendar, var teamAssigned: Team,var taskDescription: String) : Serializable {
    private val taskID: Int = 0

    var preReqTasks = ArrayList<Task>()

    var delayTime: Int = 0
    var estFinishDate: Date? = null
    //Cannot be initalised as Null, so set as estimates until changed
    //var actualStartDate: Date = estStartDate
    var actualFinishDate: Date? = null

    init {
        //set taskID to number of tasks + 1 ??
        //estFinishDate = estStartDate. + estDays
        // or smth like that^^^^

        //if(preReq != null){
       //     preReqTasks = preReq as ArrayList<Task>
        //}
    }
}