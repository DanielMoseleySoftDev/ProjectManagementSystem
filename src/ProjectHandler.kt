import java.io.FileWriter
import java.util.Date

class ProjectHandler() {

    //Variables
    var projects = mutableListOf<Project>()

    fun createProject(projectName: String, projectStartDate: Date, projectDescription: String) {
        println("ProjectHandler.createProject (Kt)- creating started")
        projects.add(Project(projectName, projectStartDate, projectDescription = projectDescription))
        println("ProjectHandler.createProject (Kt)- created")
        //projects would then be save to db

    }

    /*fun WriteToFile(projects: MutableList<Project>) = try{
        var fo= FileWriter("test.txt")

        fo.write(projects)
        fo.close()
    }catch (ex:Exception){
        println(ex.message)
    }

*/
}

