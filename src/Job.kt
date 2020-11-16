import java.time.Duration

class Job(val jobName: String, val duration: Int, var listOfChildren: ArrayList<Job> = ArrayList()) {


    var criticalDuration = 0
    var earlyStart = 0
    var earlyFinish = 0
    var lateStart = 0
    var lateFinish = 0
    var criticalStatus = IsCritical.NO

    fun setLatest(maxDuration: Int){
        lateStart = maxDuration - criticalDuration
        lateFinish = lateStart + duration

    }

}

enum class IsCritical{

    YES,NO
}