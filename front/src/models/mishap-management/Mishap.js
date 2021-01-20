export default class Mishap {

	constructor(id, name, type, status, priority, desiredDate = new Date()) {
		this.id = id;
		this.name = name;
		this.type = type;
		this.status = status;
		this.priority = priority;
		this.desiredDate = desiredDate;
	}

}