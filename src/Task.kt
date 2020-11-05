import java.util.Date

data class Task(val taskName: String = "Task", val estStartDate: Date, val estDays: Int , var teamAssigned: Team,var taskDescription: String) {
    private val taskID: Int = 0
    var delayTime: Int = 0
    var estFinishDate: Date? = null
    //Cannot be initalised as Null, so set as estimates until changed
    var actualStartDate: Date = estStartDate
    var actualFinishDate: Date? = null

    init {
        //set taskID to number of tasks + 1 ??
        //estFinishDate = estStartDate. + estDays
        // or smth like that^^^^
    }
}