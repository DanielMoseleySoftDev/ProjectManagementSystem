import java.util.Date

data class Project(var projectName: String = "Project", var projectStartDate: Date, var projectEndDate: Date) {
    val Teams = mutableListOf<Team>();
    val Tasks = mutableListOf<Task>();
    init {

    }
}
