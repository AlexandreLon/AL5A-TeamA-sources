import API from './API';
import Bid from "../models/bid-management/Bid";
import Task from "../models/supplier/Task";
import Supplier from "../models/supplier/Supplier";

class SupplierWSAPI extends API {
    
	outbid(offer, id) {
		return new Promise((resolve, reject) => {
			this.axios.post(`/supplier/${id}/outbid`, offer).then(res => {
				resolve(res);
			}).catch(error => {
				reject(error);
			});
		});
	}

	endTask(id) {
		return new Promise((resolve, reject) => {
			this.axios.put(`/supplier/${id}/endTask`).then(res => {
				if (res.data.id === undefined || res.data.name === undefined || res.data.type === undefined || res.data.status === undefined)
					reject(Error("Not received task"));
				resolve(new Task(res.data.id, res.data.name, res.data.type, res.data.status, res.data.priority, res.data.creationDate));
			}).catch(error => {
				reject(error);
			});
		});
	}

	getSuppliers() {
		return new Promise((resolve, reject) => {
			this.axios.get('/supplier').then(res => {
				resolve(res.data.map(supplier => new Supplier(supplier.id, supplier.name, supplier.taskType)));
			}).catch(error => {
				reject(error);
			});
		});
	}

	getBidsBySupplierId(id) {
		return new Promise((resolve, reject ) => {
			 this.axios.get(`/supplier/${id}/bids`).then(res => {
				resolve(res.data.map(bid => new Bid(bid.id, bid.name, bid.task, bid.status, bid.desiredDate)));
			}).catch(error => {
				reject(error);
			});
		});
	}

	getTasksBySupplierId(id) {
		return new Promise((resolve, reject) => {
			this.axios.get(`/supplier/${id}/tasks`).then(res => {
				resolve(res.data.map(task => new Task(task.id, task.name, task.type, task.status, task.priority, task.creationDate, task.realizationDate)));
			}).catch(error => {
				reject(error);
			});
		});
	}

	
}

export default SupplierWSAPI;