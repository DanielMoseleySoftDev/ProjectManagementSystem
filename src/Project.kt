import java.io.Serializable
import java.util.Date

data class Project(var projectName: String = "Project", var projectStartDate: Date, var projectEndDate: Date? = null, var projectDescription: String) : Serializable{
    var tasks = ArrayList<Task>()
}
