import Maintenance from "./Maintenance";

export default class  MaintenanceCreationModel extends Maintenance {

	constructor(id, name, type, status, desiredDate) {
		super(id, name, type, status);
		this.desiredDate = desiredDate;
	}

}