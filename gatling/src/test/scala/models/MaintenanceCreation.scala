package models

import java.util.Date

class MaintenanceCreation {
  private var desiredDate : Date = null

  def getDesiredDate: Date = desiredDate

  def setDesiredDate(desiredDate: Date): Unit = {
    this.desiredDate = desiredDate
  }
}
