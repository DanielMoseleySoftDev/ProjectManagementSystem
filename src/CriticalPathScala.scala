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
      var progess = false
      val iterator = remaining.iterator

      while(iterator.hasNext) {
        val job : Job = iterator.next()
        //if (job.getListOfChildren.forEach(child => completed.contains(child)))
        val childHashSet = new mutable.LinkedHashSet[Job]
        childHashSet.add(job.getListOfChildren.forEach())
        job.getListOfChildren.forEach(child => if(completed.contains(child)) {

        })
      }


      if(remaining.nonEmpty){
        backflow()//Todo edit input to remaining minus this iteration
      }
      completed
    }

    backflow()







    val output = new Array[Job](2)
    output
  }
}
