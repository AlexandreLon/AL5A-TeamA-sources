import Mishap from "./Mishap";

export default class MishapCreationModel extends Mishap {

	constructor(id, name, type, status, priority, desiredDate) {
		super(id, name, type, status, priority);
		this.desiredDate = desiredDate;
	}

}