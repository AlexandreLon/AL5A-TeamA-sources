package fr.polytech.models.maintenance;

import java.util.Date;

public class MaintenanceCreation extends Maintenance {
    private Date desiredDate;

    public Date getDesiredDate() {
        return desiredDate;
    }

    public void setDesiredDate(Date desiredDate) {
        this.desiredDate = desiredDate;
    }
}
