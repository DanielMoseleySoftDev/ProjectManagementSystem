import java.util

import scala.annotation.tailrec
import scala.collection.JavaConverters._
import scala.collection.mutable
import scala.collection.mutable.HashSet


class CriticalPathScala {

  def calcMaxDuration(jobs: util.HashSet[Job]):Int = {

    var max = -1

    jobs.forEach(job => if(job.getCriticalDuration > max) max = job.getCriticalDuration)

    val maxDuration = max
    println("Critical path length (Duration): " + maxDuration)

    jobs.forEach(job => job.setLatest(maxDuration))

    maxDuration

  }

  def initials(jobs: util.HashSet[Job]): HashSet[Job] = {

    val remaining = new HashSet[Job]
    jobs.forEach(job => remaining.add(job))

    jobs.forEach(job => {
      job.getListOfChildren.forEach(child => remaining.remove(child))
    })
    remaining
  }

  def setEarly(initial: Job): Unit = {

    def inLoopFunction(completionTime: Int,job: Job): Unit ={
      if(completionTime >= job.getEarlyStart){
        job.setEarlyStart(completionTime)
        job.setEarlyFinish(completionTime + job.getDuration)
      }
      setEarly(job)
    }

    val completionTime = initial.getEarlyFinish

    initial.getListOfChildren.forEach(job => inLoopFunction(completionTime,job))

  }

  def calculateEarly(initials: HashSet[Job]) = {

    initials.foreach(initial => {
      initial.setEarlyStart(0)
      initial.setEarlyFinish(initial.getDuration)
      setEarly(initial)
    })

  }

  def calculateCriticalPath(jobs: util.HashSet[Job]): Array[Job] ={
    println("SCALA TEST!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!")



    val completed = new HashSet[Job]()
    val remaining = new HashSet[Job]()
    jobs.forEach(job => remaining.add(job))


    @tailrec
    def backFlow(): Unit ={
      var progress = false
      val iterator = remaining.iterator

      while(iterator.hasNext) {
        val job : Job = iterator.next()
        val childHashSet = new HashSet[Job]
        job.getListOfChildren.forEach(child => childHashSet.add(child))
        if(childHashSet.subsetOf(completed)) {
          var critical = 0
          childHashSet.foreach(child => if(child.getCriticalDuration > critical) {
            critical = child.getCriticalDuration
          })
          job.setCriticalDuration(critical + job.getDuration)
          completed.add(job)

          remaining.remove(job)
          progress = true
          }
        }

      if(!progress) throw new RuntimeException ("There is a dependency cycle, cannot calculate critical path!")
      //if remaining isn't empty recursively call the function
      if (remaining.nonEmpty) {
        backFlow()
      }

    }

    backFlow()

    val maxDuration = calcMaxDuration(jobs)
    val initialNodes : HashSet[Job] = initials(jobs)
    calculateEarly(initialNodes)


    val ret : Array[Job] = completed.toArray
    Main.criticalPathHandler.getCriticalInfo.add("Remaining critical duration: ")
    Main.criticalPathHandler.getCriticalInfo.add(maxDuration.toString)

    //val output = new Array[Job](2)
    ret

  }
}
