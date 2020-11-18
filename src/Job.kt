import java.time.Duration

class Job(val jobName: String, val duration: Int, val jobStatus: Status,var listOfChildren: ArrayList<Job> = ArrayList()) {


    var criticalDuration = 0
    var earlyStart = 0
    var earlyFinish = 0
    var lateStart = 0
    var lateFinish = 0
    //val jobStatus = Status.NO_STATUS

    fun setLatest(maxDuration: Int){
        lateStart = maxDuration - criticalDuration
        lateFinish = lateStart + duration

    }

}

