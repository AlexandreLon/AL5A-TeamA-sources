export default class Bid {

	constructor(id, name, task, status, desiredDate = new Date()) {
		this.id = id;
		this.name = name;
		this.type = task.type;
		this.status = status;
		this.desiredDate = desiredDate;
		this.desiredDate = desiredDate;
	}

	setStatus(status){
		this.status = status;
	}

}