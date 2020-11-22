import java.util

import scala.collection.JavaConverters._
import scala.collection.mutable
import scala.collection.mutable.HashSet


class CriticalPathScala {
  def calculateCriticalPath(jobs: util.HashSet[Job]): Array[Job] ={
    println("SCALA TEST!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!")
    val completed = new HashSet[Job]()
    val remaining = new HashSet[Job]()
    jobs.forEach(job => remaining.add(job))


    def backflow(): HashSet[Job] ={
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
        backflow()
      }

    }









    val output = new Array[Job](2)
    output
  }
}
