import java.util

import scala.annotation.tailrec
import scala.collection.JavaConverters._
import scala.collection.mutable.HashSet


class CriticalPathScala {

  def calcMaxDuration(jobs: util.HashSet[Job]):Int = {

    var max = -1

    // In general, we use higher order functions with an anonymous function to loop through data structures
    jobs.forEach(job => if(job.getCriticalDuration > max) max = job.getCriticalDuration)

    val maxDuration = max
    println("Critical path length (Duration): " + maxDuration)

    jobs.forEach(job => job.setLatest(maxDuration))

    maxDuration

  }

  def setStartingJobs(jobs: util.HashSet[Job]): HashSet[Job] = {

    val remaining = new HashSet[Job]
    jobs.forEach(job => remaining.add(job))

    jobs.forEach(job => {
      job.getListOfChildren.forEach(child => remaining.remove(child))
    })
    remaining
  }

  def setEarly(startingTask: Job): Unit = {

    // An encapsulated function - a function that is only available to the function it is declared in
    def inLoopFunction(completionTime: Int,job: Job): Unit ={
      if(completionTime >= job.getEarlyStart){
        job.setEarlyStart(completionTime)
        job.setEarlyFinish(completionTime + job.getDuration)
      }
      setEarly(job)
    }

    val completionTime = startingTask.getEarlyFinish
    startingTask.getListOfChildren.forEach(child => inLoopFunction(completionTime, child))

  }

  def calcEarly(startingJobs: HashSet[Job]) = {

    startingJobs.foreach(job => {
      job.setEarlyStart(0)
      job.setEarlyFinish(job.getDuration)
      setEarly(job)
    })

  }

  def calculateCriticalPath(jobs: util.HashSet[Job]): Array[Job] ={

    val completed = new HashSet[Job]()
    val remaining = new HashSet[Job]()
    jobs.forEach(job => remaining.add(job))

    // This recursive back-flow algorithm prevents us from needing a nested while loop
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

      // If remaining isn't empty recursively call the function
      if (remaining.nonEmpty) {
        backFlow()
      }

    }

    // Initial function call
    backFlow()

    val maxDuration = calcMaxDuration(jobs)
    val initialNodes : HashSet[Job] = setStartingJobs(jobs)
    calcEarly(initialNodes)

    val completedArray : Array[Job] = completed.toArray
    println("CriticalPathScala.calculateCriticalPath.maxDuration -> " + maxDuration)
    Main.criticalPathHandler.getCriticalInfo.add("Remaining critical duration: ")
    Main.criticalPathHandler.getCriticalInfo.add(maxDuration.toString)

    completedArray

  }
}