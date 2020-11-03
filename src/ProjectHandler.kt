import java.util.Date

class ProjectHandler() {
    //Variables
    var projects = mutableListOf<Project>()

    fun createProject(projectName: String, projectStartDate: Date, projectEndDate: Date) {
        projects.add(Project(projectName, projectStartDate, projectEndDate))
        //projects would then be save to db
    }
}