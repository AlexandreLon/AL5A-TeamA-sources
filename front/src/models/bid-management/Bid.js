export default class Bid {

	constructor(id, name, task, status, desiredDate) {
		this.id = id;
		this.name = name;
		this.type = task.type;
		this.status = status;
		this.desiredDate = desiredDate;
	}

	setStatus(status){
		this.status = status;
	}

}