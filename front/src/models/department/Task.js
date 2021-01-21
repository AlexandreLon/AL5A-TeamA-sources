export default class Task {

	constructor(id, name, type, status,priority,date, desiredDate) {
		this.id = id;
		this.name = name;
		this.type = type;
		this.status = status;
		this.priority = priority;
		this.creationDate = date;
		this.desiredDate = desiredDate;
	}
}