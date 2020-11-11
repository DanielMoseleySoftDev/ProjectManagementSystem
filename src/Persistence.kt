import java.io.*

class Persistence {
    val path = System.getProperty("user.dir")
    var file = "$path/Projects.txt"

    init {
        print(file)
    }

    fun saveToFile(list : ArrayList<Project>) {
        println("-------------------------\n " +
                "Save Started - File path: \n $file " +
                "\n-------------------------")
        try {
            ObjectOutputStream(FileOutputStream(file)).use{ it -> it.writeObject(list)} //Lambda used to save to file
        }catch (ioe:IOException){
            println("Error: Unable to save - IO Exception")
        }

        println("List of Projects Saved to file\n")

    }

    fun loadFromFile(): ArrayList<Project> {


        println("-------------------------\n " +
                "Load Started - File path: \n $file " +
                "\n-------------------------")

        val nullList = ArrayList<Project>()
        var projectList : ArrayList<Project>

        try {
            //uses lambda to de-serialize the file into array of projects
            projectList = ObjectInputStream(FileInputStream(file)).use {it -> it.readObject() as ArrayList<Project>}
            println("Projects Loaded into memory")

        }catch (ioe : IOException){
            println("Error: Could not load from file:\n $file")
            return nullList
        }catch (c: ClassNotFoundException){
            println("Error: Class not found to cast")
            return nullList
        }


        return projectList
    }
}