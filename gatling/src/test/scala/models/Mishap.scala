package models

import models.MishapPriority.MishapPriority

class Mishap {
  private var priority: MishapPriority = null

  def getPriority: MishapPriority = this.priority

  def setPriority(priority: MishapPriority): Unit = {
    this.priority = priority
  }
}
