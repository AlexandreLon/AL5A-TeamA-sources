export default class  Maintenance {

	constructor(id, name, task) {
		this.id = id;
		this.name = name;
		this.type = task.type;
		this.status = task.status;
	}

}