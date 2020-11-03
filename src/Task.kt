import java.util.Date

data class Task(val taskName: String = "Task", val estStartDate: Date, val estFinishDate: Date, var teamAssigned: Team, var taskDescription: String) {
    private val taskID: Int = 0
    var delayTime: Int = 0
    //Cannot be initalised as Null, so set as estimates until changed
    var actualStartDate: Date = estStartDate
    var actualFinishDate: Date = estFinishDate

    init {
        //set taskID to number of tasks + 1 ??

    }
}