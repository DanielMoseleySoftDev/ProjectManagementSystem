import java.io.FileWriter
import java.io.Serializable
import java.util.Date

data class Project(var projectName: String = "Project", var projectStartDate: Date, var projectEndDate: Date? = null, var projectDescription: String) : Serializable{
    //val Teams = mutableListOf<Team>();
    var Tasks = ArrayList<Task>()
    init {

    }



}
