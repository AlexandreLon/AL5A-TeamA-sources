export default class  Maintenance {

	constructor(id, name, type, status, desiredDate = new Date()) {
		this.id = id;
		this.name = name;
		this.type = type;
		this.status = status;
		this.desiredDate = desiredDate;
	}

}