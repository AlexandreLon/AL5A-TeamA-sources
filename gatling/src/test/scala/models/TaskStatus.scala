package models

object TaskStatus extends Enumeration {
  type TaskStatus = Value
  val WAITING_FOR_BID_CLOSURE, PENDING, FINISHED, ABORTED = Value
}