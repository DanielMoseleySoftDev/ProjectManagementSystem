class CriticalPathKotlin {
    var allTasks = HashSet<Task>()

}

class KotlinTask(val criticalCost: Int?) {
    val cost : Int = 0;
    val name : String = "";

    var dependencies = HashSet<Task>()

    fun createKotlinTask(name : String, cost: Int, dependencies : ArrayList<String> ) {

    }
}