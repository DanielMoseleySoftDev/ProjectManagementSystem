import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

import kotlin.collections.ArrayList
import kotlin.collections.forEach
import kotlin.collections.last
import kotlin.collections.plus

class ProjectHandler() {

    //Variables
    val p = Persistence()
    var projects = ArrayList<Project>()
    //default no project selected
    var currentProject = Project(
        "No Project Selected",
        projectStartDate = Date(),
        projectDescription = "No project selected"
    )
    var projectDuration = 0


    init {
        //load projects into memory
        loadProjects()
    }

    fun calculateCriticalPath(isKotlin: Boolean){
        //Checks if there any task in the project before calculating critical path
        if(Main.taskHandler.tasks.isNotEmpty()){
            val criticalInformation = Main.criticalPathHandler.calcCriticalPath(isKotlin)
            try {
                //Last element of ArrayList is the project calculated duration
                projectDuration = criticalInformation.last()[1].toInt()

            }catch (ex : Exception){
                println("ProjectHandler.calculateCriticalPath -> Exception: Unable to cast duration")
                projectDuration = 0
            }
            Main.taskHandler.setCriticalInfo(criticalInformation)

        }else{
            println("ProjectHandler.calculateCriticalPath -> No tasks to calculate")
            projectDuration = 0
        }

    }

    fun calculateEndDate(): String{
        val endDate = Calendar.getInstance()        //gets today
        endDate.add(Calendar.DATE,projectDuration)  //Adds project duration to get end date
        val sdf = SimpleDateFormat("dd-MM-yyyy")

        return sdf.format(endDate.time)
    }

    fun calculateDaysLeft(): String{
        return projectDuration.toString()
    }

    fun createProject(projectName: String, projectStartDate: Date, projectDescription: String) {
        println("ProjectHandler.createProject (Kt)- creating started")
        projects.add(Project(projectName, projectStartDate, projectDescription = projectDescription))
        println("ProjectHandler.createProject (Kt)- created")
        //projects would then be saved to file
        saveProjects()

        selectProject(projects.last().projectName)
    }

    fun deleteProject(projectIndex: Int) : Boolean{

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
        try {
            projects = p.loadFromFile()
            println(projects)
            println("-------------------------\n")
        }catch (iob : IndexOutOfBoundsException ){
            println("Projects file was empty on load")
            println("-------------------------\n")
        }
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
        Main.taskHandler.tasks = currentProject.tasks
        println("ProjectHandler.selectProject -> Main.taskHandler.tasks set to currentProjects tasks:\n" + Main.taskHandler.tasks)
    }

    fun projectToString(projectName: String) : String{
        val selectedProject = findProject(projectName)
        val sdf = SimpleDateFormat("dd-MM-yyyy")

        return "\nProject Name: ${selectedProject.projectName} \n" +
                "Start Date: ${sdf.format(selectedProject.projectStartDate)} \n \n" +
                "Description: \n${selectedProject.projectDescription} \n "
    }
}

