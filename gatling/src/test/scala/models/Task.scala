package models

import java.util.Date

import models.TaskStatus.TaskStatus
import models.TaskType.TaskType

class Task {
  private var id = 0L
  private var name: String = null
  private var `type` : TaskType = null
  private var status : TaskStatus = null
  private var creationDate : Date = null
  private var realizationDate : Date = null

  def getCreationDate: Date = this.creationDate

  def creationDate(creationDate: Date) = {
    this.creationDate = creationDate : Date
  }

  def getId: Long = this.id

  def setId(id: Long): Unit = {
    this.id = id
  }

  def getName: String = this.name

  def setName(name: String): Unit = {
    this.name = name
  }

  def getType: TaskType = this.`type`

  def setType(`type`: TaskType): Unit = {
    this.`type` = `type`
  }

  def getStatus: TaskStatus = this.status

  def setStatus(status: TaskStatus): Unit = {
    this.status = status
  }


  def getRealizationDate: Date = realizationDate

  def setRealizationDate(realizationDate: Date): Unit = {
    this.realizationDate = realizationDate
  }
}
