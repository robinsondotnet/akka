/**
 * Copyright (C) 2016-2018 Lightbend Inc. <http://www.lightbend.com/>
 */
package akka.actor.typed.internal

import java.util.LinkedList

import scala.concurrent.ExecutionContextExecutor

class ControlledExecutor extends ExecutionContextExecutor {
  private val tasks = new LinkedList[Runnable]

  def queueSize: Int = tasks.size()

  def runOne(): Unit = tasks.pop().run()

  def runAll(): Unit = while (!tasks.isEmpty()) runOne()

  def execute(task: Runnable): Unit = {
    tasks.add(task)
  }

  def reportFailure(cause: Throwable): Unit = {
    cause.printStackTrace()
  }
}
