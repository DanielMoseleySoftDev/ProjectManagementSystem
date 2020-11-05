import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

class Persistence {
    val path = System.getProperty("user.dir")
    var file = "$path/test.txt"

    init {
        print(file)
    }

    fun saveToFile(list : Project) {
        ObjectOutputStream(FileOutputStream(file)).use{ it -> it.writeObject(list)}
    }

    fun loadFromFile(): Object {
        ObjectInputStream(FileInputStream(file)).use { it ->
            //Read the family back from the file
            val list= it.readObject()
            val listOfProjects = mutableListOf<Object>()
            //Cast it back into a MutableList
            println(list)
        }
        return Object();
    }
}