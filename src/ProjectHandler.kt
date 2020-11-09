import java.io.FileWriter
import java.util.Date

class ProjectHandler() {

    //Variables
    val p = Persistence()
    var projects = ArrayList<Project>()
    var currentProject = Project("No Project Selected",projectStartDate = Date(),projectDescription = "No project selected")

    init {
        loadProjects()
    }

    fun createProject(projectName: String, projectStartDate: Date, projectDescription: String) {
        println("ProjectHandler.createProject (Kt)- creating started")
        projects.add(Project(projectName, projectStartDate, projectDescription = projectDescription))
        println("ProjectHandler.createProject (Kt)- created")
        //projects would then be save to db

    }

    fun loadProjects(){
        projects = p.loadFromFile()
        println(projects)
        System.out.println("Project1 task: "+ projects[0].Tasks +"\n")

    }

}

