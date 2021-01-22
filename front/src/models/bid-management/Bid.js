export default class Bid {

	constructor(id, name, task, desiredDate = new Date()) {
		this.id = id;
		this.name = name;
		this.type = task.type;
		this.status = task.status;
		this.desiredDate = desiredDate;
	}

}