import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.forEach
import kotlin.collections.last
import kotlin.collections.plus

class ProjectHandler() {

    //Variables
    val p = Persistence()
    var projects = ArrayList<Project>()
    var currentProject = Project(
        "No Project Selected",
        projectStartDate = Date(),
        projectDescription = "No project selected"
    )
    var projectDuration = 0


    init {
        loadProjects()
    }

    fun calculateCriticalPath(isKotlin: Boolean){
        val criticalInformation = Main.criticalPathHandler.calcCriticalPath(true)
    }


    fun createProject(projectName: String, projectStartDate: Date, projectDescription: String) {
        println("ProjectHandler.createProject (Kt)- creating started")
        projects.add(Project(projectName, projectStartDate, projectDescription = projectDescription))
        println("ProjectHandler.createProject (Kt)- created")
        //projects would then be save to file
        saveProjects()

        selectProject(projects.last().projectName)



    }

    fun deleteProject(projectIndex: Int) : Boolean{
        //projects.removeAt(projectIndex)
        //saveProjects()

        return if(projects[projectIndex]==currentProject){
            projects.removeAt(projectIndex)
            saveProjects()
            currentProject = Project(
                "No Project Selected",
                projectStartDate = Date(),
                projectDescription = "No project selected"
            )
            Main.taskHandler.tasks.clear()
            false
        }else{
            projects.removeAt(projectIndex)
            saveProjects()
            currentProject.projectName != "No Project Selected"

        }
        //currentProject = Project("No Project Selected",projectStartDate = Date(),projectDescription = "No project selected")
        //Main.taskHandler.tasks.clear()
    }
    fun findProject(projectName: String) : Project{
        projects.forEach { project ->
            if(projectName == project.projectName){
                return project
            }
        }
        return projects[0]
    }

    fun loadProjects(){
        projects = p.loadFromFile()
        println(projects)
        println("Project1 task: " + projects[0].Tasks + "\n")

    }

    fun saveProjects(){

        p.saveToFile(projects)
        println(projects + "\n")



    }

    fun selectProject(selectedProjectStr: String){
        //Setting current project to the one chosen from OpenProjectForm
        var projectFound = false
        for (i in projects){

            if (i.projectName == selectedProjectStr){
                currentProject = i
                projectFound = true
                println("ProjectHandler.selectProject -> Selected project found ->set as current project")
                setProjectTasks()
                break
            }

        }

        //print to console if no project is found
        if (!projectFound){
            println("ProjectHandler.selectProject -> Selected Project not found")
        }
    }

    private fun setProjectTasks(){
        Main.taskHandler.tasks = currentProject.Tasks
        println("ProjectHandler.selectProject -> Main.taskHandler.tasks set to currentProjects tasks:\n" + Main.taskHandler.tasks)

    }
}

