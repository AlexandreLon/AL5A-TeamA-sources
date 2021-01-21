import Mishap from '../models/mishap-management/Mishap';
import API from './API';

class MishapWSAPI extends API {
    
	createMishap(mishap) {
		const temp = { ...mishap, desiredDate: Date.now() };
		return new Promise((resolve, reject) => {
			this.axios.post('/mishap',temp).then(res => {
				if(res.data.name === undefined || res.data.type === undefined || res.data.status === undefined)
				    reject(Error("Did not receive mishap."));
				resolve(new Mishap(res.data.id, res.data.name, res.data.type, res.data.status, res.data.priority));
			}).catch(error => {
				reject(error);
			});
		});
	}

	getMishaps() {
		return new Promise((resolve, reject) => {
			this.axios.get('/mishap').then(res => {
				resolve(res.data.map(mishap => new Mishap(mishap.id, mishap.name, mishap.type, mishap.status, mishap.priority)));
			}).catch(error => {
				reject(error);
			});
		});
	}

	getMishap(id) {
		return new Promise((resolve, reject) => {
			this.axios.get(`/mishap/${id}`).then(res => {
				resolve(new Mishap(res.data.id, res.data.name, res.data.type, res.data.status, res.data.priority));
			}).catch(error => {
				reject(error);
			});
		});
	}

	updateMishap(id, mishap) {
		return new Promise((resolve, reject) => {
			this.axios.put(`/mishap/${id}`, mishap).then(res => {
				if (res.data.id === undefined || res.data.name === undefined || res.data.type === undefined || res.data.status === undefined || res.data.priority === undefined) {
					reject(Error("Server did not return the updated mishap."));
				}
				resolve(new Mishap(res.data.id, res.data.name, res.data.type, res.data.status, res.data.priority));
			}).catch(error => {
				reject(error);
			});
		});
	}

	deleteMishap(id) {
		return new Promise((resolve, reject) => {
			this.axios.delete(`/mishap/${id}`).then(() => {
				resolve(id);
			}).catch(error => {
				reject(error);
			});
		});
	}


}

export default MishapWSAPI;